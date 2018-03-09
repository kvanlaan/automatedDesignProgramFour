package logiccircuits.library;

import logiccircuits.Circuit;
import logiccircuits.Gate;
import logiccircuits.Value;

public class And extends Gate {
    int nInputs;
    
    public And(Circuit xx, String name, int nInputs) {
        super(xx, name, nInputs, 1, "and");
        this.nInputs = nInputs;
    }
    
    // you add more
    @Override
    public boolean get() {
        boolean res = true;
        for (int i = 1; i <= this.nInputs; i ++) {
            Gate gin = super.findInputGate(super.getGid(), i);
            res = res && gin.get();
            if (res == false) {
                return false;
            }
        }
        return true;
}
}