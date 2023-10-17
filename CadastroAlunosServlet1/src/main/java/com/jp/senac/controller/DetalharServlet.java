package com.jp.senac.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.jp.senac.dao.AlunoJDBCdao;
import com.jp.senac.model.Aluno;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.websocket.Session;

/**
 * Servlet implementation class DetalharServlet
 */
public class DetalharServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		Integer id = Integer.parseInt(request.getParameter("id")); 
		
		//Adicionar a lista
		HttpSession session = request.getSession();
		
		
		
		AlunoJDBCdao dao = new AlunoJDBCdao(); 
		List<Aluno> listaAlunos = null;
		try {
			listaAlunos = dao.listarAlunos();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Recuperando o nome do aluno da lista que tem o nome selecionado
		Aluno aluno = null;
		for(Aluno a : listaAlunos) {
			if(a.getId() == id) {
				aluno = a;
			}
		}
		
		request.setAttribute("aluno", aluno);
		request.getRequestDispatcher("detalharAluno.jsp").forward(request, response);
		
	}



}
