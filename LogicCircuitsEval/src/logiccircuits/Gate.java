package logiccircuits;

public abstract class Gate {
    public final String name;
    int nInputPins, nOutputPins;
    String type;
    Circuit circuit;
    String gid;
    Value evalValue;
    
    protected Gate(Circuit circuit, String name, int nInputPins, int nOutputPins, String type) {
        this.circuit = circuit;
        
        this.name = name;
        this.nInputPins = nInputPins;
        this.nOutputPins = nOutputPins;
        this.type = type;
        
        // fill in
        this.gid = this.getGateId();
        this.addToCircuitTable(this.circuit);
    }
    
    private String getGateId() {
        if ((this.type.equals("input")) || (this.type.equals("output"))) {
            return this.name.toLowerCase();
        } else {
            this.circuit.addOneGate(this.type);
            int idx = this.circuit.getGateNum(this.type);
            return this.name.charAt(0) + Integer.toString(idx);
        }
    }
    
    private void addToCircuitTable(Circuit xx) {
        xx.gates.add(this);
    }
    
    public String getName() {
        return this.name;
    }
    
    public String getGid() {
        return this.gid;
    }
    
    public void print() {
        System.out.println(this.gid + " " + this.name + " " + Integer.toString(this.nInputPins) + " " + Integer.toString(this.nOutputPins) + " " + this.type);
    }
    
    public void setValue(Value v) {
        this.evalValue = v;
    }
    
    public void set(boolean v) {
        if (v == true) {
            this.setValue(Value.T);
        } else {
            this.setValue(Value.F);
        }
    }
   
    public Value getValue() {
        return this.evalValue;
        
    }
    
    public abstract boolean get();
    
    public Gate findInputGate(String gid, int pin_no) {
        return this.circuit.findInputGate(gid, pin_no);
    }
}