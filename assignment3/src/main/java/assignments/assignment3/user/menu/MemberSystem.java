package assignments.assignment3.user.menu;
import assignments.assignment1.NotaGenerator;
import assignments.assignment3.nota.Nota;
import assignments.assignment3.nota.NotaManager;
import assignments.assignment3.nota.service.AntarService;
import assignments.assignment3.nota.service.CuciService;
import assignments.assignment3.nota.service.SetrikaService;
import assignments.assignment3.user.Member;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MemberSystem extends SystemCLI {
    private static SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
    private static Calendar cal = Calendar.getInstance();
    /**
     * Memproses pilihan dari Member yang masuk ke sistem ini sesuai dengan menu specific.
     *
     * @param choice -> pilihan pengguna.
     * @return true jika user log.
     */
    @Override
    protected boolean processChoice(int choice) {
        boolean logout = false;
        // TODO
        if(choice==1) addLoundry();
        else if (choice==2) showNota();
        else if (choice==3) return logout = true;
        return logout;
    }

    /**
     * Displays specific menu untuk Member biasa.
     */
    @Override
    protected void displaySpecificMenu() {
        System.out.println("1. Saya ingin laundry");
        System.out.println("2. Lihat detail nota saya");
        System.out.println("3. Logout");
    }

    /**
     * Menambahkan Member baru ke sistem.
     *
     * @param member -> Member baru yang akan ditambahkan.
     */
    public void addMember(Member member) {
        // TODO
        memberList.add(member);
    }

    public void addLoundry(){
        System.out.println("Masukkan paket laundry: ");
        String paket = "";                                         

        while (true){
            String paket1 = in.nextLine();
            if (paket1.toLowerCase().equals("express")){                                                                            
                paket = paket1;
                break;
            } else if (paket1.toLowerCase().equals("fast")){                                                         
                paket = paket1;
                break;                                                                   //membuat input paket menjadi case insensitive
            } else if (paket1.toLowerCase().equals("reguler")){                //menambahkan sisaHari sesuai dengan ketentuan tiap paket
                paket = paket1;
                break;
            } else if (paket1.toLowerCase().equals("?")){                    
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
            String berat1 = in.nextLine();                       //meminta input berat cucian dalam bentuk String
            if (isNotEmpty(berat1)==false){
                System.out.println("Harap masukkan berat cucian Anda dalam bentuk bilangan positif.");
            } else {
                if (NotaGenerator.checkNum(berat1)){                               
                    berat = Integer.valueOf(berat1);                    //mengubah String menjadi integer    
                    if (berat <= 0){
                        System.out.println("Harap masukkan berat cucian Anda dalam bentuk bilangan positif.");
                        continue;
                    } else if (berat > 0 && berat < 2){                     
                        berat = 2;
                        System.out.println("Cucian kurang dari 2 kg, maka cucian akan dianggap sebagai 2 kg");    
                    }
                    break;
                } else {
                    System.out.println("Harap masukkan berat cucian Anda dalam bentuk bilangan positif.");
                }
            }
        }
        Nota newNota = new Nota(loginMember, berat, paket, fmt.format(cal.getTime()));
        newNota.addService(new CuciService());
        loginMember.addNota(newNota);
        NotaManager.addNota(newNota);
    
        System.out.print("Apakah kamu ingin cucianmu disetrika oleh staff professional kami?" +
                            "\nHanya tambah 1000 / kg :0"+
                            "\n[Ketik x untuk tidak mau]: ");
        String pilihanSetrika = in.nextLine();
        if(pilihanSetrika.toLowerCase().equals("x")){

        } else {
            newNota.addService(new SetrikaService());
        }

        System.out.print("Mau diantar oleh kurir kami? Dijamin aman dan cepat sampai tujuan!"+
                        "\nCuma 2000 / 4kg, kemudian 500 / kg" +
                        "\n[Ketik x untuk tidak mau]: ");
        String pilihanAntar = in.nextLine();
        if(pilihanAntar.toLowerCase().equals("x")){

        } else {
            newNota.addService(new AntarService());
        }
        
        System.out.println("Nota berhasil dibuat!");
    }

    public void showNota(){
        for(Nota element: loginMember.getNotaList()){
            System.out.println(element.toString()); 
        }
    }

    public boolean isNotEmpty(String x){                    //Boolean method untuk mengecek String kosong atau tidak
        boolean check = true;
        if (x.isEmpty()==true || x.length()==0)
            check = false;
        return check;
    }

}