package cn.edu.szu.Bajie.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.baomidou.mybatisplus.core.MybatisXMLLanguageDriver;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.OptimisticLockerInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.JdbcType;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.*;

/**
 * @description: TODO
 * @author hjc
 * @date 2022/11/7 22:00
 * @version 1.0
 */
@Configuration
@MapperScan(basePackages = MyBatisConfig.PACKAGE, sqlSessionFactoryRef = "sqlSessionFactory")
public class MyBatisConfig {

    static final String PACKAGE = "cn.edu.szu.Bajie.mapper";

    static final String MAPPER_LOCATION = "classpath*:mapper/*.xml";

    @Value("${spring.datasource.url}")
    private String url;

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;

    @Value("${spring.datasource.driver-class-name}")
    private String driverClassName;

    @Value("${mybatis-plus.log:false}")
    private boolean log;


    /**
     * 数据源
     * @return
     */
    @Bean("dataSource")
    public DataSource dataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setDriverClassName(driverClassName);
        List<String> connectionInitSqls = new ArrayList<>();
        connectionInitSqls.add("set names utf8mb4;");
        dataSource.setConnectionInitSqls(connectionInitSqls);
        return dataSource;
    }

    /**
     * 事务管理器
     * @param dataSource
     * @return
     */

    @Bean(name = "transactionManager")
    public DataSourceTransactionManager transactionManager(@Autowired DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    /**
     *
     * @param dataSource
     * @return
     * @throws Exception
     */

    @Bean(name = "sqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(@Autowired DataSource dataSource)
            throws Exception {
        MybatisSqlSessionFactoryBean factoryBean = new MybatisSqlSessionFactoryBean();
        factoryBean.setDataSource(dataSource);
        MybatisConfiguration configuration = new MybatisConfiguration();
        configuration.setDefaultScriptingLanguage(MybatisXMLLanguageDriver.class);
        configuration.setJdbcTypeForNull(JdbcType.NULL);
        if (log) {
            configuration.setLogImpl(org.apache.ibatis.logging.stdout.StdOutImpl.class);
        }
        factoryBean.setConfiguration(configuration);
        //指定xml路径.
        factoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(MyBatisConfig.MAPPER_LOCATION));
//        factoryBean.setPlugins(new PaginationInterceptor(),
//                               new OptimisticLockerInterceptor());
        //加载插件
        factoryBean.setPlugins(midMybatisPlusInterceptor());
        return factoryBean.getObject();
    }

    /**
     * 拦截器
     * @return
     */

    @Bean(name = "mybatisPlusInterceptor")
    public MybatisPlusInterceptor midMybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        // 乐观锁插件
        interceptor.addInnerInterceptor(new OptimisticLockerInnerInterceptor());
        // DbType：数据库类型(根据类型获取应使用的分页方言)
        // 分页插件
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));

        return interceptor;
    }



}

