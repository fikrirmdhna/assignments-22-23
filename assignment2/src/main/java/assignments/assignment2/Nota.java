package assignments.assignment2;
// Fikri Dhiya Ramadhana
// 2206819533
// TP02

import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
import assignments.assignment1.NotaGenerator;

public class Nota {
    // TODO: tambahkan attributes yang diperlukan untuk class ini
    private Member member;
    private String paket;
    private int berat;
    private String tanggalMasuk;            //attributes yang diperlukan dalam class Nota
    private int idNota;
    private int sisaHariPengerjaan;
    private boolean isReady; 

    public Nota(Member member, String paket, int berat, String tanggalMasuk, int idNota, int sisaHariPengerjaan, boolean isReady) {
        // TODO: buat constructor untuk class ini
        this.member = member;
        this.paket = paket;
        this.berat = berat;
        this.tanggalMasuk = tanggalMasuk;                   //membuat constructors class Nota
        this.idNota = idNota;                               
        this.sisaHariPengerjaan = sisaHariPengerjaan;
        this.isReady = isReady;
    }

    // TODO: tambahkan methods yang diperlukan untuk class ini
    // Setter Getter yang diperlukan dari class Nota
    public Member getMember(){
        return member;
    }
    
    public void getNota(){
        System.out.println(generateNota(member.getId(), paket, berat, tanggalMasuk, member.getBonusCounter()));
    }

    public String getTanggal(){
        return tanggalMasuk;
    }
    
    public int getIdNota(){
        return idNota;
    }

    public int getSisaHari(){
        return sisaHariPengerjaan;
    }

    public boolean getStatus(){
        return isReady;
    }

    public void addIdNota(){
        this.idNota += 1;
    }

    public void setIdNota(int num){
        this.idNota = num;
    }

    public void setSisaHari(int sisa){
        this.sisaHariPengerjaan += sisa;
    }

    public void setStatus(boolean ready){
        this.isReady = ready;
    }

    //Method generateNota dari TP01 yang diubah parameternya dan ditambah dengan ketentuan bonusCounter
    public static String generateNota(String id, String paket, int berat, String tanggalTerima, int bonusCounter){
        DateTimeFormatter formatTanggal = DateTimeFormatter.ofPattern("dd/MM/yyyy");        //menggunakan java.util dan java.time untuk mengformat tanggal
        LocalDate tanggalSelesai = null;                                                            //membuat localdate kosong agar dapat diisi dengan kondisi if else
        String harga = "";
        String status = "";
        if (bonusCounter % 3 == 0 && bonusCounter!=0){
            if (paket.toLowerCase().equals("express")){
                harga = berat + " kg x 12000 = " + NotaGenerator.myHitung(paket, berat) + " = " + (NotaGenerator.myHitung(paket, berat)/2) + " (Discount member 50%!!!)";
                tanggalSelesai = LocalDate.parse(tanggalTerima, formatTanggal).plusDays(1);  //paket express dengan diskon dari bonusCounter
            } else if (paket.toLowerCase().equals("fast")){
                harga = berat + " kg x 10000 = " + NotaGenerator.myHitung(paket, berat) + " = " + (NotaGenerator.myHitung(paket, berat)/2) + " (Discount member 50%!!!)";
                tanggalSelesai = LocalDate.parse(tanggalTerima, formatTanggal).plusDays(2);  //paket fast dengan diskon dari bonusCounter
            }else if (paket.toLowerCase().equals("reguler")){
                harga = berat + " kg x 7000 = " + NotaGenerator.myHitung(paket, berat) + " = " + (NotaGenerator.myHitung(paket, berat)/2) + " (Discount member 50%!!!)";
                tanggalSelesai = LocalDate.parse(tanggalTerima, formatTanggal).plusDays(3);  //paket reguler dengan diskon dari bonusCounter
            }
        } else {
            if (paket.toLowerCase().equals("express")){
                harga = berat + " kg x 12000 = " + NotaGenerator.myHitung(paket, berat);
                tanggalSelesai = LocalDate.parse(tanggalTerima, formatTanggal).plusDays(1);  //paket express tanggal ditambah 1 hari dengan menggunakan plusDay
            } else if (paket.toLowerCase().equals("fast")){
                harga = berat + " kg x 10000 = " + NotaGenerator.myHitung(paket, berat);
                tanggalSelesai = LocalDate.parse(tanggalTerima, formatTanggal).plusDays(2);  //paket fast tanggal ditambah 2 hari dengan menggunakan plusDay
            }else if (paket.toLowerCase().equals("reguler")){
                harga = berat + " kg x 7000 = " + NotaGenerator.myHitung(paket, berat);
                tanggalSelesai = LocalDate.parse(tanggalTerima, formatTanggal).plusDays(3);  //paket reguler tanggal ditambah 3 hari dengan menggunakan plusDay
            }
        }
        if (MainMenu.checkTanggal(MainMenu.getTime(), tanggalSelesai, formatTanggal)==false){          //mengecek status tanggal
            status = "Status      	: Belum bisa diambil :(";
        } else {
            status = "Status      	: Sudah dapat diambil!";
        }
        return "ID    : " + id+ "\n" + "Paket : " + paket+"\n" + "Harga :\n" + harga + "\n" + "Tanggal Terima  : " + tanggalTerima +"\n" + "Tanggal Selesai : " + tanggalSelesai.format(formatTanggal)+"\n" + status;
    }
}