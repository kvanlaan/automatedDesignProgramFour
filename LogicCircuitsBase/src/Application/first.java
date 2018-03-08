package Application;

import logiccircuits.*;

public class first {

    static int one = 1;
    static int two = 2;
    static int four = 4;
    static int ate = 8;
   

    public static void main(String[] args) {
        Circuit xx = new Circuit("XX");         // create a new circuit object xx called "XX"
        
        Gate A = xx.Gate("A", 0, 1, "Input");  // add the following gates and wires to xx
        Gate B = xx.Gate("B", 0, 1, "Input");
        Gate C = xx.Gate("C", 0, 1, "Input");
        Gate D = xx.Gate("D", 0, 1, "Input");
        Gate α = xx.Gate("α", 1, 0, "Output");
        Gate β = xx.Gate("β", 1, 0, "Output");

        Gate nor = xx.Gate("nor", 2, 1, "nor");
        Gate inv = xx.Gate("inv", 1, 1, "inv");
        Gate and = xx.Gate("and", 3, 1, "and");
        Gate or = xx.Gate("or", 2, 1, "or");
     
        new Wire(xx,A,nor,1);
        new Wire(xx,B,nor,2);
        new Wire(xx,nor,and, 1);
        new Wire(xx,C,or, 1);
        new Wire(xx,D,or, 2);
        new Wire(xx,C,inv, 1);
        new Wire(xx,inv,and, 2);
        new Wire(xx,or,and, 3);
        new Wire(xx,or,β);
        new Wire(xx,and, α);
    }
}
