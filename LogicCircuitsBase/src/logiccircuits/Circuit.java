package logiccircuits;

public class Circuit {

    String name;

    // fill in the rest
    public Circuit(String s) {
        name = s;
    }
    
    public Gate Gate(String name, int nInputPins, int nOutputPins, String type) {
        return new Gate(this, name, nInputPins, nOutputPins, type);
    }
}
