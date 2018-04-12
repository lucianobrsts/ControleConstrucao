package br.com.construcao.bean;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.construcao.dao.UsuarioDAO;
import br.com.construcao.domain.Usuario;
import br.com.construcao.util.JSFUtil;

@ManagedBean(name = "MBUsuario")
@ViewScoped
public class UsuarioBean {
	private Usuario usuario;
	private ArrayList<Usuario> itens;
	private ArrayList<Usuario> itensFiltrados;

	public ArrayList<Usuario> getItens() {
		return itens;
	}

	public void setItens(ArrayList<Usuario> itens) {
		this.itens = itens;
	}

	public ArrayList<Usuario> getItensFiltrados() {
		return itensFiltrados;
	}

	public void setItensFiltrados(ArrayList<Usuario> itensFiltrados) {
		this.itensFiltrados = itensFiltrados;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public void carregarListagem() {
		try {
			UsuarioDAO dao = new UsuarioDAO();
			itens = dao.listar();
		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}
	}

	public void prepararNovo() {
		usuario = new Usuario();
	}

	public void novo() {
		try {
			UsuarioDAO dao = new UsuarioDAO();
			dao.salvar(usuario);

			itens = dao.listar();

			JSFUtil.adicionarMensagemSucesso("Usuário salvo com sucesso...");
		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}
	}

	public void excluir() {
		try {
			UsuarioDAO dao = new UsuarioDAO();

			dao.excluir(usuario);

			itens = dao.listar();

			JSFUtil.adicionarMensagemSucesso("Usuário Excluído com sucesso...");
		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}
	}

	public void editar() {
		try {
			UsuarioDAO dao = new UsuarioDAO();

			dao.editar(usuario);

			itens = dao.listar();

			JSFUtil.adicionarMensagemSucesso("Usuário editado com sucesso...");
		} catch (Exception e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}
	}

}
