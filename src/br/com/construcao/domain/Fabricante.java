package br.com.construcao.domain;

public class Fabricante {

	private Long idFabricante;
	private String nome;
	private String contato;

	public Fabricante() {

	}

	public Fabricante(Long idFabricante, String nome, String contato) {
		this.idFabricante = idFabricante;
		this.nome = nome;
		this.contato = contato;
	}

	public Long getIdFabricante() {
		return idFabricante;
	}

	public void setIdFabricante(Long idFabricante) {
		this.idFabricante = idFabricante;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getContato() {
		return contato;
	}

	public void setContato(String contato) {
		this.contato = contato;
	}
	
	@Override
	public String toString() {
		String saida = this.idFabricante + " - " + this.nome + " - " + this.contato;
		return saida;
	}

}
