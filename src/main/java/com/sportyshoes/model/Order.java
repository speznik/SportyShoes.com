package com.sportyshoes.model;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Order {
  private int id, userId;
  private LocalDateTime orderDate;
  private BigDecimal totalAmount;

  public Order() {}
  public Order(int id, int u, LocalDateTime d, BigDecimal t) {
    this.id = id; this.userId = u;
    this.orderDate = d; this.totalAmount = t;
  }
  public int getId(){return id;} public void setId(int i){id=i;}
  public int getUserId(){return userId;} public void setUserId(int u){userId=u;}
  public LocalDateTime getOrderDate(){return orderDate;} public void setOrderDate(LocalDateTime d){orderDate=d;}
  public BigDecimal getTotalAmount(){return totalAmount;} public void setTotalAmount(BigDecimal t){totalAmount=t;}
}
