package Servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@WebServlet(name="ThankYouServlet", description = "ThankYouServlet", urlPatterns = { "/thankyou" })
public class ThankYouServlet extends HttpServlet {
    private int hitCount;

    public ThankYouServlet() {
        super();
    }

    @Override
    public void init() throws ServletException {
        super.init();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        hitCount++;
        String customerName = request.getParameter("customerName");
        String gender = request.getParameter("radioGender");
        String category = request.getParameter("ddlCategory");
        String message = request.getParameter("message");
       
        response.setContentType("text/html");
        response.setBufferSize(8192);
        String output = this.generateThankYouPage(customerName, gender, category, message);
        try (PrintWriter pw = response.getWriter()) {
            pw.println(output);
        }
    }

    private String generateThankYouPage(String customerName, String gender, String category, String message) {
        StringBuilder sb = new StringBuilder();
        sb.append("<!DOCTYPE html>");
        sb.append("<html lang='en'>");
        sb.append("<head>");
        sb.append("    <meta charset='UTF-8'>");
        sb.append("    <meta name='viewport' content='width=device-width, initial-scale=1.0', shrink-to-fit=no\">");
        sb.append("    <meta http-equiv='X-UA-Compatible' content='ie=edge'>");
        sb.append("    <title>Customer Contact App - Thank you!</title>");
        sb.append("    <link rel=\"stylesheet\" href=\"https://stackpath.bootstrapcdn.com/bootswatch/4.3.1/cosmo/bootstrap.min.css\" integrity=\"sha384-uhut8PejFZO8994oEgm/ZfAv0mW1/b83nczZzSwElbeILxwkN491YQXsCFTE6+nx\" crossorigin=\"anonymous\">");
        sb.append("    <link rel=\"stylesheet\" href=\"css/style.css\">");
        sb.append("</head>");
        sb.append("<body>");
        sb.append("<nav class=\"navbar navbar-expand-lg navbar-dark bg-primary\">");
        sb.append("<a class=\"navbar-brand\" href=\"index.html\">CS472 ::: Lab10</a>");
        sb.append("<button class=\"navbar-toggler\" type=\"button\" data-toggle=\"collapse\" data-target=\"#navbarsExampleDefault\" aria-controls=\"navbarsExampleDefault\" aria-expanded=\"false\" aria-label=\"Toggle navigation\">");
        sb.append("<span class=\"navbar-toggler-icon\"></span>");
        sb.append("</button>");
        sb.append("</nav>");
        sb.append("<div class=\"container\">");

        // Show current Date
        sb.append("<div class=\"container\"><br/>");
        sb.append("<span style=\"float:right;\" class=\"text-muted\">" + LocalDate.now().format(DateTimeFormatter.ofPattern("EEEE, dd MMMM yyyy")) + "</span>");
        sb.append("</div>");

        sb.append("<p>");
        sb.append("<br/>");
        sb.append("<div class=\"card\">");
        sb.append(" <div class=\"card-header\">");
        sb.append("<h2>Thank you! Your message has been received as follows:</h2>");
        sb.append("  </div>");
        sb.append("  <div class=\"card-body\">");
        sb.append("    <h4 class=\"card-title\">Name: " + customerName + "</h4><br/>");
        sb.append("    <h5 class=\"card-subtitle mb-2 text-muted\">Gender: " + gender + "</h5><br/>");
        sb.append("    <h5 class=\"card-subtitle mb-2 text-muted\">Category: " + category + "</h4><br/>");
        sb.append("    <p class=\"card-text\">Message: " + message + "</p><br/><br/>");
        sb.append("  <p>Please feel free to <a href=\"contact-form\" class=\"card-link\">Contact Us</a> again</p>");
        sb.append("  </div>");
        sb.append("</div>");
        sb.append("</p>");

        // Display Hit Counts
        sb.append("<div class=\"container\">");
        sb.append("<span class=\"text-muted\">Hit Count for this page: " + this.hitCount +"</span><span style=\"float:right;\" class=\"text-muted\">Total Hit Count for the entire WebApp: --</span>");
        sb.append("</div>");

        sb.append("</div>");
        sb.append("<footer class=\"footer\">");
        sb.append("<div id=\"footer-content\">");
        sb.append("<span class=\"text-muted\">O. Kalu ::: CS472-WAP</span><span style=\"float:right;\" class=\"text-muted\">&copy November 2019</span>");
        sb.append("</div>");
        sb.append("</footer>");
        sb.append("</body>");
        sb.append("</html>");

        return sb.toString();
    }
}
