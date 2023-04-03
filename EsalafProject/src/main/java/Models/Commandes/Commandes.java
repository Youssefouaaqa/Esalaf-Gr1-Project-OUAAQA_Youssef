package Models.Commandes;

import java.util.Date;

public class Commandes {
    private int id_commande;
    private Date date_commande;
    private String nom_client;
    private String produits;
    private int credit;

    public Commandes(int id_commande, Date date_commande, String nom_client, String produits, int credit) {
        this.id_commande = id_commande;
        this.date_commande = date_commande;
        this.nom_client = nom_client;
        this.produits = produits;
        this.credit = credit;
    }

    public int getId_commande() {
        return id_commande;
    }

    public Date getDate_commande() {
        return date_commande;
    }

    public String getNom_client() {
        return nom_client;
    }

    public String getProduits() {
        return produits;
    }

    public int getCredit() {
        return credit;
    }

}
