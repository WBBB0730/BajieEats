package cn.edu.szu.Bajie.entity;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpMethod;
import org.springframework.test.web.servlet.ResultMatcher;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Data
@Builder(toBuilder = true)
public class TestArg implements Serializable {

    /**
     * 方法名
     */
    private HttpMethod requestMethod;

    /**
     * uri
     */
    private String uri;

    /**
     * 接口参数
     */
    private Map<String,String> params;

    /**
     * contentType
     */
    private String contentType;

    /**
     * requestBody数据
     */
    private String content;

    /**
     * 期望值
     */
    private List<ResultMatcher> resultMatcherList;

}
