package models;

import java.util.Objects;

public class Utilisateur {
    private int id;
    private String username;
    private String email;
    private String password;
    private String role; // "Admin", "Client" ou "Livreur"

    public Utilisateur() {
    }

    public Utilisateur(int id, String username, String email, String password, String role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
    }

    public Utilisateur(String username, String email, String role) {
        this.username = username;
        this.email = email;
        this.role = role;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUsername(String username) { this.username = username; }

    public void setMotDePasse(String password) { this.password = password; }

    public String getUsername() { return username;}

    public String getEmail() {
        return email;
    }

    public int getId() {
        return id;
    }

    public String getMotDePasse() {
        return password;
    }

    public String getRole() { return role;}

    // Méthode toString pour affichage
    @Override
    public String toString() {
        return "Utilisateur{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", role='" + role + '\'' +
                '}';
    }

    // Méthodes equals et hashCode pour comparer les objets Utilisateur
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Utilisateur that = (Utilisateur) o;
        return id == that.id && Objects.equals(email, that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email);
    }
}