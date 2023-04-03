package com.example.esalaf;

import Models.Commandes.Commandes;
import Models.Produits.Produits;
import Models.client.Client;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.sql.Date;

public class DashboardController implements Initializable {

    @FXML
    private AnchorPane mainapp;
    @FXML
    private Button AjouterButtClick;

    @FXML
    private Button AjouterProdButtClick;

    @FXML
    private Button AjtComButt;

    @FXML
    private VBox adminDashboard;

    @FXML
    private AnchorPane clientsPage;

    @FXML
    private TableColumn<Client, Integer > col_idCli;

    @FXML
    private TableColumn<Produits, Integer> col_idProd;

    @FXML
    private TableColumn<Client, String> col_nomCli;

    @FXML
    private TableColumn<Produits, String> col_nomProd;

    @FXML
    private TableColumn<Client, String> col_prenomCli;

    @FXML
    private TableColumn<Produits, Integer> col_prixProd;

    @FXML
    private TableColumn<Produits, Integer> col_quantProd;

    @FXML
    private TableColumn<Client, String> col_teleCli;

    @FXML
    private Button espaceclientbuttonClick;

    @FXML
    private Button espacecommandeButtonClick;

    @FXML
    private Button espaceproduitsButtonClick;

    @FXML
    private Button exitButtClick;

    @FXML
    private Button modButtClick;

    @FXML
    private Button modProdButtClick;

    @FXML
    private TextField idCliField;

    @FXML
    private TextField nomCliField;

    @FXML
    private TextField idProdField;

    @FXML
    private TextField nomProdField;

    @FXML
    private TextField prenomCliField;

    @FXML
    private TextField prixProdField;

    @FXML
    private TableView<Produits> prod_tab;

    @FXML
    private AnchorPane produitspage;

    @FXML
    private Spinner<Integer> quantProdField;

    @FXML
    private Button suppButtClick;

    @FXML
    private Button suppProdButtClick;

    @FXML
    private TableView<Client> tab_Cli;

    @FXML
    private TextField teleCliField;

    @FXML
    private ComboBox<Client> choisirClient;

    @FXML
    private ComboBox<Commandes> choisirProd;

    @FXML
    private DatePicker datecomField;

    @FXML
    private TextField idcomField;

    @FXML
    private TextField prixcomField;

    @FXML
    private TextField prodcomField;

    @FXML
    private TableColumn<?, ?> col_clicom;

    @FXML
    private TableColumn<?, ?> col_creditcom;

    @FXML
    private TableColumn<Commandes, Date> col_datecom;

    @FXML
    private TableColumn<?, ?> col_idcom;

    @FXML
    private TableColumn<Commandes, String> col_prodcom;

    @FXML
    private TableView<Commandes> tabCom;

    @FXML
    private AnchorPane Cahierdecredit;

    @FXML
    private Button ModComButt;

    @FXML
    private AnchorPane backpane;




    public void exitButtClick(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("Login-view.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("S'inscrire!");
        stage.show();
    }
    public void switchtab(ActionEvent event) throws SQLException {
        if (event.getSource() == espaceclientbuttonClick){
            clientsPage.setVisible(true);
            showclients();
            choisirClient();
            produitspage.setVisible(false);
            Cahierdecredit.setVisible(false);
            backpane.setVisible(false);
        } else if (event.getSource() == espaceproduitsButtonClick) {
            clientsPage.setVisible(false);
            produitspage.setVisible(true);
            Cahierdecredit.setVisible(false);
            backpane.setVisible(false);
            showproduits();
        } else if (event.getSource() == espacecommandeButtonClick) {
            Cahierdecredit.setVisible(true);
            clientsPage.setVisible(false);
            produitspage.setVisible(false);
            backpane.setVisible(false);
        }
    }
    public Connection getConnection(){
        Connection conn;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/esalaf1","root","");
                    return conn;
        }catch (Exception ex){
            System.out.println("Error "+ ex.getMessage());
            return null;
        }
    }



