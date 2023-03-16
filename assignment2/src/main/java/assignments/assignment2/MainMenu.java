package assignments.assignment2;
// Fikri Dhiya Ramadhana
// 2206819533
// TP02

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Scanner;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
import java.util.ArrayList;
import assignments.assignment1.NotaGenerator;

public class MainMenu {
    private static final Scanner input = new Scanner(System.in);
    private static SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
    private static Calendar cal = Calendar.getInstance();
    private static ArrayList<Nota> notaList = new ArrayList<Nota>();
    private static ArrayList<Member> memberList = new ArrayList<Member>();
    private static int banyakNota = 0;

    public static void main(String[] args) {
        boolean isRunning = true;
        while (isRunning) {
            printMenu();
            System.out.print("Pilihan : ");
            String command = input.nextLine();
            System.out.println("================================");
            switch (command){
                case "1" -> handleGenerateUser();
                case "2" -> handleGenerateNota();
                case "3" -> handleListNota();
                case "4" -> handleListUser();
                case "5" -> handleAmbilCucian();
                case "6" -> handleNextDay();
                case "0" -> isRunning = false;
                default -> System.out.println("Perintah tidak diketahui, silakan periksa kembali.");
            }
        }
        System.out.println("Terima kasih telah menggunakan NotaGenerator!");
    }

    private static void handleGenerateUser() {
        // TODO: handle generate user
        System.out.println("Masukkan nama Anda: ");                 
        String nama = input.nextLine();                                 //meminta input String
        while (true){
            if (isNotEmpty(nama)==true){
                break;
            } else {
                System.out.println("Masukkan nama Anda: ");                 
                nama = input.nextLine();
            }
        }

        System.out.println("Masukkan nomor handphone Anda: ");
        String nomorHP = input.nextLine();                              //meminta input String
        while (true){
            if (isNotEmpty(nomorHP)==true){
                break;
            } else {
                System.out.println("Masukkan nomor handphone Anda: ");                 
                nomorHP = input.nextLine();
            }
        }
        while (true) {                                                 
            if (!NotaGenerator.checkNum(nomorHP)){                      //mengecek String isDigit atau tidak
                System.out.println("Field nomor hp hanya menerima digit");                          
                nomorHP = input.nextLine();
            } else {
                break;
            }
        }
        Member newMember = new Member(nama, nomorHP);                   //membuat instance member baru
        if(memberList.isEmpty()){
            memberList.add(newMember);                                  
            System.out.println("Berhasil membuat member dengan " + "ID " + newMember.getId() +"!");            
           
        } else {
            if (memberExist(newMember)==true){
                System.out.println("Member dengan nama "+ newMember.getNama() + " dan nomor hp " + newMember.getNoHp() + " sudah ada!");        //mengecek apakah list member kosong atau member sudah terdaftar
            } else {
                memberList.add(newMember);
                System.out.println("Berhasil membuat member dengan " + "ID " + newMember.getId() +"!");
            }
        }
    }

