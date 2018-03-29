package br.com.construcao.domain;

public class Fabricante {

	private Long idFabricante;
	private String nome;
	private String celular;
	private String fixo;

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

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getFixo() {
		return fixo;
	}

	public void setFixo(String fixo) {
		this.fixo = fixo;
	}

	@Override
	public String toString() {
		String saida = this.idFabricante + " - " + this.nome + " - " + this.celular + " - " + this.fixo;
		return saida;
	}

}
