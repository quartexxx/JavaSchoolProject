package servlet;

import model.Fraction;
import model.FractionCalculatorModel;
import model.InvalidValueException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import model.DBUtils;

/**
 * The CalculationServlet class handles the calculation requests from the
 * client.
 *
 * @author Remigiusz Kocialski
 * @version 1.0
 */
@WebServlet("/calculate")
public class CalculationServlet extends HttpServlet {

    /**
     * Initializes the CalculationServlet by obtaining an instance of the
     * FractionCalculatorModel.
     */
    @Override
    public void init() {
    }

    /**
     * Handles GET requests.
     *
     * @param req The HttpServletRequest object.
     * @param resp The HttpServletResponse object.
     * @throws IOException If an input or output exception occurs.
     * @throws ServletException If a servlet-specific exception occurs.
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        try {
            calculate(req, resp);
        } catch (ServletException ex) {
            Logger.getLogger(CalculationServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles POST requests.
     *
     * @param req The HttpServletRequest object.
     * @param resp The HttpServletResponse object.
     * @throws IOException If an input or output exception occurs.
     * @throws ServletException If a servlet-specific exception occurs.
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        calculate(req, resp);
    }

    /**
     * Performs the fraction calculation based on the client's request.
     *
     * @param req The HttpServletRequest object.
     * @param resp The HttpServletResponse object.
     * @throws IOException If an input or output exception occurs.
     * @throws ServletException If a servlet-specific exception occurs.
     */
    private void calculate(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        FractionCalculatorModel model = (FractionCalculatorModel) getServletContext().getAttribute("sharedModel");
        DBUtils db = (DBUtils) getServletContext().getAttribute("sharedDB");

        if (db == null) {
            System.err.println("Database not connected");
        }

        String num1Str = req.getParameter("num1");
        String den1Str = req.getParameter("den1");
        String operation = req.getParameter("operation");
        String num2Str = req.getParameter("num2");
        String den2Str = req.getParameter("den2");

        try {
            Fraction frac1 = new Fraction(num1Str + "/" + den1Str);
            Fraction frac2 = new Fraction(num2Str + "/" + den2Str);

            Fraction result = model.performOperation(frac1, operation, frac2);

            // Update the operation counter in cookies and in the model
            updateOperationCounter(req, resp);

            resp.getWriter().println("Result: " + result.toString());

            String operationStr = String.format("%s %s %s = %s", frac1.toString(), operation, frac2.toString(), result.toString());
            model.addToHistory(operationStr);

            req.setAttribute("result", result.toString());
            // Add the operation counter attribute for passing to result.jsp
            req.setAttribute("operationCounter", model.getOperationCounter());
            req.getRequestDispatcher("result.jsp").forward(req, resp);

        } catch (InvalidValueException e) {
            resp.getWriter().println("Error: " + e.getMessage());
        }
    }

    /**
     * Handles the update of the operation counter in cookies and the model.
     *
     * @param req The HttpServletRequest object.
     * @param resp The HttpServletResponse object.
     */
    private void updateOperationCounter(HttpServletRequest req, HttpServletResponse resp) {
        int currentCount = 0;
        String countCookieName = "operationCount";

        // Check if the cookie already exists
        if (req.getCookies() != null) {
            for (Cookie cookie : req.getCookies()) {
                if (cookie.getName().equals(countCookieName)) {
                    currentCount = Integer.parseInt(cookie.getValue());
                    break;
                }
            }
        }

        // Increase the operation counter
        currentCount++;

        // Set the cookie with the new value of the counter
        resp.addCookie(new Cookie(countCookieName, String.valueOf(currentCount)));

        FractionCalculatorModel model = (FractionCalculatorModel) getServletContext().getAttribute("sharedModel");
        // Update the operation counter in the model
        model.setOperationCounter(currentCount);
    }
}
