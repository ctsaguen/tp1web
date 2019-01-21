/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tp2;

import static Tp2.listeState.CHAMP_STATE;
import simplejdbc.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 *
 * @author kevin
 */
@WebServlet(name = "ListeClient", urlPatterns = {"/ListeClient"})
public class ListeClient extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ListeClient</title>"); 
            out.println("<link rel=\"stylesheet\" href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css\" integrity=\"sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS\" crossorigin=\"anonymous\">");
            out.println("</head>");
            out.println("<body>");
            try {
		String val = request.getParameter(CHAMP_STATE);
		if (val == null) {
                    throw new Exception("La paramètre state n'a pas été transmis");
		}
                DAO dao = new DAO(DataSourceFactory.getDataSource());
		List<CustomerEntity> customerList = dao.customersInState(val);
                if (customerList == null) {
					throw new Exception("Client inconnu");
				}
                out.println("<table  class=\"table table-bordered table-hover col-sm-8 table-dark\" ");
  out.println("<thead class=\"thead-light\">");
                out.println("<tr>");
    out.println("<th>nom</th>");
    out.println("<th>addresse</th>");
  out.println("</tr>");
  out.println("</thead>");
  out.println("<tbody>");
  for(CustomerEntity customer : customerList ){
      out.println("<tr>");
      out.println("<td>"+customer.getName()+"</td>");
      out.println("<td>"+customer.getAddressLine1()+"</td>");
      out.println("</tr>");
  }
out.println("<tbody>");
out.println("</table>");
            } catch (Exception e) {
				out.printf("Erreur : %s", e.getMessage());
			}
            out.printf("<hr><a href='%s' class=\"btn btn-info\">Retour au menu</a>", request.getContextPath());
            out.println("</body>");
            out.println("</html>");
        }
            
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(ListeClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(ListeClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
