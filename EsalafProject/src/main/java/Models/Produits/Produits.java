package Models.Produits;

public class Produits {
    private int id_produit;
    private String nom_prod;
    private int quant_prod;
    private int prix;

    public Produits(int id_produit, String nom_prod, int quant_prod, int prix) {
        this.id_produit = id_produit;
        this.nom_prod = nom_prod;
        this.quant_prod = quant_prod;
        this.prix = prix;
    }

    public int getId_produit() {
        return id_produit;
    }

    public String getNom_prod() {
        return nom_prod;
    }

    public int getQuant_prod() {
        return quant_prod;
    }

    public int getPrix() {
        return prix;
    }
}
