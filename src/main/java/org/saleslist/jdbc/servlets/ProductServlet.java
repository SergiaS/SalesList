package org.saleslist.jdbc.servlets;

import org.saleslist.jdbc.repository.JdbcProductRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//@WebServlet("/")
public class ProductServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		JdbcProductRepository repository = new JdbcProductRepository();
		request.setAttribute("products", repository.getAllProducts());
		request.getRequestDispatcher("/products.jsp").forward(request, response);

//		String action = request.getServletPath();
//
//		switch (action) {
//			case "/products" -> showAllProducts(request, response);
//			default -> request.getRequestDispatcher("/a").forward(request, response);
//		}
	}

	private void showAllProducts(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}
}
