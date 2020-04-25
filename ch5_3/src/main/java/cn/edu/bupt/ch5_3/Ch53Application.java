package cn.edu.bupt.ch5_3;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(value = "cn.edu.bupt.ch5_3.mapper")
public class Ch53Application {

    public static void main(String[] args) {
        SpringApplication.run(Ch53Application.class, args);
    }

}
