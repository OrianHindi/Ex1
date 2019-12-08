import Ex1.Monom;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;


public class MonomTest{

    @Test
    public void getComp() { //newfunction
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
        int [] power = {1, 4, 6, 5, 0};
        for(int i=0; i < power.length; i++) {
            Monom m1 = new Monom(3 , power[i]);
            assertEquals(power[i],m1.get_power());
        }
    }

    @Test
    public void derivative() {
        String[] s = new String[]{"5x", "3x^2", "2x"};
        String[] res = new String[]{"5.0", "6.0x", "2.0"};
        for (int i = 0; i < s.length; i++) {
            Monom m1 = new Monom(s[i]);
            Monom m2 = new Monom(res[i]);
            m1=m1.derivative();
            assertEquals(m1.toString(), m2.toString());
        }
    }


    @Test
    public void f() {
        boolean flag = true;
        double res = 0.0;
        String[] monom = {"5x", "3x", "2x"};
        int x=0;
        double[][] expected1 = {{0, 5, 10}, {0, 3, 6}, {0, 2, 4}};
        for (int i = 0; i < expected1.length; i++) {
            x=0;
            Monom m1 = new Monom(monom[i]);
            for (int j = 0; j < expected1.length; j++) {
                res = m1.f(x);
                if (res != expected1[i][j]) flag = false;
                assertEquals(flag, true);
                x++;
            }
        }
    }


    @Test
    public void testMonomString() {
        String[] s = new String[]{"5x^1", "3x^2", "2x^0"};
        String[] res = new String[]{"5.0x", "3.0x^2", "2.0"};
        for (int i = 0; i < s.length; i++) {
            Monom m1 = new Monom(s[i]);
            Monom m2 = new Monom(res[i]);
            assertEquals(m1.toString(), m2.toString());
        }
    }


        @Test
        public void isZero () {
            String[] s = new String[]{"0x", "0", "0x^2"};
            boolean ans = true;
            for (int i = 0; i < s.length; i++) {
                Monom m1 = new Monom(s[i]);
               ans = m1.isZero();
               assertEquals(ans, true);
            }
        }

        @Test
        public void add () {
            String[] s = new String[]{"5x", "3x", "2x"}; //"bx", "ax"
            String[] s1 = new String[]{"x", "3x", "x" }; // "x", "zx"
            String[] expected = new String[]{"6.0x", "6.0x", "3.0x", "0.0"};
            for (int i = 0; i < s.length; i++) {
                Monom m1 = new Monom(s[i]);
                Monom m2 = new Monom(s1[i]);
                Monom ex = new Monom(expected[i]);
                m1.add(m2);
                assertEquals(m1.toString(), ex.toString());
            }
        }


        @Test
        public void multipy () {
            String[] s = new String[]{"5x", "3x", "2x"};
            String[] s1 = new String[]{"x", "3x", "x"};
            String[] expected = new String[]{"5.0x^2", "9.0x^2", "2x^2"};
            for (int i = 0; i < s.length; i++) {
                Monom m1 = new Monom(s[i]);
                Monom m2 = new Monom(s1[i]);
                Monom ex = new Monom(expected[i]);
                m1.multipy(m2);
                assertEquals(m1.toString(), ex.toString());
                //try {
//                m1.add(m2);
//                fail("wrong input");
                // }
            }
//            catch(RuntimeException e) {}
        }


    @Test
    public void testToString() {
        String[] s = new String[]{"5.0x", "3.0x", "2.0x"};
        for (int i = 0; i < s.length; i++) {
            Monom m1 = new Monom(s[i]);
           assertEquals(m1.toString(), s[i]);
        }
    }

    @Test
    public void initFromString() { //newfunction
    }

    @Test
    public void copy() {
        String[] s = new String[]{"5.0x", "3.0x", "2.0x"};
        Monom m1 = new Monom("");
        for (int i = 0; i < s.length; i++) {
            Monom m2 = new Monom(s[i]);
            m1= (Monom) m2.copy();
            assertEquals(m1.toString(), m2.toString());
        }
    }

    @Test
    public void testEquals() {
        boolean flag = true;
        String[] s = new String[]{"5.0x", "3.0x", "2.0x"};
        String[] s1 = new String[]{"5x", "3x", "2x"};
        for (int i = 0; i < s.length; i++) {
            Monom m1 = new Monom(s[i]);
            Monom m2 = new Monom(s1[i]);
            flag = m1.equals(m2);
            assertEquals(flag , true);
           }
         }
    }
