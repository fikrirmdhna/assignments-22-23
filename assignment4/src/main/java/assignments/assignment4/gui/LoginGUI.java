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
    private JCheckBox showPasswordCheckBox;
    private LoginManager loginManager;

    public LoginGUI(LoginManager loginManager) {
        super(new BorderLayout()); // Setup layout, Feel free to make any changes
        this.loginManager = loginManager;

        // Set up main panel, Feel free to make any changes
        mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(70, 70, 70, 70)); //membuat batas border

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
        idLabel = new JLabel("  Masukkan ID Anda: ");
        idTextField = new JTextField();
        passwordLabel = new JLabel("  Masukkan password Anda: ");           //membuat properti yang dibutuhkan LoginGUI
        passwordField = new JPasswordField();                                    //dan menambahkan fitur Show Password
        loginButton = new JButton("Login");
        backButton = new JButton("Back");
        showPasswordCheckBox = new JCheckBox("Show Password");

        mainPanel.setLayout(new GridLayout(4,2,5,5));

        mainPanel.add(idLabel);
        mainPanel.add(idTextField);
        mainPanel.add(passwordLabel);                                            //mengeset letak properti dalam gridlayout 4 x 2 
        mainPanel.add(passwordField);
        mainPanel.add(new JLabel());
        mainPanel.add(showPasswordCheckBox);
        mainPanel.add(loginButton);
        mainPanel.add(backButton);


        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                handleBack();
            }
        });
                                                                                
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){                         //mengatur event action listener untuk setiap button
                handleLogin();
            }
        });

        showPasswordCheckBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                passwordField.setEchoChar(showPasswordCheckBox.isSelected()?'\u0000':'\u2022');
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
        showPasswordCheckBox.setSelected(false);                                          //me-reset semua field text dan kembali ke HomeGUI
        passwordField.setEchoChar('\u2022');
        MainFrame.getInstance().navigateTo(HomeGUI.KEY);
    }

    /**
     * Method untuk login pada sistem.
     * Akan dipanggil jika pengguna menekan "loginButton"
     * */
    /**
     * Mengecek apakah semua field sudah terisi atau belum, jika terisi semua akan memanggil method getSystem dari TP03 untuk mengecek apakah id terdaftar atau tidak
     * jika terdaftar idnya maka akan dicek passwordnya oleh method authUser apakah sesuai atau tidak, jika sesuai maka method akan mereturn user jika tidak maka akan mereturn null
     * */
    private void handleLogin() {
        if(idTextField.getText().isEmpty() || passwordField.getPassword().length==0){
            JOptionPane.showMessageDialog(this, "Semua field di atas wajib diisi!", "Login Gagal!", JOptionPane.ERROR_MESSAGE);
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
            showPasswordCheckBox.setSelected(false);  
            passwordField.setEchoChar('\u2022');
        }
    }
}