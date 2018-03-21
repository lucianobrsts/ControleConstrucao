package br.com.construcao.bean;

import java.sql.SQLException;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.construcao.dao.UsuarioDAO;
import br.com.construcao.domain.Usuario;
import br.com.construcao.util.JSFUtil;

@SuppressWarnings("deprecation")
@ManagedBean(name = "MBAutenticar")
@ViewScoped
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

	public void autenticar() {
		try {
			UsuarioDAO dao = new UsuarioDAO();
			usuarioLogado = dao.autenticar(usuarioLogado);

			if (usuarioLogado == null) {
				JSFUtil.adicionarMensagemErro("Nome e/ou senha inválidos!");
			} else {
				JSFUtil.adicionarMensagemSucesso("Usuário autenticado com sucesso!");
			}
		} catch (RuntimeException e) {
			JSFUtil.adicionarMensagemErro("Erro ao tentar autenticar no sistema!");
			e.getMessage();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public String sair() {

		usuarioLogado = null;

		return "/pages/principal.xhtml?faces-redirect=true";
	}

}
