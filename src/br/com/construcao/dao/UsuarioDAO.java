package br.com.construcao.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.com.construcao.domain.Usuario;
import br.com.construcao.factory.ConexaoFactory;

public class UsuarioDAO {

	public void salvar(Usuario user) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO usuario ");
		sql.append("(nome, senha, tipo) ");
		sql.append("VALUES (?, ?, ?)");

		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());

		comando.setString(1, user.getNome());
		comando.setString(2, user.getSenha());
		comando.setString(3, user.getTipo());

		comando.executeUpdate();
	}

	public void excluir(Usuario user) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("DELETE FROM usuario ");
		sql.append("WHERE idUsuario = ? ");

		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());

		comando.setLong(1, user.getIdUsuario());

		comando.executeUpdate();
	}

	public ArrayList<Usuario> listar() throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT idUsuario, nome, senha, tipo ");
		sql.append("FROM usuario ");
		sql.append("ORDER BY nome ASC ");

		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());

		ResultSet resultado = comando.executeQuery();

		ArrayList<Usuario> lista = new ArrayList<Usuario>();

		while (resultado.next()) {
			Usuario user = new Usuario();
			user.setIdUsuario(resultado.getLong("idUsuario"));
			user.setNome(resultado.getString("nome"));
			user.setSenha(resultado.getString("senha"));
			user.setTipo(resultado.getString("tipo"));

			lista.add(user);
		}

		return lista;
	}

	public void editar(Usuario user) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE usuario ");
		sql.append("SET nome = ?, senha = ?, tipo = ? ");
		sql.append("WHERE idUsuario = ? ");

		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setString(1, user.getNome());
		comando.setString(2, user.getSenha());
		comando.setString(3, user.getTipo());
		comando.setLong(4, user.getIdUsuario());

		comando.executeUpdate();
	}

	public Usuario buscarPorCodigo(Usuario user) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT idUsuario, nome, senha, tipo ");
		sql.append("FROM usuario ");
		sql.append("WHERE idUsuario = ? ");

		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());

		comando.setLong(1, user.getIdUsuario());

		ResultSet resultado = comando.executeQuery();

		Usuario retorno = null;

		if (resultado.next()) {
			retorno = new Usuario();
			retorno.setIdUsuario(resultado.getLong("idUsuario"));
			retorno.setNome(resultado.getString("nome"));
			retorno.setSenha(resultado.getString("senha"));
			retorno.setTipo(resultado.getString("tipo"));
		}
		return retorno;
	}

	public ArrayList<Usuario> buscarPorNome(Usuario user) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * ");
		sql.append("FROM usuario ");
		sql.append("WHERE nome LIKE ? ");
		sql.append("ORDER BY nome ASC ");

		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());

		comando.setString(1, "%" + user.getNome() + "%");

		ResultSet resultado = comando.executeQuery();

		ArrayList<Usuario> lista = new ArrayList<Usuario>();

		while (resultado.next()) {
			Usuario item = new Usuario();
			item.setIdUsuario(resultado.getLong("IdUsuario"));
			item.setNome(resultado.getString("nome"));
			item.setSenha(resultado.getString("senha"));
			item.setTipo(resultado.getString("tipo"));

			lista.add(item);
		}

		return lista;

	}

	public Usuario autenticar(String nome, String senha) throws SQLException {
		Usuario usuario = null;
		String sql = "SELECT * FROM usuario WHERE nome = ? AND senha = ?";

		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql);

		comando.setObject(1, nome);
		comando.setObject(2, senha);

		ResultSet resultado = comando.executeQuery();

		if (resultado.next()) {
			usuario = new Usuario();
			usuario.setNome(resultado.getString("nome"));
			usuario.setSenha(resultado.getString("senha"));
		}

		return usuario;
	}

}
