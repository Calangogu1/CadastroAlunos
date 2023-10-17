package com.jp.senac.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.jp.senac.dao.AlunoJDBCdao;
import com.jp.senac.model.Aluno;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


public class ConfirmarCadastroServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		// Recuperando os valores informados
		String nome = request.getParameter("nome");
		String idade = request.getParameter("idade");
		String genero = request.getParameter("genero");
		String semestre = request.getParameter("semestre");
		String semestreAtual;
		
	
		List<Aluno> listaAlunos = null;
		
		
		//criando os dados nescessarios para criar a String formatada de matricula
		  Calendar calendario = Calendar.getInstance();
	        int anoAtual = calendario.get(Calendar.YEAR);
	        int mesAtual = calendario.get(Calendar.MONTH)+1 ;
	        if(mesAtual<6) {
	        	 semestreAtual = "01";
	        }else {
	        	 semestreAtual = "02";
	        }
	        int numeroAleatorio = (int) Math.round(Math.random() * 8999)+1000;
	        String anoS = String.valueOf(anoAtual);
	        String mesS = String.valueOf(mesAtual);
	       
			String numeroComoString = String.valueOf(numeroAleatorio);
	       String matricula = null;
	       matricula = anoS+mesS+ semestreAtual+idade+numeroComoString;
		
		// Guardar no objeto aluno
		Aluno aluno = new Aluno(nome, idade, semestre, genero,matricula);
		
		AlunoJDBCdao dao = new AlunoJDBCdao();
		Aluno alunoCadastro = dao.cadastrarAluno(aluno);
		
		request.setAttribute("aluno", aluno);
		
		// Encaminhar a requisição para o JSP
		request.getRequestDispatcher("detalharAluno.jsp").forward(request, response);
		
		
		
		
	}

}
