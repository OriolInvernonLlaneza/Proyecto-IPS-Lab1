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

    /**
     * Ejecuta una consulta simple
     * @param sql La consulta
     * @return Un ResultSet con los resultados
     * @throws SQLException
     */
    public ResultSet executeQuery(String sql) throws SQLException{
    	return conn.createStatement().executeQuery(sql);
    }
    
    /**
     * Prepara y ejecuta una consulta con parametros preparados
     * @param sql La consulta
     * @param strings En orden, los parametros que se quieran preparar
     * @return Un ResultSet con los resultados
     * @throws SQLException
     */
    public ResultSet executePreparedQuery(String sql, Object... strings) throws SQLException{
    	PreparedStatement prepared = conn.prepareStatement(sql);
    	for(int i = 0; i< strings.length; i++)
    		prepared.setObject(i+1, strings[i]);
    	return prepared.executeQuery();
    }
    
    /**
     * Devuelve un Statement simple
     * @return Un Statement simple
     * @throws SQLException
     */
    public Statement returnStatement() throws SQLException{
    	return conn.createStatement();
    }
    
    /**
     * Devuelve un statement preparado. Este metodo deberia usarse cuando queramos realizar la misma consulta varias veces pero con diferentes parametros. Aprovechando
     * el statement.addBatch()
     * @param sql La String base con la que operará este Statement
     * @return
     * @throws SQLException
     */
    public PreparedStatement returnPreparedStatement(String sql) throws SQLException{
    	return conn.prepareStatement(sql);
    }
    
    public void cambiarAutoCommit(boolean valor) throws SQLException{
    	conn.setAutoCommit(valor);
    }
    
    public void commit() throws SQLException{
    	conn.commit();
    }
}
