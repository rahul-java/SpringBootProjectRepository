package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.print.attribute.PrintJobAttribute;

import com.entity.Student;

public class StudentDAO {

	private Connection conn;

	public StudentDAO(Connection conn) {
		super();
		this.conn = conn;
	}
	
	//Save Student
	public boolean addStudent(Student student )
	{
		boolean f=false;
		
		try {
			
			String sql="insert into student_mvn(name,dob,address,qualification,email) values (?,?,?,?,?)";
			PreparedStatement prepareStatement = conn.prepareStatement(sql);
			prepareStatement.setString(1, student.getName());
			prepareStatement.setString(2, student.getDob());
			prepareStatement.setString(3, student.getAddress());
			prepareStatement.setString(4, student.getQualification());
			prepareStatement.setString(5, student.getEmail());
			int i = prepareStatement.executeUpdate();
			
			if(i==1)
			{
				f=true;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return f;
	}
	
	//Fetch Student
	public List<Student> getAllStudent()
	{
		List<Student> list=new ArrayList<Student>();
		Student student=null;
		try {
			String sql="select * from student_mvn";
			PreparedStatement prepareStatement = conn.prepareStatement(sql);
			ResultSet resultSet = prepareStatement.executeQuery();
			while(resultSet.next())
			{
				student = new Student();
				student.setId(resultSet.getInt(1));
				student.setName(resultSet.getString(2));
				student.setDob(resultSet.getString(3));
				student.setAddress(resultSet.getString(4));
				student.setQualification(resultSet.getString(5));
				student.setEmail(resultSet.getString(6));
				list.add(student);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	//get One Std by id
	public Student getStudentById(int id)
	{
		Student student=null;
		try {
			String sql="select * from student_mvn where id=?";
			PreparedStatement prepareStatement = conn.prepareStatement(sql);
			prepareStatement.setInt(1, id);
			ResultSet resultSet = prepareStatement.executeQuery();
			while(resultSet.next())
			{
				student = new Student();
				student.setId(resultSet.getInt(1));
				student.setName(resultSet.getString(2));
				student.setDob(resultSet.getString(3));
				student.setAddress(resultSet.getString(4));
				student.setQualification(resultSet.getString(5));
				student.setEmail(resultSet.getString(6));
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return student;
	}
	
	//update student
	public boolean updateStudent(Student student )
	{
		boolean f=false;
		
		try {
			
			String sql="update student_mvn set name=?,dob=?,address=?,qualification=?,email=? where id=?";
			PreparedStatement prepareStatement = conn.prepareStatement(sql);
			prepareStatement.setString(1, student.getName());
			prepareStatement.setString(2, student.getDob());
			prepareStatement.setString(3, student.getAddress());
			prepareStatement.setString(4, student.getQualification());
			prepareStatement.setString(5, student.getEmail());
			prepareStatement.setInt(6, student.getId());
			int i = prepareStatement.executeUpdate();
			
			if(i==1)
			{
				f=true;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return f;
	}
	
	//delete student
	public boolean deleteStudent(int id)
	{
		boolean f=false;
		
		try {
			
			String sql="delete from student_mvn where id=?";
			PreparedStatement prepareStatement = conn.prepareStatement(sql);
			prepareStatement.setInt(1, id);
            int i = prepareStatement.executeUpdate();
			
			if(i==1)
			{
				f=true;
			}
		} catch (Exception e) {
			
		}
		
		return f;
	}
	
}
