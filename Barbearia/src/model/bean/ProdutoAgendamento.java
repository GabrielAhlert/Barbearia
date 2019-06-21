/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.bean;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Vera
 */
public class ProdutoAgendamento implements Comparable{
    //instanciar objetos da e transformalos em chave primeria e estrangeira
  Produto produto;
  Agendamento agendamento;
  int quantidades;

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Agendamento getAgendamento() {
        return agendamento;
    }

    public void setAgendamento(Agendamento agendamento) {
        this.agendamento = agendamento;
    }

    public int getQuantidades() {
        return quantidades;
    }

    public void setQuantidades(int quantidades) {
        this.quantidades = quantidades;
    }

    @Override
    public int compareTo(Object o) {
        ProdutoAgendamento pa = (ProdutoAgendamento) o;
        return this.getAgendamento().getDataH().compareTo(pa.getAgendamento().getDataH());
    }
    public double getValorTotal(){
        return this.quantidades * this.produto.getPreco();
    }
    
    public String toString() {
        Date d =  new Date();
        SimpleDateFormat sd = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat sd1 = new SimpleDateFormat("dd/MM/yyyy HH:mm");//formato do banco ano mes dia
        d = this.agendamento.getDataH();
       
        return "("+this.quantidades+"x) "+this.produto.getNome()+" "+String.valueOf(sd1.format(d)) ;
    }
    
}
