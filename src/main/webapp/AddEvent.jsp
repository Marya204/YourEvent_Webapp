<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Your Event - Add Event</title>
<link href="css/index-styles.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhzjIBNhwv6E92vN" crossorigin="anonymous">
</head>
<body>

<div class="container col-md-5">
    <div class="card">
        <div class="card-body">
                        <c:if test="${event == null}">
                            <form action="${pageContext.request.contextPath}/EventServlet?action=add" method="POST">
                            </c:if>
                            <caption>
                                <h2>
                                    <c:if test="${event != null}">
                                        Update
                                    </c:if>
                                    <c:if test="${event == null}">
                                        Add Event
                                    </c:if>
                                </h2>
                            </caption>
                       
                         <c:if test="${event != null}"> 
                              <input type="hidden" name="Eventid" value="<c:out value='${event.eventid}' />" />
                         </c:if>

            <fieldset class="form-group">
                <label>Title</label> 
                <input type="text" value="<c:out value='${event.titre}' />" class="form-control" name="Titre" required="required">
            </fieldset>

            <fieldset class="form-group">
                <label>Description</label> 
                <input type="text" value="<c:out value='${event.description}' />" class="form-control" name="Description">
            </fieldset>

            <fieldset class="form-group">
              <label>Date</label> 
              <input type="date" value="<c:out value='${event.date}' />" class="form-control" name="Date">
            </fieldset>
 

            <fieldset class="form-group">
                <label>Location</label> 
                <input type="text" value="<c:out value='${event.lieu}' />" class="form-control" name="Lieu">
            </fieldset>

            <fieldset class="form-group">
                <label>Type</label> 
                <input type="text" value="<c:out value='${event.type}' />" class="form-control" name="Type">
            </fieldset>
            <fieldset class="form-group">
                <label>Status</label> 
                <input type="text" value="<c:out value='${event.status}' />" class="form-control" name="Status">
            </fieldset>
            <fieldset class="form-group">
                <label>Price</label> 
                <input type="text" value="<c:out value='${event.prix}' />" class="form-control" name="Prix">
            </fieldset>
            <fieldset class="form-group">
                <label>Capacity</label> 
                <input type="text" value="<c:out value='${event.capacite}' />" class="form-control" name="Capacite">
            </fieldset>

            <button type="submit" class="btn btn-success">Save</button>
            </form>
        </div>
    </div>
</div>
</body>
</html>

