package com.example.demo.bean;

import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * @author 94801
 */
@Data
@Component
public class User {
    private int id;
    private String userName;
    private String password;
    private String email;
    private long telephone;
}
