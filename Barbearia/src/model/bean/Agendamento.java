/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.bean;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import view.agendamento.AdicionarAgendamento;

/**
 *
 * @author Gabriel Missio
 */
public class Agendamento implements Comparable {

    public String getData() {
        return this.data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Servico getServico() {
        return servico;
    }

    public void setServico(Servico servico) {
        this.servico = servico;
    }
   
    public String getDataHora(){
        
        return this.data + " " + this.hora;
        
    }
    
    public Timestamp getDataH(){
        return this.data_hora;
    }
    
    public void setDataH(Timestamp d){
        data_hora = d;
    }
    
    public void setDataHora(String d, String h){
        this.hora = h;
        this.data = d;
    }
    
    private String data;
    private String hora;
    private double valorTotal = 0;
    private Cliente cliente;
    private Servico servico;
    private Timestamp data_hora;
    private Integer status;

    public Timestamp getData_hora() {
        return data_hora;
    }

    public void setData_hora(Timestamp data_hora) {
        this.data_hora = data_hora;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        Date d =  new Date();
        SimpleDateFormat sd = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat sd1 = new SimpleDateFormat("dd/MM/yyyy HH:mm");//formato do banco ano mes dia
        d = this.data_hora;
       
        return String.valueOf(sd1.format(d)) ;
    }
    public String getData_h(){
        Date d =  new Date();
        SimpleDateFormat sd = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat sd1 = new SimpleDateFormat("yyyy/MM/dd HH:mm");//formato do banco ano mes dia
        d = this.data_hora;
       
        return String.valueOf(sd1.format(d)) ;
    }


    @Override
    public int compareTo(Object o) {
        Agendamento a = (Agendamento) o;
        return this.data_hora.compareTo(a.getDataH());
    }
    
    
    
}
