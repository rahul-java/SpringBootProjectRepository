package com.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.conn.DBConnect;
import com.dao.StudentDAO;
import com.entity.Student;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String name = req.getParameter("name");
		String dob = req.getParameter("dob");
		String address = req.getParameter("address");
		String qualification = req.getParameter("qualification");
		String email = req.getParameter("email");
		
		Student student = new Student(name,dob,address,qualification,email);
		
		StudentDAO studentDAO = new StudentDAO(DBConnect.getConnection());
		boolean addStudent = studentDAO.addStudent(student);
		HttpSession session = req.getSession();
		
		if(addStudent)
		{
			session.setAttribute("success", "Student Details Submitted Successfully...");
			resp.sendRedirect("add-student.jsp");

		}else {
			session.setAttribute("error", "Something WRONG...");
			resp.sendRedirect("add-student.jsp");
		}
	}

	
}
