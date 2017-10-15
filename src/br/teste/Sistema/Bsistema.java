package br.teste.Sistema;

import br.teste.Conexao.Conexao;
import br.teste.Dominio.Customers;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class Bsistema {
    static List<Customers> lista = new ArrayList<>();
    
    public void executarBatch() throws SQLException {
        gerarCustomers();
        Connection conexao = null;
        PreparedStatement p = null;
        
        String sql = "insert into tb_customer_account values (seq_id_customer.nextval, ?, ?, ?, ?)";
        
        try{
            conexao = Conexao.getConnection();
            p = conexao.prepareStatement(sql);

            for (Customers cust : lista) {
                p.setString(1, cust.getCpf_cnpj());
                p.setString(2, cust.getNome());
                if(cust.isAtivo()){
                    p.setInt(3, 1);
                }
                else{
                    p.setInt(3, 0);
                }
                p.setDouble(4, cust.getVl_total());
                p.addBatch();
            }
            p.executeBatch();
            conexao.commit();
        }
        catch(SQLException e){
            conexao.rollback();
            JOptionPane.showMessageDialog(null, "Erro ao salvar os dados! "+e);
        }
        finally {
            if (p != null) {
                p.close();
            }
 
            if (conexao != null) {
                conexao.close();
            }
        }
    }
    
    public double retornarMedia() {
        
        Connection conexao;
        PreparedStatement p;
        ResultSet rs;
        double media = 0;
        int count = 0;
        
        String sql = "select vl_total from tb_customer_account where vl_total > 560 and id_customer between 1500 and 2700";
        
        try{
            conexao = Conexao.getConnection();
            p = conexao.prepareStatement(sql);
            rs = p.executeQuery();
            while(rs.next()){
                media += rs.getDouble("vl_total");
                count++;
            }
            
            media = media/count;
        }
        catch(SQLException e){
            JOptionPane.showConfirmDialog(null, "Erro ao calcular a média!"+e);
        }
        
        return media;
    }
    
    public List<Customers> atualizarDados(){
        List<Customers> lista = null;   
        Connection conexao;
        PreparedStatement p;
        ResultSet rs;
        
        String sql = "select * from tb_customer_account where vl_total > 560 and id_customer between 1500 and 2700 order by vl_total desc";
        boolean is_active;
        
        try{
            conexao = Conexao.getConnection();
            p = conexao.prepareStatement(sql);
            rs = p.executeQuery();
            while(rs.next()){
               int id = rs.getInt("id_customer");
               String cpf_cnpj = rs.getString("cpf_cnpj");
               String nome = rs.getString("nm_customer");
               int ativo = rs.getInt("is_active");
               double valor = rs.getDouble("vl_total");
               
               if(ativo == 1){
                   is_active = true;
               }
               else{
                   is_active = false;
               }
               lista.add(new Customers(id, cpf_cnpj, nome, is_active, valor));
            }
        }
        catch(SQLException e){
            JOptionPane.showConfirmDialog(null, "Erro ao criar a tabela!"+e);
        }
        
        return lista;
    }
    
    private void gerarCustomers(){
        
		for (int i = 0; i < 3000; i++) {
			Customers cust = null;
			cust.setCpf_cnpj("999.999.999-"+i);
                        cust.setNome("Davi "+i);
                        if(i%2 == 0){
                            cust.setAtivo(true);
                        }
                        else{
                            cust.setAtivo(false);
                        }
                        cust.setVl_total(i);
			lista.add(cust);
		}
	}
}
