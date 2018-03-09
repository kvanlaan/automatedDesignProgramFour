package logiccircuits.library;

import logiccircuits.Circuit;
import logiccircuits.Gate;
import logiccircuits.Value;
import logiccircuits.Err;

public class Output extends Gate {
    public Output(Circuit xx, String name) {
        super(xx, name, 1, 0, "output");
    }
    
    @Override
    public boolean get() {
        Gate gin = super.findInputGate(super.getGid(), 1);
        return gin.get();
    }
}