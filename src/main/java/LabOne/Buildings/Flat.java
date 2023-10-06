package LabOne.Buildings;

public class Flat {
    private static final int DEFAULT_ROOMS = 2;
    private static final double DEFAULT_AREA = 50.0;

    private double square;
    private int quantity;

    public Flat() {
        square = DEFAULT_AREA;
        quantity = DEFAULT_ROOMS;
    }

    public Flat(double square) {
        this.square = square;
        this.quantity = DEFAULT_ROOMS;
    }

    public Flat(double square, int quantity) {
        this.square = square;
        this.quantity = quantity;
    }

    public double getSquare() {
        return square;
    }

    public void setSquare(double square) {
        this.square = square;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
