package assignments.assignment4.gui;

import assignments.assignment1.NotaGenerator;
import assignments.assignment3.LoginManager;
import assignments.assignment3.user.Member;
import assignments.assignment4.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Fikri Dhiya Ramadhana
// 2206819533
// TP04

public class RegisterGUI extends JPanel {
    public static final String KEY = "REGISTER";
    private JPanel mainPanel;
    private JLabel nameLabel;
    private JTextField nameTextField;
    private JLabel phoneLabel;
    private JTextField phoneTextField;
    private JLabel passwordLabel;
    private JPasswordField passwordField;
    private JButton registerButton;
    private LoginManager loginManager;
    private JButton backButton;

    public RegisterGUI(LoginManager loginManager) {
        super(new BorderLayout()); // Setup layout, Feel free to make any changes
        this.loginManager = loginManager;

        // Set up main panel, Feel free to make any changes
        mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));

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
        nameLabel = new JLabel("  Nama: ");
        nameTextField = new JTextField();
        phoneLabel = new JLabel("  No. HP: ");
        phoneTextField = new JTextField();
        passwordLabel = new JLabel("  Password: ");
        passwordField = new JPasswordField();
        registerButton = new JButton("Register");
        backButton = new JButton("Back");

        mainPanel.setLayout(new GridLayout(4,2,5,5));
        mainPanel.add(nameLabel);
        mainPanel.add(nameTextField);
        mainPanel.add(phoneLabel);
        mainPanel.add(phoneTextField);
        mainPanel.add(passwordLabel);
        mainPanel.add(passwordField);
        mainPanel.add(registerButton);
        mainPanel.add(backButton);

        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                handleBack();
            }
        });

        registerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                handleRegister();
            }
        });
    }

    /**
     * Method untuk kembali ke halaman home.
     * Akan dipanggil jika pengguna menekan "backButton"
     * */
    private void handleBack() {
        nameTextField.setText("");
        phoneTextField.setText("");
        passwordField.setText("");
        MainFrame.getInstance().navigateTo(HomeGUI.KEY);
    }

    /**
    * Method untuk mendaftarkan member pada sistem.
    * Akan dipanggil jika pengguna menekan "registerButton"
    * */
    private void handleRegister() {
        // TODO
        if(nameTextField.getText().isEmpty() || phoneTextField.getText().isEmpty() || passwordField.getPassword().length==0){
            JOptionPane.showMessageDialog(this, "Semua field di atas wajib diisi!", "Registrasi Gagal!", JOptionPane.ERROR_MESSAGE);
        } else {
            if(NotaGenerator.checkNum(phoneTextField.getText())==true){
                try{
                    Member member = loginManager.register(nameTextField.getText(), phoneTextField.getText(), new String(passwordField.getPassword()));
                    JOptionPane.showMessageDialog(this, "Berhasil membuat user dengan ID " + member.getId() + "!", "Registrasi Berhasil!" , JOptionPane.INFORMATION_MESSAGE);
                } catch (NullPointerException e){
                    JOptionPane.showMessageDialog(this, "User dengan nama "+ nameTextField.getText() + " dan nomor hp " + phoneTextField.getText() + " sudah ada!", "Registrasi Gagal!", JOptionPane.ERROR_MESSAGE);
                } finally {
                    nameTextField.setText("");
                    phoneTextField.setText("");
                    passwordField.setText("");
                    MainFrame.getInstance().navigateTo(HomeGUI.KEY);
                }
            } else JOptionPane.showMessageDialog(this, "Nomor hp hanya menerima digit!", "Registrasi Gagal!", JOptionPane.ERROR_MESSAGE);
        }
    }
}