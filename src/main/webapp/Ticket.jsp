<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
    <meta name="description" content="" />
    <meta name="author" content="" />
    <title>Tickets Manager - Your Event</title> <!-- Modification du titre de la page -->
    <!-- Favicon-->
    <link rel="icon" type="image/x-icon" href="assets/favicon.ico" />
    <!-- Font Awesome icons (free version)-->
    <script src="https://use.fontawesome.com/releases/v5.15.4/js/all.js" crossorigin="anonymous"></script>
    <!-- Google fonts-->
    <link href="https://fonts.googleapis.com/css?family=Montserrat:400,700" rel="stylesheet" type="text/css" />
    <link href="https://fonts.googleapis.com/css?family=Lato:400,700,400italic,700italic" rel="stylesheet" type="text/css" />
    <!-- Core theme CSS (includes Bootstrap)-->
    <link href="css/index-styles.css" rel="stylesheet" />
    <!-- Custom styles -->
    <style>
        body {
            background-color: #f8f9fa; /* Couleur de fond */
        }
        
        .ticket-table {
            margin-top: 50px;
        }
        
        .btn-custom {
            background-color: #198754; /* Couleur des boutons */
            color: #ffffff; /* Couleur du texte des boutons */
            margin-right: 10px;
        }
        
        .search-btn {
            background-color: #198754; /* Couleur du bouton de recherche */
            color: #ffffff; /* Couleur du texte du bouton de recherche */
            float: right;
            margin-top: 10px;
            margin-right: 20px;
        }
        
        /* Style pour les boutons en bas */
        .btn-group-bottom {
            margin-top: 20px;
            text-align: center;
        }
    </style>
</head>
<body id="page-top">
    <!-- Navigation-->
    <nav class="navbar navbar-expand-lg bg-secondary text-uppercase fixed-top" id="mainNav">
        <div class="container">
            <a class="navbar-brand" href="#page-top">Your Event</a>
            <button class="navbar-toggler text-uppercase font-weight-bold bg-primary text-white rounded"
                type="button" data-bs-toggle="collapse" data-bs-target="#navbarResponsive" aria-controls="navbarResponsive"
                aria-expanded="false" aria-label="Toggle navigation">
                Menu <i class="fas fa-bars"></i>
            </button>
            <div class="collapse navbar-collapse" id="navbarResponsive">
                <ul class="navbar-nav ms-auto">
                    <li class="nav-item mx-0 mx-lg-1"><a class="nav-link py-3 px-0 px-lg-3 rounded" href="events.jsp">Events</a></li>
                    <li class="nav-item mx-0 mx-lg-1"><a class="nav-link py-3 px-0 px-lg-3 rounded" href="#about">Tickets</a></li>
                    <li class="nav-item mx-0 mx-lg-1"><a class="nav-link py-3 px-0 px-lg-3 rounded" href="#contact">Participants</a></li>
                    <li class="nav-item mx-0 mx-lg-1"><a class="nav-link py-3 px-0 px-lg-3 rounded" href="#contact">Guests</a></li>
                    <li class="nav-item mx-0 mx-lg-1"><a class="nav-link py-3 px-0 px-lg-3 rounded" href="">Logout</a></li>
                </ul>
            </div>
        </div>
    </nav>    
    <!-- Barre de recherche -->
    <div><form class="form-inline mt-2 mt-md-0" style="float: right; margin-right: 20px;">
        <div class="input-group">
            <input class="form-control" type="text" placeholder="Search" aria-label="Search">
            <div class="input-group-append">
                <button class="btn btn-outline-secondary" type="submit">
                    <i class="fas fa-search"></i> <!-- Icône de recherche -->
                </button>
            </div>
        </div>
    </form></div>
    <!-- Tickets Table-->
    <section class="masthead text-white text-center">
        <div class="container-fluid ticket-table"> <!-- Utilisation de container-fluid pour que le tableau prenne toute la largeur -->
            <table class="table table-borderless table-dark table-responsive"> <!-- Ajout de la classe table-responsive pour que le tableau soit réactif -->
                <thead>
                    <tr class="table-active">
                        <th scope="col">Ticket ID</th>
                        <th scope="col">Event ID</th>
                        <th scope="col">Invite ID</th>
                        <th scope="col">Price</th>
                        <th scope="col">Status</th>
                    </tr>
                </thead>
                <tbody>
                    <!-- Boucle pour afficher les tickets -->
                    <c:forEach var="ticket" items="${tickets}">
                        <tr>
                            <td>${ticket.id}</td>
                            <td>${ticket.eventId}</td>
                            <td>${ticket.inviteId}</td>
                            <td>${ticket.price}</td>
                            <td>${ticket.status}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </section>
    
    <!-- Boutons en bas -->
    <div class="btn-group-bottom">
        <a href="addTicket.jsp" class="btn btn-custom">Add Ticket</a>
        <a href="updateTicket.jsp" class="btn btn-custom">Update Ticket</a>
        <button class="btn btn-custom">Remove Ticket</button>

    </div>
    
    <!-- Bootstrap core JS-->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
    <!-- Core theme JS-->
    <script src="js/scripts.js"></script>
    <!-- * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *-->
    <!-- * *                               SB Forms JS                               * *-->
    <!-- * * Activate your form at https://startbootstrap.com/solution/contact-forms * *-->
    <!-- * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *-->
    <script src="https://cdn.startbootstrap.com/sb-forms-latest.js"></script>
</body>
</html>
