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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.bean.Agendamento;
import model.bean.Cliente;
import model.bean.Servico;

/**
 *
 * @author Gabriel Missio
 */
public class AgendamentoDAO {
    
    public boolean updateStatus(String d,int i) {
        String sql = "UPDATE agendamento SET status = "+i+" WHERE data_hora = '"+d+"'";//"sintax padrão do SQL"
        try {
            PreparedStatement ps = conexao.Conexao.getConexao().prepareStatement(sql);
            ps.executeUpdate();
            return true;
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }
    
    public boolean inserirAgendamento(Agendamento a) {
        String sql = "INSERT INTO agendamento (data_hora, valor_total, cod_cliente, cod_servico, status) VALUES ('"+a.getDataHora() +"', ?, ?,?,1)";//"sintax padrão do SQL"
        try {
            PreparedStatement ps = conexao.Conexao.getConexao().prepareStatement(sql);
        //    Timestamp t = (Timestamp) a.getDataHora();
        
            //ps.setTimestamp(1, Timestamp.valueOf(a.getDataHora()));
            
            ps.setDouble(1, a.getServico().getPreco());
            ps.setInt(2, a.getCliente().getCodigo());
            ps.setInt(3, a.getServico().getCodigo());
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
    
    public List<Agendamento> read() {
        SimpleDateFormat sd = new SimpleDateFormat("yyyy/MM/dd");
        Date dataAtual = new Date(System.currentTimeMillis());
        String data = sd.format(dataAtual);  
        
        List<Agendamento>  resultados = new ArrayList<>();  
        String sql = "SELECT data_hora, valor_total, cod_servico, status, " +
                    "cod_cliente, nome, preco, nome_servico FROM agendamento JOIN cliente ON cliente.codigo = agendamento.cod_cliente " +
                    "JOIN servico ON servico.codigo = cod_servico WHERE data_hora >= '"+data+"'";
        PreparedStatement ps;
        ResultSet rs;
        
        try {
            ps = conexao.Conexao.getConexao().prepareStatement(sql);
            rs = ps.executeQuery();
            
            while(rs.next()){
                
                Agendamento a = new Agendamento();
                Servico s = new Servico();
                Cliente c = new Cliente();
                c.setNome(rs.getString("nome"));
                c.setCodigo(rs.getInt("cod_cliente"));
                s.setNome(rs.getString("nome_servico"));
                s.setCodigo(rs.getInt("cod_servico"));
                s.setPreco(rs.getDouble("preco"));
                a.setServico(s);
                a.setCliente(c);
                a.setDataH(rs.getTimestamp("data_hora"));
                a.setStatus(rs.getInt("status"));
                
//                //nome da tabela do banco de dados
//                a.setDataH(rs.getString("data_hora"));
//               
////                c.setCodigo(rs.getInt("codigo"));
////                c.setNome(rs.getString("nome"));
////                c.setCelular(rs.getString("telefone"));
////                c.setEmail(rs.getString("email"));
                resultados.add(a);
                
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
    
    public List<Agendamento> readSelec(String t, String t1) {
          
        List<Agendamento>  resultados = new ArrayList<>();  
        String sql = "SELECT data_hora, valor_total, cod_servico, status, " +
                    "cod_cliente, nome, preco, nome_servico FROM agendamento JOIN cliente ON cliente.codigo = agendamento.cod_cliente " +
                    "JOIN servico ON servico.codigo = cod_servico WHERE data_hora BETWEEN '"+t+" 00:00' AND '"+t1+" 23:59'";
        PreparedStatement ps;
        ResultSet rs;
        
        try {
            ps = conexao.Conexao.getConexao().prepareStatement(sql);
            rs = ps.executeQuery();
            
            while(rs.next()){
                
                Agendamento a = new Agendamento();
                Servico s = new Servico();
                Cliente c = new Cliente();
                c.setNome(rs.getString("nome"));
                c.setCodigo(rs.getInt("cod_cliente"));
                s.setNome(rs.getString("nome_servico"));
                s.setCodigo(rs.getInt("cod_servico"));
                s.setPreco(rs.getDouble("preco"));
                a.setServico(s);
                a.setCliente(c);
                a.setDataH(rs.getTimestamp("data_hora"));
                a.setStatus(rs.getInt("status"));
                
//                //nome da tabela do banco de dados
//                a.setDataH(rs.getString("data_hora"));
//               
////                c.setCodigo(rs.getInt("codigo"));
////                c.setNome(rs.getString("nome"));
////                c.setCelular(rs.getString("telefone"));
////                c.setEmail(rs.getString("email"));
                resultados.add(a);
                
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
    
    public boolean excluirAgendamento(Agendamento c) {
        String sql = "DELETE FROM agendamento WHERE data_hora = ?";//"sintax padrão do SQL"
        try {
            PreparedStatement ps = conexao.Conexao.getConexao().prepareStatement(sql);
            ps.setTimestamp(1, c.getDataH());
            ps.executeUpdate();
            return true;
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }
     
    
}
