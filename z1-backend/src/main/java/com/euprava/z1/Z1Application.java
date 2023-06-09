package com.euprava.z1;

import com.euprava.z1.service.Z1Service;
import com.euprava.z1.service.implementation.Z1ServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.xml.bind.JAXBException;
import java.io.IOException;

@SpringBootApplication
public class Z1Application {

    public static void main(String[] args) {
        SpringApplication.run(Z1Application.class, args);
    }

}
