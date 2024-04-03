import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

class User {
    private String username;
    private String name;
    private String lastName;
    private String phoneNumber;
    private String email;
    private String password;

    public User(String username, String name, String lastName, String phoneNumber, String email, String password) {
        this.username = username;
        this.name = name;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}

public class UserLoginSystem extends JFrame {
    private List<User> users;

    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;

    public UserLoginSystem() {
        users = new ArrayList<>();

        // Nuevos usuarios
        users.add(new User("Enmanuel", "Enmanuel", "Brito Mejia", "123456789", "Denky062199@gmail.com", "Brito"));
        users.add(new User("Pamela", "Pamela", "Brito Mejia", "987654321", "Pamelabrito@example.com", "Mejia"));

        setTitle("Login");
        setSize(300, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel panel = new JPanel();
        getContentPane().add(panel);

        panel.setLayout(null);

        JLabel userLabel = new JLabel("Username:");
        userLabel.setBounds(10, 20, 80, 25);
        panel.add(userLabel);

        usernameField = new JTextField(20);
        usernameField.setBounds(100, 20, 165, 25);
        panel.add(usernameField);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(10, 50, 80, 25);
        panel.add(passwordLabel);

        passwordField = new JPasswordField(20);
        passwordField.setBounds(100, 50, 165, 25);
        panel.add(passwordField);

        loginButton = new JButton("Login");
        loginButton.setBounds(100, 90, 80, 25);
        panel.add(loginButton);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                login();
            }
        });
    }

    private void login() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Debe ingresar su usuario y contraseña.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        User foundUser = null;
        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                foundUser = user;
                break;
            }
        }

        if (foundUser != null) {
            JOptionPane.showMessageDialog(this, "Bienvenido, " + foundUser.getName() + " " + foundUser.getLastName() + "!");
            dispose();
            showUserList();
        } else {
            JOptionPane.showMessageDialog(this, "Usuario o contraseña incorrectos.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void showUserList() {
        JFrame userListFrame = new JFrame("Lista de Usuarios");
        userListFrame.setSize(400, 300);
        userListFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        userListFrame.setLocationRelativeTo(null);
        userListFrame.setResizable(false);

        JPanel panel = new JPanel();
        userListFrame.getContentPane().add(panel);

        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel title = new JLabel("Lista de Usuarios");
        title.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        panel.add(title);

        for (User user : users) {
            JLabel userInfoLabel = new JLabel("Nombre: " + user.getName() + " " + user.getLastName() +
                    ", Teléfono: " + user.getPhoneNumber() + ", Email: " + user.getEmail());
            userInfoLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
            panel.add(userInfoLabel);
        }

        JButton logoutButton = new JButton("Cerrar Sesión");
        logoutButton.setAlignmentX(JButton.CENTER_ALIGNMENT);
        panel.add(logoutButton);

        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                userListFrame.dispose();
                new UserLoginSystem().setVisible(true);
            }
        });

        userListFrame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new UserLoginSystem().setVisible(true);
            }
        });
    }
}
