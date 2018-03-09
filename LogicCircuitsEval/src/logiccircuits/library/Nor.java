package logiccircuits.library;

import logiccircuits.Circuit;
import logiccircuits.Gate;
import logiccircuits.Value;

public class Nor extends Gate {
    int nInputs;
    
    public Nor(Circuit xx, String name, int nInputs) {
        super(xx, name, nInputs, 1, "nor");
        this.nInputs = nInputs;
    }
    
    // you add more
    @Override
    public boolean get() {
//        System.out.println("\nIm in Nor get().");
        if (super.getValue() != Value.U) {
            return super.getValue() == Value.T;
        } else {
            boolean res = false;
            for (int i = 1; i <= this.nInputs; i ++) {
                Gate gin = super.findInputGate(super.getGid(), i);
                res = res || gin.get();
                if (res == true) {
//                    System.out.println("Nor eval: false\n");
                    return false;
                }
            }
//            System.out.println("Nor eval: true\n");
            return true;
        }
    }
}