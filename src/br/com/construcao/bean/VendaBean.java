package br.com.construcao.bean;

import java.sql.SQLException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.construcao.dao.ProdutoDAO;
import br.com.construcao.domain.ItemVenda;
import br.com.construcao.domain.Produto;
import br.com.construcao.util.JSFUtil;

@ManagedBean(name = "MBVendas")
@ViewScoped
public class VendaBean {

	private List<Produto> produtos;
	private List<ItemVenda> itensVenda;

	public List<ItemVenda> getItensVenda() {
		return itensVenda;
	}

	public void setItensVenda(List<ItemVenda> itensVenda) {
		this.itensVenda = itensVenda;
	}

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

	@PostConstruct
	public void carregarListagem() {
		try {
			ProdutoDAO dao = new ProdutoDAO();
			produtos = dao.listar();
		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}
	}

}
