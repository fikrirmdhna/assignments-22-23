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
        String nama = input.nextLine();
        System.out.println("Masukkan nomor handphone Anda: ");
        String nomorHP = input.nextLine();
      
        //Handle input
        while (true) {                                                 
            if (!NotaGenerator.checkNum(nomorHP)){    
                System.out.println("Field nomor hp hanya menerima digit");                          
                nomorHP = input.nextLine();
            }
            break;
        }
        Member newMember = new Member(nama, nomorHP);
        if(memberList.isEmpty()){
            memberList.add(newMember);
            System.out.println("Berhasil membuat member dengan " + "ID " + newMember.getId() +"!");
           
        } else {
            if (memberExist(newMember)==true){
                System.out.println("Member dengan nama "+ newMember.getNama() + " dan nomor hp " + newMember.getNoHp() + " sudah ada!");
            } else {
                memberList.add(newMember);
                System.out.println("Berhasil membuat member dengan " + "ID " + newMember.getId() +"!");
            }
        }
    }

    private static void handleGenerateNota() {
        // TODO: handle ambil cucian
        System.out.println("Masukan ID member:");
        String inputId = input.nextLine();
   
        if (cariMember(inputId)!=null){
            System.out.println("Masukkan paket laundry: ");
            String paket = ""; 
            int sisaHari = 0; 

            while (true){
                String paket1 = input.nextLine().toLowerCase();
                if (paket1.equals("express")){
                    sisaHari += 1;
                    paket = paket1;
                    break;
                } else if (paket1.equals("fast")){
                    sisaHari += 2;
                    paket = paket1;
                    break;
                } else if (paket1.equals("reguler")){
                    sisaHari += 3;
                    paket = paket1;
                    break;
                } else if (paket1.equals("?")){                    
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
                String berat1 = input.nextLine();
                if (NotaGenerator.checkNum(berat1)){                               
                    berat = Integer.valueOf(berat1);                        
                    if (berat < 0){
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
            Nota nota = new Nota(cariMember(inputId), paket, berat, fmt.format(cal.getTime()), MainMenu.getBanyakNota(), sisaHari, false);
            System.out.println("Berhasil menambahkan nota!");
            System.out.println("[ID Nota = " + nota.getIdNota() + "]");

            notaList.add(nota);
            MainMenu.addBanyakNota();
            nota.getMember().addBonus();
            nota.getNota();

        } else {
            System.out.println("Member dengan ID "+ inputId +" tidak ditemukan!");
        }
    }

    private static void handleListNota() {
        // TODO: handle list semua nota pada sistem
        int banyakNota = 0;
        for (int i = 0;i<notaList.size();i++){
            banyakNota+=1;
        }
        if (banyakNota > 0){
            System.out.println("Terdaftar " + banyakNota + " nota dalam sistem.");
            for (int i = 0;i<notaList.size();i++){
                if (notaList.get(i).getStatus()==true)
                    System.out.println("- ["+ notaList.get(i).getIdNota() +"] Status      	: Sudah dapat diambil!");
                else    
                    System.out.println("- ["+ notaList.get(i).getIdNota() +"] Status      	: Belum bisa diambil :(");
            }
        } else {
            System.out.println("Terdaftar " + banyakNota + " nota dalam sistem.");
        }
    }

    private static void handleListUser() {
        // TODO: handle list semua user pada sistem
        int banyakMember = 0;
        for (int i = 0;i<memberList.size();i++){
            banyakMember +=1;
        }
        if (banyakMember > 0){
            System.out.println("Terdaftar " + banyakMember + " member dalam sistem.");
            for (int i = 0;i<memberList.size();i++){
                System.out.println("- " + memberList.get(i).getId() + " : " + memberList.get(i).getNama());
            }
        } else {
            System.out.println("Terdaftar " + banyakMember + " member dalam sistem.");
        }
    }

    private static void handleAmbilCucian() {
        // TODO: handle ambil cucian
        System.out.println("Masukan ID nota yang akan diambil:");
        String inputId = input.nextLine();
        while (true) {                                                 
            if (!NotaGenerator.checkNum(inputId)){    
                System.out.println("ID nota berbentuk angka!");                          
                inputId = input.nextLine();
            }
            break;
        }
        for (Nota element: notaList){
            if (element.getIdNota()==Integer.valueOf(inputId)&&element.getStatus()==true){
                notaList.remove(element);
                System.out.println("Nota dengan ID " + inputId + " berhasil diambil!");
                break;
            } else if (element.getIdNota()==Integer.valueOf(inputId)&&element.getStatus()==false){
                System.out.println("Nota dengan ID " + inputId + " gagal diambil!");
            } else {
                System.out.println("Nota dengan ID " + inputId + " tidak ditemukan!");
            }
        }        
    }

    private static void handleNextDay() {
        // TODO: handle ganti hari
        cal.add(Calendar.DATE, 1);
        System.out.println("Dek Depe tidur hari ini... zzz...");
        for (Nota element: notaList){
            element.setSisaHari(-1);
            if (element.getSisaHari()==0){
                element.setStatus(true);
            }
        }
        for (Nota element1: notaList){
            if (element1.getStatus()==true){
                System.out.println("Laundry dengan nota ID "+ element1.getIdNota() +" sudah dapat diambil!");
            }
        }
        System.out.println("Selamat pagi dunia!");
        System.out.println("Dek Depe: It's CuciCuci Time.");
    }

    public static boolean memberExist(Member member){
        boolean check = false;
        for(int i = 0; i<memberList.size();i++){
            if(memberList.get(i).getId().equals(member.getId())){
                check = true;
                break; 
            }
        }
        return check;
    }
    
    public static Member cariMember(String id){
        for (Member element : memberList){
            if (element.getId().equals(id))
                return element;
        }
        return null;
    } 

    public static boolean checkTanggal(String date, LocalDate selesai, DateTimeFormatter formatTanggal){
        boolean check = false;
        if ((MainMenu.getTime().toString().equals(selesai.format(formatTanggal).toString()))){
            check = true;
        }
        return check;
    }
    
    public static int getBanyakNota(){
        return banyakNota;
    }

    public static void addBanyakNota(){
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