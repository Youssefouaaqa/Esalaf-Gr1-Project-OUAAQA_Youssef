package Models.Admin;

import Models.Admin.Admin;
import Models.Admin.AdminDAO;

import java.sql.SQLException;

public class Test {
    public static void main(String[] args) throws SQLException {
        AdminDAO AdminModels = new AdminDAO();

        AdminModels.save(new Admin("hamid" , "test" , "test" , "test"));

    }
}
