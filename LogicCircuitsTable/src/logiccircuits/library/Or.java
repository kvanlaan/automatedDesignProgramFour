package logiccircuits.library;

import logiccircuits.Circuit;
import logiccircuits.Gate;

public class Or extends Gate {
    
    public Or(Circuit xx, String name, int nInputs) {
        super(xx, name, nInputs, 1, "or");
    }
    
    // you add more
}