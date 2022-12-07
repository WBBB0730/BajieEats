package cn.edu.szu.Bajie.functional;

import cn.edu.szu.Bajie.annotation.ExecuteTest;
import cn.edu.szu.Bajie.entity.TestArg;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpMethod;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.result.ContentResultMatchers;
import org.springframework.test.web.servlet.result.HeaderResultMatchers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.result.StatusResultMatchers;

import java.util.LinkedList;
import java.util.List;
public class BannerTest {


    /**
     * 测试获取轮播图
     * @return
     */

    @ExecuteTest(execute = true)
    public static TestArg getBannerListTest(){

        List<ResultMatcher> resultMatcherList =new LinkedList<>();

        // 相应状态码
        StatusResultMatchers status = MockMvcResultMatchers.status();
        // 200
        resultMatcherList.add(status.isOk());

        // 响应头
        HeaderResultMatchers header = MockMvcResultMatchers.header();
        ResultMatcher contentType = header.string("Content-Type", "application/json");
        resultMatcherList.add(contentType);

        // 响应体
        ContentResultMatchers content = MockMvcResultMatchers.content();

        ResultMatcher result = content.json("{\n" +
                "    \"code\": 200,\n" +
                "    \"message\": \"操作成功\",\n" +
                "    \"data\": [\n" +
                "        {\n" +
                "            \"bannerId\": 1,\n" +
                "            \"bannerUrl\": \"https://img1.baidu.com/it/u=96950203,2709338131&fm=253&fmt=auto&app=138&f=JPEG?w=624&h=376\",\n" +
                "            \"redirectUrl\": \"https://www.baidu.com/\",\n" +
                "            \"weight\": 10,\n" +
                "            \"sort\": 1,\n" +
                "            \"expireTime\": \"2023-03-31T03:51:26.000+00:00\",\n" +
                "            \"createTime\": \"2022-11-13\",\n" +
                "            \"updateTime\": \"2022-11-13\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"bannerId\": 2,\n" +
                "            \"bannerUrl\": \"https://img0.baidu.com/it/u=3572583651,3412385609&fm=253&fmt=auto&app=138&f=JPEG?w=750&h=500\",\n" +
                "            \"redirectUrl\": \"https://www.baidu.com/\",\n" +
                "            \"weight\": 9,\n" +
                "            \"sort\": 2,\n" +
                "            \"expireTime\": \"2023-04-02T03:52:19.000+00:00\",\n" +
                "            \"createTime\": \"2022-11-13\",\n" +
                "            \"updateTime\": \"2022-11-13\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"bannerId\": 3,\n" +
                "            \"bannerUrl\": \"https://img1.baidu.com/it/u=1352581799,791408713&fm=253&fmt=auto&app=138&f=JPEG?w=742&h=500\",\n" +
                "            \"redirectUrl\": \"https://www.baidu.com/\",\n" +
                "            \"weight\": 8,\n" +
                "            \"sort\": 3,\n" +
                "            \"expireTime\": \"2023-05-07T03:53:09.000+00:00\",\n" +
                "            \"createTime\": \"2022-11-13\",\n" +
                "            \"updateTime\": \"2022-11-13\"\n" +
                "        }\n" +
                "    ]\n" +
                "}");

        resultMatcherList.add(result);

        return TestArg.builder()
                .requestMethod(HttpMethod.GET)
                .uri("/banner/list")
                .resultMatcherList(resultMatcherList)
                .build();
    }


}
