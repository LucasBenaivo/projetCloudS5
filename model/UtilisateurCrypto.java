/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Toky
 */
public class UtilisateurCrypto {
    private int idUtilisateur;
    private int idCrypto;
    private String nomCrypto;
    private double quantiteDetenue;

    public int getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(int idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    public int getIdCrypto() {
        return idCrypto;
    }

    public void setIdCrypto(int idCrypto) {
        this.idCrypto = idCrypto;
    }

    public String getNomCrypto() {
        return nomCrypto;
    }

    public void setNomCrypto(String nomCrypto) {
        this.nomCrypto = nomCrypto;
    }

    public double getQuantiteDetenue() {
        return quantiteDetenue;
    }

    public void setQuantiteDetenue(double quantiteDetenue) {
        this.quantiteDetenue = quantiteDetenue;
    }
    
    public List<UtilisateurCrypto> getByIdUser(Connection connection) throws SQLException {
        List<UtilisateurCrypto> result = new ArrayList<>();
        String query = "SELECT * FROM v_utilisateur_crypto WHERE id_utilisateur = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, idUtilisateur);
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            UtilisateurCrypto uc = new UtilisateurCrypto();
            uc.setIdUtilisateur(resultSet.getInt("id_utilisateur"));
            uc.setIdCrypto(resultSet.getInt("id_crypto"));
            uc.setNomCrypto(resultSet.getString("nom_crypto"));
            uc.setQuantiteDetenue(resultSet.getDouble("quantite_detenue"));
            result.add(uc);
        }

        resultSet.close();
        statement.close();
        return result;
    }
}
