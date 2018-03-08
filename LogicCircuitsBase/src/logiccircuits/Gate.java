/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logiccircuits;

public class Gate {
    public final String name;
    int nInputPins, nOutputPins;
    String type;
    Circuit circuit;
    
    protected Gate(Circuit circuit, String name, int nInputPins, int nOutputPins, String type) {
        this.circuit = circuit;
        
        this.name = name;
        this.nInputPins = nInputPins;
        this.nOutputPins = nOutputPins;
        this.type = type;
        // fill in
    }
}
