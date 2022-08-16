package ru.geekbrains.persist.product;

public class Product {
    private Long id;
    private String title;
    private int cost;
    public Product (String title, int cost) {
        this.title = title;
        this.cost = cost;
    }
    public Product (String title, String cost) {
        this.title = title;
        this.cost = Integer.parseInt (cost);
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getProductTitle() {
        return title;
    }
    public void setProductTitle(String title) {
        this.title = title;
    }
    public int getCost() {
        return cost;
    }
    public void setCost(int cost) {
        this.cost = cost;
    }
    public void printProduct (){
        System.out.println(this.id + " " + this.title + " " + this.cost);
    }
}
