package br.com.construcao.bean;

import java.sql.SQLException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.construcao.dao.ProdutoDAO;
import br.com.construcao.domain.Produto;
import br.com.construcao.util.JSFUtil;

@SuppressWarnings("deprecation")
@ManagedBean(name = "MBVendas")
@ViewScoped
public class VendaBean {

	private List<Produto> produtos;

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
