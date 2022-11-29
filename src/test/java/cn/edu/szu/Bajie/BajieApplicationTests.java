package cn.edu.szu.Bajie;

import cn.edu.szu.Bajie.entity.Canteen;
import cn.edu.szu.Bajie.entity.TestArg;
import cn.edu.szu.Bajie.functional.BannerTest;
import cn.edu.szu.Bajie.service.CanteenService;
import cn.hutool.core.util.ObjectUtil;
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

import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Collectors;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class BajieApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CanteenService canteenService;
    @Test
    void contextLoads() {

    }

    @Test
    void testBanner()throws Exception{
        doTest(BannerTest::getBannerListTest);
    }




    void doTest(Supplier<TestArg> supplier) throws Exception {
        TestArg arg = supplier.get();

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .request(arg.getRequestMethod(), arg.getUri());

        if(StrUtil.isNotBlank(arg.getContentType())){
            request.contentType(arg.getContentType());
        }

        if(StrUtil.isNotBlank(arg.getContent())){
            request.content(arg.getContent());
        }

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

        ResultActions perform = mockMvc.perform(request);

        for (ResultMatcher resultMatcher : arg.getResultMatcherList()) {
            perform.andExpect(resultMatcher);
        }

    }

}
