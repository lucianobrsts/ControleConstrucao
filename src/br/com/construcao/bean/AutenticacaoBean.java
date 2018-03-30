package br.com.construcao.bean;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;

import br.com.construcao.dao.UsuarioDAO;
import br.com.construcao.domain.Usuario;

@ManagedBean(name = "MBAutenticar")
@SessionScoped
public class AutenticacaoBean {

	private Usuario usuario;
	private Usuario usuarioLogado;

	public Usuario getUsuarioLogado() {
		return usuarioLogado;
	}

	public void setUsuarioLogado(Usuario usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@PostConstruct
	public void iniciar() {
		usuario = new Usuario();
	}

	public void autenticar() {
		try {
			UsuarioDAO dao = new UsuarioDAO();

			usuarioLogado = dao.autenticar(usuario.getNome(), usuario.getSenha(), usuario.getTipo());

			if (usuarioLogado == null) {
				Messages.addGlobalError("Usuário e/ou senha incorretos!");
				return;
			}

			Faces.redirect("faces/pages/principal.xhtml");
		} catch (IOException e) {
			e.printStackTrace();
			Messages.addGlobalError(e.getMessage());
		} catch (SQLException e) {
			e.printStackTrace();
			Messages.addGlobalError(e.getMessage());
		}
	}

	public boolean temPermissoes(List<String> permissoes) {
		for (String permissao : permissoes) {
			if (usuarioLogado.getTipo().equals(permissao)) {
				return true;
			}
		}
		return false;
	}

	public void sair() {
		usuarioLogado = null;
	}

}
