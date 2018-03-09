package logiccircuits;

import java.util.function.BiConsumer;
import java.util.function.Predicate;
import logiccircuits.library.And;
import logiccircuits.library.Input;
import logiccircuits.library.Inv;
import logiccircuits.library.Nor;
import logiccircuits.library.Or;
import logiccircuits.library.Output;
import logiccircuits.library.nAnd;
import org.junit.Test;
import org.junit.Ignore;
import static org.junit.Assert.*;

public class CircuitTest {
    static final String testout = "test.txt";
    static final String correct = "test/logiccircuits/Correct/";

    // pass multiple arguments to a test
    class fourWay {
        // up to 4 inputs
        int one = 1;
        int two = 2;
        int four = 4;
        int ate = 8;

        // test values
        boolean a, b, c, d;

        public String toString() {
            return "(" + a + "," + b + "," + c + "," + d + ")";
        }

        fourWay(int i) {
            a = z(i, one);
            b = z(i, two);
            c = z(i, four);
            d = z(i, ate);
        }

        boolean z(int i, int mod) {
            return (i & mod) == 0;
        }
    }

    // does the main work -- sets the values of a fourway to the inputs of circuit xx
    // extract the boolean output of gate g, and confirm it is the correct result
    public void testit(Circuit xx, Predicate<fourWay> greek, BiConsumer<Circuit, fourWay> set, Gate g) {
        boolean succeed = true;
        for (int i = 0; i < 16; i++) {
            fourWay f = new fourWay(i);
            set.accept(xx, f);
            boolean computed = g.get();
            boolean correct = greek.test(f);
            if (computed != correct) {
                System.out.format("experiment %s #%d failed\n", f, i);
                succeed = false;
            }
        }
        if (!succeed) {
            fail();
        }
    }

    public CircuitTest() {
    }

    /********* XX ***************************/
    
    @Test
    public void XX() {
        RegTest.Utility.redirectStdOut(testout);
        // "compact version of XX"
        Circuit xx = new Circuit("XX");         // create a new circuit object xx called "XX"

        Gate A = new Input(xx,"A");
        Gate B = new Input(xx,"B");
        Gate C = new Input(xx,"C");
        Gate D = new Input(xx,"D");
        Gate alpha = new Output(xx,"alpha");
        Gate beta = new Output(xx,"beta");

        Gate nor = new Nor(xx,"nor",2);
        Gate inv = new Inv(xx,"inv");
        Gate and = new And(xx,"and",3);
        Gate or = new Or(xx,"or",2);

        new Wire(xx,A,nor,1);
        new Wire(xx,B,nor,2);
        new Wire(xx,nor,and,1);
        new Wire(xx,C,or,1);
        new Wire(xx,D,or,2);
        new Wire(xx,C,inv);
        new Wire(xx,inv,and,2);
        new Wire(xx,or,and,3);
        new Wire(xx,or,beta);
        new Wire(xx,and,alpha);

        // validate
        xx.validate();
        xx.print();

        BiConsumer<Circuit, fourWay> setInputs = (x, y) -> {
            A.set(y.a);
            B.set(y.b);
            C.set(y.c);
            D.set(y.d);
        };
        testit(xx, f -> alphaXX(f), setInputs, alpha);
        testit(xx, f -> betaXX(f), setInputs, beta);
        RegTest.Utility.validate(testout, correct+"xx.txt", false);
    }
    
    static boolean alphaXX(fourWay f) {
        boolean a = f.a;
        boolean b = f.b;
        boolean c = f.c;
        boolean d = f.d;

        boolean nor = !(a || b);
        boolean or = (c || d);
        boolean inv = !c;
        return nor && inv && or;
    }

    static boolean betaXX(fourWay f) {
        boolean a = f.a;
        boolean b = f.b;
        boolean c = f.c;
        boolean d = f.d;

        return c || d;
    }

    /*--------------------------YY------------------------*/

