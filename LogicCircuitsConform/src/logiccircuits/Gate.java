package logiccircuits;

public abstract class Gate {
    public final String name;
    int nInputPins, nOutputPins;
    String type;
    Circuit circuit;
    String gid;
    
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
    
    protected String getGateId() {
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
    
    public void print() {
        System.out.format("gate(%s,%s,%d,%d).\n", this.name, this.type, this.nInputPins, this.nOutputPins);
    }
}