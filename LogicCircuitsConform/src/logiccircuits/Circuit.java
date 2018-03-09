package logiccircuits;

import java.util.ArrayList;
import java.util.List;
import java.util.LinkedList;
import PrologDB.*;
import java.util.HashSet;

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

    public void validate() {
        ErrorReport er = new ErrorReport();

        // Constraint 1: Each Gate has a unique id
        ArrayList<String> gateNames = new ArrayList<String>();
        gates.stream().forEach(gate -> {
            gateNames.add(gate.getGateId());
        });
        HashSet<String> gateNameSet = new HashSet<String>();
        for (String name : gateNames) {
            if (!gateNameSet.add(name)) {
                er.add("Duplicate Gate Id is Found");
            }
        }

        // Constraint 2: Circuit has >= 1 gates
        if (gates.size() < 1) {
            er.add("Circuit is missing gates");
        }
        // Constraint 3: Circuit has >= 1 wire
        if (wires.size() < 1) {
            er.add("Circuit is missing wires");
        }

        // Constraint 4: All wires are properly connected
        ArrayList<Gate> inputGates = new ArrayList<Gate>();
        ArrayList<Gate> outputGates = new ArrayList<Gate>();
        wires.stream().forEach(wire -> {
            if (wire.to == null || wire.from == null) {
                er.add("Wire is missing either a to or from pin");
            }
            if (wire.to.equals(wire.from)) {
                er.add("Wire connects to the same two gates");
            }

            if (wire.from.type.equals("output")) {
                er.add("Wire's from gate is an ouput");
            }
            if (wire.to.type.equals("input")) {
                er.add("Wire's to gate is an input");
            }

            if (wire.from.type.equals("input")) {
                inputGates.add(wire.from);
            }

            if (wire.to.type.equals("input")) {
                inputGates.add(wire.to);
            }

            if (wire.from.type.equals("output")) {
                outputGates.add(wire.from);
            }

            if (wire.to.type.equals("output")) {
                outputGates.add(wire.to);
            }
        });

        // Constraint 5: Circuit has >= 1 input pins
        if (inputGates.size() < 1) {
            er.add("Circuit is missing an input gate");
        }

        // Constraint 6: Circuit has >= 1 output pins
        if (outputGates.size() < 1) {
            er.add("Circuit is missing an output gate");
        }

        // Finish by reporting collected errors
        er.printReportEH(System.out);

        if (!er.printReport(System.out)) {
            System.out.format("All constraints satisfied for this cicuit");
        }
    }
}
