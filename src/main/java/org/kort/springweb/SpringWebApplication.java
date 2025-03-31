package org.kort.springweb;

import com.formdev.flatlaf.FlatLightLaf;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.kort.springweb.LoginAndSignUp.LoginAndSignUp;

import javax.swing.*;

@SpringBootApplication(scanBasePackages = "org.kort.springweb")

public class SpringWebApplication {

    public static void main(String[] args) {
        System.setProperty("java.awt.headless", "false");

        FlatLightLaf.setup();

        SpringApplication.run(SpringWebApplication.class, args);
        SwingUtilities.invokeLater(() -> new LoginAndSignUp());

    }

}
