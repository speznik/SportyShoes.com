package com.sportyshoes.model;
import java.math.BigDecimal;

public class Product {
  private int id;
  private String name, description, imageUrl;
  private BigDecimal price;

  public Product() {}
  public Product(int id, String name, String desc, BigDecimal price, String url) {
    this.id = id; this.name = name;
    this.description = desc; this.price = price;
    this.imageUrl = url;
  }

  public int getId(){return id;} public void setId(int i){id=i;}
  public String getName(){return name;} public void setName(String n){name=n;}
  public String getDescription(){return description;} public void setDescription(String d){description=d;}
  public BigDecimal getPrice(){return price;} public void setPrice(BigDecimal p){price=p;}
  public String getImageUrl(){return imageUrl;} public void setImageUrl(String u){imageUrl=u;}
}
