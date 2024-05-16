<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>Reset Password - YourEvent</title>

<!-- Font Icon -->
<link rel="stylesheet" href="fonts/material-icon/css/material-design-iconic-font.min.css">

<!-- Main css -->
<link rel="stylesheet" href="css/style.css">
</head>
<body>
    <div class="main">

        <!-- Sign in Form -->
        <section class="sign-in">
            <div class="container">
                <div class="signin-content">
                    <div class="signin-form">
                        <h2 class="form-title">Reset Password</h2>
                        <form method="post" action="${pageContext.request.contextPath}/ResetPass" class="login-form">
                            
                            <div class="form-group">
                                <label for="email"><i class="zmdi zmdi-account material-icons-name"></i></label>
                                <input type="text" name="email" id="email" placeholder="Your Email" required />
                            </div>
                            <div class="form-group">
                                <label for="newPassword"><i class="zmdi zmdi-lock"></i></label>
                                <input type="password" name="newPassword" id="newPassword" placeholder="New Password" required />
                            </div>
                            <div class="form-group">
                                <label for="confirmNewPassword"><i class="zmdi zmdi-lock"></i></label>
                                <input type="password" name="confirmNewPassword" id="confirmNewPassword" placeholder="Confirm Password" required />
                            </div>
                            
                            <div class="form-group form-button">
                                <input type="submit" name="resetPassword" class="form-submit" value="Reset Password" />
                            </div>    
                            
                           <div class="form-group form-button">
                                 <a href="${pageContext.request.contextPath}/login" class="form-submit">Cancel</a>
                           </div>
                            <c:if test="${not empty error}">
                               <div class="error-message">${error}</div>
                            </c:if>

                            <c:if test="${not empty message}">
                               <div class="success-message">${message}</div>
                            </c:if>
                            
                         </form>
                    </div>
                      
                    <div class="signup-image">
                         <figure>
                            <img src="images/yedesigne2.png" alt="logo YourEvent">
                         </figure>
                    </div>
                </div>
            </div>
        </section>
    </div>

    <!-- JS -->
    <script src="vendor/jquery/jquery.min.js"></script>
    <script src="js/main.js"></script>
</body>
<!-- This templates was made by Colorlib (https://colorlib.com) -->
</html>
