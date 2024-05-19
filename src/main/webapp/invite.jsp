<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %><!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Liste des Inviters</title>
    <link rel="stylesheet" href="css/style.css">
    <link href="css/index-styles.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhzjIBNhwv6E92vN" crossorigin="anonymous">
</head>
<body>
    
        <h3>Liste des Inviters</h3>
                <table class="table table-bordered">
                    <thead>
                        <tr>
                            <th>Invite Id</th>
                            <th>Name</th>
                            <th>Email</th>
                            <th>Eventid</th>
                        </tr>
                    </thead>
                    <tbody>
                        
                        <c:forEach var="invite" items="${listinvite}">
                            <tr>
                                <td><c:out value="${invite.inviteid}" /></td>
                                <td><c:out value="${invite.name}" /></td>
                                <td><c:out value="${invite.email}" /></td>
                                <td><c:out value="${invite.eventid}" /></td>
                            </tr>
                            </c:forEach>
                            <c:if test="${empty listinvite}">
                            <tr>
                                <td colspan="8">No invite found.</td>
                         </tr>
                        </c:if>
                    </tbody> 
                </table>
        <a href="AddInvite.jsp" class="btn btn-primary">Add Invite</a>
        <a href="UpdateInvite.jsp" class="btn btn-primary">Update Invite</a>
        <a href="${pageContext.request.contextPath}/InviteServlet?action=delete&inviteId=${invite.Inviteid}" class="btn btn-primary">Delete</a>
