<%-- 
    Document   : rejoindre
    Created on : 30 mars 2016, 15:19:14
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<form:form modelAttribute="newJoueur" action="new_jouer" method="post" id="formInscription">
    <table>
        <tr>
            <td>
                Nom : <form:input path="nom"></form:input>
            </td>
            <td>
                MDP : <form:password path="mdp"/>
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <input type="button" value="Rejoindre" onclick="ajoutJoueur(${partie.id})"/>
            </td>
        </tr>
    </table>
</form:form>

