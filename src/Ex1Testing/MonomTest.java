import org.junit.Test;
import Ex1.Monom;

import static org.junit.Assert.*;

public class MonomTest{

    @Test
    public void getComp() {
    }

    @Test
    public void get_coefficient() {
        Monom s= new Monom(1,4);
        Monom s1=new Monom("-x");
        Monom s2= new Monom("4.7x");
        assertEquals(1,s.get_coefficient(),0.0);
        assertEquals(-1,s1.get_coefficient(),0.0);
        assertEquals(4.7,s2.get_coefficient(),0.0);
    }

    @Test
    public void get_power() {
    }

    @Test
    public void derivative() {
    }

    @Test
    public void f() {
    }

    @Test
    public void isZero() {
    }

    @Test
    public void add() {
        boolean flag = true;
        Monom s= new Monom("x");
        Monom s1=new Monom("4x");
        Monom s2=new Monom("5x");
        s.add(s1);
        if (s == s2) {
            flag = false;
        }
        assertEquals(flag,true );

    }

    @Test
    public void multipy() {
        boolean flag = true;
        Monom s= new Monom("x");
        Monom s1=new Monom("4x");
        Monom s2=new Monom("4x^2");
        s.multipy(s1);
        if (s == s2) {
            flag = false;
        }
        assertEquals(flag,true );
    }

    @Test
    public void testToString() {
    }

    @Test
    public void initFromString() {
    }

    @Test
    public void copy() {

    }

    @Test
    public void testEquals() {
//        boolean flag = true;
//        Monom s= new Monom("x");
//        Monom s1= new Monom("x");
//        if (s != s1) flag = false;
//        assertEquals(flag , true);

    }

    @Test
    public void main() {
    }
}