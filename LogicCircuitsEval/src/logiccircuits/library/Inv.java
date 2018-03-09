package logiccircuits.library;

import logiccircuits.Circuit;
import logiccircuits.Gate;
import logiccircuits.Value;

public class Inv extends Gate {
    
    public Inv(Circuit xx, String name) {
        super(xx, name, 1, 1, "inv");
    }
    
    // you add more 
    @Override
    public boolean get() {
        Gate gin = super.findInputGate(super.getGid(), 1);
        return !gin.get();
    }
}