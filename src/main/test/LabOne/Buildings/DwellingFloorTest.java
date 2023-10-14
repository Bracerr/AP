package LabOne.Buildings;
import LabOne.Buildings.Flat;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DwellingFloorTest {
    private final Flat flat1 = new Flat();
    private final Flat flat2 = new Flat();
    private final Flat[] DwellingFloors = new Flat[] { flat1, flat2 };
    private final DwellingFloor Floor = new DwellingFloor(DwellingFloors);


    @Test
    void getTotalFlats() {
        int flatsExpected = 2;
        int flatsActual = Floor.getTotalFlats();

        Assertions.assertEquals(flatsExpected, flatsActual);
    }


    @Test
    void getFlatsSquare() {
        double squareExpected = 100;
        double squareActual = Floor.getFlatsSquare();

        Assertions.assertEquals(squareExpected, squareActual);
    }

    @Test
    void GetFlatsQuantity() {
        int quantityExpected = 4;
        int quantityActual = Floor.getFlatsQuantity();

        Assertions.assertEquals(quantityExpected, quantityActual);
    }

    @Test
    void getFlats() {
        Flat[] result = Floor.getFlats();

        Assertions.assertEquals(DwellingFloors, result);
    }

    @Test
    void getFlat() {
        Flat result = Floor.getFlat(1);
        Assertions.assertEquals(flat2, result);
    }

    @Test
    void setFlat() {
        int index = 1;
        Floor.setFlat(index, flat2);
        Flat result = Floor.getFlat(index);

        Assertions.assertEquals(flat2, result);
    }

    @Test
    void addFlat() {
        int index = 2;
        Floor.addFlat(index, flat1);
        Flat result = Floor.getFlat(index);

        Assertions.assertEquals(flat1, result);
    }

    @Test
    void deleteFlat() {
        int index = 0;
        Floor.deleteFlat(0);
        Flat result = Floor.getFlat(index);

        Assertions.assertEquals(flat2, result);
    }

    @Test
    void getBestSquare() {
        Flat result = Floor.getBestSquare();
        Assertions.assertEquals(50, result.getSquare());
    }
}