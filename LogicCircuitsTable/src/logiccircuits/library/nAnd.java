package logiccircuits.library;

import logiccircuits.Circuit;
import logiccircuits.Gate;

public class nAnd extends Gate {
    
    public nAnd(Circuit xx, String name, int nInputs) {
        super(xx, name, nInputs, 1, "nand");
    }
    
    // you add more
}