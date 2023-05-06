package assignments.assignment3.nota.service;
// Fikri Dhiya Ramadhana
// 2206819533
// TP03
public class AntarService implements LaundryService{
    private boolean status = false;                 //membuat boolean variable agar status service dengan service yang lain independent
    @Override
    public String doWork() {
        // TODO
        status = true;                              //sekali dipanggil langsung mengeset status menjadi true
        return "Sedang mengantar...";
    }

    @Override
    public boolean isDone() {
        // TODO                                     
        return status;                             //mereturn status
    }

    @Override
    public long getHarga(int berat) {
        // TODO
        long harga = 0;
        if(berat<=4) harga = 2000;                 //mereturn harga dengan ketentuan minimal harga 2000
        else harga = berat*500;
        return harga;
    }

    @Override
    public String getServiceName() {              //mereturn String "Antar"
        return "Antar";
    }
}
