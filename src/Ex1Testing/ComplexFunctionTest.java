import Ex1.ComplexFunction;
import Ex1.Monom;
import Ex1.Operation;
import Ex1.Polynom;
import org.junit.Test;

import static org.junit.Assert.*;

//להוסיף בנאי אחר גם
public class ComplexFunctionTest {
    String[] s = new String[]{"5x+1", "3x^2", "2x"};
    String[] s1 = new String[]{"x", "3x+2", "x^2"};


    @Test
    public void plus() {
        String[] expected = new String[]{"6x+1", "3x^2+3x+2", "2x+x^2"};
        for (int i = 0; i < s.length; i++) {
            ComplexFunction cf = new ComplexFunction(new Polynom(s[i]));
            ComplexFunction cf1 = new ComplexFunction(new Polynom(s1[i]));
            ComplexFunction ex = new ComplexFunction(new Polynom(expected[i]));
            cf.plus(cf1);
            assertEquals(cf, ex);
        }
    }


    @Test
    public void mul() {
        String[] expected1 = new String[]{"5+(1/x)", "(3x^2)/(3x+2)", "2/x"};
        for (int i = 0; i < s.length; i++) {
            ComplexFunction cf = new ComplexFunction(new Polynom(s[i]));
            ComplexFunction cf1 = new ComplexFunction(new Polynom(s1[i]));
            ComplexFunction ex = new ComplexFunction(new Polynom(expected1[i]));
            cf.div(cf1);
            assertEquals(cf, ex);
        }
    }

    @Test
    public void div() {
        String[] expected1 = new String[]{"5x^2+x", "9x^3+6x^2", "2x^3"};
        for (int i = 0; i < s.length; i++) {
            ComplexFunction cf = new ComplexFunction(new Polynom(s[i]));
            ComplexFunction cf1 = new ComplexFunction(new Polynom(s1[i]));
            ComplexFunction ex = new ComplexFunction(new Polynom(expected1[i]));
            cf.mul(cf1);
            assertEquals(cf, ex);
        }
    }


    @Test //עוד מקרים ומה שחוזק ממקס
    public void max() {
            ComplexFunction cf1 = new ComplexFunction("plus",new Monom(4,3),new Monom(4,2));
            ComplexFunction cf = new ComplexFunction("plus",new Monom(2,3),new Monom(2,2));
            String ex = "max(plus(2.0x^3,2.0x^2),plus(4.0x^3,4.0x^2))";
            cf.max(cf1);
            double x=cf.f(2);
            assertEquals(x,48,0.0);
    }


    @Test
    public void min() {
//            ComplexFunction cf = new ComplexFunction("plus",new Monom(4,3),new Monom(4,2));
//            ComplexFunction cf1 = new ComplexFunction(new Polynom(s1[i]));
//            ComplexFunction ex = new ComplexFunction(new Polynom(expected1[i]));
//            cf.min(cf1);
//            assertEquals(cf.equals(ex), true);
//        }
    }


    @Test
    public void comp() {
        String[] expected1 = new String[]{"5x+1", "27x^2+36x+12", "2x^2"};
        for (int i = 0; i < s.length; i++) {
            ComplexFunction cf = new ComplexFunction(new Polynom(s[i]));
            ComplexFunction cf1 = new ComplexFunction(new Polynom(s1[i]));
            ComplexFunction ex = new ComplexFunction(new Polynom(expected1[i]));
            cf.comp(cf1);
            assertEquals(cf, ex);
        }
    }


    @Test
    public void left() { //add more examples
            ComplexFunction cf = new ComplexFunction("plus",new Monom(4,3),new Monom(4,2));
            String expected = "4.0x^3";
            assertEquals(cf.left.toString(), expected);
    }

    @Test
    public void right() {
        ComplexFunction cf = new ComplexFunction("plus",new Monom(4,3),new Monom(4,2));
        String expected = "4.0x^2";
        assertEquals(cf.right.toString(), expected);
    }


    @Test
    public void getOp() {
        ComplexFunction cf = new ComplexFunction(new Polynom("x"));
        ComplexFunction cf1 = new ComplexFunction(new Polynom("x+3"));
        cf.plus(cf1);
        assertEquals(cf.getOp(), Operation.Plus);

    }

    @Test
    public void f() {
        boolean flag = true;
        double result = 0.0;
        String[] s = {"5x+1", "3x+2", "2x"};
        int x=0;
        double[][] expected1 = {{1, 6, 11}, {2, 5, 8}, {0, 2, 4}};
        for (int i = 0; i < expected1.length; i++) {
            x=0;
            ComplexFunction cf = new ComplexFunction(new Polynom(s[i]));
            for (int j = 0; j < expected1.length; j++) {
                result = cf.f(x);
                if (result != expected1[i][j]) flag = false;
                assertEquals(flag, true);
                x++;
            }
        }
    }

    @Test
    public void initFromString() {
        String[] expected1 = new String[]{"5x+1", "3x^2", "2x"};
        for (int i = 0; i < s.length; i++) {
            ComplexFunction ex = new ComplexFunction(new Polynom(expected1[i]));
            ComplexFunction cf = new ComplexFunction(new Polynom(s[i]));
            cf.initFromString(s[i]);
            assertEquals(cf, ex);
        }
    }

    @Test
    public void copy() {
        for (int i = 0; i < s.length; i++) {
            ComplexFunction cf = new ComplexFunction(new Polynom(s[i]));
            ComplexFunction cf1 = new ComplexFunction(new Polynom(s1[i]));
            cf = (ComplexFunction) cf1.copy();
            assertEquals(cf, cf1);
        }
    }

    //Add more examples
    @Test
    public void testToString() {
        ComplexFunction cf = new ComplexFunction("plus",new Monom(4,3),new Monom(4,2));
        String expected1 = "plus(4.0x^3,4.0x^2)";
            assertEquals(cf.toString(), expected1);

    }

    @Test
    public void testEquals() {
        for (int i = 0; i < s.length; i++) {
            ComplexFunction cf = new ComplexFunction(new Polynom(s[i]));
            ComplexFunction cf1 = new ComplexFunction(new Polynom(s1[i]));
            cf = (ComplexFunction) cf1.copy();
            assertEquals(cf.equals(cf1), true);
        }
    }

}

