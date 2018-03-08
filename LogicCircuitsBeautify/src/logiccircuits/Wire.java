package logiccircuits;

public class Wire {
    Circuit circuit;
    Gate from, to;
    int fromPin, toPin;

    // fill in the rest
    public Wire(Circuit xx, Gate from, int fromPin, Gate to, int toPin) {
        this.circuit = xx;
        
        this.from = from;
        this.fromPin = fromPin;
        this.to = to;
        this.toPin = toPin;
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
}
