package assignments.assignment1;
// Fikri Dhiya Ramadhana
// 2206819533
// TP01

import java.util.Scanner;
import java.time.*;
import java.time.format.DateTimeFormatter;

public class NotaGenerator {
    private static final Scanner input = new Scanner(System.in);

    /**
     * Method main, program utama kalian berjalan disini.
     */
    public static void main(String[] args) {
        boolean run = true;     //indikator on/off do while
        do {
            printMenu();
            System.out.print("Pilihan : ");
            String choice = input.nextLine();

            if (choice.equals("1")){                                 //if condition untuk membuat generateId
                // generate ID
                System.out.println("================================");
                System.out.println("Masukkan nama Anda: ");                 //meminta input String
                String nama = input.nextLine();

                System.out.println("Masukkan nomor handphone Anda: ");
                while (true) {                                                 //menggunakan while loop agar bisa berulang sampai input benar
                    String nomorHP = input.nextLine();
                    if (checkNum(nomorHP)){                              //memanggil method checkNum untuk mendeteksi apa ada no.handphone selain angka
                        System.out.println("ID Anda : " + generateId(nama, nomorHP));
                        break; 
                    } else {
                        System.out.println("Nomor hp hanya menerima digit");
                    }
            }

            } else if (choice.equals("2")) {                          //else if untuk generateNota
                System.out.println("================================");
                System.out.println("Masukkan nama Anda: ");
                String nama = input.nextLine();                                      //meminta input String

                System.out.println("Masukkan nomor handphone Anda: ");
                String id = "";
                while (true) {                                                  //menggunakan while loop agar bisa berulang sampai input benar
                    String nomorHP = input.nextLine();                    
                    if (checkNum(nomorHP)){                               //memanggil method checkNum untuk mendeteksi apa ada no.handphone selain angka
                        id = generateId(nama, nomorHP); 
                        break; 
                    } else {
                        System.out.println("Nomor hp hanya menerima digit");
                    }
                }

                System.out.println("Masukkan tanggal terima:");
                String inputTanggal = input.nextLine();                          //meminta input tanggal dengan format (dd/MM/yyyy) dijamin valid
                
                System.out.println("Masukkan paket laundry: ");
                String paket = "";                                               //membuat varible string kosong untuk menyimpan input paket1 setelah divalidasi
                while (true){
                    String paket1 = input.nextLine().toLowerCase();
                    if (paket1.equals("express") || paket1.equals("fast") || paket1.equals("reguler")){
                        paket = paket1;
                        break;
                    } else if (paket1.equals("?")){                    //menggunakan equals untuk menyamakan input dengan yg ditentukan
                        showPaket();
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
                    if (checkNum(berat1)){                                //menvalidasi input dengan checkNum karena agar tidak ada huruf dan input berat pertama merupakan string
                        berat = Integer.valueOf(berat1);                        //mengubah String menjadi int value
                        if (berat < 0){
                            System.out.println("Harap masukkan berat cucian Anda dalam bentuk bilangan positif.");
                            continue;
                        } else if (berat > 0 && berat < 2){                     //pengecualian untuk berat yg kurang dari 2 kg akan dijadikan 2 kg sebagai berat minimal
                            berat = 2;
                            System.out.println("Cucian kurang dari 2 kg, maka cucian akan dianggap sebagai 2 kg Nota Laundry");    
                        }
                        break;
                    } else {
                        System.out.println("Harap masukkan berat cucian Anda dalam bentuk bilangan positif.");
                    }
                }
                System.out.println("Nota Laundry");
                System.out.println(generateNota(id, paket, berat, inputTanggal)); //generateId untuk nota laundry

            } else if (choice.equals("0")){                            //saat pilihan: 0 maka program akan mengeprint string terima kasih dan run jadi false agar do while berhenti
                System.out.println("================================");
                System.out.println("Terima kasih telah menggunakan NotaGenerator!");
                run = false;

            } else {                                                            //mengatasi untuk perintah yang tidak diketahui
                System.out.println("================================");
                System.out.println("Perintah tidak diketahui, silakan periksa kembali.");
            }
        } while (run);
    }

    /**
     * Method untuk menampilkan menu di NotaGenerator.
     */
    public static void printMenu() {
        System.out.println("Selamat datang di NotaGenerator!");
        System.out.println("==============Menu==============");
        System.out.println("[1] Generate ID");
        System.out.println("[2] Generate Nota");
        System.out.println("[0] Exit");
    }

    /**
     * Method untuk menampilkan paket.
     */
    public static void showPaket() {
        System.out.println("+-------------Paket-------------+");
        System.out.println("| Express | 1 Hari | 12000 / Kg |");
        System.out.println("| Fast    | 2 Hari | 10000 / Kg |");
        System.out.println("| Reguler | 3 Hari |  7000 / Kg |");
        System.out.println("+-------------------------------+");
    }

    /**
     * Method untuk membuat ID dari nama dan nomor handphone.
     * Parameter dan return type dari method ini tidak boleh diganti agar tidak mengganggu testing
     *
     * @return String ID anggota dengan format [NAMADEPAN]-[nomorHP]-[2digitcheckSum]
     */
    public static String generateId(String nama, String nomorHP){
        int sumChar = 0;
        int sumNomor = 0;                                                      //membuat tiga int variable untuk menyimpan hasil
        int checkSum = 0;
        String [] namaSplitted = nama.split(" ");                       //menggunakan method split dan menyimpan kata setiap nama dalam array

        String namaDepan = namaSplitted[0].toUpperCase();                     //mengubah kata pertama nama menjadi uppercase semua agar memiliki range nilai unicode huruf kapital

        for (int k = 0; k < nomorHP.length();k++){
            for (int l = 48; l < 58; l++){
                if (nomorHP.charAt(k)==l){                                   //nested for loop untuk menjumlahkan nilai unicode angka 0 - 9 ke dalam sumNomer 
                    sumNomor += (l - 48);                                    // dan dikurang dengan nilai unicode 0 yaitu 48 agar mendapat value sesuai digit angka
                }
            }
        }
        for (int i = 0; i < namaDepan.length();i++){
            if (namaDepan.charAt(i) > 64 && namaDepan.charAt(i) < 91){
                for (int j = 65; j < 91; j++){                                    //nested for loop untuk menjumlahkan nilai unicode huruf A - Z ke dalam sumChar
                    if (namaDepan.charAt(i)==j){                                  // dan dikurang dengan dengan 64 agar mendapat value sesuai kententuan A = 1, B = 2, C = 3, ... dst
                        sumChar += (j - 64);
                    }
                }
            } else if (namaDepan.charAt(i) > 47 && namaDepan.charAt(i) < 58){
                for (int j = 48; j < 58; j++){
                    if (namaDepan.charAt(i)==j){                                   //nested for loop untuk menjumlahkan nilai unicode angka 0 - 9 ke dalam sumChar 
                        sumChar += (j - 48);                                       //dan dikurang dengan nilai unicode 0 yaitu 48 agar mendapat value sesuai digit angka
                    }
                }
            } else {
                sumChar += 7;
            }
                
        }
        checkSum = sumChar + sumNomor + 7;                                   //checkSum merupakan jumlah dari sumNomor dan sumChar
        String checkSumFormatted = Integer.toString(checkSum);               //mengubah int checkSum menjadi bentuk String agar dapat mereturn String

        if (checkSum < 10){
            return namaDepan + "-" + nomorHP+ "-" + "0" + checkSumFormatted; //jika checkSum < 10 maka akan mendapat angka 0 di sebelum angka checksum
        } else if (checkSum >= 10 && checkSum <= 99){
            return namaDepan + "-" + nomorHP + "-" + checkSumFormatted;
        } else {                                                             //kondisi dimana checkSum >= 100 sesuai ketentuan checkSum hanya ada 2 digit maka yang diambil 2 digit terakhir saja
            return namaDepan + "-" + nomorHP + "-" + checkSumFormatted.substring(checkSumFormatted.length()-2, checkSumFormatted.length());
        }
    }

    /**
     *
     * Method untuk membuat Nota.
     * Parameter dan return type dari method ini tidak boleh diganti agar tidak mengganggu testing.
     *
     * @return string nota dengan format di bawah:
     *         <p>ID    : [id]
     *         <p>Paket : [paket]
     *         <p>Harga :
     *         <p>[berat] kg x [hargaPaketPerKg] = [totalHarga]
     *         <p>Tanggal Terima  : [tanggalTerima]
     *         <p>Tanggal Selesai : [tanggalTerima + LamaHariPaket]
     */
    public static String generateNota(String id, String paket, int berat, String tanggalTerima){
        DateTimeFormatter formatTanggal = DateTimeFormatter.ofPattern("dd/MM/yyyy");        //menggunakan java.util dan java.time untuk mengformat tanggal
        LocalDate tanggalSelesai = null;                                                            //membuat localdate kosong agar dapat diisi dengan kondisi if else
        String harga = "";
        if (paket.equals("express")){
            harga = berat + " kg x 12000 = " + myHitung(paket, berat);
            tanggalSelesai = LocalDate.parse(tanggalTerima, formatTanggal).plusDays(1);  //paket express tanggal ditambah 1 hari dengan menggunakan plusDay
        } else if (paket.equals("fast")){
            harga = berat + " kg x 10000 = " + myHitung(paket, berat);
            tanggalSelesai = LocalDate.parse(tanggalTerima, formatTanggal).plusDays(2);  //paket fast tanggal ditambah 2 hari dengan menggunakan plusDay
        }else if (paket.equals("reguler")){
            harga = berat + " kg x 7000 = " + myHitung(paket, berat);
            tanggalSelesai = LocalDate.parse(tanggalTerima, formatTanggal).plusDays(3);  //paket reguler tanggal ditambah 3 hari dengan menggunakan plusDay
        }
        return "ID    : " + id+ "\n" + "Paket : " + paket+"\n" + "Harga :\n" + harga + "\n" + "Tanggal Terima  : " + tanggalTerima +"\n" + "Tanggal Selesai : " + tanggalSelesai.format(formatTanggal);
    }   //mereturn String sesuai dengan format yang ditentukan di method Nota

    /**
     * Method untuk mengecheck no.handphone apakah terdapat huruf atau tidak dan mereturn boolean
     */
    public static Boolean checkNum(String num){
        Boolean check = true;
        for (int i  = 0;i<num.length();i++){
            if (Character.isDigit(num.charAt(i))){
                check = true;
            } else {
                check = false;
                break;
            }
        }
        return check;
    }

    /**
     * Method untuk menghitung totak harga dari setiap jenis paket.
     */
    public static long myHitung(String paket, int berat){
        long totalHarga = 0;
        if (paket.toLowerCase().equals("express")){
            totalHarga = (long)berat * 12000;
        } else if (paket.toLowerCase().equals("fast")){
            totalHarga = (long)berat * 10000;
        } else if (paket.toLowerCase().equals("reguler")){
            totalHarga = (long)berat * 7000;
        }
        return totalHarga;
    }
}