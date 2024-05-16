package Model;



public class User {
    private String email;
    private String password; // Stockez les mots de passe hachés en production

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    // Getters et Setters

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
