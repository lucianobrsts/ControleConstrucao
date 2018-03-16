package br.com.construcao.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.com.construcao.domain.Fabricante;
import br.com.construcao.factory.ConexaoFactory;

public class FabricanteDAO {

	public void salvar(Fabricante f) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO fabricante ");
		sql.append("(nome, contato) ");
		sql.append("VALUES (?, ?)");

		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());

		comando.setString(1, f.getNome());
		comando.setString(2, f.getContato());

		comando.executeUpdate();
	}

	public void excluir(Fabricante f) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("DELETE FROM fabricante ");
		sql.append("WHERE idfabricante = ? ");

		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());

		comando.setLong(1, f.getIdFabricante());

		comando.executeUpdate();
	}

	public void editar(Fabricante f) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE fabricante ");
		sql.append("SET nome = ?, contato = ? ");
		sql.append("WHERE idfabricante = ? ");

		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setString(1, f.getNome());
		comando.setString(2, f.getContato());
		comando.setLong(3, f.getIdFabricante());

		comando.executeUpdate();
	}

	public Fabricante buscarPorId(Fabricante f) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT idfabricante, nome, contato ");
		sql.append(" FROM fabricante ");
		sql.append(" WHERE idfabricante = ? ");

		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setLong(1, f.getIdFabricante());

		ResultSet resultado = comando.executeQuery();

		Fabricante retorno = null;

		if (resultado.next()) {
			retorno = new Fabricante();
			retorno.setIdFabricante(resultado.getLong("idFabricante"));
			retorno.setNome(resultado.getString("nome"));
			retorno.setContato(resultado.getString("contato"));
		}

		return retorno;
	}

	public ArrayList<Fabricante> listar() throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * ");
		sql.append("FROM fabricante ");
		sql.append("ORDER BY nome ASC ");

		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());

		ResultSet resultado = comando.executeQuery();

		ArrayList<Fabricante> lista = new ArrayList<Fabricante>();

		while (resultado.next()) {
			Fabricante f = new Fabricante();
			f.setIdFabricante(resultado.getLong("idFabricante"));
			f.setNome(resultado.getString("nome"));
			f.setContato(resultado.getString("contato"));

			lista.add(f);
		}

		return lista;
	}

	public ArrayList<Fabricante> buscarPorNome(Fabricante f) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * ");
		sql.append("FROM fabricante ");
		sql.append("WHERE nome LIKE ? ");
		sql.append("ORDER BY nome ASC ");

		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setString(1, "%" + f.getNome() + "%");

		ResultSet resultado = comando.executeQuery();

		ArrayList<Fabricante> lista = new ArrayList<Fabricante>();

		while (resultado.next()) {
			Fabricante item = new Fabricante();
			item.setIdFabricante(resultado.getLong("idFabricante"));
			item.setNome(resultado.getString("nome"));
			item.setContato(resultado.getString("contato"));

			lista.add(item);
		}

		return lista;
	}

}
