package logiccircuits.library;

import logiccircuits.Circuit;
import logiccircuits.Gate;

public class Input extends Gate {
    public Input(Circuit xx, String name) {
        super(xx, name, 0, 1, "input");
    }
}