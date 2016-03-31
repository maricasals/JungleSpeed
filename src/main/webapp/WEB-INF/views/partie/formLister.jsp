<%-- 
    Document   : lister
    Created on : 30 mars 2016, 14:38:49
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<h1>LIST DES PARTIES :</h1>
<table>
    <tr>
        <td>
            <label>Nom Partie</label>
        </td>
        <td>
            <label>Etat</label>
        </td>
        <td></td>
    </tr>
    <c:forEach items="${PartiesDispo}" var="partie">
        <tr>
            <td>
                ${partie.nom}
                <br>
            </td>
            <td>
                ${partie.etat}
                <br>
            </td>
            <td>
                <input type="button" onclick="chargeContenu('new_joueur/'+${partie.id})" value="Jouer"/>
                <br> 
            </td>
        </tr>
    </c:forEach>
</table>
