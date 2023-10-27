package LabOne.Buildings;

import lombok.Data;

@Data
public abstract class AbstractSpace {
    private static int DEFAULT_ROOMS = 2;
    private static int DEFAULT_AREA = 50;

    private int square;
    private int quantity;

    public AbstractSpace() {
        square = DEFAULT_AREA;
        quantity = DEFAULT_ROOMS;
    }

    public AbstractSpace(int square) {
        this.square = square;
        this.quantity = DEFAULT_ROOMS;
    }

    public AbstractSpace(int square, int quantity) {
        this.square = square;
        this.quantity = quantity;
    }

}