    @Test
    public void yy() {
        RegTest.Utility.redirectStdOut(testout);
        Circuit yy = new Circuit("YY");  

        Gate A = new Input(yy,"A");  
        Gate B = new Input(yy,"B");
        Gate C = new Input(yy,"C");
        Gate alpha = new Output(yy,"alpha");
        Gate beta = new Output(yy,"beta");
        Gate gamma = new Output(yy,"gamma");

        Gate i1 = new Inv(yy,"i1");
        Gate i2 = new Inv(yy,"i2");
        Gate i3 = new Inv(yy,"i3");
        Gate a1 = new And(yy,"a1",3);
        Gate a2 = new And(yy,"a2",3);
        Gate a3 = new And(yy,"a3",3);
        Gate a4 = new And(yy,"a4",3);
        Gate o1 = new Or(yy,"o1",4);
        
        new Wire(yy,A,i1);
        new Wire(yy,B,i2);
        new Wire(yy,C,i3);
        
        new Wire(yy,A,a2,1);
        new Wire(yy,A,a3,1);
        new Wire(yy,A,a4,1);
        
        new Wire(yy,B,a1,2);
        new Wire(yy,B,a3,2);
        new Wire(yy,B,a4,2);
        
        new Wire(yy,C,a1,3);
        new Wire(yy,C,a2,3);
        new Wire(yy,C,a4,3);
        
        new Wire(yy,i1,a1,1);
        new Wire(yy,i2,a2,2);
        new Wire(yy,i3,a3,3);
        
        new Wire(yy,a1,o1,1);
        new Wire(yy,a2,o1,2);
        new Wire(yy,a3,o1,3);
        new Wire(yy,a4,o1,4);
        
        new Wire(yy,o1,alpha);
        new Wire(yy,a1,beta);
        new Wire(yy,a4,gamma);
        
        yy.validate();
        yy.print();

        BiConsumer<Circuit, fourWay> setInputs = (x, y) -> {
            A.set(y.a);
            B.set(y.b);
            C.set(y.c);
        };
        testit(yy, f -> alphaYY(f), setInputs, alpha);
        testit(yy, f -> betaYY(f), setInputs, beta);
        testit(yy, f -> gammaYY(f), setInputs, gamma);
        RegTest.Utility.validate(testout, correct+"yy.txt", false);
    }
    
    
    static boolean alphaYY(fourWay f) {
        boolean a = f.a;
        boolean b = f.b;
        boolean c = f.c;

        boolean t1 = !a && b && c;
        boolean t2 = a && !b && c;
        boolean t3 = a && b && !c;
        boolean t4 = a && b && c;

        return t1 || t2 || t3 || t4;
    }

    static boolean betaYY(fourWay f) {
        boolean a = f.a;
        boolean b = f.b;
        boolean c = f.c;

        return !a && b && c;
    }
    
    static boolean gammaYY(fourWay f) {
        boolean a = f.a;
        boolean b = f.b;
        boolean c = f.c;

        return a && b && c;
    }

    
    /*--------------------------ZZ------------------------*/
    @Test
    public void zz() {
        RegTest.Utility.redirectStdOut(testout);
        Circuit zz = new Circuit("ZZ");  

        Gate A = new Input(zz,"A");  
        Gate B = new Input(zz,"B");
        Gate C = new Input(zz,"C");
        Gate alpha = new Output(zz,"alpha");

        Gate i1 = new Inv(zz,"i1");
        Gate o1 = new Or(zz,"o1",2);
        Gate na1 = new nAnd(zz,"na1",2);

        new Wire(zz,A,i1);
        new Wire(zz,B,o1,1);
        new Wire(zz,C,o1,2);
        new Wire(zz,i1,na1,1);
        new Wire(zz,o1,na1,2);
        new Wire(zz,na1,alpha);
  
        zz.validate();
        zz.print();

        BiConsumer<Circuit, fourWay> setInputs = (x, y) -> {
            A.set(y.a);
            B.set(y.b);
            C.set(y.c);
        };
        testit(zz, f -> alphaZZ(f), setInputs, alpha);
        RegTest.Utility.validate(testout, correct+"zz.txt", false);
    }
    
    
    static boolean alphaZZ(fourWay f) {
        boolean a = f.a;
        boolean b = f.b;
        boolean c = f.c;
        
        boolean or = b || c;
        boolean inv = !a;
        return !(or && inv);
    }

}
