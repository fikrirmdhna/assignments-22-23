package assignments.assignment4.gui.member.member;

import assignments.assignment3.nota.Nota;
import assignments.assignment3.user.Member;
import assignments.assignment3.user.menu.SystemCLI;
import assignments.assignment4.MainFrame;
import assignments.assignment4.gui.member.AbstractMemberGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

// Fikri Dhiya Ramadhana
// 2206819533
// TP04

public class MemberSystemGUI extends AbstractMemberGUI {
    public static final String KEY = "MEMBER";              //subclass dari AbstractMemberGUI, jika panel.getPageName() mereturn key member maka panel untuk member yang akan dimunculkan

    public MemberSystemGUI(SystemCLI systemCLI) {
        super(systemCLI);
    }

    @Override
    public String getPageName(){
        return KEY;
    }

    public Member getLoggedInMember(){
        return loggedInMember;
    }

    /**
     * Method ini mensupply buttons yang sesuai dengan requirements MemberSystem.
     * Button yang disediakan method ini BELUM memiliki ActionListener.
     *
     * @return Array of JButton, berisi button yang sudah stylize namun belum ada ActionListener.
     * */
    @Override
    protected JButton[] createButtons() {
        // TODO
        return new JButton[]{
            new JButton("Saya ingin laundry"),                  //membuat button sesuai dengan index action listener agar sesuai dengan event yang diinginkan
            new JButton("Lihat detail nota saya")
        };
    }

    /**
     * Method ini mensupply ActionListener korespondensi dengan button yang dibuat createButtons()
     * sesuai dengan requirements MemberSystem.
     *
     * @return Array of ActionListener.
     * */
    @Override
    protected ActionListener[] createActionListeners() {
        return new ActionListener[]{
                e -> createNota(),                                  //urutan actionlistener untuk setiap button (Template)
                e -> showDetailNota(),
        };
    }

    /**
     * Menampilkan detail Nota milik loggedInMember.
     * Akan dipanggil jika pengguna menekan button pertama pada createButtons
     * */
    private void showDetailNota() {
        // TODO
        TextArea textArea = new TextArea();
        textArea.setPreferredSize(new Dimension(450,300));      //menggunakan textArea untuk menyimpan detail Nota member yang login
        textArea.setEditable(false);

        if(getLoggedInMember().getNotaList().length!=0){
            String text = "";
            int i = 0;
            for(Nota element: getLoggedInMember().getNotaList()){             //mengecek apakah member memiliki nota dan mengeprint nota jika punya
                text += element.toString();
                if(i < getLoggedInMember().getNotaList().length-1){
                    text += "\n";
                    i++;
                }
            }
            textArea.setText(text);
            JOptionPane.showMessageDialog(this, textArea ,"Detail Nota", JOptionPane.INFORMATION_MESSAGE);
        } else {
            textArea.setText("Kamu belum pernah Laundry di CuciCuci, hiks ;(");     //pesan ini akan ditunjukkan jika member belum mempunyai nota
            JOptionPane.showMessageDialog(this, textArea ,"Detail Nota", JOptionPane.INFORMATION_MESSAGE);  
        }
    }

    /**
     * Pergi ke halaman CreateNotaGUI.
     * Akan dipanggil jika pengguna menekan button kedua pada createButtons
     * */
    private void createNota() {
        // TODO
        MainFrame.getInstance().navigateTo(CreateNotaGUI.KEY);
    }

}
