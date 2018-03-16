package br.com.construcao.domain;

public class Produto {

	private Long idProduto;
	private String nome;
	private Long quantidade;
	private Double valor;
	private Fabricante fabricante  = new Fabricante();

	public Produto() {

	}

	public Produto(Long idProduto, String nome, Long quantidade, double valor, Fabricante fabricante) {
		this.idProduto = idProduto;
		this.nome = nome;
		this.quantidade = quantidade;
		this.valor = valor;
		this.fabricante = fabricante;
	}

	public Long getIdProduto() {
		return idProduto;
	}

	public void setIdProduto(Long idProduto) {
		this.idProduto = idProduto;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Long getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Long quantidade) {
		this.quantidade = quantidade;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public Fabricante getFabricante() {
		return fabricante;
	}

	public void setFabricante(Fabricante fabricante) {
		this.fabricante = fabricante;
	}

}
