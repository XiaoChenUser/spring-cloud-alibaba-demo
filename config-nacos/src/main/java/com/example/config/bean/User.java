package com.example.config.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.io.Serializable;
import java.util.List;

@Data
@Configuration
@JsonIgnoreProperties(value = {"CGLIB$BOUND","CGLIB$CALLBACK_0","CGLIB$CALLBACK_1","CGLIB$CALLBACK_2","$$beanFactory"})
@ConfigurationProperties(prefix = "user")
public class User implements Serializable {
    private static final long serialVersionUID = 6722303225512077394L;

    private String name;
    private Integer age;
    private String address;
    private List<String> sports;
    private School school;
}
