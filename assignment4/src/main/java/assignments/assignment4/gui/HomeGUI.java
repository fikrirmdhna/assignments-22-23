package assignments.assignment4.gui;

import assignments.assignment3.nota.NotaManager;
import assignments.assignment4.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static assignments.assignment3.nota.NotaManager.toNextDay;

// Fikri Dhiya Ramadhana
// 2206819533
// TP04

public class HomeGUI extends JPanel {
    public static final String KEY = "HOME";
    private JLabel titleLabel;
    private JLabel dateLabel;
    private JPanel mainPanel;
    private JButton loginButton;
    private JButton registerButton;
    private JButton toNextDayButton;

    public HomeGUI(){
        super(new BorderLayout()); // Setup layout, Feel free to make any changes

        // Set up main panel, Feel free to make any changes
        mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); //membuat batas border

        initGUI();

        add(mainPanel, BorderLayout.CENTER);
    }

    /**
     * Method untuk menginisialisasi GUI.
     * Selama funsionalitas sesuai dengan soal, tidak apa apa tidak 100% sama.
     * Be creative and have fun!
     * */
    private void initGUI() {
        mainPanel.setLayout(new GridLayout(5,1,5,5));  //menggunakan gridlayout 5 x 1
        
        titleLabel = new JLabel("Selamat datang di CuciCuci System!", SwingConstants.CENTER);       //membuat label title
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));

        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel();            //menggunakan JPanel untuk setiap button agar bisa di set size buttonnya 
        JPanel panel3 = new JPanel();

        loginButton = new JButton("Login");
        loginButton.setPreferredSize(new Dimension(120,40));

        registerButton = new JButton("Register");
        registerButton.setPreferredSize(new Dimension(120,40));         //membuat button - button yang diperlukan di HomeGUI

        toNextDayButton = new JButton("Next Day");
        toNextDayButton.setPreferredSize(new Dimension(120,40));

        dateLabel = new JLabel("Hari ini: " + NotaManager.fmt.format(NotaManager.cal.getTime()), SwingConstants.CENTER);    //membuat label tanggal
        dateLabel.setFont(new Font("Arial", Font.BOLD, 16));

        panel1.add(loginButton);
        panel2.add(registerButton);         //memasukkan setiap button ke dalam panel yang berbeda
        panel3.add(toNextDayButton);
        
        mainPanel.add(titleLabel);
        mainPanel.add(panel1);  
        mainPanel.add(panel2);             //memasukkan properti - properti ke dalam mainPanel dengan gridlayout 5 x 1
        mainPanel.add(panel3);
        mainPanel.add(dateLabel);

        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                handleToLogin();
            }
        });

        registerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){                 //mengatur event action listener setiap button
                handleToRegister();
            }
        });

        toNextDayButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                handleNextDay();
            }
        });
    }

    /**
     * Method untuk pergi ke halaman register.
     * Akan dipanggil jika pengguna menekan "registerButton"
     * */
    private static void handleToRegister() {
        MainFrame.getInstance().navigateTo(RegisterGUI.KEY);
    }

    /**
     * Method untuk pergi ke halaman login.
     * Akan dipanggil jika pengguna menekan "loginButton"
     * */
    private static void handleToLogin() {
        MainFrame.getInstance().navigateTo(LoginGUI.KEY);
    }

    /**
     * Method untuk skip hari.
     * Akan dipanggil jika pengguna menekan "toNextDayButton"
     * */
    private void handleNextDay() {
        toNextDay();        //memanggil method nextday dari TP03 dan mengeset label tanggal agar berubah tanggalnya
        dateLabel.setText("Hari ini: " + NotaManager.fmt.format(NotaManager.cal.getTime()));
        JOptionPane.showMessageDialog(this, "Kamu tidur hari ini... zzz...", "To Next Day :)" , JOptionPane.INFORMATION_MESSAGE);
    }
}
