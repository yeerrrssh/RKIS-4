package ru.fridge.fourth;

public class Fridge {
    private int id;
    private String model;
    private String brand;
    private String color;
    private double price;
    private int quantity;

    public Fridge(){
        this.model = "Def";
        this.brand = "Def";
        this.color = "white";
        this.price = 0;
        this.quantity = 1;
    }

    public Fridge(String model, String brand, String color, double price, int quantity) {
        this.model = model;
        this.brand = brand;
        this.color = color;
        this.price = price;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public String getModel() {
        return model;
    }

    public String getBrand() {
        return brand;
    }

    public String getColor() {
        return color;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Clothes{" +
                "id:" + id +
                ", model: '" + model + '\'' +
                ", brand: '" + brand + '\'' +
                ", color: '" + color + '\'' +
                ", price: " + price +
                ", quantity: " + quantity +
                '}';
    }
}