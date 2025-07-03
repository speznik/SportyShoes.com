package com.sportyshoes.model;
public class Admin {
  private int id;
  private String username, password;
  public Admin() {}
  public Admin(int id, String u, String p) {
    this.id = id; this.username = u; this.password = p;
  }
  public int getId(){return id;} public void setId(int i){id=i;}
  public String getUsername(){return username;} public void setUsername(String u){username=u;}
  public String getPassword(){return password;} public void setPassword(String p){password=p;}
}
