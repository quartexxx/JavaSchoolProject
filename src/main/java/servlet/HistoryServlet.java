package servlet;

import model.FractionCalculatorModel;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * The HistoryServlet class handles requests for viewing the calculation
 * history.
 *
 * @author Remigiusz Kocialski
 * @version 1.0
 */
@WebServlet("/history")
public class HistoryServlet extends HttpServlet {

    private FractionCalculatorModel model;

    /**
     * Initializes the HistoryServlet by obtaining an instance of the
     * FractionCalculatorModel.
     */
    @Override
    public void init() {
        model = FractionCalculatorModel.getInstance();
    }

    /**
     * Handles GET requests to display the calculation history.
     *
     * @param req The HttpServletRequest object.
     * @param resp The HttpServletResponse object.
     * @throws IOException If an input or output exception occurs.
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        getHistory(req, resp);
    }

    /**
     * Displays the calculation history.
     *
     * @param req The HttpServletRequest object.
     * @param resp The HttpServletResponse object.
     * @throws IOException If an input or output exception occurs.
     */
    private void getHistory(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html");
        try (PrintWriter out = resp.getWriter()) {
            out.println("History of calculations: </br>");

            // Iterate through the history and print each calculation
            model.getHistory().forEach((historyEntry) -> {
                out.println(historyEntry + "</br>");
            });

            out.println("<a id=\"backbutton\" href=\"/serwer/\">Go back</a>");
        }
    }

    /**
     * Handles POST requests to display the calculation history.
     *
     * @param req The HttpServletRequest object.
     * @param resp The HttpServletResponse object.
     * @throws IOException If an input or output exception occurs.
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        getHistory(req, resp);
    }
}
