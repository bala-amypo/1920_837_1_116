package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
public class User {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String email;
  private String password;
  private String roles; // CSV

  public User() {}

  public Long getId() { return id; }
  public String getEmail() { return email; }
  public void setEmail(String e) { this.email = e; }
  public String getPassword() { return password; }
  public void setPassword(String p) { this.password = p; }
  public String getRoles() { return roles; }
  public void setRoles(String r) { this.roles = r; }
}
