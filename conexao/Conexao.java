package conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
    public static final String url = "jdbc:mysql://localhost:3306/banco_de_dados_locadora_de_veiculos";
    public static final String user = "root";
    public static final String password = "Spurubru5.";
    
    private static Connection conn;
    
    public static Connection getConexao(){
        try{
            if (conn == null){
                conn = DriverManager.getConnection(url, user, password);
                return conn;
            }else{
                return conn;
            }
        } catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }
}
