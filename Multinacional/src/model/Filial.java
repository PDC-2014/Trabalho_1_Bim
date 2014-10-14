package model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Filial implements Serializable {

	private String codigo;
	private String nome;
	private int porta;

	public Filial() {
		super();
	}

	public Filial(String codigo, String nome, int porta) {
		super();
		this.codigo = codigo;
		this.nome = nome;
		this.porta = porta;
	}

	public String getCodigo() {
		return codigo;
	}
	
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
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
