package webdriver;

import javax.swing.*;
import java.awt.*;

public class Main {

    static JFrame frame = new JFrame("Ingrese sus datos");
    static JPanel panel = new JPanel();

    public static void main(String[] args) {

        frame.setSize(300, 150);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        panel.setBackground(Color.lightGray);
          frame.add(panel);
        inicio(panel);
        frame.setVisible(true);

    }

    private static void inicio(JPanel panel) {
        panel.removeAll();
        panel.setLayout(null);
        JLabel options = new JLabel("Elija la opcion deseada");
        options.setBounds(10, 10, 200, 25);
        panel.add(options);


        JButton unfollow = new JButton("Dejar de seguir");
        unfollow.setBounds(10, 40, 200, 25);
        panel.add(unfollow);
        unfollow.addActionListener(n -> {
            ingresarDatos(panel);
        });

        JButton removeFollowers = new JButton("Eliminar seguidores");
        removeFollowers.setBounds(10, 70, 200, 25);
        panel.add(removeFollowers);

        JButton closeWindow = new JButton("Salir");
        closeWindow.setBounds(10, 100, 200, 25);
        panel.add(closeWindow);
        closeWindow.addActionListener(l -> {
            System.exit(0 );
        });

        panel.updateUI();
    }

    private static void ingresarDatos(JPanel panel) {

        panel.removeAll();
        panel.setLayout(null);

        JLabel userLabel = new JLabel("Username");
        userLabel.setBounds(10, 10, 80, 25);
        panel.add(userLabel);

        JTextField usernameText = new JTextField(30);
        usernameText.setBounds(100, 10, 160, 25);
        panel.add(usernameText);

        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(10, 40, 80, 25);
        panel.add(passwordLabel);

        JTextField passwordText = new JPasswordField(30);
        passwordText.setBounds(100, 40, 160, 25);
        panel.add(passwordText);

        JButton confirm = new JButton("Confirm");
        confirm.setBounds(10, 80, 150, 25);
        panel.add(confirm);
        confirm.addActionListener(l -> {
            if (usernameText.getText().isEmpty() || usernameText.getText().isBlank() || passwordText.getText().isBlank() || passwordText.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Debe ingresar nombre de usuario y contraseña");
            }
            String cleanUsername = usernameText.getText().trim();
            Unfollow unfollow = new Unfollow();
            unfollow.login(cleanUsername, passwordText.getText().trim());
            unfollow.goToProfile();
            unfollow.openFollowing(cleanUsername);
            unfollow.getNumberOfFollowing();
            unfollow.unfollow();
        });

        JButton cancelButton = new JButton("Cancel");
        cancelButton.setBounds(180, 80, 150, 25);
        panel.add(cancelButton);
        cancelButton.addActionListener(actionEvent -> {
            Main.inicio(panel);
        });
        panel.updateUI();
    }
}
