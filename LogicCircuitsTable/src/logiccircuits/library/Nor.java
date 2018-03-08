package logiccircuits.library;

import logiccircuits.Circuit;
import logiccircuits.Gate;

public class Nor extends Gate {
    
    public Nor(Circuit xx, String name, int nInputs) {
        super(xx, name, nInputs, 1, "nor");
    }
    
    // you add more
}