    private static void handleGenerateNota() {
        // TODO: handle ambil cucian
        System.out.println("Masukan ID member:");
        String inputId = input.nextLine();                             //meminta input String
   
        if (cariMember(inputId)!=null){                                //mencari member dari inputId dengan method cariMember jika tidak sama dengan null maka member terdaftar
            System.out.println("Masukkan paket laundry: ");
            String paket = "";                                         
            int sisaHari = 0; 

            while (true){
                String paket1 = input.nextLine();
                if (paket1.toLowerCase().equals("express")){                      
                    sisaHari += 1;                                                          
                    paket = paket1;
                    break;
                } else if (paket1.toLowerCase().equals("fast")){
                    sisaHari += 2;                                                          
                    paket = paket1;
                    break;                                                                   //membuat input paket menjadi casse insensitive
                } else if (paket1.toLowerCase().equals("reguler")){                //menambahkan sisaHari sesuai dengan ketentuan tiap paket
                    sisaHari += 3;
                    paket = paket1;
                    break;
                } else if (paket1.toLowerCase().equals("?")){                    
                    NotaGenerator.showPaket();
                    System.out.println("Masukkan paket laundry: ");
                } else {
                    System.out.println("Paket " + paket1 + " tidak diketahui");
                    System.out.println("[ketik ? untuk mencari tahu jenis paket]");
                }
            }

            System.out.println("Masukkan berat cucian Anda [Kg]:");
            int berat = 0;
            while (true){
                String berat1 = input.nextLine();                       //meminta input berat cucian dalam bentuk String
                if (isNotEmpty(berat1)==false){
                    System.out.println("Harap masukkan berat cucian Anda dalam bentuk bilangan positif.");
                } else {
                    if (NotaGenerator.checkNum(berat1)){                               
                        berat = Integer.valueOf(berat1);                    //mengubah String menjadi integer    
                        if (berat <= 0){
                            System.out.println("Harap masukkan berat cucian Anda dalam bentuk bilangan positif.");
                            continue;
                        } else if (berat > 0 && berat < 2){                     
                            berat = 2;
                            System.out.println("Cucian kurang dari 2 kg, maka cucian akan dianggap sebagai 2 kg Nota Laundry");    
                        }
                        break;
                    } else {
                        System.out.println("Harap masukkan berat cucian Anda dalam bentuk bilangan positif.");
                    }
                }
            }
            Nota nota = new Nota(cariMember(inputId), paket, berat, fmt.format(cal.getTime()), MainMenu.getBanyakNota(), sisaHari, false);  //membuat instance Nota baru
            System.out.println("Berhasil menambahkan nota!");
            System.out.println("[ID Nota = " + nota.getIdNota() + "]");

            notaList.add(nota);
            MainMenu.addBanyakNota();       //menambah idx banyak nota untuk mencetak setiap idNota yang unik
            nota.getMember().addBonus();    //menambah bonusCounter ke dalam member
            nota.getNota();                 //mengeprint nota Cuci Cuci

        } else {
            System.out.println("Member dengan ID "+ inputId +" tidak ditemukan!");
        }
    }

    private static void handleListNota() {
        // TODO: handle list semua nota pada sistem
        int banyakNota = 0;
        for (int i = 0;i<notaList.size();i++){         //menghitung banyakNota dalam notaList
            banyakNota+=1;
        }
        if (banyakNota > 0){
            System.out.println("Terdaftar " + banyakNota + " nota dalam sistem.");
            for (int i = 0;i<notaList.size();i++){
                if (notaList.get(i).getStatus()==true)
                    System.out.println("- ["+ notaList.get(i).getIdNota() +"] Status      	: Sudah dapat diambil!");       
                else                                                                                                       //jika banyakNota > 0 akan menunjukkan status dari masing - masing nota
                    System.out.println("- ["+ notaList.get(i).getIdNota() +"] Status      	: Belum bisa diambil :(");      
            }
        } else {
            System.out.println("Terdaftar " + banyakNota + " nota dalam sistem.");
        }
    }

    private static void handleListUser() {
        // TODO: handle list semua user pada sistem
        int banyakMember = 0;
        for (int i = 0;i<memberList.size();i++){       //menghitung banyakMember dalam memberList
            banyakMember +=1;
        }
        if (banyakMember > 0){
            System.out.println("Terdaftar " + banyakMember + " member dalam sistem.");
            for (int i = 0;i<memberList.size();i++){
                System.out.println("- " + memberList.get(i).getId() + " : " + memberList.get(i).getNama());               //jika banyakMember > 0 akan menunjukkan member yang terdaftar 
            }
        } else {
            System.out.println("Terdaftar " + banyakMember + " member dalam sistem.");
        }
    }

