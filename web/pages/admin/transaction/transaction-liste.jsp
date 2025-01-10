<%@page import="model.ListeTransaction"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

    <title>Recherche de Transactions</title>
    <%@ include file="../header.jsp" %>
    
    <div class="breadcrumbs">
        <div class="breadcrumbs-inner">
            <div class="row m-0">
                <div class="col-sm-8">
                    <div class="page-header float-left">
                        <div class="page-title">
                            <ol class="breadcrumb text-right">
                                <li><a>Home</a></li>
                                <li><a>Transactions</a></li>
                                <li class="active">Liste</li>
                            </ol>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div class="content">
        <div class="animated fadeIn">
            <div class="row">

                <div class="col-lg-10 offset-md-1">
                    <form action="ListeTransactionServlet" method="post">
                        <div class="card">
                            <div class="card-header">
                                <strong>Recherche avancee</strong> 
                            </div>
                            
                            <div class="card-body">
                                <div class="row"> 
                                    <div class="col-4">
                                        <div class=" form-group">
                                            <label for="utilisateur" class="control-label mb-1">Utilisateur:</label>
                                            <input type="text" id="utilisateur" name="utilisateur" class="form-control">            
                                        </div>
                                    </div>
   
                                    <div class="col-4">
                                                    
                                        <div class=" form-group">
        
                                            <label for="dateDebut" class="control-label mb-1">DateDebut:</label>
        
                                            <input type="date" id="dateDebut" name="dateDebut" class="form-control" value="<%= request.getAttribute("dateDebut") != null ? request.getAttribute("dateDebut") : "" %>">
                             
                                        </div>
                                                
                                    </div>

                                                 
                                    <div class="col-4">
                                                    
                                        <div class=" form-group">
        
                                            <label for="dateFin" class="control-label mb-1">DateFin:</label>
        
                                            <input type="date" id="dateFin" name="dateFin" class="form-control" value="<%= request.getAttribute("dateFin") != null ? request.getAttribute("dateFin") : "" %>">
                                                    
                                        </div>
                                                
                                    </div>

                                    <div class="col-4">
                                                    
                                        <div class=" form-group">
        
                                            <label for="cryptomonnaie" class="control-label mb-1">Cryptomonnaie:</label>
        
                                            <input type="text" id="cryptomonnaie" name="cryptomonnaie" class="form-control" value="<%= request.getAttribute("cryptomonnaie") != null ? request.getAttribute("cryptomonnaie") : "" %>">
                                                    
                                        </div>
                                                
                                    </div>

                                    <div class="col-4">
                                        <div class="form-group">
                                        
                                            <label for="typeTransaction">TypeTransaction:</label>
                                        

                                            <select id="typeTransaction" name="typeTransaction" class="form-control">
                                                <option value="" <%= (request.getAttribute("typeTransaction") == null || "".equals(request.getAttribute("typeTransaction"))) ? "selected" : "" %>>Choix du type de transaction</option>
                                                <option value="Achat" <%= "Achat".equals(request.getAttribute("typeTransaction")) ? "selected" : "" %>>Achat</option>
                                                <option value="Vente" <%= "Vente".equals(request.getAttribute("typeTransaction")) ? "selected" : "" %>>Vente</option>
                                            </select>
                                        </div>
                                      
                                    </div> 


                                                 
                                    
                                </div>

                                <div class="row">

                                      
                                    <div class="col-4">
                                        
                                        <div class="form-group">
                                            <label for="orderBy">Colonne:</label>
                                            <select id="orderBy" name="orderBy" class="form-control">
                                                <option value="">Sélectionnez l'ordre</option>
                                                <option value="utilisateur" <%= "utilisateur".equals(request.getAttribute("orderBy")) ? "selected" : "" %>>Utilisateur</option>
                                                <option value="dateheure" <%= "dateheure".equals(request.getAttribute("orderBy")) ? "selected" : "" %>>Date/Heure</option>
                                                <option value="cryptomonnaie" <%= "cryptomonnaie".equals(request.getAttribute("orderBy")) ? "selected" : "" %>>Cryptomonnaie</option>
                                                <option value="type_transaction" <%= "type_transaction".equals(request.getAttribute("orderBy")) ? "selected" : "" %>>Type de transaction</option>
                                                <option value="quantite" <%= "quantite".equals(request.getAttribute("orderBy")) ? "selected" : "" %>>Quantité</option>
                                            </select>
                                        </div>
                                      
                                    </div> 

                                      
                                    <div class="col-4"> 
                                        <div class="form-group">
                                        <label for="ascOrDesc" class="form-control-label">Order:</label>
                                        <select id="ascOrDesc" name="ascOrDesc" class="form-control">
                                            <option value="asc" <%= "asc".equals(request.getAttribute("ascOrDesc")) ? "selected" : "" %>>ASC</option>
                                            <option value="desc" <%= "desc".equals(request.getAttribute("ascOrDesc")) ? "selected" : "" %>>Desc</option>
                                        </select>
                                        </div>
                                      </div>

                                    </div>
                
                                
                            </div>
                            <div class="card-footer">
                                <button class="btn btn-primary btn-sm" type="submit"><i class="fa fa-dot-circle-o"></i> Afficher</button>                   
                            </div>

                            
                        </div>
            
                    </form>
                    
                </div>


<%
    // Récupérer la liste des transactions depuis la requête
    List<ListeTransaction> transactions = (List<ListeTransaction>) request.getAttribute("transactions");

    if (transactions != null && !transactions.isEmpty()) {
%>
                <div class="col-lg-10 offset-md-1">
                        
                    <div class="card">
                            
                        <div class="card-header">
                                
                            <strong class="card-title">Liste des transactions</strong>
                            
                        </div>
                            
                        <div class="card-body">
                                
                            <table class="table">
                                    
                                <thead>
                                        
                                    <tr>
      
                                        <th>Utilisateur</th>
                                        <th>Date/Heure</th>
                                        <th>Cryptomonnaie</th>
                                        <th>Type</th>
                                        <th>Quantité</th>

                                      
                                    </tr>
                                  
                                </thead>
                                 
                                <tbody>
                                    <%
                                        for (ListeTransaction transaction : transactions) {
                                    %>
                                    <tr>
                                        <td><%= transaction.getUtilisateur() %></td>
                                        <td><%= transaction.getDateHeure() %></td>
                                        <td><%= transaction.getCryptomonnaie() %></td>
                                        <td><%= transaction.getTypeTransaction() %></td>
                                        <td><%= transaction.getQuantite() %></td>
                                    </tr>
                                    <%
                                        }
                                    %>
                                </tbody>
                            </table>
                        </div>
                    </div>
                  
                </div>
                 <%
                    }
                %>


            </div>
            
        </div>
        
    </div><!-- .animated -->
    
</div><!-- .content -->
<%@ include file="../footer.jsp" %>
