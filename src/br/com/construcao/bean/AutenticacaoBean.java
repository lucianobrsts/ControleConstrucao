package br.com.construcao.bean;

import java.io.IOException;
import java.sql.SQLException;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;

import br.com.construcao.dao.UsuarioDAO;
import br.com.construcao.domain.Usuario;

@SuppressWarnings("deprecation")
@ManagedBean(name = "MBAutenticar")
@SessionScoped
public class AutenticacaoBean {

	private Usuario usuarioLogado;

	public Usuario getUsuarioLogado() {
		if (usuarioLogado == null) {
			usuarioLogado = new Usuario();
		}

		return usuarioLogado;
	}

	public void setUsuarioLogado(Usuario usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}

	/*
	 * public void autenticar() { try { UsuarioDAO dao = new UsuarioDAO();
	 * usuarioLogado = dao.autenticar(usuarioLogado);
	 * 
	 * if (usuarioLogado == null) {
	 * JSFUtil.adicionarMensagemErro("Nome e/ou senha inválidos!"); } else {
	 * JSFUtil.adicionarMensagemSucesso("Usuário autenticado com sucesso!"); } }
	 * catch (RuntimeException e) {
	 * JSFUtil.adicionarMensagemErro("Erro ao tentar autenticar no sistema!");
	 * e.getMessage();
	 * 
	 * } catch (SQLException e) { e.printStackTrace(); } }
	 */

	public void autenticar() {
		try {
			UsuarioDAO dao = new UsuarioDAO();
			Usuario usuarioLogadoAplicacao = dao.autenticar(usuarioLogado.getNome(), usuarioLogado.getSenha());
			if (usuarioLogadoAplicacao == null) {
				Messages.addGlobalError("Usuário e/ou senha incorretos!");
				return;
			}

			Faces.redirect("./pages/principal.xhtml");
		} catch (IOException e) {
			e.printStackTrace();
			Messages.addGlobalError(e.getMessage());
		} catch (SQLException e) {
			e.printStackTrace();
			Messages.addGlobalError(e.getMessage());
		}
	}

	public String sair() {

		usuarioLogado = null;

		return "/pages/principal.xhtml?faces-redirect=true";
	}

}
