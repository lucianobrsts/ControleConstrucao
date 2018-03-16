package br.com.construcao.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.com.construcao.domain.Usuario;
import br.com.construcao.factory.ConexaoFactory;

public class UsuarioDAO {

	public void salvar(Usuario u) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO usuario ");
		sql.append("(nome, senha) ");
		sql.append("VALUES (?, ?)");

		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());

		comando.setString(1, u.getNome());
		comando.setString(2, u.getSenha());

		comando.executeUpdate();
	}

	public void excluir(Usuario u) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("DELETE FROM usuario ");
		sql.append("WHERE idUsuario = ? ");

		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());

		comando.setLong(1, u.getIdUsuario());

		comando.executeUpdate();
	}

	public ArrayList<Usuario> listar() throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * ");
		sql.append("FROM usuario ");
		sql.append("ORDER BY nome ASC ");

		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());

		ResultSet resultado = comando.executeQuery();

		ArrayList<Usuario> lista = new ArrayList<Usuario>();

		while (resultado.next()) {
			Usuario u = new Usuario();
			u.setIdUsuario(resultado.getLong("idUsuario"));
			u.setNome(resultado.getString("nome"));
			u.setSenha(resultado.getString("senha"));

			lista.add(u);
		}

		return lista;
	}
	
	public void editar(Usuario u) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE usuario ");
		sql.append("SET nome = ?, senha = ? ");
		sql.append("WHERE idUsuario = ? ");

		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setString(1, u.getNome());
		comando.setString(2, u.getSenha());
		comando.setLong(3, u.getIdUsuario());

		comando.executeUpdate();
	}

}
