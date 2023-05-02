package assignments.assignment3.nota.service;

// import assignments.assignment3.nota.Nota;

public class AntarService implements LaundryService{
    private boolean status = false;
    @Override
    public String doWork() {
        // TODO
        status = true;
        return "Sedang mengantar...";
    }

    @Override
    public boolean isDone() {
        // TODO
        return status;
    }

    @Override
    public long getHarga(int berat) {
        // TODO
        long harga = 0;
        if(berat<=4) harga = 2000;
        else harga = berat*500;
        return harga;
    }

    @Override
    public String getServiceName() {
        return "Antar";
    }
}
