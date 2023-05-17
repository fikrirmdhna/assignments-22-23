package assignments.assignment4.gui;

import assignments.assignment3.LoginManager;
import assignments.assignment4.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Fikri Dhiya Ramadhana
// 2206819533
// TP04

public class LoginGUI extends JPanel {
    public static final String KEY = "LOGIN";
    private JPanel mainPanel;
    private JLabel idLabel;
    private JTextField idTextField;
    private JLabel passwordLabel;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton backButton;
    private LoginManager loginManager;

    public LoginGUI(LoginManager loginManager) {
        super(new BorderLayout()); // Setup layout, Feel free to make any changes
        this.loginManager = loginManager;

        // Set up main panel, Feel free to make any changes
        mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        initGUI();

        add(mainPanel, BorderLayout.CENTER);
    }

    /**
     * Method untuk menginisialisasi GUI.
     * Selama funsionalitas sesuai dengan soal, tidak apa apa tidak 100% sama.
     * Be creative and have fun!
     * */
    private void initGUI() {
        // TODO
        idLabel = new JLabel("Masukkan ID Anda: ");
        idTextField = new JTextField();
        passwordLabel = new JLabel("Masukkan password Anda: ");
        passwordField = new JPasswordField();
        loginButton = new JButton("Login");
        backButton = new JButton("Back");

        mainPanel.setLayout(new GridLayout(3,2));
        mainPanel.add(idLabel);
        mainPanel.add(idTextField);
        mainPanel.add(passwordLabel);
        mainPanel.add(passwordField);
        mainPanel.add(loginButton);
        mainPanel.add(backButton);

        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                handleBack();
            }
        });

        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                handleLogin();
            }
        });
    }

    /**
     * Method untuk kembali ke halaman home.
     * Akan dipanggil jika pengguna menekan "backButton"
     * */
    private void handleBack() {
        idTextField.setText("");
        passwordField.setText("");
        MainFrame.getInstance().navigateTo(HomeGUI.KEY);
    }

    /**
     * Method untuk login pada sistem.
     * Akan dipanggil jika pengguna menekan "loginButton"
     * */
    private void handleLogin() {
        if(idTextField.getText().isEmpty() || passwordField.getPassword().length==0){
            JOptionPane.showMessageDialog(this, "Semua field harus diisi", "Login Gagal!", JOptionPane.ERROR_MESSAGE);
        } else {
            if (loginManager.getSystem(idTextField.getText())!= null){
                if (loginManager.getSystem(idTextField.getText()).authUser(idTextField.getText(), new String(passwordField.getPassword())) != null) {
                    MainFrame.getInstance().login(idTextField.getText(), new String(passwordField.getPassword()));
                } else {
                    JOptionPane.showMessageDialog(this, "Id atau Password yang Anda masukkan salah", "Login Gagal!", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Id atau Password yang Anda masukkan salah", "Login Gagal!", JOptionPane.ERROR_MESSAGE);
            }
            idTextField.setText("");
            passwordField.setText("");
        }
    }
}