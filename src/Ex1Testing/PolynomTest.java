import Ex1.Monom;
import Ex1.Polynom;
import Ex1.Polynom_able;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class PolynomTest {
    static Polynom[] Polynoms = new Polynom[6];
    static Polynom[] Polynoms1 = new Polynom[6];
    static String[] StringsForPolynom ={"x^2+1.3x-1","-x^3+x^5-1.4x+1.3x^3+5","2x","-x+3","7x^50+x","-x+4x-3"};
    @Before
    public void BeforeEach(){
        Polynoms[0]= new Polynom("x+x^3-5");
        Polynoms[1]= new Polynom("4.2x^3+12x+0");
        Polynoms[2]= new Polynom("2.6x");
        Polynoms[3] = new Polynom("3");
        Polynoms[4] = new Polynom("-x^3+2x-4x^2+1");
        Polynoms[5] = new Polynom("-0.3x^2+5x+1");

        for (int i = 0; i <Polynoms1.length ; i++) {
            Polynoms1[i]= new Polynom(StringsForPolynom[i]);
        }

    }
    @Test
    public void f() {
        double[] expected ={30.968,316.8,-13,3,-2,1};
        double[] values ={3.2,4,-5,15,1,0};
        for (int i = 0; i < expected.length; i++) {
            assertEquals(Polynoms[i].f(values[i]),expected[i], Monom.EPSILON);
        }

    }


    @Test
    public void add() {
        for (int i = 0; i <Polynoms1.length ; i++) {
            Polynoms1[i]= new Polynom(StringsForPolynom[i]);
        }
        String[] expected = {"1.0x^3+1.0x^2+2.3x-6.0","1.0x^5+4.5x^3+10.6x+5.0","4.6x","-x+6.0","7.0x^50-1.0x^3-4.0x^2+3.0x+1.0","-0.3x^2+8.0x-2.0"};

        for (int i = 0; i <Polynoms1.length ; i++) {
            Polynoms[i].add(Polynoms1[i]);
            assertEquals(expected[i],Polynoms[i].toString());
        }
        Polynom s1 = new Polynom("0");
        String s = "0.0";
        assertEquals(s1.toString(),s);
    }

    @Test
    public void testAdd() {
        String[] expected = {"1.0x^3+x-5.0" , "8.53333x^3+12.0x","-1.0x^2+2.6x","0.5x+3.0","-1.0x^3-4.0x^2+2.0x+5.0","-0.3x^2+4.0x+1.0"};
        String[] monomstring= {"0.0","4.33333x^3","-x^2","0.5x","+4","-x"};
        Monom[] monomsAdd = new Monom[6];
        for (int i = 0; i <monomsAdd.length ; i++) {
            monomsAdd[i]= new Monom(monomstring[i]);
        }
        for (int i = 0; i <Polynoms.length ; i++) {
            Polynoms[i].add(monomsAdd[i]);
            assertEquals(Polynoms[i].toString(),expected[i]);
        }
    }

    @Test
    public void substract() {
        String[] expected={"x^3-1.0x^2-0.3x-4.0","-1.0x^5+3.9x^3+13.4x-5.0","0.6x","x","-7x^50-1.0x^3-4.0x^2+x+1.0","-0.3x^2+2.0x+4.0"};
        for (int i = 0; i <Polynoms.length ; i++) {
            Polynoms[i].substract(Polynoms1[i]);
            assertTrue(Polynoms[i].equals(new Polynom(expected[i])));
        }
    }

    @Test
    public void multiply() {
        String[] expected = {"1.0x^5+1.3x^4-3.7x^2-7.5x+5.0","4.2x^8+13.26x^6-2.2799999999999994x^4+21.0x^3-16.799999999999997x^2+60.0x","5.2x^2","-3.0x+9.0","-7.0x^53-28.0x^52+14.0x^51+7.0x^50-1.0x^4-4.0x^3+2.0x^2+x","-0.8999999999999999x^3+15.9x^2-12.0x-3.0"};
        for (int i = 0; i <Polynoms.length ; i++) {
            Polynoms[i].multiply(Polynoms1[i]);
            assertTrue(Polynoms[i].equals(new Polynom(expected[i])));
        }
    }

    @Test
    public void TestEquals() {
        String[] expected ={"x^2+1.3x","x^5+0.3x^3-0.4x+6","x^2+2x+2","x^3-x+6","7x^50+x^4+x+4","x^5+3x+2"};
        Polynom[] ans = new Polynom[6];
        for (int i = 0; i <expected.length ; i++) {
            ans[i]= new Polynom(expected[i]);
        }
        for (int i = 0; i <Polynoms1.length ; i++) {
            Polynoms1[i].add(new Monom("x^"+i));
            Polynoms1[i].add(new Monom("" +i));
            assertEquals(true,Polynoms1[i].equals(ans[i]));
        }
        for (int i = 0; i <Polynoms.length ; i++) {
            Polynom p = new Polynom(Polynoms[i].toString());
            Polynoms[i].substract(p);
            assertEquals(true,Polynoms[i].equals(Monom.ZERO));
        }
    }

    @Test
    public void isZero() {
        for (int i = 0; i < Polynoms.length; i++) {
            Polynom p = new Polynom(Polynoms[i].toString());
            Polynoms[i].substract(p);
            assertTrue(Polynoms[i].isZero());
        }
        Polynoms[0]= new Polynom("x+x^3-5");
        Polynoms[1]= new Polynom("4.2x^3+12x+0");
        Polynoms[2]= new Polynom("2.6x");
        Polynoms[3] = new Polynom("3");
        Polynoms[4] = new Polynom("-x^3+2x-4x^2+1");
        Polynoms[5] = new Polynom("-0.3x^2+5x+1");
        for (int i = 0; i <Polynoms.length ; i++) {
            Polynoms[i].add(Polynoms1[i]);
            assertEquals(false,Polynoms[i].isZero());
        }
    }
    @Test
    public void root() {
        double[] expected = {2.23606,2.11305};
        Polynom s = new Polynom("x^2-5");
        Polynom s1 = new Polynom("x^3+5x-20");
        assertEquals(expected[0],s.root(1,3,Monom.EPSILON),0.0001);
        assertEquals(expected[1],s1.root(1,3,Monom.EPSILON),0.0001);
    }

    @Test
    public void copy() {
        Polynom_able[] copies = new Polynom[6];
        for (int i = 0; i <Polynoms.length ; i++) {
            copies[i]=Polynoms[i].copy();
        }
        for (int i = 0; i <Polynoms.length ; i++) {
            assertEquals(Polynoms[i].toString(),copies[i].toString());
        }
    }

    @Test
    public void derivative() {
        String[] expected = {"3.0x^2+1.0","12.6x^2+12.0","2.6","0.0","-3.0x^2-8.0x+2.0","-0.6x+5.0"};
        for (int i = 0; i <Polynoms.length ; i++) {
           Polynom_able p = Polynoms[i].derivative();
            assertTrue(p.equals(new Polynom(expected[i])));
        }
    }

    @Test
    public void area() {
        Polynom s= new Polynom("-3x+4");
        Polynom s1= new Polynom("5x^2-5x-76");
        Polynom s2 = new Polynom("-5x^2+5");
        assertEquals(10.4016670,s.area(-1.3,3,Monom.EPSILON),Monom.EPSILON);
        assertEquals(0.0,s1.area(-1.3,3,Monom.EPSILON),Monom.EPSILON);
        assertEquals(6.6666666,s2.area(-1.3,3,Monom.EPSILON),Monom.EPSILON);


    }

    @Test
    public void testMultiply() {
        Polynom p = new Polynom("x-1");
        Polynom p2= new Polynom("x+1");
        Polynom p3 = new Polynom("x^3+x-5");
        Polynom p4 = new Polynom("4x");
        p.multiply(p2);
        p3.multiply(p4);
        Polynom ans= new Polynom("x^2-1");
        Polynom ans2 = new Polynom("4x^4+4x^2-20x");
        assertEquals(true,p.equals(ans));
        assertEquals(true, p3.equals(ans2));
    }

    @Test
    public void testToString() {
        String[] expected = {"1.0x^3+x-5.0","4.2x^3+12.0x","2.6x","3.0","-1.0x^3-4.0x^2+2.0x+1.0","-0.3x^2+5.0x+1.0"};
        for (int i = 0; i <Polynoms.length ; i++) {
            assertEquals(expected[i],Polynoms[i].toString());
        }
    }

    @Test
    public void initFromString() {
        Polynom help = new Polynom();
        String[] forpoly = {"1.0x^3+x-5.0","4.2x^3+12.0x","2.6x","3.0","-1.0x^3-4.0x^2+2.0x+1.0","-0.3x^2+5.0x+1.0"};
        Polynom[] check = new Polynom[6];
        for (int i = 0; i <check.length ; i++) {
            check[i]=(Polynom)help.initFromString(forpoly[i]);
        }
        for (int i = 0; i <check.length ; i++) {
            assertEquals(true,Polynoms[i].equals(check[i]));
        }

    }
}