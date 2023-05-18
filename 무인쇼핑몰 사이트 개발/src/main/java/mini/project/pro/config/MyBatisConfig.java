package mini.project.pro.config;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan(basePackages = "mini.project.pro.mapper")
public class MyBatisConfig {
    
}
