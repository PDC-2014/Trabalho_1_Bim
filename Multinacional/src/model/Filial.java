package model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Filial implements Serializable{
	
    private String nome;
    private String codigo;
    private int porta;
    
    public Filial() {
        super();
    }
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
    
    public int getPorta() {
		return porta;
	}

	public void setPorta(int porta) {
		this.porta = porta;
	}

	@Override
    public String toString() {
        return nome;
    }
}
