package database;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
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
            props.load(new FileInputStream("DB.properties"));
            conn = DriverManager.getConnection("jdbc:oracle:thin:@156.35.94.99:1521:DESA", props);
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

    public ResultSet executeQuery(String sql){
        try {
            return conn.prepareStatement(sql).executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
