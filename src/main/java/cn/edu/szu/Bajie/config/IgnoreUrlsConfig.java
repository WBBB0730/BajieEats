package cn.edu.szu.Bajie.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import java.util.List;

/**
 * @description: TODO
 * @author hjc
 * @date 2022/11/17 16:11
 * @version 1.0
 */
@Data
@Component
@ConfigurationProperties(prefix="secure")
public class IgnoreUrlsConfig {
    private List<String> urls;
}
