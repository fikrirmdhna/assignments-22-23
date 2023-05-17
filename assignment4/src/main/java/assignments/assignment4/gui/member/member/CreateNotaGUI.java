package assignments.assignment4.gui.member.member;

import assignments.assignment1.NotaGenerator;
import assignments.assignment3.nota.Nota;
import assignments.assignment3.nota.NotaManager;
import assignments.assignment3.nota.service.AntarService;
import assignments.assignment3.nota.service.CuciService;
import assignments.assignment3.nota.service.SetrikaService;
import assignments.assignment4.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;

// Fikri Dhiya Ramadhana
// 2206819533
// TP04

public class CreateNotaGUI extends JPanel {
    public static final String KEY = "CREATE_NOTA";
    private JLabel paketLabel;
    private JComboBox<String> paketComboBox;
    private JButton showPaketButton;
    private JLabel beratLabel;
    private JTextField beratTextField;
    private JCheckBox setrikaCheckBox;
    private JCheckBox antarCheckBox;
    private JButton createNotaButton;
    private JButton backButton;
    private final SimpleDateFormat fmt;
    private final Calendar cal;
    private final MemberSystemGUI memberSystemGUI;

    public CreateNotaGUI(MemberSystemGUI memberSystemGUI) {
        this.memberSystemGUI = memberSystemGUI;
        this.fmt = NotaManager.fmt;
        this.cal = NotaManager.cal;

        // Set up main panel, Feel free to make any changes
        initGUI();
    }

    /**
     * Method untuk menginisialisasi GUI.
     * Selama funsionalitas sesuai dengan soal, tidak apa apa tidak 100% sama.
     * Be creative and have fun!
     * */
    private void initGUI() {
        // TODO
        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel();
        JPanel panel3 = new JPanel();
        JPanel panel4 = new JPanel();

        panel1.setLayout(new GridLayout(1,2));
        panel2.setLayout(new GridLayout(1,2));
        panel3.setLayout(new GridLayout(1,2));
        panel4.setLayout(new GridLayout(1,2));

        paketLabel = new JLabel("  Paket Laundry:");
        paketComboBox = new JComboBox<String>(new String[]{"Express","Fast","Reguler"});
        showPaketButton = new JButton("Show Paket");
        beratLabel = new JLabel("  Berat Cucian (Kg):");
        beratTextField = new JTextField();
        setrikaCheckBox = new JCheckBox("Tambah Setrika Service (1000 / kg)");
        antarCheckBox = new JCheckBox("Tambah Antar Service (2000 / 4kg pertama, kemudian 500 / kg)");
        createNotaButton = new JButton("Buat Nota");
        backButton = new JButton("Back");

        panel3.add(paketComboBox);
        panel3.add(showPaketButton);

        panel4.add(beratTextField);
        panel4.add(new JLabel());

        panel1.add(paketLabel);
        panel1.add(panel3);

        panel2.add(beratLabel);
        panel2.add(panel4);

        setBorder(BorderFactory.createEmptyBorder(80,50,80,50));
        setLayout(new GridLayout(6,1,5,5));

        add(panel1);
        add(panel2);
        add(setrikaCheckBox);
        add(antarCheckBox);
        add(createNotaButton);
        add(backButton);
        
        showPaketButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                showPaket();
            }
        });

        createNotaButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                createNota();
            }
        });

        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                handleBack();
            }
        });
    }

    /**
     * Menampilkan list paket pada user.
     * Akan dipanggil jika pengguna menekan "showPaketButton"
     * */
    private void showPaket() {
        String paketInfo = """
                        <html><pre>
                        +-------------Paket-------------+
                        | Express | 1 Hari | 12000 / Kg |
                        | Fast    | 2 Hari | 10000 / Kg |
                        | Reguler | 3 Hari |  7000 / Kg |
                        +-------------------------------+
                        </pre></html>
                        """;

        JLabel label = new JLabel(paketInfo);
        label.setFont(new Font("monospaced", Font.PLAIN, 12));
        JOptionPane.showMessageDialog(this, label, "Paket Information", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Method untuk melakukan pengecekan input user dan mendaftarkan nota yang sudah valid pada sistem.
     * Akan dipanggil jika pengguna menekan "createNotaButton"
     * */
    private void createNota() {
        // TODO
        if(beratTextField.getText().length()!=0 && NotaGenerator.checkNum(beratTextField.getText())==true && Integer.valueOf(beratTextField.getText())>0){
            int berat = Integer.valueOf(beratTextField.getText());
    
            if(berat<2) {
                berat = 2; 
                JOptionPane.showMessageDialog(this, "Cucian kurang dari 2 kg, maka cucian akan dianggap sebagai 2 kg", "Info", JOptionPane.INFORMATION_MESSAGE);
            }

            Nota nota = new Nota(memberSystemGUI.getLoggedInMember(), berat, paketComboBox.getSelectedItem().toString(), fmt.format(cal.getTime()));
            nota.addService(new CuciService());
            memberSystemGUI.getLoggedInMember().addNota(nota);
            NotaManager.addNota(nota);

            if(setrikaCheckBox.isSelected()) nota.addService(new SetrikaService());
            if(antarCheckBox.isSelected()) nota.addService(new AntarService());

            JOptionPane.showMessageDialog(this, "Nota berhasil dibuat!", "Success", JOptionPane.INFORMATION_MESSAGE);

            paketComboBox.setSelectedItem("Express");
            beratTextField.setText("");
            setrikaCheckBox.setSelected(false);
            antarCheckBox.setSelected(false);
        } else JOptionPane.showMessageDialog(this, "Harap masukkan berat cucian Anda dalam bentuk bilangan positif.", "Nota Gagal Dibuat!", JOptionPane.ERROR_MESSAGE);
    }

    /**
     * Method untuk kembali ke halaman home.
     * Akan dipanggil jika pengguna menekan "backButton"
     * */
    private void handleBack() {
        // TODO
        paketComboBox.setSelectedItem("Express");
        beratTextField.setText("");
        setrikaCheckBox.setSelected(false);
        antarCheckBox.setSelected(false);
        MainFrame.getInstance().navigateTo(MemberSystemGUI.KEY);
    }
}
