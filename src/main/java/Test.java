import LabOne.Buildings.Dwelling;
import LabOne.Buildings.DwellingFloor;
import LabOne.Buildings.Flat;
public class Test {
    public static void main(String[] args) {
        int[] numOfFlats = new int[] {1, 2, 3, 4};
        Flat FlatOne = new Flat(100);
        Flat FlatTwo = new Flat(18.5);

        DwellingFloor Floor = new DwellingFloor(9);
        Dwelling Eroshevka = new Dwelling(4, numOfFlats);

        System.out.println("Общее колличество этажей " + Eroshevka.getDwellingFloorQuantity());
        System.out.println("Общее колличество квартир " + Eroshevka.getFlatsQuantity());
        System.out.println("Общая площадь " + Eroshevka.getFlatsSquare());
        System.out.println("Общее колличество комнат " + Eroshevka.getRoomsQuantity());

        DwellingFloor[] FloorsArray = Eroshevka.getDwellingFloors(); // массив этажей
        System.out.println(Eroshevka.getDwellingFloor(1).getClass()); // объект этажа

        Eroshevka.setDwellingFloor(0, Floor); // изменение этажа
        System.out.println("Общее колличество квартир после изменения " + Eroshevka.getFlatsQuantity());

        System.out.println(Eroshevka.getFlat(2).getClass()); // объект квартиры

        Eroshevka.setFlat(2, FlatOne); // измненение квартиры по номеру
        System.out.println("Общая площадь после изменения квартиры " + Eroshevka.getFlatsSquare());

        Eroshevka.addFlat(5, FlatTwo);
        System.out.println("Общее колличество квартир после добавления " + Eroshevka.getFlatsQuantity());

        Eroshevka.deleteFlat(5);
        System.out.println("Общее колличество квартир после удаления " + Eroshevka.getFlatsQuantity());

        System.out.println("Самая большая площадь " + Eroshevka.getBestFlatBySquare().getSquare());

        Flat[] FlatArray = Eroshevka.getSortFlatsBySquare(0);
        for (Flat Flats: FlatArray){
            System.out.print(Flats.getSquare() + " ");
        }
    }
}
