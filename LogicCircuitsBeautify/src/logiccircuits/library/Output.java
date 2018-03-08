package logiccircuits.library;

import logiccircuits.Circuit;
import logiccircuits.Gate;

public class Output extends Gate {
    public Output(Circuit xx, String name) {
        super(xx, name, 1, 0, "output");
    }
}