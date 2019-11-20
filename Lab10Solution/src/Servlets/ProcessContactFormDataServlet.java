package Servlets;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ProcessContactFormDataServlet", urlPatterns = {"/process-contact-form"}, description = "")
public class ProcessContactFormDataServlet extends HttpServlet {
    private int hitCount;

    public ProcessContactFormDataServlet() {
        super();
    }

  
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        hitCount++;
        response.sendRedirect("contact-form");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // hitCount++;
        String missingFieldsMsg = "";
        String customerName = request.getParameter("customerName"); // this is to be send back 
        String gender = request.getParameter("radioGender");
        String category = request.getParameter("ddlCategory");
        String message = request.getParameter("message");
        System.out.println("name= "+ customerName + ", gender= " + gender + ", cat= " + category + ", msg= " + message);
        
        // Check for missing fields data
        if(customerName.equals("")) { 
            missingFieldsMsg += "<span style='color:red;font-size:1em'>Name is missing.</span><br/>";
        }
        if(gender == null) {
            missingFieldsMsg += "<span style='color:red;'>Gender is missing.</span><br/>";
        }
        if(category.equals("null")) {
            missingFieldsMsg += "<span style='color:red;'>Category is missing.</span><br/>";
        }
        if(message.equals("")) {
            missingFieldsMsg += "<span style='color:red;'>Message is missing.</span><br/>";
        }
        
        if(!missingFieldsMsg.equals("")) {
            request.setAttribute("errMsgs", missingFieldsMsg);
            // forward back to sender
            RequestDispatcher rd = request.getRequestDispatcher("/contact-form"); //request is above
            
            
            rd.forward(request, response);
            
            
        } else {
        	
            String redirectUrl = "thankyou?customerName=" + customerName + "&radioGender=" + gender + "&ddlCategory=" + category + "&message=" + message;
            
            
            response.sendRedirect(redirectUrl); // Redirect sends to the browser
        }
    }

}
