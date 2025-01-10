/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import data.EmailHTML;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import javax.servlet.http.HttpServletRequest;
import util.MailSender;

/**
 *
 * @author Toky
 */
public class MouvementFiatWallet {
    private int idmvtwallet;
    private Timestamp dateheure;
    private double depot;
    private double retrait;
    private int id_utilisateur;

    public MouvementFiatWallet(int idmvtwallet, Timestamp dateheure, double depot, double retrait, int id_utilisateur) {
        this.idmvtwallet = idmvtwallet;
        this.dateheure = dateheure;
        this.depot = depot;
        this.retrait = retrait;
        this.id_utilisateur = id_utilisateur;
    }

    public MouvementFiatWallet() {
    }

    public int getIdmvtwallet() {
        return idmvtwallet;
    }

    public void setIdmvtwallet(int idmvtwallet) {
        this.idmvtwallet = idmvtwallet;
    }

    public Timestamp getDateheure() {
        return dateheure;
    }

    public void setDateheure(Timestamp dateheure) {
        this.dateheure = dateheure;
    }

    public double getDepot() {
        return depot;
    }

    public void setDepot(double depot) {
        this.depot = depot;
    }

    public double getRetrait() {
        return retrait;
    }

    public void setRetrait(double retrait) {
        this.retrait = retrait;
    }

    public int getId_utilisateur() {
        return id_utilisateur;
    }

    public void setId_utilisateur(int id_utilisateur) {
        this.id_utilisateur = id_utilisateur;
    }

    

    public void insert(Connection conn,String email,HttpServletRequest request) throws SQLException {
        // Requête préparée pour éviter les injections SQL
        String sql = "INSERT INTO mvtwallet (depot, retrait, id_utilisateur) VALUES (?, ?, ?)";
        PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS); 
        stmt.setDouble(1, depot);
        stmt.setDouble(2, retrait);
        stmt.setInt(3, id_utilisateur);
        int rowsAffected = stmt.executeUpdate();

        if (rowsAffected > 0) {
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int idmvtwallet = generatedKeys.getInt(1); 
                    // Utilisez l'ID généré ici
                    System.out.println("ID généré : " + idmvtwallet);
                    
                    MailSender sender = new MailSender();
        
                    // Remplacez les valeurs par vos informations
                    String recipient = email;
                    String subject = "Validation transaction";
                    String contextPath = request.getContextPath();
                    String baseUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + contextPath;

                    String body = EmailHTML.confirmationTransactionEmail(baseUrl+"/ValidationMouvementServlet", idmvtwallet+"");

                    sender.sendEmail(recipient, subject, body);
                } else {
                    throw new SQLException("Creating user failed, no ID obtained.");
                }
            }
        }

        stmt.close();
    }
    
    public void valider(Connection conn) throws SQLException {
        // Requête préparée pour éviter les injections SQL
        String sql = "INSERT INTO mvtwalletvalid (id) VALUES (?)";
        PreparedStatement stmt = conn.prepareStatement(sql); 
        stmt.setInt(1, idmvtwallet);
        stmt.executeUpdate();

        stmt.close();
    }
}
