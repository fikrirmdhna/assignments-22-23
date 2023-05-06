package assignments.assignment3.nota;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

// Fikri Dhiya Ramadhana
// 2206819533
// TP03

public class NotaManager {
    public static SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
    public static Calendar cal = Calendar.getInstance();
    public static ArrayList<Nota> notaList1 = new ArrayList<Nota>();
    public static Nota[] notaList;

    /**
     * Skips ke hari berikutnya dan update semua entri nota yang sesuai.
     */
    public static void toNextDay(){
        //TODO: implement skip hari
        cal.add(Calendar.DATE, 1);
        for(Nota element: notaList1){                    //mengurangi sisaHariPengerjaan setiap nota
            element.toNextDay();
        }
    }

    /**
     * Menambahkan nota baru ke NotaList.
     *
     * @param nota Nota object untuk ditambahkan.
     */
    public static void addNota(Nota nota){
        //TODO: implement add nota
        notaList1.add(nota);                            //menambahkan nota ke ArrayList notaList1 untuk NotaManager
    }

    public static Nota[] getNotaList(){
        notaList = new Nota[notaList1.size()];         //meng-convert ArrayList notaList1 menjadi array primitive notaList agar return tidak berubah dari template
        notaList1.toArray(notaList);
        return notaList;
    }
}