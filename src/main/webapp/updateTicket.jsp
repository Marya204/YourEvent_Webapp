<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>Update Ticket - YourEvent</title> <!-- Modification du titre de la page -->
<!-- Font Icon -->
<link rel="stylesheet" href="fonts/material-icon/css/material-design-iconic-font.min.css">
<!-- Main css -->
<link rel="stylesheet" href="css/style.css">
</head>
<body class="ticket-form">
    <div class="main">
        <section>
            <div class="container">
                <div class="signin-content">
                    <div class="signin-form">
                        <h2 class="form-title">Update Ticket</h2> <!-- Modification du titre du formulaire -->
                        <form method="post" action="TicketServlet" class="login-form" id="update-ticket-form">
                            <!-- Champ hidden pour l'ID du billet -->
                            <input type="hidden" name="ticketId" value="${ticket.id}" />
                            <div class="form-group">
                                <label for="eventId"><i class="zmdi zmdi-account material-icons-name"></i></label>
                                <input type="text" name="eventId" id="eventId" placeholder="Event ID" aria-label="Event ID" value="${ticket.eventId}" required />
                            </div>
                            <div class="form-group">
                                <label for="inviteId"><i class="zmdi zmdi-lock"></i></label>
                                <input type="text" name="inviteId" id="inviteId" placeholder="Invite ID" aria-label="Invite ID" value="${ticket.inviteId}" required />
                            </div>
                            <div class="form-group">
                                <label for="price"><i class="zmdi zmdi-lock"></i></label>
                                <input type="text" name="price" id="price" placeholder="Price" aria-label="Price" value="${ticket.price}" required />
                            </div>
                            <div class="form-group">
                                <label for="status"><i class="zmdi zmdi-lock"></i></label>
                                <input type="text" name="status" id="status" placeholder="Status" aria-label="Status" value="${ticket.status}" required />
                            </div>
                            <div class="form-group form-button">
                                <input type="submit" name="update-ticket" id="update-ticket" class="form-submit" value="Update Ticket" /> <!-- Modification du texte du bouton -->
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </section>
    </div>
    <!-- JS -->
    <script src="vendor/jquery/jquery.min.js"></script>
    <script src="js/main.js"></script>
</body>
</html>
