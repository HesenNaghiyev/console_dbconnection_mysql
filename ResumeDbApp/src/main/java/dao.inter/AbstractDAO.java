package dao.inter;

import java.sql.Connection;
import java.sql.DriverManager;

public abstract class AbstractDAO {
    public  static Connection connect() throws Exception{

        String url = "jdbc:mysql://localhost:3306/***";
        String username ="***";
        String password  ="***";
        Connection connection  = DriverManager.getConnection(url,username,password); //connection between database and application

        return connection;

    }
}
