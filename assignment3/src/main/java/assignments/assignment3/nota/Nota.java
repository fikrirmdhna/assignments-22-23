package assignments.assignment3.nota;
import java.util.ArrayList;

import assignments.assignment1.NotaGenerator;
import assignments.assignment3.nota.service.LaundryService;
import assignments.assignment3.user.Member;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
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
        this.tanggalMasuk = tanggal;
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
        services1.add(service);
    }

    public String kerjakan(){
        // TODO
        String statusKerjakan = "";
        for(LaundryService element: services1){
            if(element.isDone()==false){
                if(element.getServiceName().equals("Cuci")){
                    statusKerjakan = element.doWork();
                    break;
                } else if (element.getServiceName().equals("Antar")){
                    statusKerjakan = element.doWork();
                    break;
                } else if (element.getServiceName().equals("Setrika")){
                    statusKerjakan = element.doWork();
                    break;
                }
            } else {
                this.isDone = true;
                statusKerjakan = "Sudah selesai.";
            }
        }
        return statusKerjakan;
    }
    public void toNextDay() {
        // TODO
        if(this.isDone!=true) {
            this.sisaHariPengerjaan--;
            lateDayDone = sisaHariPengerjaan < 0 ? lateDayDone + 1 : 0;
        }
    }

    public long calculateHarga(){
        // TODO
        return baseHarga;
    }

    public String getNotaStatus(){
        // TODO
        String statusNota = "";
        if(this.isDone == true) statusNota = "Sudah selesai.";
        else statusNota = "Belum selesai."; 
        return statusNota;
    }

    @Override
    public String toString(){
        // TODO
        DateTimeFormatter formatTanggal = DateTimeFormatter.ofPattern("dd/MM/yyyy");        //menggunakan java.util dan java.time untuk mengformat tanggal
        LocalDate tanggalSelesai = null;                                                            //membuat localdate kosong agar dapat diisi dengan kondisi if else
        String harga = "";
        String services = "";
        String kompensasi = "";
        long hargaAkhir = baseHarga;

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
                hargaAkhir += element.getHarga(berat);
            } else if (element.getServiceName().equals("Antar")) {
                services += "-Antar @ Rp." + element.getHarga(this.berat);
                hargaAkhir += element.getHarga(berat);
            }
            services += "\n";
        }

        if(lateDayDone>0) {
            hargaAkhir -= lateDayDone*2000;
            kompensasi += " Ada kompensasi keterlambatan " + lateDayDone + " * 2000 hari";
        }

        return "[ID Nota = " + id + "]" +
        "\n" + "ID    : " + member.getId()+ 
        "\n" + "Paket : " + paket+
        "\n" + "Harga :\n" + harga + 
        "\n" + "Tanggal Terima  : " + this.tanggalMasuk +
        "\n" + "Tanggal Selesai : " + tanggalSelesai.format(formatTanggal)+
        "\n" + "--- SERVICE LIST ---" +
        "\n" + services +
        "Harga Akhir: " + hargaAkhir + kompensasi +"\n";
    }

    // Dibawah ini adalah getter
    public void setStatusNota(){
        for(LaundryService element: services1){
            if(element.isDone()==false) this.isDone = false;
            else this.isDone = true;
        }
    }
    public void setSisaHari(int x){
        this.sisaHariPengerjaan = x;
    }
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
        services1.toArray(services);
        return services;
    }
}