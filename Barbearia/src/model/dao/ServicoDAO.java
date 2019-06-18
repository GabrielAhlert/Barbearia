/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.bean.Servico;

/**
 *
 * @author Gabriel Missio
 */
public class ServicoDAO {
    
    public boolean inserirServico(Servico s) {
        String sql = "INSERT INTO servico (nome, preco) VALUES (?, ?)";//"sintax padrão do SQL"
        try {
            PreparedStatement ps = conexao.Conexao.getConexao().prepareStatement(sql);
            ps.setString(1, s.getNome());
            ps.setDouble(2, s.getPreco());
            ps.executeUpdate();
            return true;
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }
    
     public boolean updateServico(Servico s) {
        String sql = "UPDATE servico SET preco = ? WHERE codigo = ?";//"sintax padrão do SQL"
        try {
            PreparedStatement ps = conexao.Conexao.getConexao().prepareStatement(sql);
            ps.setDouble(1, s.getPreco());
            ps.setInt(2, s.getCodigo());
            ps.executeUpdate();
            return true;
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }
     
      public boolean ExcluirServico(Servico s) {
        String sql = "DELETE FROM servico WHERE codigo = ?";//"sintax padrão do SQL"
        try {
            PreparedStatement ps = conexao.Conexao.getConexao().prepareStatement(sql);
            ps.setInt(1, s.getCodigo());
            ps.executeUpdate();
            return true;
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }
    
    
      
      public List<Servico> read() {
          
        List<Servico>  resultados = new ArrayList<>();  
        String sql = "SELECT codigo, nome, preco FROM servico";
        PreparedStatement ps;
        ResultSet rs;
        
        try {
            ps = conexao.Conexao.getConexao().prepareStatement(sql);
            rs = ps.executeQuery();
            
            while(rs.next()){
                
                Servico s = new Servico();
                
                s.setCodigo(rs.getInt("codigo"));
                s.setNome(rs.getString("nome"));
                s.setPreco(rs.getDouble("preco"));
                resultados.add(s);
                
            }
            Collections.sort(resultados);
            return resultados;
            
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(ServicoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
       
        
    }
    
}
