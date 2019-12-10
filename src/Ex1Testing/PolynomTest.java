import Ex1.Monom;
import Ex1.Polynom;
import org.junit.BeforeClass;
import org.junit.Test;

public class PolynomTest {
    static Polynom[] Polynoms = new Polynom[6];
    static Polynom[] Polynoms1 = new Polynom[6];
    static String[] StringsForPolynom ={"x^2+1.3x-1","-x^3+x^5-1.4x+1.3x^3+5","2x","-x+3","7x^50+x","-x+4x-3"};
    @BeforeClass
    public static void BeforeAll(){
        Polynoms[0]= new Polynom("x+x^3-5");
        Polynoms[1]= new Polynom("4.2x^3+12x+0");
        Polynoms[2]= new Polynom("2.6x");
        Polynoms[3] = new Polynom("3");
        Polynoms[4] = new Polynom("-x^3+2x-4x^2+1");
        Polynoms[5] = new Polynom("-0.3x^2+5x+1");

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
        String[] expected = {"1.0x^3+1.0x^2+2.3x-6.0","1.0x^5+4.5x^3+10.6x+5.0","4.6x","-x+6","7.0x^50-1.0x^3-4.0x^2+3.0x+1.0","-0.3x^2+8.0x-2.0"};
        for (int i = 0; i <Polynoms1.length ; i++) {

        }


    }

    @Test
    public void testAdd() {
    }

    @Test
    public void substract() {
    }

    @Test
    public void multiply() {
    }

    @Test
    public void testEquals() {
    }

    @Test
    public void isZero() {
    }

    @Test
    public void root() {
    }

    @Test
    public void copy() {
    }

    @Test
    public void derivative() {
    }

    @Test
    public void area() {
    }

    @Test
    public void iteretor() {
    }

    @Test
    public void testMultiply() {
    }

    @Test
    public void sortPolynom() {
    }

    @Test
    public void checkZeros() {
    }

    @Test
    public void testToString() {
    }

    @Test
    public void initFromString() {
    }

    @Test
    public void checkSame() {
    }

    @Test
    public void main() {
    }
}