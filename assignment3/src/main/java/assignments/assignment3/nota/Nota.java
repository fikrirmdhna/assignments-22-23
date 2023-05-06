package assignments.assignment3.nota;
import java.util.ArrayList;

import assignments.assignment1.NotaGenerator;
import assignments.assignment3.nota.service.LaundryService;
import assignments.assignment3.user.Member;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;

// Fikri Dhiya Ramadhana
// 2206819533
// TP03

public class Nota {
    private Member member;
    private String paket;
    private LaundryService[] services;
    private ArrayList<LaundryService> services1; 
    private long baseHarga;                         
    private int sisaHariPengerjaan;
    private int berat;
    private int id;
    private String tanggalMasuk;
    private boolean isDone;
    private int lateDayDone = 0;
    static public int totalNota;

    public Nota(Member member, int berat, String paket, String tanggal) {
        //TODO
        this.member = member;
        this.berat = berat;
        this.paket = paket;
        this.tanggalMasuk = tanggal;                                            //constructor yang diperlukan sesuai ketentuan soal
        this.isDone = false;
        this.id = totalNota++;
        this.baseHarga = NotaGenerator.myHitung(this.paket, this.berat);
        this.services = new LaundryService[0];
        this.services1 = new ArrayList<LaundryService>();

        if(this.paket.toLowerCase().equals("express")) this.sisaHariPengerjaan = 1;
        else if(this.paket.toLowerCase().equals("fast")) this.sisaHariPengerjaan = 2;
        else if (this.paket.toLowerCase().equals("reguler")) this.sisaHariPengerjaan = 3;
    }
    
    public void addService(LaundryService service){
        //TODO
        services1.add(service);                                                //menambahkan service yang dipilih oleh member ke dalam ArrayList services1
    }

    public String kerjakan(){
        // TODO
        String statusKerjakan = "";
        for(LaundryService element: services1){
            if(element.isDone()==false){
                if(element.getServiceName().equals("Cuci")){
                    statusKerjakan = element.doWork();
                    break;
                } else if (element.getServiceName().equals("Antar")){           //mengerjakan service - service satu per satu untuk setiap nota milik seluruh member 
                    statusKerjakan = element.doWork();
                    break;
                } else if (element.getServiceName().equals("Setrika")){
                    statusKerjakan = element.doWork();
                    break;
                }
            } else {
                this.isDone = true;
                statusKerjakan = "Sudah selesai.";                                       //status yang ditampilkan jika isDone true
            }
        }
        return statusKerjakan;
    }
    public void toNextDay() {
        // TODO
        if(this.isDone!=true) {
            this.sisaHariPengerjaan--;
            lateDayDone = sisaHariPengerjaan < 0 ? lateDayDone + 1 : 0;                 //jika status isDone masih false maka sisa hari akan dikurang 1 dan lateDayDone ditambah 1 jika sisaHariPengerjaan negative
        }
    }

    public long calculateHarga(){
        // TODO
        return baseHarga;                                                              //mereturn nilai baseHarga
    }

    public String getNotaStatus(){
        // TODO
        String statusNota = "";
        if(this.isDone == true) statusNota = "Sudah selesai.";
        else statusNota = "Belum selesai.";                                            //menampilkan status nota saat selesai dan belum selesai
        return statusNota;
    }

    @Override
    public String toString(){
        // TODO
        DateTimeFormatter formatTanggal = DateTimeFormatter.ofPattern("dd/MM/yyyy");        //menggunakan java.util dan java.time untuk mengformat tanggal
        LocalDate tanggalSelesai = null;                                                            //membuat localdate kosong agar dapat diisi dengan kondisi if else
        String harga = "";                  //String untuk bagian harga
        String services = "";               //String untuk bagian ngeprint service yang dipilih
        String kompensasi = "";             //String kompensasi apabila laundry telat dikerjakan oleh employee
        long hargaAkhir = baseHarga;        //Long total harga akhir

        if (paket.toLowerCase().equals("express")){
            harga = berat + " kg x 12000 = " + calculateHarga();
            tanggalSelesai = LocalDate.parse(this.tanggalMasuk, formatTanggal).plusDays(1);  //paket express tanggal ditambah 1 hari dengan menggunakan plusDay
        } else if (paket.toLowerCase().equals("fast")){
            harga = berat + " kg x 10000 = " + calculateHarga();
            tanggalSelesai = LocalDate.parse(this.tanggalMasuk, formatTanggal).plusDays(2);  //paket fast tanggal ditambah 2 hari dengan menggunakan plusDay
        }else if (paket.toLowerCase().equals("reguler")){
            harga = berat + " kg x 7000 = " + calculateHarga();
            tanggalSelesai = LocalDate.parse(this.tanggalMasuk, formatTanggal).plusDays(3);  //paket reguler tanggal ditambah 3 hari dengan menggunakan plusDay
        }

        for(LaundryService element: services1){
            if(element.getServiceName().equals("Cuci")) services += "-Cuci @ Rp." + element.getHarga(this.berat);
            else if (element.getServiceName().equals("Setrika")) {
                services += "-Setrika @ Rp." + element.getHarga(this.berat);
                hargaAkhir += element.getHarga(berat);                               //mengeprint services yang dipilih dan mengupdate jumlah harga akhir sesuai services yang dipilih
            } else if (element.getServiceName().equals("Antar")) {
                services += "-Antar @ Rp." + element.getHarga(this.berat);
                hargaAkhir += element.getHarga(berat);
            }
            services += "\n";
        }

        if(lateDayDone>0) {
            hargaAkhir -= lateDayDone*2000;                                                    //ketentuan kompensasi saat lateDayDone bernilai > 0 dan harga tidak boleh negatif
            if(hargaAkhir < 0) hargaAkhir = 0;
            kompensasi += " Ada kompensasi keterlambatan " + lateDayDone + " * 2000 hari";      
        }

        return "[ID Nota = " + id + "]" +
        "\n" + "ID    : " + member.getId()+ 
        "\n" + "Paket : " + paket+
        "\n" + "Harga :\n" + harga + 
        "\n" + "Tanggal Terima  : " + this.tanggalMasuk +                                      //mengeprint nota
        "\n" + "Tanggal Selesai : " + tanggalSelesai.format(formatTanggal)+
        "\n" + "--- SERVICE LIST ---" +
        "\n" + services +
        "Harga Akhir: " + hargaAkhir + kompensasi +"\n";
    }

    // Dibawah ini adalah getter
    public void setStatusNota(){
        for(LaundryService element: services1){
            if(element.isDone()==false) this.isDone = false;                         //method untuk mengeset status nota agar langsung terupdate statusnya setelah employee sudah selesai mengerjakan service
            else this.isDone = true;
        }
    }
    //method - method getter
    public String getPaket() {
        return paket;
    }
    public int getBerat() {
        return berat;
    }
    public int getId() {
        return id;
    }
    public String getTanggal() {
        return this.tanggalMasuk;
    }
    public int getSisaHariPengerjaan(){
        return sisaHariPengerjaan;
    }
    public boolean isDone() {
        return isDone;
    }
    public LaundryService[] getServices(){
        services = new LaundryService[services1.size()];
        services1.toArray(services);                                               //meng-convert ArrayList services1 menjadi array primitive services agar return sesuai dengan template
        return services;
    }
}