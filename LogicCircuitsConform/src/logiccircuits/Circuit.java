package logiccircuits;

import java.util.List;
import java.util.LinkedList;
import PrologDB.*;
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
        // call logicConform logic here

        ErrorReport er = new ErrorReport();
//        gates.stream().filter().forEach(;
        
        // Step 4: finish by reporting collected errors
        er.printReportEH(System.out);
        
        if (!er.printReport(System.out)) {
            System.out.format("All constraints satisfied for this cicuit");
        }
    }
}