    public ObservableList<Client> getclientsListe(){
        ObservableList<Client> ClientsList = FXCollections.observableArrayList();
        Connection conn = getConnection();
        String req = "SELECT * FROM client";
        Statement st;
        ResultSet rs;

        try{
            st = conn.createStatement();
            rs = st.executeQuery(req);
            Client clients;
            while(rs.next()){
                clients = new Client(rs.getInt("id_client")
                        ,rs.getString("nom")
                        ,rs.getString("prenom")
                        ,rs.getString("telephone"));
                ClientsList.add(clients);
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return ClientsList;
    }
    public void showclients(){
        ObservableList<Client> Liste = getclientsListe();
        col_idCli.setCellValueFactory(new PropertyValueFactory<>("id_client"));
        col_nomCli.setCellValueFactory(new PropertyValueFactory<Client,String>("nom"));
        col_prenomCli.setCellValueFactory(new PropertyValueFactory<Client,String>("prenom"));
        col_teleCli.setCellValueFactory(new PropertyValueFactory<Client,String>("telephone"));

        tab_Cli.setItems(Liste);
    }
    public void onAjouterButtClick(){
        String req = "INSERT INTO client VALUES (" + idCliField.getText() + ",'" + nomCliField.getText() + "','"
                + prenomCliField.getText() + "','" + teleCliField.getText() + "')";
        executeQuery(req);
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"Client ajoute avec succes!", ButtonType.OK);
        alert.showAndWait();
        showclients();
    }
    public void onmodButtClick(){
        String req = "UPDATE client SET nom = '" + nomCliField.getText() + "', prenom = '" + prenomCliField.getText()
                + "', telephone = " + teleCliField.getText() + " WHERE id_client = " + idCliField.getText() + "";
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"Information modifie avec succes!", ButtonType.OK);
        alert.showAndWait();
        executeQuery(req);
        showclients();
    }
    public void onsuppButtClick() {
        String req = "DELETE FROM client WHERE id_client =" + idCliField.getText() + "";
        executeQuery(req);
        Alert alert = new Alert(Alert.AlertType.WARNING,"Voulez-vous supprimez ce client", ButtonType.YES);
        alert.showAndWait();
        showclients();
    }
    public void executeQuery(String req) {
        Connection conn = getConnection();
        Statement st;
        try{
            st = conn.createStatement();
            st.executeUpdate(req);
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    public void onMouseClick(MouseEvent eve){
        Client cli = tab_Cli.getSelectionModel().getSelectedItem();
        idCliField.setText(""+cli.getId_client());
        nomCliField.setText(cli.getNom());
        prenomCliField.setText(cli.getPrenom());
        teleCliField.setText(cli.getTelephone());
    }



    public ObservableList<Produits> getproduitsListe(){
        ObservableList<Produits> ProduitsList = FXCollections.observableArrayList();
        Connection conn = getConnection();
        String req = "SELECT * FROM produits";
        Statement st;
        ResultSet rs;

        try{
            st = conn.createStatement();
            rs = st.executeQuery(req);
            Produits produits;
            while(rs.next()){
                produits = new Produits(rs.getInt("id_produit")
                        ,rs.getString("nom_prod")
                        ,rs.getInt("quant_prod")
                        ,rs.getInt("prix"));
                ProduitsList.add(produits);
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return ProduitsList;
    }
    public void showproduits(){
        ObservableList<Produits> Liste = getproduitsListe();
        col_idProd.setCellValueFactory(new PropertyValueFactory<>("id_produit"));
        col_nomProd.setCellValueFactory(new PropertyValueFactory<Produits,String>("nom_prod"));
        col_quantProd.setCellValueFactory(new PropertyValueFactory<Produits,Integer>("quant_prod"));
        col_prixProd.setCellValueFactory(new PropertyValueFactory<Produits,Integer>("prix"));

        prod_tab.setItems(Liste);
    }
    public void onAjouterProdButtClick(){
        String req = "INSERT INTO produits VALUES (" + idProdField.getText() + ",'" + nomProdField.getText() + "',"
                + quantProdField.getValue() + "," + prixProdField.getText() + ")";
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"Produits ajoute avec succes!", ButtonType.OK);
        alert.showAndWait();
        executeQuery(req);
        showproduits();
    }
    public void onmodProdButtClick(){
        String req = "UPDATE produits SET nom_prod = '" + nomProdField.getText() + "', quant_prod = " + quantProdField.getValue()
                + ", prix = " + prixProdField.getText() + " WHERE id_produit = " + idProdField.getText() + "";
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"Information modifiee avec succes!", ButtonType.OK);
        alert.showAndWait();
        executeQuery(req);
        showproduits();
    }
    public void onsuppProdButtClick() {
        String req = "DELETE FROM produits WHERE id_produit =" + idProdField.getText() + "";
        Alert alert = new Alert(Alert.AlertType.WARNING,"Voulea-vous supprimer ce produit?", ButtonType.YES);
        alert.showAndWait();
        executeQuery(req);
        showproduits();
    }
    public void onMouseClickProd(MouseEvent eve){
        Produits prod = prod_tab.getSelectionModel().getSelectedItem();
        idProdField.setText(""+prod.getId_produit());
        nomProdField.setText(prod.getNom_prod());
        quantProdField.getValueFactory().setValue(prod.getQuant_prod());
        prixProdField.setText(""+prod.getPrix());
    }
    private SpinnerValueFactory spinner;
    public void produitsspinner(){
        spinner = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,50,0);
        quantProdField.setValueFactory(spinner);
    }
    private int qty;
    public void produitsspinnerValue(){
        qty = quantProdField.getValue();
    }






    private String [] clientsListe = {"Youssef", "Bilal"};
    public void choisirClient(){
        List<String> liste = new ArrayList<>();

        for (String data: clientsListe){
            liste.add(data);
        }
        ObservableList statdata = FXCollections.observableArrayList(liste);
        choisirClient.setItems(statdata);
    }

    private String [] ProdsListe = {"Dari – Couscous Moyen 500g", "St Louis-Cassonade Roux 1kg"};
    public void choisirProd(){
        List<String> listeProd = new ArrayList<>();

        for (String prod: ProdsListe){
            listeProd.add(prod);
        }
        ObservableList setdata = FXCollections.observableArrayList(listeProd);
        choisirProd.setItems(setdata);
    }
    public void onAjtComButt(){
        String req = "INSERT INTO commande VALUES (" + idcomField.getText() + ",'" + datecomField.getValue() + "','"
                + choisirClient.getSelectionModel().getSelectedItem() + "','" + choisirProd.getSelectionModel().getSelectedItem() + "'," + prixcomField.getText() +")";
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"Commande ajoutee avec succes!", ButtonType.OK);
        alert.showAndWait();
        executeQuery(req);
        showcommandes();
    }
    public void onSuppComButt(){
        String req = "DELETE FROM commande WHERE id_commande =" + idcomField.getText() + "";
        Alert alert = new Alert(Alert.AlertType.WARNING,"Voulez-vous supprimez cette commande?", ButtonType.YES);
        alert.showAndWait();
        executeQuery(req);
        showcommandes();
    }
    public void onModComButt(){
        String req = "UPDATE commande SET date_commande = '" + datecomField.getValue()
                + "', nom_client = '" + choisirClient.getSelectionModel().getSelectedItem() + "', produits = '" + choisirProd.getSelectionModel().getSelectedItem() +
                "', credit = " + prixcomField.getText() +" WHERE id_commande = " + idcomField.getText() + "";
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"Commande modifiee avec succes!", ButtonType.OK);
        alert.showAndWait();
        executeQuery(req);
        showcommandes();
    }
    public void onMouseClickComm(MouseEvent eve){
        Commandes comm = tabCom.getSelectionModel().getSelectedItem();

        idcomField.setText(""+comm.getId_commande());
        datecomField.setValue(comm.getDate_commande().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        nomCliField.setText(""+comm.getNom_client());
        prodcomField.setText(comm.getProduits());
        prixcomField.setText(""+comm.getCredit());
    }
    public ObservableList<Commandes> getcommandesListe(){
        ObservableList<Commandes> CommandesList = FXCollections.observableArrayList();
        Connection conn = getConnection();
        String req = "SELECT * FROM commande";
        Statement st;
        ResultSet rs;

        try{
            st = conn.createStatement();
            rs = st.executeQuery(req);
            Commandes commande;
            while(rs.next()){
                commande = new Commandes(rs.getInt("id_commande")
                        , rs.getDate("date_commande")
                        ,rs.getString("nom_client")
                        ,rs.getString("produits")
                        ,rs.getInt("credit"));
                CommandesList.add(commande);
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return CommandesList;
    }
    public void showcommandes(){
        ObservableList<Commandes> Liste = getcommandesListe();
        col_idcom.setCellValueFactory(new PropertyValueFactory<>("id_commande"));
        col_datecom.setCellValueFactory(new PropertyValueFactory<Commandes, Date>("date_commande"));
        col_clicom.setCellValueFactory(new PropertyValueFactory<>("nom_client"));
        col_prodcom.setCellValueFactory(new PropertyValueFactory<>("produits"));
        col_creditcom.setCellValueFactory(new PropertyValueFactory<>("credit"));

        tabCom.setItems(Liste);
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        showclients();
        showproduits();
        choisirClient();
        choisirProd();
        showcommandes();
        produitsspinner();
    }
}
