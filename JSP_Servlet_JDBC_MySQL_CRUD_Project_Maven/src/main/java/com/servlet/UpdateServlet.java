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

@WebServlet("/update")
public class UpdateServlet extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		int id=Integer.parseInt(req.getParameter("id"));
		String name = req.getParameter("name");
		String dob = req.getParameter("dob");
		String address = req.getParameter("address");
		String qualification = req.getParameter("qualification");
		String email = req.getParameter("email");
		
		Student student = new Student(id,name,dob,address,qualification,email);
		
		StudentDAO studentDAO = new StudentDAO(DBConnect.getConnection());
		boolean updateStudent = studentDAO.updateStudent(student);
		HttpSession session = req.getSession();
		
		if(updateStudent)
		{
			session.setAttribute("success", "Student Details Updated Successfully...");
			resp.sendRedirect("index.jsp");

		}else {
			session.setAttribute("error", "Something WRONG in updation...");
			resp.sendRedirect("index.jsp");
		}
		
	}
	
}
