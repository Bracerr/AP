package LabOne.Buildings.impl;

import LabOne.Buildings.Interfaces.Building;
import LabOne.Buildings.Interfaces.Floor;
import LabOne.Buildings.Interfaces.Space;
import LabOne.Buildings.impl.DwellingFloor;
import LabOne.Buildings.impl.Flat;

public class OfficeDwelling implements Building {
    private Floor[] floors;

    public OfficeDwelling(int numFloors, int[] numApartmentsPerFloor) {
        floors = new Floor[numFloors];
        for (int i = 0; i < numFloors; i++) {
            floors[i] = new DwellingFloor(numApartmentsPerFloor[i]);
        }
    }

    public OfficeDwelling(Floor[] floors) {
        this.floors = floors;
    }

    // Метод для получения общего количества этажей дома
    public int getFloorsQuantity() {
        return floors.length;
    }

    // Метод для получения общего количества квартир в доме
    public int getTotalFlats() {
        int totalQuantity = 0;
        for (Floor floor : floors) {
            totalQuantity += floor.getTotalFlats();
        }
        return totalQuantity;
    }

    // Метод для получения общей площади квартир дома
    public int getFlatsSquare() {
        int totalSquare = 0;
        for (Floor floor : floors) {
            totalSquare += floor.getFlatsSquare();
        }
        return totalSquare;
    }

    // Метод для получения общего количества комнат в доме
    public int getFlatsQuantity() {
        int totalQuantity = 0;
        for (Floor floor : floors) {
            totalQuantity += floor.getFlatsQuantity();
        }
        return totalQuantity;
    }

    // Метод для получения массива этажей жилого дома
    public Floor[] getFloors() {
        return floors;
    }

    // Метод для получения объекта этажа по его номеру в доме
    public Floor getFloor(int index) {
        if (index >= 0 && index < floors.length) {
            return floors[index];
        }
        return null; // Возвращаем null, если индекс выходит за границы массива
    }

    // Метод для изменения этажа по его номеру в доме и ссылке на обновленный этаж
    public void setFloor(int index, Floor newDwellingFloor) {
        if (index >= 0 && index < floors.length) {
            floors[index] = newDwellingFloor;
        }
    }

    // Метод для получения объекта квартиры по ее номеру в доме
    public Space getFlat(int index) {
        int currentIndex = index;
        for (Floor floor : floors) {
            int numFlatsOnFloor = floor.getTotalFlats();
            if (currentIndex < numFlatsOnFloor) {
                return floor.getFlat(currentIndex);
            } else {
                currentIndex -= numFlatsOnFloor;
            }
        }
        return null;
    }

    // Метод для изменения объекта квартиры по ее номеру в доме и ссылке на объект квартиры
    public void setFlat(int index, Space newFlat) {
        int currentIndex = index;
        for (Floor floor : floors) {
            int numFlatsOnFloor = floor.getTotalFlats();
            if (currentIndex < numFlatsOnFloor) {
                floor.setFlat(currentIndex, newFlat);
                return;
            } else {
                currentIndex -= numFlatsOnFloor;
            }
        }
    }

    // Метод для добавления квартиры в дом по будущему номеру квартиры в доме
    public void addFlat(int index, Space newFlat) {
        int currentIndex = index;
        for (Floor floor : floors) {
            int numFlatsOnFloor = floor.getTotalFlats();
            if (currentIndex <= numFlatsOnFloor) {
                floor.addFlat(currentIndex, newFlat);
                return;
            } else {
                currentIndex -= numFlatsOnFloor;
            }
        }
    }

    // Метод для удаления квартиры по ее номеру в доме
    public void deleteFlat(int index) {
        int currentIndex = index;
        for (Floor floor : floors) {
            int numFlatsOnFloor = floor.getTotalFlats();
            if (currentIndex < numFlatsOnFloor) {
                floor.deleteFlat(currentIndex);
                return;
            } else {
                currentIndex -= numFlatsOnFloor;
            }
        }
    }

    // Метод для получения самой большой по площади квартиры в доме
    public Space getBestSpaceBySquare() {
        Space bestFlat = null;
        double maxSquare = 0.0;

        for (Floor floor : floors) {
            Space floorBestFlat = floor.getBestSquare();
            if (floorBestFlat != null && floorBestFlat.getSquare() > maxSquare) {
                bestFlat = floorBestFlat;
                maxSquare = floorBestFlat.getSquare();
            }
        }
        return bestFlat;
    }


    public Space[] getSortSpacesBySquare(int order) {
        int totalFlats = getFlatsQuantity();
        Space[] flatArray = new Flat[totalFlats];

        int currentIndex = 0;
        for (Floor floor : floors) {
            Space[] floorFlats = floor.getFlats();
            System.arraycopy(floorFlats, 0, flatArray, currentIndex, floorFlats.length);
            currentIndex += floorFlats.length;
        }

        int n = flatArray.length;

        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (order == 1 && flatArray[j].getSquare() > flatArray[j + 1].getSquare()) {
                    // По возрастанию
                    Space temp = flatArray[j];
                    flatArray[j] = flatArray[j + 1];
                    flatArray[j + 1] = temp;
                } else if (order == 0 && flatArray[j].getSquare() < flatArray[j + 1].getSquare()) {
                    // По убыванию
                    Space temp = flatArray[j];
                    flatArray[j] = flatArray[j + 1];
                    flatArray[j + 1] = temp;
                }
            }
        }

        return flatArray;
    }

}
