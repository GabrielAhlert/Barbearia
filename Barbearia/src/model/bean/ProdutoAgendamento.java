/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.bean;

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
    
}
