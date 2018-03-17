package br.com.construcao.test;

import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.Ignore;
import org.junit.Test;

import br.com.construcao.dao.UsuarioDAO;
import br.com.construcao.domain.Usuario;

public class UsuarioDAOTest {

	@Test
	@Ignore
	public void salvar() throws SQLException {
		Usuario u = new Usuario();
		UsuarioDAO dao = new UsuarioDAO();

		u.setNome("Luciano Brito");
		u.setSenha("123456");

		dao.salvar(u);
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

		for (Usuario u : lista) {
			System.out.println("Codigo: " + u.getIdUsuario());
			System.out.println("Nome: " + u.getNome());
			System.out.println("Valor: " + u.getSenha());
			System.out.println();
		}
	}

	@Test
	@Ignore
	public void editar() throws SQLException {
		Usuario u = new Usuario();
		
		u.setNome("Luciano Brito");
		u.setSenha("testeSenha");
		u.setIdUsuario(1L);
		
		UsuarioDAO dao = new UsuarioDAO();
		dao.editar(u);
	}
	
}
