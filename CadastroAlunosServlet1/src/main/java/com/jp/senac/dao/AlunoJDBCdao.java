package com.jp.senac.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.jp.senac.model.Aluno;

public class AlunoJDBCdao {
	
	public Connection getConexao() throws ClassNotFoundException {
		
		// Driver
		String driver = "com.mysql.cj.jdbc.Driver";
		Class.forName(driver);
		
		String url = "jdbc:mysql://localhost:3306/cadastroalunos?useTimezone=true&serverTimezone=UTC";
		
		String user = "root";
		
		String password = "root";
		
		Connection con = null;
		try {			
			con = DriverManager.getConnection(url,user,password);
			System.out.println("Conectado ao banco de dados");
			
		}catch(SQLException e){
			e.printStackTrace();
		}
		return con;
	}
	
	public List<Aluno> listarAlunos() throws SQLException, ClassNotFoundException{
		
		List<Aluno> alunos = new ArrayList<>();
		String query = "SELECT * FROM alunos";
			Connection con = getConexao();
			PreparedStatement pst = con.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			while(rs.next()){
				int id = rs.getInt(1);
				String nome = rs.getString(2);
				String idade = rs.getString(3);
				String semestre = rs.getString(4);
				String genero = rs.getString(5);
				String matricula = rs.getString(6);
				alunos.add(new Aluno(id,nome, idade, semestre, genero, matricula));
			}
			
			rs.close();
			pst.close();
			con.close();
		return alunos;
	}
	
	public Aluno pesquisarPorId(Integer id) throws ClassNotFoundException {
		String query= "Select * from alunos where id = ?";
		ResultSet rs= null;	
		Aluno aluno = null;
		try {
			Connection con = getConexao();
			PreparedStatement pst = con.prepareStatement(query); ; 
			pst.setInt(1, id);
			rs = pst.executeQuery();
			String name = rs.getString(2);
			
			
			aluno.setNome(name);
			String idade=rs.getString(3);
			aluno.setIdade(idade);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return aluno;	
	}

	
	public Aluno cadastrarAluno (Aluno aluno) {
		String query = "Insert into alunos (nome,idade,semestre,genero,matricula) Values (?,?,?,?,?)";
		
		try {
			Connection con= getConexao();
			PreparedStatement pst = con.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
			pst.setString(1, aluno.getNome());
			pst.setString(2, aluno.getIdade());
			pst.setString(3, aluno.getSemestre());
			pst.setString(4, aluno.getGenero());
			pst.setString(5, aluno.getMatricula());
			pst.executeUpdate();
			
			ResultSet rs = pst.getGeneratedKeys();
			while(rs.next()) {
				int chaveGerada = rs.getInt(1);
				System.out.println("Id gerado foi" + chaveGerada);
				aluno.setId(chaveGerada);
			}
			
		}catch (ClassNotFoundException | SQLException e) {
			// TODO: handle exception
		}
		
		return aluno;
	}
	
	
	public void excluirAluno(String id) {
		String query = "Delete from alunos where (id= ?)";
		
			try {
				 Connection con = getConexao();
			        PreparedStatement pst = con.prepareStatement(query);
			        pst.setString(1, id);
			        pst.executeUpdate();
			} catch (Exception e) {
				// TODO: handle exception
			}

	}
	
	public Aluno alterarAluno(String id) {
		String query = "Update alunos set nome = ? idade = ? semestre = ? genero = ?  where (id = ?)";
		try {
			Connection con = getConexao();
	        PreparedStatement pst = con.prepareStatement(query);
	        
			
		}catch(Exception e){
			
		}
		
		return null;
	}

	
	

}
