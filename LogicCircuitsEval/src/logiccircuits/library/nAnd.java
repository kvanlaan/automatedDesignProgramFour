package logiccircuits.library;

import logiccircuits.Circuit;
import logiccircuits.Gate;
import logiccircuits.Value;

public class nAnd extends Gate {
    int nInputs;
    
    public nAnd(Circuit xx, String name, int nInputs) {
        super(xx, name, nInputs, 1, "nand");
        this.nInputs = nInputs;
    }
    
    // you add more
    @Override
    public boolean get() {
//        System.out.println("\nIm in Nand get().");
        if (super.getValue() != Value.U) {
            return super.getValue() == Value.T;
        } else {
            boolean res = true;
            for (int i = 1; i <= this.nInputs; i ++) {
                Gate gin = super.findInputGate(super.getGid(), i);
                res = res && gin.get();
                if (res == false) {
//                    System.out.println("Nand eval: true\n");
                    return true;
                }
            }
//            System.out.println("Nand eval: false\n");
            return false;
        }
    }
}