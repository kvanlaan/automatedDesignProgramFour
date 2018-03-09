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
        boolean res = false;
        for (int i = 1; i <= this.nInputs; i ++) {
            Gate gin = super.findInputGate(super.getGid(), i);
            res = res || gin.get();
            if (res == true) {
                return true;
            }
        }
        return false;
    }
}