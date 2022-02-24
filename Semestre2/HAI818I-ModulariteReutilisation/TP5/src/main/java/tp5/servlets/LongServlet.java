package tp5.servlets;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "LongServlet", value = "/long")
public class LongServlet extends HttpServlet
{
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        try
        {
            Thread.sleep(1000); // ;-)
        }
        catch (InterruptedException e)
        {
        }

        response.getOutputStream().println("Hello World !");
    }
}
