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

//        xx.print();
        
        for (int i = 0; i < 16; i++) {
            xx.initValues();
            
            boolean Avalue = z(i, one);
            boolean Bvalue = z(i, two);
            boolean Cvalue = z(i, four);
            boolean Dvalue = z(i, ate);

            A.set(Avalue);
            B.set(Bvalue);
            C.set(Cvalue);
            D.set(Dvalue);
            boolean result;
            result = α.get();
            String is = i+"";
            if (result != alpha(Avalue, Bvalue, Cvalue, Dvalue)) {
                throw Err.toss("alpha doesn't match for experiment %s", is);
            }
            result = β.get();
            if (result!= beta(Avalue, Bvalue, Cvalue, Dvalue)) {
                throw Err.toss("beta doesn't match for experiment %s", is);
            }
        }
        
        // if we get this far all evaluations are good
        System.out.println();
        System.out.println("All Good!");
    }

    static boolean z(int i, int mod) {
        return (i & mod) == 0;
    }

    static boolean alpha(boolean a, boolean b, boolean c, boolean d) {
        boolean nor = !(a || b);
        boolean or = (c || d);
        boolean inv = !c;
        return nor && inv && or;
    }

    static boolean beta(boolean a, boolean b, boolean c, boolean d) {
        return c || d;
    }

}
