package Models.Admin;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class AdminDAO extends BaseDAO<Admin> {
    public AdminDAO() throws SQLException {
        super();
    }


    @Override
    //Inserer Objet Admin par une requête prepare
    public void save(Admin object) throws SQLException {
        String req = "INSERT INTO Admin (nom , telephone , email , password) values (?,?,?,?);";

        this.preparedStatement = this.connection.prepareStatement(req);

        this.preparedStatement.setString(1 , object.getNom());
        this.preparedStatement.setString(2 , object.getTelephone());
        this.preparedStatement.setString(3 , object.getEmail());
        this.preparedStatement.setString(4 , object.getPassword());

        this.preparedStatement.execute();
    }

    @Override
    public Admin read(int id) throws SQLException {
        String req = "SELECT * FROM Admin WHERE id_admin = ?";

        this.preparedStatement = this.connection.prepareStatement(req);

        this.preparedStatement.setLong(1,id);
        this.resultSet = this.preparedStatement.executeQuery();

        Admin result = null;
        if(resultSet.next()){
            result = new Admin(
                    resultSet.getInt("id_admin"),
                    resultSet.getString("nom"),
                    resultSet.getString("telephone"),
                    resultSet.getString("email"),
                    resultSet.getString("password")
            );
        }

        return result;
    }

    @Override
    public void update(Admin object) throws SQLException {
        String req = "UPDATE Admin SET nom = ? , telephone = ? WHERE id_admin = ?";
        AdminDAO c = new AdminDAO();
        if (c.getOne(object.getId_admin()) == null){
            //create a static method to check the existence of a Admin to use in other CRUD methods
            throw new SQLException("Admin With id : "+ object.getId_admin()+" is not found");
        }
        else {
            this.preparedStatement = this.connection.prepareStatement(req);
            this.preparedStatement.setString(1 , object.getNom());
            this.preparedStatement.setString(2 , object.getTelephone());
            this.preparedStatement.setInt(3 , object.getId_admin());

            this.preparedStatement.execute();
        }
    }

    @Override
    public void delete(int id) throws SQLException {
        String req = "DELETE FROM Admin WHERE id_admin = ?";
        AdminDAO c = new AdminDAO();

        if (c.getOne(id) == null){
            throw new SQLException("Admin With id : "+ id+" is not found");
        }

        this.preparedStatement = this.connection.prepareStatement(req);
        this.preparedStatement.setInt(1,id);
        this.preparedStatement.execute();
    }

    @Override
    //selection dun client par son Id est returner le result
    public Admin getOne(int id) throws SQLException {
        String req = "SELECT * FROM Admin WHERE id_admin = ?";

        this.preparedStatement = this.connection.prepareStatement(req);

        this.preparedStatement.setLong(1,id);
        this.resultSet = this.preparedStatement.executeQuery();

        Admin result = null;
        if(resultSet.next()){
            result = new Admin(
                    resultSet.getInt("id_admin"),
                    resultSet.getString("nom"),
                    resultSet.getString("telephone"),
                    resultSet.getString("email"),
                    resultSet.getString("password")
            );
        }
        return result;
    }



    @Override
    //selection de la table Client et la stoker dans une List
    public List<Admin> getAll() throws SQLException {
        List<Admin> SQLCleint = new ArrayList<Admin>();
        String req = "SELECT * FROM Admin";

        this.statement = this.connection.createStatement();

        this.resultSet = this.statement.executeQuery(req);

        while (this.resultSet.next()){
            SQLCleint.add(
                    new Admin(
                            this.resultSet.getInt(1),
                            this.resultSet.getString(2),
                            this.resultSet.getString(3),
                            this.resultSet.getString(4),
                            this.resultSet.getString(5)
                    )
            );
        }
        return SQLCleint;
    }

    public Admin getAdminByEmailAndPassword(String email , String password) throws SQLException{
        String req = "SELECT * FROM Admin WHERE Email = ? AND password = ?";

        preparedStatement = connection.prepareStatement(req);
        preparedStatement.setString(1 , email);
        preparedStatement.setString(2 , password);

        this.resultSet = this.preparedStatement.executeQuery();

        Admin result = null;
        if(resultSet.next()){
            result = new Admin(
                    resultSet.getInt("id_admin"),
                    resultSet.getString("nom"),
                    resultSet.getString("telephone"),
                    resultSet.getString("email"),
                    resultSet.getString("password")
            );
        }
        return result;


    }
}
