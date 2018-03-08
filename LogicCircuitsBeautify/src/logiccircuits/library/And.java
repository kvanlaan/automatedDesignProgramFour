package logiccircuits.library;

import logiccircuits.Circuit;
import logiccircuits.Gate;

public class And extends Gate {
    
    public And(Circuit xx, String name, int nInputs) {
        super(xx, name, nInputs, 1, "and");
    }
    
    // you add more
}