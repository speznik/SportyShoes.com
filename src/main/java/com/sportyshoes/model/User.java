package com.sportyshoes.model;
public class User {
  private int id;
  private String email, fullName, password;
  public User() {}
  public User(int id, String email, String fullName, String password) {
    this.id = id; this.email = email;
    this.fullName = fullName; this.password = password;
  }
  public int getId(){return id;} public void setId(int i){id=i;}
  public String getEmail(){return email;} public void setEmail(String e){email=e;}
  public String getFullName(){return fullName;} public void setFullName(String n){fullName=n;}
  public String getPassword(){return password;} public void setPassword(String p){password=p;}
}
