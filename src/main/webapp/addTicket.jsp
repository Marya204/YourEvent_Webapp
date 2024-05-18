<!DOCTYPE html>
<html>
<head>
<title>Your Event - Add Ticket</title>
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
                             <c:if test="${billet == null}"> 
                                   Add Ticket
                             </c:if>
                          </h2>
                      </caption>
                   <form action="TicketServlet?action=add" method="post">
                         <c:if test="${billet != null}"> 
                              <input type="hidden" name="id" value="<c:out value='${billet.id}' />" />
                         </c:if>

            <fieldset class="form-group">
                <label>Id</label> 
                <input type="text" value="<c:out value='${billet.id}' />" class="form-control" name="id" required="required">
            </fieldset>

            <fieldset class="form-group">
                <label>Eventid</label> 
                <input type="text" value="<c:out value='${billet.eventid}' />" class="form-control" name="eventid">
            </fieldset>

            <fieldset class="form-group">
                <label>Inviteid</label> 
                <input type="text" value="<c:out value='${billet.inviteid}' />" class="form-control" name="inviteid">
            </fieldset>

            <fieldset class="form-group">
                <label>Price</label> 
                <input type="text" value="<c:out value='${billet.price}' />" class="form-control" name="price">
            </fieldset>

            <fieldset class="form-group">
                <label>Status</label> 
                <input type="text" value="<c:out value='${billet.status}' />" class="form-control" name="status">
            </fieldset>
            <button type="submit" class="btn btn-success">Save</button>
            </form>
        </div>
    </div>
</div>
</body>
</html>

