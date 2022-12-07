package cn.edu.szu.Bajie;

import cn.edu.szu.Bajie.annotation.ExecuteTest;
import cn.edu.szu.Bajie.entity.Canteen;
import cn.edu.szu.Bajie.entity.CommentDish;
import cn.edu.szu.Bajie.entity.TestArg;
import cn.edu.szu.Bajie.functional.BannerTest;
import cn.edu.szu.Bajie.repository.CommentRepository;
import cn.edu.szu.Bajie.service.CanteenService;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.ReflectUtil;
import cn.hutool.core.util.StrUtil;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.util.MultiValueMapAdapter;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class BajieApplicationTests {

    @Autowired
    private MockMvc mockMvc;


    private static final String[] PACKAGE_NAMES = {"cn.edu.szu.Bajie.functional"};

    @Test
    void contextLoads() {

        Arrays.stream(PACKAGE_NAMES)
                .map(this::getClasses)
                .reduce(
                        new LinkedList<>(),
                        (x,y)->{
                            x.addAll(y);
                            return x;
                        }
                )
                .forEach(this::testClass);

    }

    /**
     * 获取包下所有的类的字节码
     * @param packageName
     * @return
     */

    List<Class<?>> getClasses(String packageName){
        // 获取类加载器
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        // 准备资源路径
        String path = packageName.replace(".", "/");

        Enumeration<URL> resources = null;

        try {
            // 获取路径资源
            resources = classLoader.getResources(path);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 获取所有的文件
        List<File> dirs = new LinkedList<>();
        while(true){
            assert resources != null;
            if (!resources.hasMoreElements()) break;
            URL url = resources.nextElement();
            dirs.add(new File(url.getFile()));
        }

        return dirs.stream()
                .map(file -> {
                    // 获取字节码文件
                    if(!file.exists()){
                        return new LinkedList<Class<?>>();
                    }
                    return findClasses(file,packageName);
                })
                // 将多个List合为一个List
                .reduce(
                        new LinkedList<>(),
                        (x,y)->{
                            x.addAll(y);
                            return x;
                        }
                );
    }

    List<Class<?>> findClasses(File file,String packageName){

        return Arrays.stream(Objects.requireNonNull(file.listFiles()))

                .map(fileItem->{
                    // 若文件是文件夹，则递归获取
                    if(fileItem.isDirectory()){
                        assert !fileItem.getName().contains(".");
                        return findClasses(fileItem,packageName+"."+fileItem.getName());
                    }

                    // 获取字节码
                    List<Class<?>> list =new LinkedList<>();
                    if(fileItem.getName().endsWith(".class")){
                        try {
                            Class<?> aClass = Class.forName(packageName + "." + fileItem.getName().substring(0, file.getName().length()));
                            list.add(aClass);
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        }
                    }
                    return list;
                })
                .reduce(
                        new LinkedList<>(),
                        (x,y)->{
                            x.addAll(y);
                            return x;
                        }
                );
    }

    /**
     * 测试当前类下所有的方法
     * @param aClass
     */

    void testClass(Class<?> aClass){
        // 创建实例对象
        Object instance = ReflectUtil.newInstance(aClass);

        Arrays.stream(
                ReflectUtil
                        // 获取类下所有的方法
                .getMethods(aClass))
                // 保留标有ExecuteTest注解的方法
                .filter(method ->
                        method.isAnnotationPresent(ExecuteTest.class)
                                &&method.getAnnotation(ExecuteTest.class).execute()
                )
                // 执行测试
                .forEach(method -> {
                    try {
                        doTest((TestArg) method.invoke(instance));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });

    }

    /**
     * 执行测试
     * @param arg
     * @throws Exception
     */

     void doTest(TestArg arg) throws Exception {
        // 准备模拟请求
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .request(arg.getRequestMethod(), arg.getUri());
        // 添加contenType
        if(StrUtil.isNotBlank(arg.getContentType())){
            request.contentType(arg.getContentType());
        }
        // 添加内容
        if(StrUtil.isNotBlank(arg.getContent())){
            request.content(arg.getContent());
        }
        // 添加参数
        if(ObjectUtil.isNotNull(arg.getParams())){
            request.params(
                    new MultiValueMapAdapter<>(
                            arg.getParams()
                                    .entrySet()
                                    .stream()
                                    .collect(Collectors.groupingBy(Map.Entry::getKey,Collectors.mapping(Map.Entry::getValue,Collectors.toList())))
                    )
            );
        }

        // 添加期望
        ResultActions perform = mockMvc.perform(request);

        for (ResultMatcher resultMatcher : arg.getResultMatcherList()) {
            perform.andExpect(resultMatcher);
        }

    }

}
