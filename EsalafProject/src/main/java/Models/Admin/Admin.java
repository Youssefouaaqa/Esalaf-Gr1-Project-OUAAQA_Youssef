package Models.Admin;

public class Admin {
    private int id_admin;
    private String nom;
    private String telephone;

    private String Email;
    private String password;

    public Admin(int id_admin, String nom, String telephone, String email, String password) {
        this.id_admin = id_admin;
        this.nom = nom;
        this.telephone = telephone;
        Email = email;
        this.password = password;
    }

    public Admin(String nom, String telephone, String email, String password) {
        this.nom = nom;
        this.telephone = telephone;
        Email = email;
        this.password = password;
    }

    public Admin() {

    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId_admin() {
        return id_admin;
    }

    public String getNom() {
        return nom;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setId_admin(int id_admin) {
        this.id_admin = id_admin;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "id_admin=" + id_admin +
                ", nom='" + nom + '\'' +
                ", telephone='" + telephone + '\'' +
                '}';
    }
}

