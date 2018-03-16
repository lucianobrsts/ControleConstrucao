package br.com.construcao.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.com.construcao.domain.Fabricante;
import br.com.construcao.domain.Produto;
import br.com.construcao.factory.ConexaoFactory;

public class ProdutoDAO {

	public void salvar(Produto p) throws SQLException {
		StringBuilder sql = new StringBuilder();

		sql.append("INSERT INTO produto ");
		sql.append("(nome, quantidade, valor, idfabricante) ");
		sql.append("VALUES (?, ?, ?, ?) ");

		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setString(1, p.getNome());
		comando.setLong(2, p.getQuantidade());
		comando.setDouble(3, p.getValor());
		comando.setLong(4, p.getFabricante().getIdFabricante());

		comando.executeUpdate();

	}

	public ArrayList<Produto> listar() throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT p.idproduto, p.nome, p.valor, p.quantidade, f.idfabricante, f.nome ");
		sql.append("FROM produto p ");
		sql.append("INNER JOIN fabricante f ON f.idfabricante = p.idfabricante ");

		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());

		ResultSet resultado = comando.executeQuery();

		ArrayList<Produto> itens = new ArrayList<Produto>();

		while (resultado.next()) {
			Fabricante f = new Fabricante();
			f.setIdFabricante(resultado.getLong("f.idfabricante"));
			f.setNome(resultado.getString("f.nome"));

			Produto p = new Produto();
			p.setIdProduto(resultado.getLong("idProduto"));
			p.setNome(resultado.getString("nome"));
			p.setValor(resultado.getDouble("valor"));
			p.setQuantidade(resultado.getLong("quantidade"));
			p.setFabricante(f);

			itens.add(p);
		}
		return itens;
	}

	public void excluir(Produto p) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("DELETE FROM produto ");
		sql.append("WHERE idproduto = ? ");

		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());

		comando.setLong(1, p.getIdProduto());

		comando.executeUpdate();

	}

	public void editar(Produto p) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE produto ");
		sql.append("SET nome = ?, valor = ?, quantidade = ?, idfabricante = ? ");
		sql.append("WHERE idproduto = ? ");

		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());

		comando.setString(1, p.getNome());
		comando.setDouble(2, p.getValor());
		comando.setLong(3, p.getQuantidade());
		comando.setLong(4, p.getFabricante().getIdFabricante());
		comando.setLong(5, p.getIdProduto());

		comando.executeUpdate();
	}

}
