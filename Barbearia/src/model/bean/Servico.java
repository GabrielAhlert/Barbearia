/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.bean;

/**
 *
 * @author Gabriel Missio
 */
public class Servico implements Comparable{
    
    private Integer codigo;
    private String nome;
    private double preco;

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    @Override
    public int compareTo(Object o) {
        Servico s = (Servico) o;
        return this.codigo.compareTo(s.getCodigo());//To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String toString() {
        return this.getNome();
    }

    

    
}
