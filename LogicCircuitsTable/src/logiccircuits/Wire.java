package logiccircuits;

public class Wire {
    Circuit circuit;
    Gate from, to;
    int fromPin, toPin;
    String wid;

    // fill in the rest
    public Wire(Circuit xx, Gate from, int fromPin, Gate to, int toPin) {
        this.circuit = xx;
        
        this.from = from;
        this.fromPin = fromPin;
        this.to = to;
        this.toPin = toPin;
        
        this.wid = this.getWireId();
        addToCircuitTable(this.circuit);
    }
    
    public Wire(Circuit circuit, Gate from, int fromPin, Gate to) {
        this(circuit, from, fromPin, to, 1);
    }

    public Wire(Circuit circuit, Gate from, Gate to, int toPin) {
        this(circuit, from, 1, to, toPin);
    }

    public Wire(Circuit circuit, Gate from, Gate to) {
        this(circuit, from, 1, to, 1);
    }
    
    private String getWireId() {
        int idx;
        idx = this.circuit.wires.size() + 1;
        return wid = "w" + Integer.toString(idx);
    }
    
    private void addToCircuitTable(Circuit xx) {
        xx.wires.add(this);
    }
    
    public void print() {
        System.out.println(this.wid + " " + from.getName() + " " + Integer.toString(fromPin) + " " + to.getName() + " " + Integer.toString(toPin));
    }
}
