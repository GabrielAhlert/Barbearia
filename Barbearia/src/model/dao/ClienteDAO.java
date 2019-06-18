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
import model.bean.Cliente;

/**
 *
 * @author Gabriel Missio
 */
public class ClienteDAO {
    public boolean inserirCliente(Cliente c) {
        String sql = "INSERT INTO cliente (nome, telefone, email) VALUES (?, ?, ?)";//"sintax padrão do SQL"
        try {
            PreparedStatement ps = conexao.Conexao.getConexao().prepareStatement(sql);
            ps.setString(1, c.getNome());
            ps.setString(2, c.getCelular());
            ps.setString(3, c.getEmail());
            ps.executeUpdate();
            return true;
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }
    
     public List<Cliente> read() {
          
        List<Cliente>  resultados = new ArrayList<>();  
        String sql = "SELECT codigo, nome, telefone, email FROM cliente";
        PreparedStatement ps;
        ResultSet rs;
        
        try {
            ps = conexao.Conexao.getConexao().prepareStatement(sql);
            rs = ps.executeQuery();
            
            while(rs.next()){
                
                Cliente c = new Cliente();
                //nome da tabela do banco de dados
                c.setCodigo(rs.getInt("codigo"));
                c.setNome(rs.getString("nome"));
                c.setCelular(rs.getString("telefone"));
                c.setEmail(rs.getString("email"));
                resultados.add(c);
                
            }
            Collections.sort(resultados);
            return resultados;
            
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(ServicoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
       
        
    }
     
        public boolean excluirCliente(Cliente c) {
        String sql = "DELETE FROM cliente WHERE codigo = ?";//"sintax padrão do SQL"
        try {
            PreparedStatement ps = conexao.Conexao.getConexao().prepareStatement(sql);
            ps.setInt(1, c.getCodigo());
            ps.executeUpdate();
            return true;
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }
        
        public boolean updateCliente(Cliente c) {
        String sql = "UPDATE cliente SET telefone = ?, email = ? WHERE codigo = ?";//"sintax padrão do SQL"
        try {
            PreparedStatement ps = conexao.Conexao.getConexao().prepareStatement(sql);
            ps.setString(1, c.getCelular());
            ps.setString(2, c.getEmail());
            ps.setInt(3, c.getCodigo());
            ps.executeUpdate();
            return true;
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }
        
        
    
}
