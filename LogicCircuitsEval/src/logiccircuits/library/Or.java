package logiccircuits.library;

import logiccircuits.Circuit;
import logiccircuits.Gate;
import logiccircuits.Value;

public class Or extends Gate {
    int nInputs;
    
    public Or(Circuit xx, String name, int nInputs) {
        super(xx, name, nInputs, 1, "or");
        this.nInputs = nInputs;
    }
    
    // you add more
    @Override
    public boolean get() {
//        System.out.println("\nIm in Or get().");
        if (super.getValue() != Value.U) {
            return super.getValue() == Value.T;
        } else {
            boolean res = false;
            for (int i = 1; i <= this.nInputs; i ++) {
                Gate gin = super.findInputGate(super.getGid(), i);
                res = res || gin.get();
                if (res == true) {
//                    System.out.println("Or eval: true\n");
                    return true;
                }
            }
//            System.out.println("Or eval: false\n");
            return false;
        }
    }
}