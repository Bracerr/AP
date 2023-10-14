package LabOne.Buildings;

import java.util.Arrays;

public class Dwelling {
    private DwellingFloor[] floors;

    public Dwelling(int numFloors, int[] numApartmentsPerFloor) {
        floors = new DwellingFloor[numFloors];
        for (int i = 0; i < numFloors; i++) {
            floors[i] = new DwellingFloor(numApartmentsPerFloor[i]);
        }
    }

    public Dwelling(DwellingFloor[] floors) {
        this.floors = floors;
    }

    // Метод для получения общего количества этажей дома
    public int getDwellingFloorQuantity() {
        return floors.length;
    }

    // Метод для получения общего количества квартир в доме
    public int getFlatsQuantity() {
        int totalQuantity = 0;
        for (DwellingFloor floor : floors) {
            totalQuantity += floor.getTotalFlats();
        }
        return totalQuantity;
    }

    // Метод для получения общей площади квартир дома
    public double getFlatsSquare() {
        double totalSquare = 0;
        for (DwellingFloor floor : floors) {
            totalSquare += floor.getFlatsSquare();
        }
        return totalSquare;
    }

    // Метод для получения общего количества комнат в доме
    public int getRoomsQuantity() {
        int totalQuantity = 0;
        for (DwellingFloor floor : floors) {
            totalQuantity += floor.getFlatsQuantity();
        }
        return totalQuantity;
    }

    // Метод для получения массива этажей жилого дома
    public DwellingFloor[] getDwellingFloors() {
        return floors;
    }

    // Метод для получения объекта этажа по его номеру в доме
    public DwellingFloor getDwellingFloor(int index) {
        if (index >= 0 && index < floors.length) {
            return floors[index];
        }
        return null; // Возвращаем null, если индекс выходит за границы массива
    }

    // Метод для изменения этажа по его номеру в доме и ссылке на обновленный этаж
    public void setDwellingFloor(int index, DwellingFloor newDwellingFloor) {
        if (index >= 0 && index < floors.length) {
            floors[index] = newDwellingFloor;
        }
    }

    // Метод для получения объекта квартиры по ее номеру в доме
    public Flat getFlat(int index) {
        int currentIndex = index;
        for (DwellingFloor floor : floors) {
            int numFlatsOnFloor = floor.getTotalFlats();
            if (currentIndex < numFlatsOnFloor) {
                return floor.getFlat(currentIndex);
            } else {
                currentIndex -= numFlatsOnFloor;
            }
        }
        return null; // Возвращаем null, если индекс выходит за границы
    }

    // Метод для изменения объекта квартиры по ее номеру в доме и ссылке на объект квартиры
    public void setFlat(int index, Flat newFlat) {
        int currentIndex = index;
        for (DwellingFloor floor : floors) {
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
    public void addFlat(int index, Flat newFlat) {
        int currentIndex = index;
        for (DwellingFloor floor : floors) {
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
        for (DwellingFloor floor : floors) {
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
    public Flat getBestFlatBySquare() {
        Flat bestFlat = null;
        double maxSquare = 0.0;

        for (DwellingFloor floor : floors) {
            Flat floorBestFlat = floor.getBestSquare();
            if (floorBestFlat != null && floorBestFlat.getSquare() > maxSquare) {
                bestFlat = floorBestFlat;
                maxSquare = floorBestFlat.getSquare();
            }
        }
        return bestFlat;
    }
    // Метод для получения массива квартир в доме, отсортированных по убыванию(order = 1)/увеличению(order=-1) площади.


    // утильный класс переделаю потом, временное решение :)
    public Flat[] getSortFlatsBySquare(int order) {
        int totalFlats = getFlatsQuantity();
        Flat[] flatArray = new Flat[totalFlats];

        int currentIndex = 0;
        for (DwellingFloor floor : floors) {
            Flat[] floorFlats = floor.getFlats();
            System.arraycopy(floorFlats, 0, flatArray, currentIndex, floorFlats.length);
            currentIndex += floorFlats.length;
        }

        Arrays.sort(flatArray, (flat1, flat2) -> {
            if (order == 1) {
                return Double.compare(flat1.getSquare(), flat2.getSquare()); // По возрастанию
            }
            if (order == 0) {
                return Double.compare(flat2.getSquare(), flat1.getSquare()); // По убыванию
            }
            else{
                return 1;
            }
        });

        return flatArray;
    }
}
