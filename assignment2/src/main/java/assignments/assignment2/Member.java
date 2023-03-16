package assignments.assignment2;
// Fikri Dhiya Ramadhana
// 2206819533
// TP02

import assignments.assignment1.NotaGenerator;

public class Member {
    // TODO: tambahkan attributes yang diperlukan untuk class ini
    private String nama;
    private String noHp;           //attributes yang digunakan di class Member
    private String Id;
    private int bonusCounter;

    public Member(String nama, String noHp) {
        // TODO: buat constructor untuk class ini
        this.nama = nama;
        this.noHp = noHp;
        this.Id = NotaGenerator.generateId(nama,noHp);      //membuat constructor dari class Member
        this.bonusCounter = 0;
    }

    // TODO: tambahkan methods yang diperlukan untuk class ini
    public String getId(){
        return this.Id;
    }
    
    public String getNama(){
        return nama;
    }
    
    public String getNoHp(){                //setter getter yang diperlukan dari class Member
        return noHp;
    }

    public int getBonusCounter(){
        return bonusCounter;
    }

    public void addBonus(){
        bonusCounter+=1; 
    }
}