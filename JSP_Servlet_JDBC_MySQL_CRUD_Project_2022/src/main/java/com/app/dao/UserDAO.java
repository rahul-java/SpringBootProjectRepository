package com.app.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.app.model.User;



//here we perform crud operation in Users table
//DAO : Data Access Object

public class UserDAO {
	
	private String jdbcURL="jdbc:mysql://localhost:3306/rahuldatabase";
	private String jdbcUsername="root";
	private String jdbcPassword="root";
	
	private static final String INSERT_USERS_SQL="INSERT INTO users "+" (name,email,country) VALUES "+" (?,?,?);";
	private static final String SELECT_USERS_BY_ID="select id,name,email,country from users where id=?";
	private static final String SELECT_ALL_USERS="select * from users";
	private static final String DELETE_USERS_BY_ID="delete from users where id=?;";
	private static final String UPDATE_USERS_BY__ID="update users set name=?,email=?,country=? where id=?;";

	//Create and Get Connection
	protected Connection getConnection()
	{
		Connection connection=null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection=DriverManager.getConnection(jdbcURL,jdbcUsername,jdbcPassword);
		} catch (SQLException s) {
			s.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return connection;
		
	}
	
	//Create User or Insert User
	public void insertUser(User user)
	{
		try {
			Connection connection = getConnection();
			PreparedStatement prepareStatement = connection.prepareStatement(INSERT_USERS_SQL);
			prepareStatement.setString(1, user.getName());
			prepareStatement.setString(2, user.getEmail());
			prepareStatement.setString(3, user.getCountry());
			System.out.println(prepareStatement);
			prepareStatement.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//Update User
	public boolean updateUser(User user)
	{
		boolean rowUpdated=false;
		try {
			Connection connection = getConnection();
			PreparedStatement prepareStatement = connection.prepareStatement(UPDATE_USERS_BY__ID);
			prepareStatement.setString(1, user.getName());
			prepareStatement.setString(2, user.getEmail());
			prepareStatement.setString(3, user.getCountry());
			prepareStatement.setInt(4, user.getId());
			System.out.println(prepareStatement);
			rowUpdated=prepareStatement.executeUpdate()>0;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rowUpdated;
	}
	
	//Select User by id
	public User selectUserById(int id)
	{
		User user=null;
		try {
			Connection connection = getConnection();
			PreparedStatement prepareStatement = connection.prepareStatement(SELECT_USERS_BY_ID);
			prepareStatement.setInt(1, id);
			System.out.println(prepareStatement);
			ResultSet resultSet = prepareStatement.executeQuery();
			
			while(resultSet.next())
			{
				String name = resultSet.getString("name");
				String email = resultSet.getString("email");
				String country = resultSet.getString("email");
				user = new User(id,name,email,country);
				
			}
			
		}catch (SQLException e) {
			
			e.printStackTrace();
		}
		return user;
	}
	
	//Select All Users
	public List<User> selectAllUsers()
	{
		List<User> list = new ArrayList<>();
		try {
			Connection connection = getConnection();
			PreparedStatement prepareStatement = connection.prepareStatement(SELECT_ALL_USERS);
			System.out.println(prepareStatement);
			ResultSet resultSet = prepareStatement.executeQuery();
			
			while(resultSet.next())
			{
				int id = resultSet.getInt("id");
				String name = resultSet.getString("name");
				String email = resultSet.getString("email");
				String country = resultSet.getString("email");
                list.add(new User(id,name,email,country));
				
			}
			
		}catch (SQLException e) {
			
			e.printStackTrace();
		}
		return list;
	}
	
	//Delete User by id
	public boolean deleteUserByID(int id)
	{
		boolean rowDeleted=false;
		try {
			Connection connection = getConnection();
			PreparedStatement prepareStatement = connection.prepareStatement(DELETE_USERS_BY_ID);
			prepareStatement.setInt(1, id);
			System.out.println(prepareStatement);
			rowDeleted=prepareStatement.executeUpdate()>0;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return rowDeleted;
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
}
