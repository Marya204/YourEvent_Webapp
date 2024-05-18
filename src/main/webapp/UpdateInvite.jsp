<!DOCTYPE html>
<html>
<head>
<title>Votre Événement - Mise à jour de l'Invitation</title>
<link href="css/index-styles.css" rel="stylesheet">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhzjIBNhwv6E92vN" crossorigin="anonymous">
</head>
<body>
    <br>
    <div class="container col-md-5">
        <div class="card">
            <div class="card-body">
                <h2>Update Invite</h2>
                <form action="InviteServlet?action=update" method="post">
                    <input type="hidden" name="inviteId" value="<c:out value='${invite.Inviteid}' />" />

                    <fieldset class="form-group">
                        <label>Nom</label>
                        <input type="text" value="<c:out value='${invite.Name}' />" class="form-control" name="name" required="required">
                    </fieldset>

                    <fieldset class="form-group">
                        <label>Email</label>
                        <input type="text" value="<c:out value='${invite.Email}' />" class="form-control" name="email">
                    </fieldset>

                    <fieldset class="form-group">
                        <label>Event ID</label>
                        <input type="text" value="<c:out value='${invite.Eventid}' />" class="form-control" name="eventId" required="required">
                    </fieldset>

                    <button type="submit" class="btn btn-success">Save</button>
                </form>
            </div>
        </div>
    </div>
</body>
</html>
