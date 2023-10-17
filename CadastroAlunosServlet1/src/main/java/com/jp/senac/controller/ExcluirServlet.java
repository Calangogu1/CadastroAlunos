package com.jp.senac.controller;

import java.io.IOException;

import com.jp.senac.dao.AlunoJDBCdao;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class ExcluirServlet
 */
public class ExcluirServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
			HttpSession session = request.getSession();
			Integer id = Integer.parseInt(request.getParameter("id"));
			AlunoJDBCdao dao= new AlunoJDBCdao();

				dao.excluirAluno(request.getParameter("id"));
			
			request.getRequestDispatcher("ListarServlet").forward(request, response);

		}	

	}

