package com.cafelite.repository;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import com.cafelite.configuration.DBConfiguration;
import com.cafelite.model.Menu;
import com.cafelite.model.DTO.MenuDTO;

public class MenuRepository {
	private static final String INSERT_MENU_SQL = "INSERT INTO menu (foodName, foodCategory, price, availability, quantity, image) VALUES (?, ?, ?, ?, ?, ?);";
	private static final String SELECT_MENU_BY_ID = "select menuID,foodName,foodCategory,price,availability,quantity,image from menu where menuID =?";
	private static final String SELECT_ALL_MENU = "select * from menu";
	private static final String DELETE_MENU_SQL = "delete from menu where menuID = ?;";
	private static final String UPDATE_MENU_SQL = "update menu set foodName = ?,foodCategory= ?, price =? , availability =?, quantity =?, image=? where menuID = ?;";

	 //  Execute the query or update query
	private PreparedStatement getDBStatement(String statement) throws SQLException {
		PreparedStatement preparedStatement = null;
		try {
			Connection connection = DBConfiguration.getConnection();
			preparedStatement = connection.prepareStatement(statement);
		} catch (SQLException e) {
			// print SQL exception information
			printSQLException(e);
		}
		return preparedStatement;
	}
	
	private void printSQLException(SQLException ex) {
		for (Throwable e : ex) {
			if (e instanceof SQLException) {
				e.printStackTrace(System.err);
				System.err.println("SQLState: " + ((SQLException) e).getSQLState());
				System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
				System.err.println("Message: " + e.getMessage());
				Throwable t = ex.getCause();
				while (t != null) {
					System.out.println("Cause: " + t);
					t = t.getCause();
				}
			}
		}
	}

	public void insertMenu(Menu menu) throws SQLException {
		try {
			PreparedStatement preparedStatement = getDBStatement(INSERT_MENU_SQL);

			  // try-with-resource statement will auto close the connection.
			preparedStatement.setString(1, menu.getFoodName());
			preparedStatement.setString(2, menu.getFoodCategory());
			preparedStatement.setString(3, menu.getPrice());
			preparedStatement.setString(4, menu.getAvailability());
			preparedStatement.setString(5, menu.getQuantity());
			preparedStatement.setBlob(6, menu.getImage());

			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			printSQLException(e);
		}
	}

	public Menu selectMenu(int menuID) {
		Menu menu = null;

		try {
			 // Create a statement using connection object
			PreparedStatement preparedStatement = getDBStatement(SELECT_MENU_BY_ID);
			preparedStatement.setInt(1, menuID);

			//  Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();
			 //  Process the ResultSet object.
			while (rs.next()) {
				String foodName = rs.getString("foodName");
				String foodCategory = rs.getString("foodCategory");
				String price = rs.getString("price");
				String availability = rs.getString("availability");
				String quantity = rs.getString("quantity");
				InputStream image = rs.getBlob("image").getBinaryStream();
				menu = new Menu(menuID, foodName, foodCategory, price, availability, quantity, image);
			}
		} catch (SQLException e) {
			printSQLException(e);
		}

		return menu;
	}

	public List<MenuDTO> selectAllMenu() {
		 // using try-with-resources to avoid closing resources (boiler plate code)
		List<MenuDTO> menu = new ArrayList<>();

		try {
			 // Create a statement using connection object
			PreparedStatement preparedStatement = getDBStatement(SELECT_ALL_MENU);
		//  Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();
			 //  Process the ResultSet object.
			while (rs.next()) {
				int menuID = rs.getInt("menuID");
				String foodName = rs.getString("foodName");
				String foodCategory = rs.getString("foodCategory");
				String price = rs.getString("price");
				String availability = rs.getString("availability");
				String quantity = rs.getString("quantity");
				byte[] image = rs.getBytes("image");
				
				String encode = Base64.getEncoder().encodeToString(image);
				
				menu.add(new MenuDTO(menuID, foodName, foodCategory, price, availability, quantity, encode));
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return menu;
	}

	public boolean deleteMenu(int menuID) throws SQLException {
		boolean rowDeleted = false;
		try {
			PreparedStatement preparedStatement = getDBStatement(DELETE_MENU_SQL);
			preparedStatement.setInt(1, menuID);
			rowDeleted = preparedStatement.executeUpdate() > 0;
		} catch (SQLException e) {
			printSQLException(e);
		}
		return rowDeleted;
	}

	public boolean updateMenu(Menu menu) throws SQLException {
		boolean rowUpdated = false;
		try {
			PreparedStatement preparedStatement = getDBStatement(UPDATE_MENU_SQL);
			preparedStatement.setString(1, menu.getFoodName());
			preparedStatement.setString(2, menu.getFoodCategory());
			preparedStatement.setString(3, menu.getPrice());
			preparedStatement.setString(4, menu.getAvailability());
			preparedStatement.setString(5, menu.getQuantity());
			preparedStatement.setBlob(6, menu.getImage());
			preparedStatement.setInt(7, menu.getMenuID());

			rowUpdated = preparedStatement.executeUpdate() > 0;
		} catch (SQLException e) {
			printSQLException(e);
		}
		return rowUpdated;
	}

}
