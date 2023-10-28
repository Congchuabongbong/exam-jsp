package com.example.exam;
import Model.Employee;
import dao.EmployeeDao;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/ViewServlet")
public class ViewServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        PrintWriter printWriter=response.getWriter();
        printWriter.println("<a href='index.html'>Add New Employee</a>");
        printWriter.println("<h1>Employees List</h1>");

        List<Employee> list= EmployeeDao.getAllEmployee();

        printWriter.print("<table border='1' borderColor='#009879' width='50%'");
        printWriter.print("<tr><th>Id</th><th>Name</th><th>birthDay</th><th>Address</th><th>Address</th><th>Position</th><th>Department</th><th>Edit</th><th>Delete</th></tr>");
        for(Employee e:list){
            // each row is identified by means of its id
            // hence on click of 'edit', it is sent as
            // <a href='EditServlet?id="+e.getId()+"'>edit</a>
            // on click of 'delete', it is sent as
            // <a href='DeleteServlet?id="+e.getId()+"'>delete</a>
            printWriter.print("<tr><td >"+e.getId()+"</td><td>"+e.getFullName()+"</td><td>"+e.getBirthday()+"</td><td>"+e.getAddress()+"</td><td>"+ e.getPosition() + "</td><td>" + e.getDepartment() +"</td><td><a href='EditServlet?id="+e.getId()+"'>edit</a></td><td><a href='DeleteServlet?id="+e.getId()+"'>delete</a></td></tr>");
        }
        printWriter.print("</table>");

        printWriter.close();
    }
}
