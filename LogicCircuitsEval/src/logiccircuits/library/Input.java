package logiccircuits.library;

import logiccircuits.Circuit;
import logiccircuits.Gate;
import logiccircuits.Value;
import logiccircuits.Err;

public class Input extends Gate {
    public Input(Circuit xx, String name) {
        super(xx, name, 0, 1, "input");
    }
    
    @Override
    public boolean get() {
        if (super.getValue() == Value.U) {
            throw Err.toss("Input gate %s value is unknown.", name);
        }
        return super.getValue() == Value.T;
    }
}