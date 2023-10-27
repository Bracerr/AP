package LabOne.Buildings.impl;

import LabOne.Buildings.AbstractSpace;
import LabOne.Buildings.Interfaces.Space;

public class Flat extends AbstractSpace implements Space  {
    public Flat() {
        super();
    }
    public Flat(int square){
        super(square);
    }
    public Flat(int square, int quantity){
        super(square, quantity);
    }
}
