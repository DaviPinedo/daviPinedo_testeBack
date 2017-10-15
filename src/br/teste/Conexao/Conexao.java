package br.teste.Conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class Conexao {
    
    private static Connection conexao;
    private static final String DRIVER = "";
    private static final String URL = "";
    private static final String USUARIO = "";
    private static final String SENHA = "";
    
    private Conexao() {};
    
    public static synchronized Connection getConnection() {
        if(conexao == null) {
            try {
                Class.forName(DRIVER);
                conexao = DriverManager.getConnection(URL, USUARIO, SENHA);
            } 
            catch(ClassNotFoundException e) {
                JOptionPane.showMessageDialog(null, "Erro ao carregar o driver de conexão\n"+e);
            }
            catch(SQLException e) {
                JOptionPane.showMessageDialog(null, "Erro ao estabelecer conexão com o banco de dados\n"+e);
            }
        }        
        return conexao;
    }
    
}
