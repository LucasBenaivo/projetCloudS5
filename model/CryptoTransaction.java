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
import java.sql.Statement;

/**
 *
 * @author Toky
 */
public class CryptoTransaction {
    
     private Connection connection;

    public CryptoTransaction(Connection connection) {
        this.connection = connection;
    }

    public CryptoTransaction() {
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
     
     
     
    public void insererAchat(int idUtilisateur, String idCrypto, double quantite, double prixUnitaire) throws SQLException {
        // Démarrer une transaction pour garantir l'atomicité
        connection.setAutoCommit(false);

        try (PreparedStatement stmtTransaction = connection.prepareStatement(
                "INSERT INTO transaction (id_utilisateur, id_crypto, entree, prixunitaire) VALUES (?, (select id_crypto from crypto where nom=?), ?, ?)");
            PreparedStatement stmtMvtWallet = connection.prepareStatement(
                "INSERT INTO mvtwallet (id_utilisateur, retrait) VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS);
             PreparedStatement stmtMvtWalletValid = connection.prepareStatement(
                "INSERT INTO mvtwalletvalid (id) VALUES (?)")) {

            // Calculer le montant total du retrait
            double montantRetrait = quantite * prixUnitaire;

            // Exécuter les requêtes
            stmtTransaction.setInt(1, idUtilisateur);
            stmtTransaction.setString(2, idCrypto);
            stmtTransaction.setDouble(3, quantite);
            stmtTransaction.setDouble(4, prixUnitaire);
            stmtTransaction.executeUpdate();

            stmtMvtWallet.setInt(1, idUtilisateur);
            stmtMvtWallet.setDouble(2, montantRetrait);
            stmtMvtWallet.executeUpdate();

            // Récupérer l'ID généré
            ResultSet generatedKeys = stmtMvtWallet.getGeneratedKeys();
            if (generatedKeys.next()) {
                long idMvtWallet = generatedKeys.getInt(1);

                // Insérer l'ID dans mvtwalletvalid
                stmtMvtWalletValid.setLong(1, idMvtWallet);
                stmtMvtWalletValid.executeUpdate();
            } else {
                throw new SQLException("Aucun ID généré pour mvtwallet");
            }

            // Valider la transaction
            connection.commit();
        } catch (SQLException e) {
            // Annuler la transaction en cas d'erreur
            connection.rollback();
            throw e;
        } finally {
            // Rétablir l'autocommit par défaut
            connection.setAutoCommit(true);
        }
    }

    public void insererVente(int idUtilisateur, String idCrypto, double quantite, double prixUnitaire) throws SQLException {
        // Démarrer une transaction pour garantir l'atomicité
        connection.setAutoCommit(false);

        try (PreparedStatement stmtTransaction = connection.prepareStatement(
                "INSERT INTO transaction (id_utilisateur, id_crypto, sortie, prixunitaire) VALUES (?, (select id_crypto from crypto where nom=?), ?, ?)");
            PreparedStatement stmtMvtWallet = connection.prepareStatement(
                "INSERT INTO mvtwallet (id_utilisateur, depot) VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS);
             PreparedStatement stmtMvtWalletValid = connection.prepareStatement(
                "INSERT INTO mvtwalletvalid (id) VALUES (?)")) {

            // Calculer le montant total du retrait
            double montantRetrait = quantite * prixUnitaire;

            // Exécuter les requêtes
            stmtTransaction.setInt(1, idUtilisateur);
            stmtTransaction.setString(2, idCrypto);
            stmtTransaction.setDouble(3, quantite);
            stmtTransaction.setDouble(4, prixUnitaire);
            stmtTransaction.executeUpdate();

            stmtMvtWallet.setInt(1, idUtilisateur);
            stmtMvtWallet.setDouble(2, montantRetrait);
            stmtMvtWallet.executeUpdate();

            // Récupérer l'ID généré
            ResultSet generatedKeys = stmtMvtWallet.getGeneratedKeys();
            if (generatedKeys.next()) {
                long idMvtWallet = generatedKeys.getInt(1);

                // Insérer l'ID dans mvtwalletvalid
                stmtMvtWalletValid.setLong(1, idMvtWallet);
                stmtMvtWalletValid.executeUpdate();
            } else {
                throw new SQLException("Aucun ID généré pour mvtwallet");
            }

            // Valider la transaction
            connection.commit();
        } catch (SQLException e) {
            // Annuler la transaction en cas d'erreur
            connection.rollback();
            throw e;
        } finally {
            // Rétablir l'autocommit par défaut
            connection.setAutoCommit(true);
        }
    }

}
