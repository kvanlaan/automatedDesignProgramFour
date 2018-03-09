package Application;

import logiccircuits.*;
import logiccircuits.library.*;

public class first {

    static int one = 1;
    static int two = 2;
    static int four = 4;
    static int ate = 8;
   
    public static void main(String[] args) {
        Circuit xx = new Circuit("XX");         // create a new circuit object xx called "XX"

        Gate A = new Input(xx,"A");
        Gate B = new Input(xx,"B");
        Gate C = new Input(xx,"C");
        Gate D = new Input(xx,"D");
        Gate α = new Output(xx,"α");
        Gate β = new Output(xx,"β");

        Gate nor = new Nor(xx,"nor",2);
        Gate inv = new Inv(xx,"inv");
        Gate and = new And(xx,"and", 3);
        Gate or = new Or(xx,"or", 2);
     
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
        xx.validate();
        xx.print();
    }
}
