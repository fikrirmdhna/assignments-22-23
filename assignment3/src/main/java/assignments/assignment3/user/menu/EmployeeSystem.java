package assignments.assignment3.user.menu;

import assignments.assignment3.nota.Nota;
import assignments.assignment3.user.Employee;

import static assignments.assignment3.nota.NotaManager.notaList1;
// Fikri Dhiya Ramadhana
// 2206819533
// TP03

public class EmployeeSystem extends SystemCLI {

    /**
     * Membuat object baru EmployeeSystem dan mendaftarkan Employee pada CuciCuci
     */
    public EmployeeSystem() {
        memberList.add(new Employee("Dek Depe", "akuDDP"));
        memberList.add(new Employee("Depram", "musiktualembut"));               //mengubah template menjadi add untuk ArrayList
        memberList.add(new Employee("Lita Duo", "gitCommitPush"));
        memberList.add(new Employee("Ivan Hoshimachi", "SuamiSahSuisei"));
    }

    /**
     * Memproses pilihan dari employee yang masuk ke sistem ini sesuai dengan menu specific.
     *
     * @param choice -> pilihan pengguna.
     * @return true jika user log.
     */
    @Override
    protected boolean processChoice(int choice) {
        boolean logout = false;
        // TODO:
        if(choice==1) doNyuci(); 
        else if (choice==2) showNota();                 //memproses pilihan employee
        else if (choice==3) return logout = true;
        return logout;
    }

    /**
     * Displays specific menu untuk Employee.
     */
    @Override
    protected void displaySpecificMenu() {
        System.out.println("1. It's nyuci time");
        System.out.println("2. Display List Nota");
        System.out.println("3. Logout");
    }
    
    public void doNyuci(){
        System.out.println("Stand back! " + loginMember.getNama() + " beginning to nyuci!");
        if(notaList1.size()>0){
            for(Nota element: notaList1){                                                       
                System.out.println("Nota " + element.getId() + " : " + element.kerjakan());         //employee mengerjakan semua service yang dipilih oleh masing masing member
                element.setStatusNota();                                                            //dan langsung menge-set status isDone
            }
        } else {
            System.out.println("Terdaftar " + notaList1.size() + " nota yang perlu dikerjakan.");
        }
    }

    public void showNota(){
        if(notaList1.size()>0){
            for(Nota element: notaList1){
                System.out.println("Nota " + element.getId() + " : " + element.getNotaStatus());     //mengeprint seluruh nota status milik semua member jika ada
            }
        } else {
            System.out.println("Terdaftar " + notaList1.size() + " nota dalam sistem.");
        }
    }
}