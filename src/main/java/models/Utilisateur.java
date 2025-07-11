package models;

import java.util.Objects;

public class Utilisateur {
    private int id;
    private String username;
    private String email;
    private String password;
    private String role; // "Admin", "Client", or "Livreur"

    // Optional: useful for password reset in the future
    private String securityQuestion;
    private String securityAnswer;

    // Constructors
    public Utilisateur() {}

    public Utilisateur(int id, String username, String email, String password, String role) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public Utilisateur(String username, String email, String password, String role) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public Utilisateur(String username, String email, String role) {
        this.username = username;
        this.email = email;
        this.role = role;
    }

    // Getters
    public int getId() { return id; }

    public String getUsername() { return username; }

    public String getEmail() { return email; }

    public String getPassword() { return password; }

    public String getRole() { return role; }

    public String getSecurityQuestion() { return securityQuestion; }

    public String getSecurityAnswer() { return securityAnswer; }

    // Setters
    public void setId(int id) { this.id = id; }

    public void setUsername(String username) { this.username = username; }

    public void setEmail(String email) { this.email = email; }

    public void setPassword(String password) { this.password = password; }

    public void setRole(String role) { this.role = role; }

    public void setSecurityQuestion(String securityQuestion) { this.securityQuestion = securityQuestion; }

    public void setSecurityAnswer(String securityAnswer) { this.securityAnswer = securityAnswer; }

    // toString
    @Override
    public String toString() {
        return "Utilisateur{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", role='" + role + '\'' +
                '}';
    }

    // equals and hashCode (based on ID and email)
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Utilisateur)) return false;
        Utilisateur that = (Utilisateur) o;
        return id == that.id && Objects.equals(email, that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email);
    }
}
