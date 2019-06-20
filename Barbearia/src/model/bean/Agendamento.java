/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.bean;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

/**
 *
 * @author Gabriel Missio
 */
public class Agendamento {

    public String getData() {
        return data;
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
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat dateFormat1 = new SimpleDateFormat("dd/MM/yyyy");
        
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
    
}
