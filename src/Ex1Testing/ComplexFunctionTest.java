import Ex1.ComplexFunction;
import Ex1.Monom;
import Ex1.Operation;
import Ex1.Polynom;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.Assert.fail;

public class ComplexFunctionTest {
    ComplexFunction cf;
    ComplexFunction cf1;
    Operation op = Operation.Plus;
    @Test
    void ComplexFunctionequal() {
        if (cf.equals(cf1) == false)
            fail("the complex function is equal");
    }
    @Test
    void ComplexFunctioncheckOP() {
        cf1= (ComplexFunction) cf.copy();
        if (cf1.equals(cf) == false)
            fail("copy is not good");
    }
//    @Test
//    void ComplexFunctionequal() {
//        if (cf.equals(cf1) == false)
//            fail("the complex function is equal");
//    }
//    @Test
//    void ComplexFunctionequal() {
//        if (cf.equals(cf1) == false)
//            fail("the complex function is equal");
//    }
//    @Test
//    void ComplexFunctionequal() {
//        if (cf.equals(cf1) == false)
//            fail("the complex function is equal");
//    }
//    @Test
//    void ComplexFunctionequal() {
//        if (cf.equals(cf1) == false)
//            fail("the complex function is equal");
//    }
//    @Test
//    void ComplexFunctionequal() {
//        if (cf.equals(cf1) == false)
//            fail("the complex function is equal");
//    }
//    @Test
//    void ComplexFunctionequal() {
//        if (cf.equals(cf1) == false)
//            fail("the complex function is equal");
//    }
//


    @BeforeEach
    void setUp() throws Exception {
         cf = new ComplexFunction(new Polynom("x+x+1"));
         cf1 = new ComplexFunction(new Polynom("2x+1"));
        ComplexFunction r = new ComplexFunction("plus", new Monom(2,4),new Monom(4,1));

    }
}
