/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tp2;

import simplejdbc.*;
import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name = "ProprieteClient", urlPatterns = {"/ProprieteClient"})
public class ProprieteClient extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {
        response.setContentType("text/html;charset=UTF-8");
        DAO dao = new DAO(DataSourceFactory.getDataSource());
        
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Propriete Client</title>"); 
            out.println("<link rel=\"stylesheet\" href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css\" integrity=\"sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS\" crossorigin=\"anonymous\">");
            out.println("</head>");
            out.println("<body>");
            out.println("<div class=\"container\">");
          try{
              String customerID = request.getParameter("customerID");
        if (customerID == null) {
				throw new Exception("La paramètre customerID n'a pas été transmis");
			}
        int id = Integer.valueOf(customerID);
                CustomerEntity customer = dao.findCustomer(id);
                if(customer!=null){
                    out.println("<strong>Customer n°</strong> "+customerID+ " <br> <strong>name:</strong> "+customer.getName()+" <br> <strong>address:</strong> "+customer.getAddressLine1());
                }
                else{
                    throw new Exception("Client inconnu");
                }
                }
            catch(Exception e){
                out.printf("Erreur: %s", e.getMessage());
                Logger.getLogger(ProprieteClient.class.getName()).log(Level.SEVERE, null, e);
            }
            out.printf("<hr><a href='%s' class=\"btn btn-info\">Retour au menu</a>", request.getContextPath());
            out.println("</div>");
            out.println("</body>");
            out.println("</html>");
        }catch (Exception ex) {
			Logger.getLogger("servlet").log(Level.SEVERE, "Erreur de traitement", ex);
		}
    }
    

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(ProprieteClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(ProprieteClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
