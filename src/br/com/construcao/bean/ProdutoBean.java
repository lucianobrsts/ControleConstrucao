package br.com.construcao.bean;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.construcao.dao.FabricanteDAO;
import br.com.construcao.dao.ProdutoDAO;
import br.com.construcao.domain.Fabricante;
import br.com.construcao.domain.Produto;
import br.com.construcao.util.JSFUtil;

@SuppressWarnings("deprecation")
@ManagedBean(name = "MBProduto")
@ViewScoped
public class ProdutoBean {
	private ArrayList<Produto> itens;
	private ArrayList<Produto> itensFiltrados;

	private Produto produto;
	private ArrayList<Fabricante> comboFabricantes;

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public ArrayList<Fabricante> getComboFabricantes() {
		return comboFabricantes;
	}

	public void setComboFabricantes(ArrayList<Fabricante> comboFabricantes) {
		this.comboFabricantes = comboFabricantes;
	}

	public ArrayList<Produto> getItensFiltrados() {
		return itensFiltrados;
	}

	public void setItensFiltrados(ArrayList<Produto> itensFiltrados) {
		this.itensFiltrados = itensFiltrados;
	}

	public ArrayList<Produto> getItens() {
		return itens;
	}

	public void setItens(ArrayList<Produto> itens) {
		this.itens = itens;
	}

	public void carregarListagem() {
		try {
			ProdutoDAO dao = new ProdutoDAO();
			itens = dao.listar();
		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}
	}

	public void prepararNovo() {
		try {
			produto = new Produto();

			FabricanteDAO dao = new FabricanteDAO();

			comboFabricantes = dao.listar();
		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}
	}

	public void novo() {
		try {
			ProdutoDAO dao = new ProdutoDAO();
			dao.salvar(produto);

			itens = dao.listar();

			JSFUtil.adicionarMensagemSucesso("Produto salvo com sucesso...");
		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}
	}

	public void excluir() {
		try {
			ProdutoDAO dao = new ProdutoDAO();

			dao.excluir(produto);

			itens = dao.listar();

			JSFUtil.adicionarMensagemSucesso("Produto Excluído com sucesso...");
		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}
	}

	public void prepararEditar() {
		try {
			FabricanteDAO dao = new FabricanteDAO();

			comboFabricantes = dao.listar();
		} catch (Exception e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}
	}

	public void editar() {
		try {
			ProdutoDAO dao = new ProdutoDAO();

			dao.editar(produto);

			itens = dao.listar();

			JSFUtil.adicionarMensagemSucesso("Produto editado com sucesso...");
		} catch (Exception e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}
	}
}
