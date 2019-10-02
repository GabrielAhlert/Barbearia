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
import model.bean.Produto;
import model.bean.Produto;

/**
 *
 * @author Gabriel Missio
 */
public class ProdutoDAO {
    
    public boolean inserirProduto(Produto p) {
        String sql = "INSERT INTO produto (nome_produto, preco, estoque) VALUES (?, ?, ?)";//"sintax padrão do SQL"
        try {
            PreparedStatement ps = conexao.Conexao.getConexao().prepareStatement(sql);
            ps.setString(1, p.getNome());
            ps.setDouble(2, p.getPreco());
            ps.setInt(3, p.getQuantidade());
            ps.executeUpdate();
            return true;
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }
    
        public boolean updateProduto(Produto p) {
        String sql = "UPDATE produto SET preco = ? WHERE codigo = ?";//"sintax padrão do SQL"
        try {
            PreparedStatement ps = conexao.Conexao.getConexao().prepareStatement(sql);
            ps.setDouble(1, p.getPreco());
            ps.setInt(2, p.getCodigo());
            ps.executeUpdate();
            return true;
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }
        
        public boolean ExcluirProduto(Produto p) {
        String sql = "DELETE FROM produto WHERE codigo = ?";//"sintax padrão do SQL"
        try {
            PreparedStatement ps = conexao.Conexao.getConexao().prepareStatement(sql);
            ps.setInt(1, p.getCodigo());
            ps.executeUpdate();
            return true;
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }
        
        public List<Produto> read() {
          
        List<Produto>  resultados = new ArrayList<>();  
        String sql = "SELECT codigo, nome_produto, preco FROM produto";
        PreparedStatement ps;
        ResultSet rs;
        
        try {
            ps = conexao.Conexao.getConexao().prepareStatement(sql);
            rs = ps.executeQuery();
            
            while(rs.next()){
                
                Produto p = new Produto();
                
                p.setCodigo(rs.getInt("codigo"));
                p.setNome(rs.getString("nome_produto"));
                p.setPreco(rs.getDouble("preco"));
                resultados.add(p);
                
            }
            Collections.sort(resultados);
            return resultados;
            
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(ServicoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
       
        
    }
    
    
}
