package logiccircuits;

import java.util.List;
import java.util.LinkedList;

public class Circuit {

    String name;
    List<Gate> gates;
    List<Wire> wires;
    int num_and = 0, num_or = 0, num_nand = 0, num_nor = 0, num_inv = 0;

    // fill in the rest
    public Circuit(String s) {
        name = s;
        gates = new LinkedList<>();
        wires = new LinkedList<>();
    }

    public int getGateNum(String type) {
        switch (type) {
            case "and":
                return num_and;
            case "or":
                return num_or;
            case "nand":
                return num_nand;
            case "nor":
                return num_nor;
            case "inv":
                return num_inv;
            default:
                return -1;
        }
    }

    public void addOneGate(String type) {
        switch (type) {
            case "and":
                num_and += 1;
                break;
            case "or":
                num_or += 1;
                break;
            case "nand":
                num_nand += 1;
                break;
            case "nor":
                num_nor += 1;
                break;
            case "inv":
                num_inv += 1;
                break;
        }
    }

    public void printTables() {
        System.out.println("printing circuit tables...");

        System.out.println("Gates:");
        gates.stream().forEach(gate -> {
            if (!gate.type.equals("input") && !gate.type.equals("output")) {
                gate.print();
            }
        });
        gates.stream().forEach(gate -> {
            if (gate.type.equals("input") || gate.type.equals("output")) {
                gate.print();
            }
        });

        System.out.println("\nWires:");
        wires.stream().forEach(wire -> {
            wire.print();
        });
    }
    
    public void print() {
        System.out.format("circuit(%s).\n", this.name);
        
        System.out.println();
        gates.stream().forEach(gate -> {
            gate.print();
        });
        
        System.out.println();
        wires.stream().forEach(wire -> {
            wire.print();
        });
    }
    
    public void initValues() {
        gates.stream().forEach(gate -> {
            gate.setValue(Value.U);
        });
    }
    
    public Gate findInputGate(String to_gid, int to_pin_no) {
        for (Wire wire : wires) {
            if ((wire.getTo().getGid().equals(to_gid)) && (wire.getToPin() == to_pin_no)) {
                return wire.getFrom();
            }
        }
        throw Err.toss("Input gate %s value is unknown.", to_gid);
    }
}
