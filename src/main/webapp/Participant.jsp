<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Liste des Participants</title>
    <link rel="stylesheet" href="css/style.css">
    <link href="css/index-styles.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhzjIBNhwv6E92vN" crossorigin="anonymous">
</head>
<body>
    
        <h3>Liste des Participants</h3>
                <table class="table table-bordered">
                    <thead>
                        <tr>
                            <th>PartcipantID</th>
                            <th>Name</th>
                            <th>Email</th>
                            <th>Eventid</th>
                        
                        </tr>
                    </thead>
                    <tbody>
                        
                        <c:forEach var="partcipant" items="${listparticipant}">
                            <tr>
                                <td><c:out value="${participant.ParticipantID}" /></td>
                                <td><c:out value="${participant.Name}" /></td>
                                <td><c:out value="${participant.Email}" /></td>
                                <td><c:out value="${participant.Eventid}" /></td>
                              
                            </tr>
                            </c:forEach>
                            <c:if test="${empty listparticipant}">
                            <tr>
                                <td colspan="8">No participants found.</td>
                         </tr>
                        </c:if>
                    </tbody> 
                </table>
        <a href="AddParticipant.jsp" class="btn btn-primary">Add Participant</a>
        <a href="${pageContext.request.contextPath}/ParticipantServlet?action=edit&ParticipantID=${participant.ParticipantID}" class="btn btn-primary">Update</a>
        <a href="${pageContext.request.contextPath}/ParticipantServlet?action=delete&ParticipantId=${participant.Pid}" class="btn btn-primary">Delete</a>

    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js" integrity="sha384-eMN9FxVmF/oe3og+1Q0qFbg76QIKGzXtWfZkU6K7LltKGAO5MZGT5GkiOfkI5Xr9" crossorigin="anonymous"></script>
   <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js" integrity="sha384-pzjw8f+ua7Kw1TIq5XrU4F6LR3Ou4b+e2ABdDa5cXQJKk3qxz5stQNmwjeSIExlB" crossorigin="anonymous"></script>

