import Ex1.*;
import org.junit.Test;

import java.util.Iterator;

import static org.junit.Assert.*;
/**
 * Note: minor changes (thanks to Amichai!!)
 * The use of "get" was replaced by iterator!
 *
 * Partial JUnit + main test for the GUI_Functions class, expected output from the main:
 * 0) java.awt.Color[r=0,g=0,b=255]  f(x)= plus(-1.0x^4 +2.4x^2 +3.1,+0.1x^5 -1.2999999999999998x +5.0)
 1) java.awt.Color[r=0,g=255,b=255]  f(x)= plus(div(+1.0x +1.0,mul(mul(+1.0x +3.0,+1.0x -2.0),+1.0x -4.0)),2.0)
 2) java.awt.Color[r=255,g=0,b=255]  f(x)= div(plus(-1.0x^4 +2.4x^2 +3.1,+0.1x^5 -1.2999999999999998x +5.0),-1.0x^4 +2.4x^2 +3.1)
 3) java.awt.Color[r=255,g=200,b=0]  f(x)= -1.0x^4 +2.4x^2 +3.1
 4) java.awt.Color[r=255,g=0,b=0]  f(x)= +0.1x^5 -1.2999999999999998x +5.0
 5) java.awt.Color[r=0,g=255,b=0]  f(x)= max(max(max(max(plus(-1.0x^4 +2.4x^2 +3.1,+0.1x^5 -1.2999999999999998x +5.0),plus(div(+1.0x +1.0,mul(mul(+1.0x +3.0,+1.0x -2.0),+1.0x -4.0)),2.0)),div(plus(-1.0x^4 +2.4x^2 +3.1,+0.1x^5 -1.2999999999999998x +5.0),-1.0x^4 +2.4x^2 +3.1)),-1.0x^4 +2.4x^2 +3.1),+0.1x^5 -1.2999999999999998x +5.0)
 6) java.awt.Color[r=255,g=175,b=175]  f(x)= min(min(min(min(plus(-1.0x^4 +2.4x^2 +3.1,+0.1x^5 -1.2999999999999998x +5.0),plus(div(+1.0x +1.0,mul(mul(+1.0x +3.0,+1.0x -2.0),+1.0x -4.0)),2.0)),div(plus(-1.0x^4 +2.4x^2 +3.1,+0.1x^5 -1.2999999999999998x +5.0),-1.0x^4 +2.4x^2 +3.1)),-1.0x^4 +2.4x^2 +3.1),+0.1x^5 -1.2999999999999998x +5.0)

 * @author boaz_benmoshe
 *
 */
public class Functions_GUITest {
    static ComplexFunction helper = new ComplexFunction();
    public static void main(String[] a) {
        functions data = FunctionsFactory();
        int w=1000, h=600, res=200;
        Range rx = new Range(-10,10);
        Range ry = new Range(-5,15);
        data.drawFunctions(w,h,rx,ry,res);
        String file = "function_file.txt";
        String file2 = "function_file2.txt";
        try {
            data.saveToFile(file);
            Functions_GUI data2 = new Functions_GUI();
            data2.initFromFile(file);
            data.saveToFile(file2);
        }
        catch(Exception e) {e.printStackTrace();}

        String JSON_param_file = "/Users/yardn/Desktop/GUI_params.txt";
        data.drawFunctions(JSON_param_file);
    }
    private functions _data=null;

    @Test
    public void initFromFile() {
        Functions_GUI xf = new Functions_GUI();
        String[] cf = {"max(div(mul(x^12,4x^3),4x^2),3)","min(comp(4x^2,3),3)","x^2+5x+3","max(mul(div(4x,3),x^3),3x+2)"};
        for (int i = 0; i <cf.length ; i++) {
            xf.add(helper.initFromString(cf[i]));
        }
        Functions_GUI same = new Functions_GUI();
        try {
            same.initFromFile("/Users/yardn/Desktop/testfile.txt");
        }
        catch (Exception e){
            e.printStackTrace();
        }
        for (int i = 0; i <xf.size() ; i++) {
            assertEquals(xf.get(i),same.get(i));
        }
    }

    @Test
    public void saveToFile() {
        String[] cf = {"max(div(mul(x^12,4x^3),4x^2),3)","min(comp(4x^2,3),3)","x^2+5x+3","max(mul(div(4x,3),x^3),3x+2)"};
        Functions_GUI fg = new Functions_GUI();
        for (int i = 0; i <cf.length ; i++) {
            fg.add(helper.initFromString(cf[i]));
        }
        try {
            fg.saveToFile("/Users/yardn/Desktop/1234.txt");
        }
        catch (Exception e){
            e.printStackTrace();
        }
        Functions_GUI fg2= new Functions_GUI();
        try {
            fg2.initFromFile("/Users/yardn/Desktop/1234.txt");
        }
        catch (Exception e){
            e.printStackTrace();
        }
        for (int i = 0; i <fg2.size() ; i++) {
            assertEquals(fg.get(i).toString(),fg2.get(i).toString());
        }
    }


    //the default drawFunctions.
    @Test
    public void drawFunctions() {
        Functions_GUI xf = new Functions_GUI();
        try {
            xf.initFromFile("/Users/yardn/Desktop/function_file.txt");
        }
        catch(Exception e){
            e.printStackTrace();
        }
        xf.drawFunctions();
    }

    //the function that get parameters.
    @Test
    public void testDrawFunctions() {
        Functions_GUI xf = new Functions_GUI();
        try{
            xf.initFromFile("/Users/yardn/Desktop/function_file.txt");
        }
        catch(Exception e){
            e.printStackTrace();
        }
        Range rx = new Range(-12,12);
        Range ry = new Range(-20,20);
        xf.drawFunctions(500,550,rx,ry,300);
    }
    //draw function that get a parameters from jsonfile.
    @Test
    public void testDrawFunctions1() {
        Functions_GUI xf = new Functions_GUI();
        try{
            xf.initFromFile("/Users/yardn/Desktop/function_file.txt");
        }
        catch (Exception e){
            e.printStackTrace();
        }
        xf.drawFunctions("/Users/yardn/Desktop/GUI_params.txt");
    }
    public static functions FunctionsFactory() {
        functions ans = new Functions_GUI();
        String s1 = "3.1 +2.4x^2 -x^4";
        String s2 = "5 +2x -3.3x +0.1x^5";
        String[] s3 = {"x +3","x -2", "x -4"};
        Polynom p1 = new Polynom(s1);
        Polynom p2 = new Polynom(s2);
        Polynom p3 = new Polynom(s3[0]);
        ComplexFunction cf3 = new ComplexFunction(p3);
        for(int i=1;i<s3.length;i++) {
            cf3.mul(new Polynom(s3[i]));
        }

        ComplexFunction cf = new ComplexFunction(Operation.Plus, p1,p2);
        ComplexFunction cf4 = new ComplexFunction("div", new Polynom("x +1"),cf3);
        cf4.plus(new Monom("2"));
        ans.add(cf.copy());
        ans.add(cf4.copy());
        cf.div(p1);
        ans.add(cf.copy());
        String s = cf.toString();
        function cf5 = cf4.initFromString(s1);
        function cf6 = cf4.initFromString(s2);
        ans.add(cf5.copy());
        ans.add(cf6.copy());
        Iterator<function> iter = ans.iterator();
        function f = iter.next();
        ComplexFunction max = new ComplexFunction(f);
        ComplexFunction min = new ComplexFunction(f);
        while(iter.hasNext()) {
            f = iter.next();
            max.max(f);
            min.min(f);
        }
        ans.add(max);
        ans.add(min);
        return ans;
    }
}