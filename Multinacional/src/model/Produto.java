package model;

import java.io.Serializable;

public class Produto implements Serializable{
	
    private Integer id;
    private String nome;
    private Integer quantidadeDisponivel;
    private Double peso;
    private Double precoUnitario;
    private Filial filial;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getQuantidadeDisp() {
        return quantidadeDisponivel;
    }

    public void setQuantidadeDisp(Integer quantidadeDisp) {
        this.quantidadeDisponivel = quantidadeDisp;
    }

    public Double getPeso() {
        return peso;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }

    public Double getPrecoUnitario() {
        return precoUnitario;
    }

    public void setPrecoUnitario(Double precoUnitario) {
        this.precoUnitario = precoUnitario;
    }

    public Filial getFilial() {
        return filial;
    }

    public void setFilial(Filial filial) {
        this.filial = filial;
    }
}
