package cn.edu.szu.Bajie;



import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author hjc
 * @date 20220921
 */

@SpringBootApplication
@ServletComponentScan
@EnableScheduling //开启定时任务
@EnableAsync //开启异步调用
@EnableFeignClients// 远程调用
@EnableConfigurationProperties
@EnableCaching // springCache
public class BajieApplication {

    public static void main(String[] args) {
        SpringApplication.run(BajieApplication.class, args);

    }

}
