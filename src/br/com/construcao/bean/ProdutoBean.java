package br.com.construcao.bean;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import br.com.construcao.dao.FabricanteDAO;
import br.com.construcao.dao.ProdutoDAO;
import br.com.construcao.domain.Fabricante;
import br.com.construcao.domain.Produto;
import br.com.construcao.util.JSFUtil;

@ManagedBean(name = "MBProduto")
@ViewScoped
public class ProdutoBean {
	private ArrayList<Produto> itens;
	private ArrayList<Produto> itensFiltrados;

	private Produto produto;
	private ArrayList<Fabricante> comboFabricantes;

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public ArrayList<Fabricante> getComboFabricantes() {
		return comboFabricantes;
	}

	public void setComboFabricantes(ArrayList<Fabricante> comboFabricantes) {
		this.comboFabricantes = comboFabricantes;
	}

	public ArrayList<Produto> getItensFiltrados() {
		return itensFiltrados;
	}

	public void setItensFiltrados(ArrayList<Produto> itensFiltrados) {
		this.itensFiltrados = itensFiltrados;
	}

	public ArrayList<Produto> getItens() {
		return itens;
	}

	public void setItens(ArrayList<Produto> itens) {
		this.itens = itens;
	}

	public void carregarListagem() {
		try {
			ProdutoDAO dao = new ProdutoDAO();
			itens = dao.listar();
		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}
	}

	public void prepararNovo() {
		try {
			produto = new Produto();

			FabricanteDAO dao = new FabricanteDAO();

			comboFabricantes = dao.listar();
		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}
	}

	public void novo() {
		try {

			if (produto.getCaminho() == null) {
				JSFUtil.adicionarMensagemErro("O campo foto � obrigat�rio.");
				return;
			}

			ProdutoDAO dao = new ProdutoDAO();
			dao.salvar(produto);

			Path origem = Paths.get(produto.getCaminho());
			Path destino = Paths.get("F:/Documentos/Java/imagens/UploadsConstrutor/" + produto.getIdProduto() + ".png");
			Files.copy(origem, destino, StandardCopyOption.REPLACE_EXISTING);

			itens = dao.listar();

			JSFUtil.adicionarMensagemSucesso("Produto salvo com sucesso...");
		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		} catch (IOException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}
	}

	public void excluir() {
		try {
			ProdutoDAO dao = new ProdutoDAO();

			dao.excluir(produto);

			Path arquivo = Paths.get("F:/Documentos/Java/imagens/UploadsConstrutor/" + produto.getIdProduto() + ".png");
			Files.deleteIfExists(arquivo);

			itens = dao.listar();

			JSFUtil.adicionarMensagemSucesso("Produto Exclu�do com sucesso...");
		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		} catch (IOException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}
	}

	public void prepararEditar() {
		try {
			FabricanteDAO dao = new FabricanteDAO();

			comboFabricantes = dao.listar();
		} catch (Exception e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}
	}

	public void editar() {
		try {
			ProdutoDAO dao = new ProdutoDAO();

			dao.editar(produto);
			
			produto.setCaminho("F:/Documentos/Java/imagens/UploadsConstrutor/" + produto.getIdProduto() + ".png");

			itens = dao.listar();

			JSFUtil.adicionarMensagemSucesso("Produto editado com sucesso...");
		} catch (Exception e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}
	}

	public void upload(FileUploadEvent evento) {

		try {
			UploadedFile arquivoUpload = evento.getFile();

			Path arquivoTemporario = Files.createTempFile(null, null);

			Files.copy(arquivoUpload.getInputstream(), arquivoTemporario, StandardCopyOption.REPLACE_EXISTING);

			JSFUtil.adicionarMensagemSucesso("Upload realizado com sucesso!");
			produto.setCaminho(arquivoTemporario.toString());
		} catch (IOException e) {
			JSFUtil.adicionarMensagemErro("Aconteceu um erro ao realizar o upload do arquivo.");
			e.printStackTrace();
		}
	}
}
