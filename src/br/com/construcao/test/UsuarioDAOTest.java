package br.com.construcao.test;

import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import br.com.construcao.dao.UsuarioDAO;
import br.com.construcao.domain.Usuario;

public class UsuarioDAOTest {

	@Test
	@Ignore
	public void salvar() throws SQLException {
		Usuario user = new Usuario();
		UsuarioDAO dao = new UsuarioDAO();

		user.setNome("Teste 8");
		user.setSenha("Testando");
		user.setTipo("Administrador");

		dao.salvar(user);
	}

	@Test
	@Ignore
	public void excluir() throws SQLException {
		Usuario u = new Usuario();
		u.setIdUsuario(2L);

		UsuarioDAO dao = new UsuarioDAO();
		dao.excluir(u);

	}

	@Test
	@Ignore
	public void listar() throws SQLException {
		UsuarioDAO dao = new UsuarioDAO();
		ArrayList<Usuario> lista = dao.listar();

		for (Usuario user : lista) {
			System.out.println("Codigo: " + user.getIdUsuario());
			System.out.println("Nome: " + user.getNome());
			System.out.println("Valor: " + user.getSenha());
			System.out.println("Tipo: " + user.getTipo());
			System.out.println();
		}
	}

	@Test
	@Ignore
	public void editar() throws SQLException {
		Usuario user = new Usuario();

		user.setNome("Roberto Kennedy");
		user.setSenha("teste");
		user.setTipo("Administrador");
		user.setIdUsuario(7L);

		UsuarioDAO dao = new UsuarioDAO();
		dao.editar(user);
	}
	
	@Test
	public void autenticar() throws SQLException {
		Usuario user = new UsuarioDAO().autenticar("Luciano Brito", "testeSenha");
		
		//System.out.println("Usuário: " + user);
		
		Assert.assertNotNull(user);
	}

}
