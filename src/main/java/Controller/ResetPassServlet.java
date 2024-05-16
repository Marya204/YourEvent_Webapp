package Controller;

import java.io.IOException;
import java.sql.SQLException;

import model.UserDAO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ResetPassServlet", urlPatterns = {"/ResetPassServlet"})
public class ResetPassServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Redirection vers la page de réinitialisation du mot de passe
        request.getRequestDispatcher("ResetPass.jsp").forward(request, response);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String newPassword = request.getParameter("newPassword");
        String confirmNewPassword = request.getParameter("confirmNewPassword");

        // Vérifier si les deux mots de passe correspondent
        if (!newPassword.equals(confirmNewPassword)) {
            request.setAttribute("error", "Les mots de passe ne correspondent pas");
            request.getRequestDispatcher("ResetPass.jsp").forward(request, response);
            return;
        }

        // Réinitialiser le mot de passe dans la base de données
        UserDAO userDAO = new UserDAO();
        try {
            userDAO.resetPassword(email, newPassword);
            // Afficher un message de confirmation
            request.setAttribute("message", "Votre mot de passe a été réinitialisé avec succès. Veuillez vous connecter avec votre nouveau mot de passe.");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            throw new ServletException("Erreur d'accès à la base de données", e);
        }
    }
}
