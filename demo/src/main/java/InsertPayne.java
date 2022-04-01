
/**
 * @file InsertPayne.java
 */
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/InsertPayne")
public class InsertPayne extends HttpServlet {
   private static final long serialVersionUID = 1L;

   public InsertPayne() {
      super();
   }

   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	  String first_name = request.getParameter("first_name");
	  String last_name = request.getParameter("last_name");
	  String phone = request.getParameter("phone");
      String email = request.getParameter("email");

      Connection connection = null;
      String insertSql = " INSERT INTO MyTableFinal (id, first_name, last_name, phone, email) values (default, ?, ?, ?,?)";

      try {
         DBConnectionPayne.getDBConnection();
         connection = DBConnectionPayne.connection;
         PreparedStatement preparedStmt = connection.prepareStatement(insertSql);
         preparedStmt.setString(1, first_name);
         preparedStmt.setString(2, last_name);
         preparedStmt.setString(3, phone);
         preparedStmt.setString(4, email);
         preparedStmt.execute();
         connection.close();
      } catch (Exception e) {
         e.printStackTrace();
      }

      // Set response content type
      response.setContentType("text/html");
      PrintWriter out = response.getWriter();
      String title = "Insert Data to DB table";
      String docType = "<!doctype html public \"-//w3c//dtd html 4.0 " + "transitional//en\">\n";
      out.println(docType + //
            "<html>\n" + //
            "<head><title>" + title + "</title></head>\n" + //
            "<body bgcolor=\"#f0f0f0\">\n" + //
            "<h2 align=\"center\">" + title + "</h2>\n" + //
            "<ul>\n" + //

            "  <li><b>First Name</b>: " + first_name + "\n" + //
            "  <li><b>Last Name</b>: " + last_name + "\n" + //
            "  <li><b>Phone</b>: " + phone + "\n" + //
            "  <li><b>Email</b>: " + email + "\n" + //

            "</ul>\n");

      out.println("<a href=/demo/insert_paynefinal.html>Search Data</a> <br>");
      out.println("</body></html>");
   }

   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      doGet(request, response);
   }

}

