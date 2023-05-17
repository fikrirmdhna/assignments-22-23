package assignments.assignment4.gui.member;
// Fikri Dhiya Ramadhana
// 2206819533
// TP04
public interface Loginable {
    boolean login(String id, String password);
    void logout();
    String getPageName();

}
