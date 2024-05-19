<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Liste des Événements</title>
    <link rel="stylesheet" href="css/style.css">
    <link href="css/index-styles.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhzjIBNhwv6E92vN" crossorigin="anonymous">
</head>
<body>
    
        <h3>Liste des Événements</h3>
                <table class="table table-bordered">
                    <thead>
                        <tr>
                            <th>Event Id</th>
                            <th>Titre</th>
                            <th>Description</th>
                            <th>Date</th>
                            <th>Lieu</th>
                            <th>Type</th>
                            <th>Status</th>
                            <th>Prix</th>
                            <th>Capacite</th>
                        </tr>
                    </thead>
                    <tbody>
                        
                        <c:forEach var="event" items="${listevent}">
                            <tr>
                                <td><c:out value="${event.eventid}" /></td>
                                <td><c:out value="${event.titre}" /></td>
                                <td><c:out value="${event.description}" /></td>
                                <td><c:out value="${event.date}" /></td>
                                <td><c:out value="${event.lieu}" /></td>
                                <td><c:out value="${event.type}" /></td>
                                <td><c:out value="${event.status}" /></td>
                                <td><c:out value="${event.prix}" /></td>
                                <td><c:out value="${event.capacite}" /></td>
                            </tr>
                            </c:forEach>
                            <c:if test="${empty listevent}">
                            <tr>
                                <td colspan="8">No events found.</td>
                         </tr>
                        </c:if>
                    </tbody> 
                </table>
        <a href="AddEvent.jsp" class="btn btn-primary">Add Event</a>
        <a href="edit?eventid=<c:out value='${event.eventid}' /> "class="btn btn-primary">Update</a>
                                    &nbsp;&nbsp;&nbsp;&nbsp; 
        <a href="delete?eventid=<c:out value='${event.eventid}' />"class="btn btn-primary">Delete</a></td>

    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js" integrity="sha384-eMN9FxVmF/oe3og+1Q0qFbg76QIKGzXtWfZkU6K7LltKGAO5MZGT5GkiOfkI5Xr9" crossorigin="anonymous"></script>
   <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js" integrity="sha384-pzjw8f+ua7Kw1TIq5XrU4F6LR3Ou4b+e2ABdDa5cXQJKk3qxz5stQNmwjeSIExlB" crossorigin="anonymous"></script>

 