    private static void handleAmbilCucian() {
        // TODO: handle ambil cucian
        System.out.println("Masukan ID nota yang akan diambil:");
        String inputIdNota = input.nextLine();
        while (true) {                                                 
            if (!NotaGenerator.checkNum(inputIdNota)){    
                System.out.println("ID nota berbentuk angka!");       //mengecek inputIdNota isDigit                       
                inputIdNota = input.nextLine();
            } else {
                break;
            }
        }
        int numIdNota = Integer.valueOf(inputIdNota);
        if (idNotaExist(numIdNota)==true){
            for (Nota element: notaList){
                if (element.getIdNota()==numIdNota&&element.getStatus()==true){      //mencari dan me-remove element dalam memberList yang status elementnya sudah bisa diambil (isReady = true)
                    notaList.remove(element);
                    System.out.println("Nota dengan ID " + numIdNota + " berhasil diambil!");
                    break;
                } else if (element.getIdNota()==numIdNota&&element.getStatus()==false){     //jika idNota ketemu dan status isReady = false maka akan gagal mengambil  
                    System.out.println("Nota dengan ID " + numIdNota + " gagal diambil!");
                }
            }
        } else {
            System.out.println("Nota dengan ID " + numIdNota + " tidak ditemukan!");                 //else untuk idNota yang tidak ditemukan
        }
        
    }

    private static void handleNextDay() {
        // TODO: handle ganti hari
        cal.add(Calendar.DATE, 1);                           //menambah satu hari pada calender
        System.out.println("Dek Depe tidur hari ini... zzz...");
        for (Nota element: notaList){
            element.setSisaHari(-1);
            if (element.getSisaHari()==0){                          //mengurangi sisaHari setiap element notaList jika sudah 0 maka akan diset status true
                element.setStatus(true);
            }
        }
        for (Nota element1: notaList){
            if (element1.getStatus()==true){                        //mengeprint element notaList yang sudah getStatusnya true
                System.out.println("Laundry dengan nota ID "+ element1.getIdNota() +" sudah dapat diambil!");
            }
        }
        System.out.println("Selamat pagi dunia!");
        System.out.println("Dek Depe: It's CuciCuci Time.");
    }

    public static boolean memberExist(Member member){               //boolean method untuk mengecek member exist di memberList
        boolean check = false;
        for(int i = 0; i<memberList.size();i++){
            if(memberList.get(i).getId().equals(member.getId())){
                check = true;
                break; 
            }
        }
        return check;
    }
    
    public static Member cariMember(String id){                     //Member method untuk mencari id member di memberList           
        for (Member element : memberList){
            if (element.getId().equals(id))
                return element;
        }
        return null;
    } 

    public static boolean idNotaExist(int x){                       //Boolean method untuk mencari IdNota ada atau tidak
        boolean check = false;
        for (int i = 0;i<notaList.size();i++){
            if (notaList.get(i).getIdNota()==x){
                check = true;
                break;
            }
        }
        return check;
    }

    public static boolean isNotEmpty(String x){                    //Boolean method untuk mengecek String kosong atau tidak
        boolean check = true;
        if (x.isEmpty()==true || x.length()==0)
            check = false;
        return check;
    }

    public static boolean checkTanggal(String date, LocalDate selesai, DateTimeFormatter formatTanggal){      //boolean method untuk mengecek status di generateNota
        boolean check = false;
        if ((MainMenu.getTime().toString().equals(selesai.format(formatTanggal).toString()))){
            check = true;
        }
        return check;
    }
    
    public static int getBanyakNota(){                      
        return banyakNota;
    }

    public static void addBanyakNota(){                    //method setter getter dari class MainMenu
        banyakNota += 1;
    }
  
    public static String getTime(){
        return fmt.format(cal.getTime()).toString();
    }

    private static void printMenu() {
        System.out.println("\nSelamat datang di CuciCuci!");
        System.out.printf("Sekarang Tanggal: %s\n", fmt.format(cal.getTime()));
        System.out.println("==============Menu==============");
        System.out.println("[1] Generate User");
        System.out.println("[2] Generate Nota");
        System.out.println("[3] List Nota");
        System.out.println("[4] List User");
        System.out.println("[5] Ambil Cucian");
        System.out.println("[6] Next Day");
        System.out.println("[0] Exit");
    }
}