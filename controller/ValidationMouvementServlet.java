/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import acces.Connexion;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.MouvementFiatWallet;

/**
 *
 * @author Toky
 */
@WebServlet(name = "ValidationMouvementServlet", urlPatterns = {"/ValidationMouvementServlet"})
public class ValidationMouvementServlet extends HttpServlet {

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
            throws ServletException, IOException {
        // Récupération de l'id depuis la requête
        String idParam = request.getParameter("id");
        int id = Integer.parseInt(idParam);

        // Création d'une instance de MouvementFiatWallet
        MouvementFiatWallet mouvement = new MouvementFiatWallet();
        mouvement.setIdmvtwallet(id);
        
         try (Connection conn = new Connexion().getConnection()) {
            mouvement.valider(conn);
            // Créer la réponse JSON
            Map<String, Object> responseBody = new HashMap<>();
            responseBody.put("status", "Succes");
            responseBody.put("code", 200);
            responseBody.put("success", true); // Mettre à false en cas d'erreur
            responseBody.put("message", "Validation effectué avec succès");

            // Envoyer la réponse JSON
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            PrintWriter out = response.getWriter();
            out.print(new Gson().toJson(responseBody));
            out.flush();
        } catch (SQLException e) {
            // Gérer l'exception
            e.printStackTrace();
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
        processRequest(request, response);
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
        processRequest(request, response);
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
