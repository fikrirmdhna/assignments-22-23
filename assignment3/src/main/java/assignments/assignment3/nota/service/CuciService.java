package assignments.assignment3.nota.service;
// Fikri Dhiya Ramadhana
// 2206819533
// TP03
public class CuciService implements LaundryService{
    private boolean status = false;                //membuat boolean variable agar status service dengan service yang lain independent
    @Override
    public String doWork() {
        // TODO
        status = true;                             //sekali dipanggil langsung mengeset status menjadi true
        return "Sedang mencuci...";
    }

    @Override
    public boolean isDone() {
        // TODO
        return status;                             //mereturn status
    }

    @Override
    public long getHarga(int berat) {
        // TODO
        return 0;                                  //mereturn 0 karena default dan dilihat dari baseHarga nya
    }

    @Override
    public String getServiceName() {                
        return "Cuci";                              //mereturn String "Cuci"
    }
}
