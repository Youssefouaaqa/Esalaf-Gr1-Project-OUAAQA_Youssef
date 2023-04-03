package Models.client;

public class Client {
    private int id_client;
    private String nom;
    private String prenom;
    private String telephone;

    public Client(int id_client, String nom, String prenom, String telephone) {
        this.id_client = id_client;
        this.nom = nom;
        this.prenom = prenom;
        this.telephone = telephone;
    }

    public int getId_client() {
        return id_client;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getTelephone() {
        return telephone;
    }
}



