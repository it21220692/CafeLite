package com.cafelite.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.cafelite.model.Menu;
import com.cafelite.model.DTO.MenuDTO;
import com.cafelite.repository.MenuRepository;

@MultipartConfig(maxFileSize = 16177215)
@WebServlet("/admin/*")
public class AdminMenuServlet extends HttpServlet { //in
	// number that will help the JVM to identify the state of an object when it reads the state of the object from a file
	private static final long serialVersionUID = 1L;
	private MenuRepository menuRepository;

	public AdminMenuServlet() {
		menuRepository = new MenuRepository();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		String pathInfo = request.getPathInfo();
		try {
			if (pathInfo == null) {
				listMenu(request, response);
			} else {
				switch (pathInfo) {
				case "/new":
					showNewForm(request, response);
					break;
				case "/insert":
					insertMenu(request, response);
					break;
				case "/delete":
					deleteMenu(request, response);
					break;
				case "/edit":
					showEditForm(request, response);
					break;
				case "/update":
					updateMenu(request, response);
					break;
				case "/menu":
					viewMenu(request, response);
					break;
				default:
					break;
				}
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}

	private void listMenu(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<MenuDTO> listMenu = menuRepository.selectAllMenu();

		request.setAttribute("listMenu", listMenu);
		RequestDispatcher dispatcher = request.getRequestDispatcher("menu-list.jsp");
		dispatcher.forward(request, response);
	}

	private void viewMenu(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<MenuDTO> listMenu = menuRepository.selectAllMenu();
		request.setAttribute("listMenu", listMenu);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
		dispatcher.forward(request, response);
	}

	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("I'am here");
		RequestDispatcher dispatcher = request.getRequestDispatcher("/menu-form.jsp");
		dispatcher.forward(request, response);
	}

	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		int menuID = Integer.parseInt(request.getParameter("menuID"));
		Menu existingMenu = menuRepository.selectMenu(menuID);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/menu-form.jsp");
		request.setAttribute("menu", existingMenu);
		dispatcher.forward(request, response);
	}

	private void insertMenu(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		String foodName = request.getParameter("foodName");
		String foodCategory = request.getParameter("foodCategory");
		String price = request.getParameter("price");
		String availability = request.getParameter("availability");
		String quantity = request.getParameter("quantity");
		Part file = request.getPart("image");

		InputStream imageStream = file.getInputStream();
		Menu menu = new Menu(foodName, foodCategory, price, availability, quantity, imageStream);

		menuRepository.insertMenu(menu);
		response.sendRedirect("/admin");
	}

	private void updateMenu(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		int menuID = Integer.parseInt(request.getParameter("menuID"));
		String foodName = request.getParameter("foodName");
		String foodCategory = request.getParameter("foodCategory");
		String price = request.getParameter("price");
		String availability = request.getParameter("availability");
		String quantity = request.getParameter("quantity");
		Part file = request.getPart("image");

		InputStream imageStream = file.getInputStream();
		Menu menu = new Menu(menuID, foodName, foodCategory, price, availability, quantity, imageStream);
		
		menuRepository.updateMenu(menu);
		response.sendRedirect("/admin");
	}

	private void deleteMenu(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		int menuID = Integer.parseInt(request.getParameter("menuID"));
		menuRepository.deleteMenu(menuID);
		response.sendRedirect("/admin");
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
