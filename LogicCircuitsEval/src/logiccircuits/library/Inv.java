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
//        System.out.println("\nIm in Inv get().");
        if (super.getValue() != Value.U) {
            return super.getValue() == Value.T;
        } else {
            Gate gin = super.findInputGate(super.getGid(), 1);
//            System.out.println("Inv eval: " + String.valueOf(!gin.get()) + "\n");
            return !gin.get();
        }
    }
}