package br.com.construcao.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoFactory {

	private static final String USUARIO = "root";
	private static final String SENHA = "123456";
	private static final String URL = "jdbc:mysql://localhost:3306/construtor";

	public static Connection conectar() throws SQLException {
		DriverManager.registerDriver(new com.mysql.jdbc.Driver());
		Connection conexao = DriverManager.getConnection(URL, USUARIO, SENHA);
		return conexao;
	}

	/*
	 * public static void main(String[] args) {
	 * 
	 * try {
	 * 
	 * @SuppressWarnings("unused") Connection conexao = ConexaoFactory.conectar();
	 * System.out.println("Coneão realizada com sucesso..."); } catch (SQLException
	 * e) { e.printStackTrace();
	 * System.out.println("Não foi possível realizar a conexão..."); }
	 * 
	 * }
	 */

}
