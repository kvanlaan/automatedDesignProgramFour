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
        boolean res = false;
        for (int i = 1; i <= this.nInputs; i ++) {
            Gate gin = super.findInputGate(super.getGid(), i);
            res = res || gin.get();
            if (res == true) {
                return false;
            }
        }
        return true;
    }
}