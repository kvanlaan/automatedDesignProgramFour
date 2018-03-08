package logiccircuits;

public class Wire {
    Circuit circuit;
    Gate out, in;
    int oPin = 1, iPin;

    // fill in the rest
    public Wire(Circuit xx, Gate out, Gate in, int iPin) {
        this.circuit = xx;
        
        this.out = out;
        this.in = in;
        this.iPin = iPin;
    }
    
    public Wire(Circuit xx, Gate out, Gate in) {
        this.circuit = xx;
        
        this.out = out;
        this.in = in;
        this.iPin = 1;
    }
}
