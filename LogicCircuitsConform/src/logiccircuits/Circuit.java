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

    public void validate() {
        ErrorReport er = new ErrorReport();

        // setting up
        ArrayList<Gate> gatesArray = new ArrayList<Gate>();
        gates.stream().forEach(gate -> gatesArray.add(gate));

        // constraint 1: all gates have unique names
        ArrayList<String> gateNames = new ArrayList<String>();
        gates.stream().forEach(gate -> gateNames.add(gate.getGateId()));

        HashSet<String> gateNameSet = new HashSet<String>();
        for (String name : gateNames) {
            if (!gateNameSet.add(name)) {
                // rule is that gates must have unique id
                er.add("Duplicate Gate Id is Found");
            }
        }

        // Constraint 2: > Circuit must have a list of defined gates (at least length one)
        if (gates.size() < 1) {
            er.add("Circuit is missing gates");
        }
        // Constraint 3: Circuit must have a list of defined wires (at least length one)
        if (wires.size() < 1) {
            er.add("Circuit is missing wires");
        }

        // Constraint 4: verify that all wires are properly connected
        // wire connects an output pin to an input pin.
        ArrayList<Wire> wireArray = new ArrayList<Wire>();
        wires.stream().forEach(wire -> wireArray.add(wire));

        wires.stream().forEach(wire -> {
            if (wire.to == null || wire.from == null) {
                er.add("Wire is missing either a to or from pin");
            }
            
             if (wire.to.equals(wire.from)) {
                er.add("Wire connects to same gates");
            }
                if (wire.from.type.equals("output")) {
                    er.add("Wire's from gate is an ouput");
                }
                if (wire.to.type.equals("input")) {
                    er.add("Wire's to gate is an input");
                }
        });
        // Constraint 5: Circuit has >1 input pins
        ArrayList<Gate> inputGates = new ArrayList<Gate>();

        gates.stream().forEach(gate -> {
            if ((gate.type.equals("input"))) {
                inputGates.add(gate);
            }
        });
        if (inputGates.size() < 1) {
            er.add("Circuit is missing an input gate");
        }

        // Constraint 6:   Circuit has >1 output pins
        ArrayList<Gate> outputGates = new ArrayList<Gate>();
        gates.stream().forEach(gate -> {
            if ((gate.type.equals("output"))) {
                outputGates.add(gate);
            }
        });
        if (outputGates.size() < 1) {
            er.add("Circuit is missing an output gate");
        }
        // Step 4: finish by reporting collected errors
        er.printReportEH(System.out);

        if (!er.printReport(System.out)) {
            System.out.format("All constraints satisfied for this cicuit");
        }
    }
}