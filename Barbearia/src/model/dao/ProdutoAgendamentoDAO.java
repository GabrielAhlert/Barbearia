/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.bean.Agendamento;
import model.bean.Cliente;
import model.bean.Produto;
import model.bean.ProdutoAgendamento;
import model.bean.Servico;

/**
 *
 * @author Vera
 */
public class ProdutoAgendamentoDAO {
    
    public boolean inserirProdutoAgendamento(ProdutoAgendamento pa) {
        String sql = "INSERT INTO produto_agendamento (cod_produto, cod_agendamento, quantidade) VALUES (?,'"+pa.getAgendamento().getDataH()+"',?)";//"sintax padrão do SQL"
        try {
            PreparedStatement ps = conexao.Conexao.getConexao().prepareStatement(sql);
        //    Timestamp t = (Timestamp) a.getDataHora(
            //ps.setTimestamp(1, Timestamp.valueOf(a.getDataHora()));
            ps.setInt(1, pa.getProduto().getCodigo());
            ps.setInt(2, pa.getQuantidades());
            //ps.setString(1, c.getNome());
            //ps.setString(2, c.getCelular());
            //ps.setString(3, c.getEmail());
            ps.executeUpdate();
            return true;
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }
    
    public boolean updateProdutoAgendamento(ProdutoAgendamento pa) {
        String sql = "UPDATE produto_agendamento SET quantidade = ? WHERE cod_agendamento = ? AND cod_produto = ?";//"sintax padrão do SQL"
        try {
            PreparedStatement ps = conexao.Conexao.getConexao().prepareStatement(sql);
            ps.setInt(1, pa.getQuantidades());
            ps.setTimestamp(2, pa.getAgendamento().getDataH());
            ps.setInt(3, pa.getProduto().getCodigo());
            ps.executeUpdate();
            return true;
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }
        public List<ProdutoAgendamento> readP(Timestamp t) {
          
        List<ProdutoAgendamento>  resultados = new ArrayList<>();  
        String sql = "SELECT nome_produto, produto.preco AS precop, servico.preco AS precos, cod_produto, cod_agendamento,cod_servico ,quantidade FROM produto_agendamento pa " +
                     "JOIN produto ON produto.codigo = pa.cod_produto " +
                      "JOIN agendamento ON agendamento.data_hora = pa.cod_agendamento "
                     +"JOIN servico ON servico.codigo = agendamento.cod_servico"
                    + " WHERE cod_agendamento = '"+t+"' AND quantidade > 0";
        PreparedStatement ps;
        ResultSet rs;
        
        try {
            ps = conexao.Conexao.getConexao().prepareStatement(sql);
            rs = ps.executeQuery();
            
            while(rs.next()){
                
                Agendamento a = new Agendamento();
                Produto p = new Produto();
                ProdutoAgendamento pa = new ProdutoAgendamento();
                Servico s = new Servico();
                
                s.setPreco(rs.getDouble("precos"));
                a.setServico(s);
                p.setCodigo(rs.getInt("cod_produto"));
                p.setPreco(rs.getDouble("precop"));
                p.setNome(rs.getString("nome_produto"));
                
                a.setDataH(rs.getTimestamp("cod_agendamento"));
                
                pa.setAgendamento(a);
                pa.setProduto(p);
                pa.setQuantidades(rs.getInt("quantidade"));
//                c.setNome(rs.getString("nome"));
//                c.setCodigo(rs.getInt("cod_cliente"));
//                s.setNome(rs.getString("nome_servico"));
//                s.setCodigo(rs.getInt("cod_servico"));
//                a.setServico(s);
//                a.setCliente(c);
//                a.setDataH(rs.getTimestamp("data_hora"));
//                

                resultados.add(pa);
                
            }
            rs.close();
            ps.close();
            Collections.sort(resultados);
            return resultados;
            
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(ServicoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        
    }
     public List<ProdutoAgendamento> readP(String t) {
          //converter formato
        Date d =  new Date();
        SimpleDateFormat sd = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        SimpleDateFormat sd1 = new SimpleDateFormat("yyyy/MM/dd");//formato do banco ano mes dia
        SimpleDateFormat sd2 = new SimpleDateFormat("yyyy/MM/dd HH:mm");//formato do banco ano mes dia
        try {
            d = sd.parse(t);
            String a = sd2.format(d);
            t = a;
        } catch (ParseException ex) {
            Logger.getLogger(ProdutoAgendamentoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
               List<ProdutoAgendamento>  resultados = new ArrayList<>();  
        String sql = "SELECT nome_produto, produto.preco AS precop, servico.preco AS precos, cod_produto, cod_agendamento,cod_servico ,quantidade FROM produto_agendamento pa " +
                     "JOIN produto ON produto.codigo = pa.cod_produto " +
                      "JOIN agendamento ON agendamento.data_hora = pa.cod_agendamento "
                     +"JOIN servico ON servico.codigo = agendamento.cod_servico"
                    + " WHERE cod_agendamento = '"+t+"' AND quantidade > 0";
        PreparedStatement ps;
        ResultSet rs;
        
        try {
            ps = conexao.Conexao.getConexao().prepareStatement(sql);
            rs = ps.executeQuery();
            
            while(rs.next()){
                
                Agendamento a = new Agendamento();
                Produto p = new Produto();
                ProdutoAgendamento pa = new ProdutoAgendamento();
                Servico s = new Servico();
                
                s.setPreco(rs.getDouble("precos"));
                a.setServico(s);
                p.setCodigo(rs.getInt("cod_produto"));
                p.setPreco(rs.getDouble("precop"));
                p.setNome(rs.getString("nome_produto"));
                
                a.setDataH(rs.getTimestamp("cod_agendamento"));
                
                pa.setAgendamento(a);
                pa.setProduto(p);
                pa.setQuantidades(rs.getInt("quantidade"));
//                c.setNome(rs.getString("nome"));
//                c.setCodigo(rs.getInt("cod_cliente"));
//                s.setNome(rs.getString("nome_servico"));
//                s.setCodigo(rs.getInt("cod_servico"));
//                a.setServico(s);
//                a.setCliente(c);
//                a.setDataH(rs.getTimestamp("data_hora"));
//                

                resultados.add(pa);
                
            }
            rs.close();
            ps.close();
            Collections.sort(resultados);
            return resultados;
            
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(ServicoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        
    }
    public List<ProdutoAgendamento> read() {
          
        List<ProdutoAgendamento>  resultados = new ArrayList<>();  
        String sql = "SELECT nome_produto, preco, cod_produto, cod_agendamento, quantidade, cod_cliente, nome " +
                     "FROM produto_agendamento pa " +
                     "JOIN produto ON produto.codigo = pa.cod_produto " +
                     "JOIN agendamento ON agendamento.data_hora = pa.cod_agendamento " +
                     "JOIN cliente ON cliente.codigo = agendamento.cod_cliente";
        PreparedStatement ps;
        ResultSet rs;
        
        try {
            ps = conexao.Conexao.getConexao().prepareStatement(sql);
            rs = ps.executeQuery();
            
            while(rs.next()){
                
                Agendamento a = new Agendamento();
                Produto p = new Produto();
                ProdutoAgendamento pa = new ProdutoAgendamento();
                Servico s = new Servico();
                Cliente c = new Cliente();
                
                c.setNome(rs.getString("nome"));
                p.setCodigo(rs.getInt("cod_produto"));
                p.setPreco(rs.getDouble("preco"));
                p.setNome(rs.getString("nome_produto"));
                
                a.setDataH(rs.getTimestamp("cod_agendamento"));
                
                pa.setAgendamento(a);
                pa.setProduto(p);
                pa.setQuantidades(rs.getInt("quantidade"));
//                c.setNome(rs.getString("nome"));
//                c.setCodigo(rs.getInt("cod_cliente"));
//                s.setNome(rs.getString("nome_servico"));
//                s.setCodigo(rs.getInt("cod_servico"));
//                a.setServico(s);
//                a.setCliente(c);
//                a.setDataH(rs.getTimestamp("data_hora"));
//                

                resultados.add(pa);
                
            }
            rs.close();
            ps.close();
            Collections.sort(resultados);
            return resultados;
            
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(ServicoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
       
        
    }
    
    public boolean excluirProdutoAgendamento(Timestamp a) {
        String sql = "DELETE FROM produto_agendamento WHERE cod_agendamento = '"+a+"'";//"sintax padrão do SQL"
        try {
            PreparedStatement ps = conexao.Conexao.getConexao().prepareStatement(sql);
            ps.executeUpdate();
            return true;
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    
       }
    
       public boolean excluirProdutoAgendamentoE(ProdutoAgendamento pa) {
        String sql = "DELETE FROM produto_agendamento WHERE cod_agendamento = '"+pa.getAgendamento().getDataH()+"' AND cod_produto = ?";//"sintax padrão do SQL"
        try {
            PreparedStatement ps = conexao.Conexao.getConexao().prepareStatement(sql);
           // ps.setTimestamp(1, pa.getAgendamento().getDataH());
            ps.setInt(1, pa.getProduto().getCodigo());
            ps.executeUpdate();
            return true;
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    
       }
    
}
