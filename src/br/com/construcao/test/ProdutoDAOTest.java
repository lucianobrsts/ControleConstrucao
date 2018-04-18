package br.com.construcao.test;

import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.Ignore;
import org.junit.Test;

import br.com.construcao.dao.ProdutoDAO;
import br.com.construcao.domain.Fabricante;
import br.com.construcao.domain.Produto;

public class ProdutoDAOTest {

	@Test
	@Ignore
	public void salvar() throws SQLException {
		Fabricante f = new Fabricante();
		f.setIdFabricante(3L);

		Produto p = new Produto();
		p.setNome("Fio 2'");
		p.setQuantidade(30L);
		p.setValor(2.50);
		p.setFabricante(f);

		ProdutoDAO dao = new ProdutoDAO();
		dao.salvar(p);
	}

	@Test
	@Ignore
	public void listar() throws SQLException {
		ProdutoDAO dao = new ProdutoDAO();
		ArrayList<Produto> lista = dao.listar();

		for (Produto p : lista) {
			System.out.println("Codigo: " + p.getIdProduto());
			System.out.println("Nome: " + p.getNome());
			System.out.println("Valor: " + p.getValor());
			System.out.println("Quantidade: " + p.getQuantidade());
			System.out.println("Codigo do Fabricante: " + p.getFabricante().getIdFabricante());
			System.out.println("Nome do Fabricante: " + p.getFabricante().getNome());
			System.out.println();
		}
	}

	@Test
	@Ignore
	public void excluir() throws SQLException {
		Produto p = new Produto();
		p.setIdProduto(3L);

		ProdutoDAO dao = new ProdutoDAO();
		dao.excluir(p);
	}

	@Test
	@Ignore
	public void editar() throws SQLException {
		Produto p = new Produto();
		p.setIdProduto(1L);
		p.setNome("Verniz vitral");
		p.setValor(15.00);
		p.setQuantidade(12L);

		Fabricante f = new Fabricante();
		f.setIdFabricante(7L);

		p.setFabricante(f);

		ProdutoDAO dao = new ProdutoDAO();
		dao.editar(p);
	}
}
