package abc;

import LabOne.Buildings.Interfaces.Floor;
import LabOne.Buildings.Interfaces.Space;
import LabOne.Buildings.impl.DwellingFloor;
import LabOne.Buildings.impl.Flat;
import LabOne.Buildings.impl.Dwelling;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CommonTests {
    @Nested
    public class FlatTests {
        private static final int DEFAULT_SQUARE = 50;
        private static final int DEFAULT_QUANTITY = 2;


        @Test
        public void test_shouldCreateFlatWithDefaultValues_whenDefaultConstructor() {
            Flat flat = new Flat();
            assertEquals(DEFAULT_SQUARE, flat.getSquare());
            assertEquals(DEFAULT_QUANTITY, flat.getQuantity());
        }

        @Test
        public void test_shouldCreateFlatWithDefaultQuantity_whenConstructorWithSquareParam() {
            int square = 10;
            Flat flat = new Flat(square);
            assertEquals(square, flat.getSquare());
            assertEquals(DEFAULT_QUANTITY, flat.getQuantity());
        }

        @Test
        public void testAllArgsConstructor() {
            int square = 10;
            int quantity = 1;
            Flat flat = new Flat(square, quantity);
            assertEquals(square, flat.getSquare());
            assertEquals(quantity, flat.getQuantity());
        }
    }

    @Nested
    public class DwellingFloorTests {
        private Flat[] flats;
        private DwellingFloor dwellingFloor;

        @BeforeEach
        public void setup() {
            flats = new Flat[]{
                    new Flat(1, -1),
                    new Flat(-1, -2),
                    new Flat(2, 103)
            };
            dwellingFloor = new DwellingFloor(flats);
            for (int i = 0; i < flats.length; i++) {
                assertEquals(flats[i], dwellingFloor.getFlat(i));
            }
        }

        @Test
        public void testGetTotalFlats_whenQuantityParamConstructor() {
            int flatsQuantity = 5;
            DwellingFloor dwellingFloor = new DwellingFloor(flatsQuantity);
            assertEquals(flatsQuantity, dwellingFloor.getTotalFlats());
        }

        @Test
        public void testGetTotalFlats_whenAllArgConstructor() {
            assertEquals(flats.length, dwellingFloor.getTotalFlats());
        }

        @Test
        public void testGetFlatsCharacteristics() {
            assertEquals(100, dwellingFloor.getFlatsQuantity());
            assertEquals(2, dwellingFloor.getFlatsSquare());
        }

        @Test
        public void testSetFlats() {
            Flat[] testFlats = new Flat[]{
                    flats[2],
                    flats[1],
                    flats[0]
            };

            for (int i = 0; i < testFlats.length; i++) {
                dwellingFloor.setFlat(i, testFlats[i]);
            }

            for (int i = 0; i < testFlats.length; i++) {
                assertEquals(testFlats[i], dwellingFloor.getFlat(i));
            }
        }

        @Test
        public void testAddFlat() {
            Flat newFlat = new Flat(1, 1);
            dwellingFloor.addFlat(1, newFlat);

            assertEquals(flats[0], dwellingFloor.getFlat(0));
            assertEquals(newFlat, dwellingFloor.getFlat(1));
            assertEquals(flats[1], dwellingFloor.getFlat(2));
            assertEquals(flats[2], dwellingFloor.getFlat(3));
        }

        @Test
        public void testDeleteFlat() {
            dwellingFloor.deleteFlat(1);

            assertEquals(2, dwellingFloor.getTotalFlats());
            assertEquals(flats[0], dwellingFloor.getFlat(0));
            assertEquals(flats[2], dwellingFloor.getFlat(1));
        }

        @Test
        public void testGetBestSquare() {
            assertEquals(flats[2], dwellingFloor.getBestSquare());
        }
    }

    @Nested
    class DwellingTest {
        private Flat[][] flats;
        private DwellingFloor[] dwellingFloors;
        private Dwelling dwelling;

        @BeforeEach
        public void setup() {
            flats = new Flat[][]{
                    {
                            new Flat(1, 2),
                            new Flat(1, 4),
                            new Flat(2, -1)
                    },
                    {
                            new Flat(1, -1)
                    },
                    {
                            new Flat(15, 10),
                            new Flat(1, 5)
                    }
            };
            dwellingFloors = new DwellingFloor[flats.length];
            for (int i = 0; i < flats.length; i++) {
                dwellingFloors[i] = new DwellingFloor(flats[i]);
            }
            dwelling = new Dwelling(dwellingFloors);
            assertEquals(dwellingFloors, dwelling.getFloors());
        }

        @Test
        void defaultConstructor() {
            Flat defaultFlat = new Flat();
            dwelling = new Dwelling(2, new int[]{1, 3});

            Floor currentDwellingFloor = dwelling.getFloors()[0];
            assertEquals(1, currentDwellingFloor.getTotalFlats());
            assertEquals(defaultFlat, currentDwellingFloor.getFlat(0));

            currentDwellingFloor = dwelling.getFloors()[1];
            assertEquals(3, currentDwellingFloor.getTotalFlats());
            assertEquals(defaultFlat, currentDwellingFloor.getFlat(0));
            assertEquals(defaultFlat, currentDwellingFloor.getFlat(1));
            assertEquals(defaultFlat, currentDwellingFloor.getFlat(2));
        }

        @Test
        void getFloorsQuantity() {
            assertEquals(dwellingFloors.length, dwelling.getFloorsQuantity());
        }

        @Test
        void getTotalFlats() {
            assertEquals(6, dwelling.getTotalFlats());
        }

        @Test
        void getFlatsSquare() {
            assertEquals(21, dwelling.getFlatsSquare());
        }

        @Test
        void getFlatsQuantity() {
            assertEquals(19, dwelling.getFlatsQuantity());
        }

        @Test
        void getFloor() {
            for (int i = 0; i < dwellingFloors.length; i++) {
                assertEquals(dwellingFloors[i], dwelling.getFloor(i));
            }
        }

        @Test
        void setFloor() {
            Flat[] newFlats = new Flat[]{
                    new Flat(100, 500)
            };
            DwellingFloor newDwellingFloor = new DwellingFloor(newFlats);
            dwelling.setFloor(1, newDwellingFloor);

            assertEquals(dwellingFloors[0], dwelling.getFloors()[0]);
            assertEquals(newDwellingFloor, dwelling.getFloors()[1]);
            assertEquals(dwellingFloors[2], dwelling.getFloors()[2]);
        }

        @Test
        void getBestFlatBySquare() {
            assertEquals(flats[2][0], dwelling.getBestSpaceBySquare());
        }

        @Test
        void getSortFlatsBySquare() {
            dwelling.setDwellingFloors(new DwellingFloor[]{new DwellingFloor(new Flat[]{
                    new Flat(2, 30),
                    new Flat(1, 0),
                    new Flat(3, 10)
            })});

            Space[] sortedFlats = dwelling.getSortSpacesBySquare(1);
            assertArrayEquals(new Flat[]{
                    new Flat(3, 10),
                    new Flat(2, 30),
                    new Flat(1, 0)
            }, sortedFlats);

            sortedFlats = dwelling.getSortSpacesBySquare(-1);
            assertArrayEquals(new Flat[]{
                    new Flat(1, 0),
                    new Flat(2, 30),
                    new Flat(3, 10)
            }, sortedFlats);
        }

        @Test
        void getFlat() {
            testTwoFloors();
            assertEquals(flats[2][0], dwelling.getFlat(4));
            assertEquals(flats[2][1], dwelling.getFlat(5));

            assertNull(dwelling.getFlat(100500));
        }

        @Test
        void setFlat() {
            Flat newTestFlat = new Flat(100500, 500100);
            dwelling.setFlat(4, newTestFlat);

            testTwoFloors();
            assertEquals(newTestFlat, dwelling.getFlat(4));
            assertEquals(flats[2][1], dwelling.getFlat(5));
        }

        @Test
        void addFlat() {
            Flat newTestFlat = new Flat(100500, 500100);
            dwelling.addFlat(4, newTestFlat);
            Flat newTestFlat2 = new Flat(100500, 500100);
            dwelling.addFlat(6, newTestFlat2);

            testTwoFloors();
            assertEquals(newTestFlat, dwelling.getFlat(4));
            assertEquals(flats[2][0], dwelling.getFlat(5));
            assertEquals(newTestFlat2, dwelling.getFlat(6));
            assertEquals(flats[2][1], dwelling.getFlat(7));
        }

        @Test
        void deleteFlat() {
            assertEquals(2, dwelling.getFloor(2).getTotalFlats());
            dwelling.deleteFlat(4);

            testTwoFloors();
            assertEquals(flats[2][1], dwelling.getFlat(4));
            assertEquals(1, dwelling.getFloor(2).getTotalFlats());
        }

        private void testTwoFloors() {
            assertEquals(flats[0][0], dwelling.getFlat(0));
            assertEquals(flats[0][1], dwelling.getFlat(1));
            assertEquals(flats[0][2], dwelling.getFlat(2));

            assertEquals(flats[1][0], dwelling.getFlat(3));
        }
    }
}
