package LabOne.Buildings.impl;

import LabOne.Buildings.Interfaces.Floor;
import LabOne.Buildings.Interfaces.Space;

public class DwellingFloor implements Floor {
    private Space[] flats;

    public DwellingFloor(int numFlats) {
        flats = new Space[numFlats];
        for (int i = 0; i < numFlats; i++) {
            flats[i] = new Flat();
        }
    }

    public DwellingFloor(Flat[] flats) {
        this.flats = flats;
    }

    // Метод для получения общего количества квартир на этаже
    public int getTotalFlats() {
        return flats.length;
    }

    // Метод для получения общей площади квартир этажа
    public int getFlatsSquare() {
        int totalSquare = 0;
        for (Space flat : flats) {
            totalSquare += flat.getSquare();
        }
        return totalSquare;
    }

    // Метод для получения общего количества комнат этажа
    public int getFlatsQuantity() {
        int totalQuantity = 0;
        for (Space flat : flats) {
            totalQuantity += flat.getQuantity();
        }
        return totalQuantity;
    }

    // Метод для получения массива квартир этажа
    public Space[] getFlats() {
        return flats;
    }

    // Метод для получения объекта квартиры по ее номеру на этаже
    public Space getFlat(int index) {
        if (index >= 0 && index < flats.length) {
            return flats[index];
        }
        return null; // Возвращаем null, если индекс выходит за границы массива
    }

    // Метод для изменения квартиры по ее номеру на этаже и ссылке на новую квартиру
    public void setFlat(int index, Space newFlat) {
        if (index >= 0 && index < flats.length) {
            flats[index] = newFlat;
        }
    }

    // Метод для добавления новой квартиры на этаже по будущему номеру квартиры и ссылке на объект новой квартиры
    public void addFlat(int index, Space newFlat) {
        if (index >= 0 && index <= flats.length) {
            Space[] newFlats = new Flat[flats.length + 1];
            for (int i = 0; i < index; i++) {
                newFlats[i] = flats[i];
            }
            newFlats[index] = newFlat;
            for (int i = index; i < flats.length; i++) {
                newFlats[i + 1] = flats[i];
            }
            flats = newFlats;
        }
    }

    // Метод для удаления квартиры по ее номеру на этаже
    public void deleteFlat(int index) {
        if (index >= 0 && index < flats.length) {
            Space[] newFlats = new Flat[flats.length - 1];
            for (int i = 0, j = 0; i < flats.length; i++) {
                if (i != index) {
                    newFlats[j++] = flats[i];
                }
            }
            flats = newFlats;
        }
    }

    // Метод для получения самой большой по площади квартиры этажа
    public Space getBestSquare() {
        if (flats.length == 0) {
            return null;
        }

        Space bestFlat = flats[0];
        for (Space flat : flats) {
            if (flat.getSquare() > bestFlat.getSquare()) {
                bestFlat = flat;
            }
        }
        return bestFlat;
    }
}

