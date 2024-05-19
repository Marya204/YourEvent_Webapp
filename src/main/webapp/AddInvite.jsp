<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %><!DOCTYPE html>
<html>
<head>
<title>Your Event - Add Invite</title>
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
                             <c:if test="${invite == null}"> 
                                   Add Invite
                             </c:if>
                          </h2>
                      </caption>
                   <form action="InviteServlet?action=add" method="post">
                         <c:if test="${invite != null}"> 
                              <input type="hidden" name="inviteid" value="<c:out value='${invite.inviteid}' />" />
                         </c:if>

            <fieldset class="form-group">
                <label>Name</label> 
                <input type="text" value="<c:out value='${invite.name}' />" class="form-control" name="name" required="required">
            </fieldset>

            <fieldset class="form-group">
                <label>Email</label> 
                <input type="text" value="<c:out value='${invite.email}' />" class="form-control" name="email">
            </fieldset>
            <fieldset class="form-group">
                <label>Enviteid</label> 
                <input type="text" value="<c:out value='${invite.evenid}' />" class="form-control" name="eventid">
            </fieldset>

            <button type="submit" class="btn btn-success">Save</button>
            </form>
        </div>
    </div>
</div>
</body>
</html>

