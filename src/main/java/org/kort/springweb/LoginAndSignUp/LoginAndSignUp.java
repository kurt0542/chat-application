package org.kort.springweb.LoginAndSignUp;

import javax.swing.*;
import java.awt.*;

import com.formdev.flatlaf.FlatLightLaf;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.client.RestTemplate;

public class LoginAndSignUp extends JFrame {
    private static final String API_URL = "http://localhost:8080/api/users";
    private JTextField username = new JTextField(15);
    private JPasswordField password = new JPasswordField(15);
    private JButton login = new JButton("Login");
    private JButton signup = new JButton("Signup");
    private JPanel panel = new JPanel(new GridBagLayout());

    public LoginAndSignUp() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(400, 250);
        this.setLocationRelativeTo(null);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;


        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(new JLabel("Username:"), gbc);
        gbc.gridx = 1;
        panel.add(username, gbc);


        gbc.gridx = 0; gbc.gridy = 1;
        panel.add(new JLabel("Password:"), gbc);
        gbc.gridx = 1;
        panel.add(password, gbc);


        gbc.gridx = 1; gbc.gridy = 2;
        panel.add(login, gbc);
        gbc.gridx = 0;
        panel.add(signup, gbc);

        this.add(panel);
        this.setVisible(true);

        login.addActionListener(e -> handleLogin());
        signup.addActionListener(e -> handleSignup());
    }

    public static void main(String[] args) {
        FlatLightLaf.setup();
        new LoginAndSignUp();
    }

    private void handleLogin() {
        String userInput = username.getText();
        String passwordInput = String.valueOf(password.getPassword());
        if(userInput.equals("") || passwordInput.equals("")) {
            JOptionPane.showMessageDialog(this, "Please enter a valid username/password");
        }

        RestTemplate restTemplate = new RestTemplate();
        String userApiUrl = API_URL + "/by-username/" + userInput;
        try {
            User user = restTemplate.getForObject(userApiUrl, User.class);
            if (user != null) {
                BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
                if(bCryptPasswordEncoder.matches(passwordInput, user.getPassword())) {
                    JOptionPane.showMessageDialog(this, "Login Successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
                }else{
                    JOptionPane.showMessageDialog(this, "Incorrect Username or Password!", "Failed", JOptionPane.ERROR_MESSAGE);
                }


            } else {
                JOptionPane.showMessageDialog(this, "Invalid username or password", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    private void handleSignup() {
        String userInput = username.getText();
        String passwordInput = String.valueOf(password.getPassword());
        if(userInput.equals("") || passwordInput.equals("")) {
            JOptionPane.showMessageDialog(this, "Please enter a valid username/password");
            return;
        }

        RestTemplate restTemplate = new RestTemplate();
        User newUser = new User();
        newUser.setUsername(userInput);
        newUser.setPassword(passwordInput);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<User> request = new HttpEntity<>(newUser, headers);
        try{
            restTemplate.exchange(API_URL, HttpMethod.POST, request, User.class);
            JOptionPane.showMessageDialog(this, "Sign Up Successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
        }catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
