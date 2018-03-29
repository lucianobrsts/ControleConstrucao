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
		sql.append("(nome, celular, fixo) ");
		sql.append("VALUES (?, ?, ?)");

		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());

		comando.setString(1, f.getNome());
		comando.setString(2, f.getCelular());
		comando.setString(3, f.getFixo());

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
		sql.append("SET nome = ?, celular = ?, fixo = ? ");
		sql.append("WHERE idfabricante = ? ");

		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setString(1, f.getNome());
		comando.setString(2, f.getCelular());
		comando.setString(3, f.getFixo());
		comando.setLong(4, f.getIdFabricante());

		comando.executeUpdate();
	}

	public Fabricante buscarPorId(Fabricante f) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT idfabricante, nome, celular, fixo ");
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
			retorno.setCelular(resultado.getString("celular"));
			retorno.setFixo(resultado.getString("fixo"));
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
			f.setCelular(resultado.getString("celular"));
			f.setFixo(resultado.getString("fixo"));

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
			item.setCelular(resultado.getString("celular"));
			item.setFixo(resultado.getString("fixo"));

			lista.add(item);
		}

		return lista;
	}

}
