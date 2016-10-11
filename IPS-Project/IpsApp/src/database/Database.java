package database;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class Database {

    private static Database instance;
    private Connection conn;

    public static Database getInstance() {
        if (instance == null) {
            instance = new Database();
        }
        return instance;
    }

    private Database() {
        Properties props;
        try {
            props = new Properties();
            props.load(new FileInputStream("IpsApp/DB.properties"));
            conn = DriverManager.getConnection("jdbc:oracle:thin:@156.35.94.99:1521:DESA", props);
            //conn = DriverManager.getConnection("jdbc:h2:./IpsApp/Database/files/DBApp;IFEXISTS=TRUE", "SA", "");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException file){
            file.printStackTrace();
        }
    }

    public void close() {
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ResultSet executeQuery(String sql) throws SQLException{
    	return conn.createStatement().executeQuery(sql);
    }
    
    public ResultSet executePreparedQuery(String sql, Object... strings) throws SQLException{
    	PreparedStatement prepared = conn.prepareStatement(sql);
    	for(int i = 0; i< strings.length; i++)
    		prepared.setObject(i+1, strings[i]);
    	return prepared.executeQuery();
    }
}
