package org.kort.springweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.kort.springweb.GUI.LoginAndSignUp;
import javax.swing.*;

@SpringBootApplication
public class SpringWebApplication {

    public static void main(String[] args) {
        System.setProperty("java.awt.headless", "false");
        SpringApplication.run(SpringWebApplication.class, args);
        SwingUtilities.invokeLater(() -> new LoginAndSignUp());

    }

}
