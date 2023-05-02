package assignments.assignment3.nota.service;

public class SetrikaService implements LaundryService{
    private boolean status = false;
    @Override
    public String doWork() {
        // TODO
        status = true;
        return "Sedang menyetrika...";
    }

    @Override
    public boolean isDone() {
        // TODO
        return status;
    }

    @Override
    public long getHarga(int berat) {
        // TODO
        long harga = berat*1000;
        return harga;
    }

    @Override
    public String getServiceName() {
        return "Setrika";
    }
}
