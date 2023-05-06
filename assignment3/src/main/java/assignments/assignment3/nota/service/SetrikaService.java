package assignments.assignment3.nota.service;
// Fikri Dhiya Ramadhana
// 2206819533
// TP03
public class SetrikaService implements LaundryService{
    private boolean status = false;                  //membuat boolean variable agar status service dengan service yang lain independent
    @Override
    public String doWork() {
        // TODO
        status = true;                               //sekali dipanggil langsung mengeset status menjadi true
        return "Sedang menyetrika...";
    }

    @Override
    public boolean isDone() {
        // TODO
        return status;                               //mereturn status
    }

    @Override
    public long getHarga(int berat) {
        // TODO
        long harga = berat*1000;                     //mereturn harga tanpa ada harga minimal    
        return harga;
    }

    @Override
    public String getServiceName() {                
        return "Setrika";                            //mereturn String "Setrika"
    }
}
