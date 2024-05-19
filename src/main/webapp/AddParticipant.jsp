<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Your Event - Add Participant</title>
<link href="css/index-styles.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhzjIBNhwv6E92vN" crossorigin="anonymous">
</head>
<body>


<br>
<div class="container col-md-5">
    <div class="card">
        <div class="card-body">
                       <caption>
                         <h2>
                             <c:if test="${participant == null}"> 
                                   Add Participant
                             </c:if>
                          </h2>
                      </caption>
                   <form action="ParticipantServlet?action=add" method="post">
                         <c:if test="${participant != null}"> 
                              <input type="hidden" name="participantID" value="<c:out value='${participant.participantID}' />" />
                         </c:if>

            <fieldset class="form-group">
                <label>Name</label> 
                <input type="text" value="<c:out value='${participant.name}' />" class="form-control" name="name" required="required">
            </fieldset>

            <fieldset class="form-group">
                <label>Email</label> 
                <input type="text" value="<c:out value='${participant.email}' />" class="form-control" name="email">
            </fieldset>

            <fieldset class="form-group">
              <label>Eventid</label> 
              <input type="text" value="<c:out value='${participant.eventid}' />" class="form-control" name="eventid">
            </fieldset>

            <button type="submit" class="btn btn-success">Save</button>
            </form>
        </div>
    </div>
</div>
</body>
</html>


