package assignments.assignment4.gui.member.employee;

import assignments.assignment3.nota.Nota;
import assignments.assignment3.nota.NotaManager;

import assignments.assignment3.user.menu.SystemCLI;
import assignments.assignment4.gui.member.AbstractMemberGUI;

import javax.swing.*;
import java.awt.event.ActionListener;

// Fikri Dhiya Ramadhana
// 2206819533
// TP04

public class EmployeeSystemGUI extends AbstractMemberGUI {
    public static final String KEY = "EMPLOYEE";            //subclass dari AbstractMemberGUI, jika panel.getPageName() mereturn key employee maka panel untuk employee yang akan dimunculkan

    public EmployeeSystemGUI(SystemCLI systemCLI) {
        super(systemCLI);
    }


    @Override
    public String getPageName(){
        return KEY;
    }

    /**
     * Method ini mensupply buttons yang sesuai dengan requirements Employee.
     * Button yang disediakan method ini BELUM memiliki ActionListener.
     *
     * @return Array of JButton, berisi button yang sudah stylize namun belum ada ActionListener.
     * */
    @Override
    protected JButton[] createButtons() {
        // TODO
        return new JButton[]{
            new JButton("It's nyuci time"),             //membuat button sesuai dengan index action listener agar sesuai dengan event yang diinginkan
            new JButton("Display List Nota")
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
                e -> cuci(),                                 //urutan actionlistener untuk setiap button (Template)
                e -> displayNota(),
        };
    }

    /**
     * Menampilkan semua Nota yang ada pada sistem.
     * Akan dipanggil jika pengguna menekan button pertama pada createButtons
     * */
    private void displayNota() {
        // TODO
        if(NotaManager.getNotaList().length!=0){
            String text = "";
            for(Nota element: NotaManager.getNotaList()){                                   //menampilkan status setiap nota jika ada
                text += "Nota " + element.getId() + " : " + element.getNotaStatus() +"\n";
            }
            JOptionPane.showMessageDialog(this, text ,"List Nota", JOptionPane.INFORMATION_MESSAGE);
        } else JOptionPane.showMessageDialog(this, "Terdaftar " + NotaManager.getNotaList().length + " nota dalam sistem." ,"Display Nota", JOptionPane.INFORMATION_MESSAGE);
    } 

    /**
     * Menampilkan dan melakukan action mencuci.
     * Akan dipanggil jika pengguna menekan button kedua pada createButtons
     * */
    private void cuci() {
        // TODO
        if(NotaManager.notaList1.size()>0){
            String text = "";
            for(Nota element: NotaManager.notaList1){                                                       
                text += "Nota " + element.getId() + " : " + element.kerjakan()+"\n";       //employee mengerjakan semua nota laundry dari semua member yang ada di NotaManager jika ada 
                element.setStatusNota();                                                            
            }
            JOptionPane.showMessageDialog(this, "Stand back! " + loggedInMember.getNama() + " beginning to nyuci!" ,"Nyuci Time", JOptionPane.INFORMATION_MESSAGE);
            JOptionPane.showMessageDialog(this, text ,"Nyuci Results", JOptionPane.INFORMATION_MESSAGE);
        } else JOptionPane.showMessageDialog(this, "Terdaftar " + NotaManager.notaList1.size() + " nota yang perlu dikerjakan." ,"Display Nota", JOptionPane.INFORMATION_MESSAGE);
    }
}
