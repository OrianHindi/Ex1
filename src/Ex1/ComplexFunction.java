package Ex1;

import java.util.Objects;

/**
 * @authors Orian Hindi and Shani Hayik.
 */
public class ComplexFunction implements  complex_function {
    public Operation op=Operation.None;
    public function left=null,right=null;

    /**
     * Constructor that buid a ComplexFunction from Operation and two function.
     * @param op the Operation for the new function.
     * @param left the left function of the complex function.
     * @param right the right function of the complex function.
     */
    public ComplexFunction(Operation op, function left,function right){
       if(op == Operation.Error) throw new RuntimeException("ERROR.Change Operation.");
        this.op = op;
        this.left= left;
        this.right=right;
    }

    /**
     * Constructor that build a ComplexFunction From string and two function.
     * @param s String that repesent the Operation from the ComplexFunction.
     * @param left the left function of the complex function.
     * @param right the right function of the complex function.
     *  throw Runtimeexception error if the if the Operation in None with 2 functions or the Operation is Error.
     */
    public ComplexFunction(String s, function left, function right) {
        s = s.toLowerCase();
        switch (s) {
            case "plus":
                this.op = Operation.Plus;
                this.left = left;
                this.right = right;
                break;

            case "div":
                this.op = Operation.Divid;
                this.left = left;
                this.right = right;
                break;

            case "mul":
                this.op = Operation.Times;
                this.left = left;
                this.right = right;
                break;

            case "max":
                this.op = Operation.Max;
                this.left = left;
                this.right = right;
                break;

            case "min":
                this.op = Operation.Min;
                this.left = left;
                this.right = right;
                break;

            case "comp":
                this.op = Operation.Comp;
                this.left = left;
                this.right = right;
                break;

            default:
               throw new RuntimeException("Wrong string for Operation.try again.");
        }
    }

    /**
     * A copy Constructor.
     * @param f is a complex function that we want to copy.
     */
    public ComplexFunction(ComplexFunction f) {
        this.op = f.op;
        this.left = f.left;
        this.right = f.right;
    }

    /**
     * A default Constructor for self use in the inside classes.
     */
    public ComplexFunction() {
        this.left = this.right = null;
    }

    /**
     * A Constructor that build a Complex function with only left function,right function point to null and Operation is None.
     * @param left the left function of the new complex function.
     */
    public ComplexFunction(function left) {
        this.left = left;
        this.right = null;
    }

    /**
     *Add to this Complex function the right function.
     * @param right the function which will be added to this complex function.
     */
    @Override
    public void plus(function right) {
        if (this.right == null) {
            this.right = right;
            this.op = Operation.Plus;
            return;
        }
        ComplexFunction temp = new ComplexFunction();
        temp.op = Operation.Plus;
        temp.left = new ComplexFunction(this);
        temp.right = right;
        this.op = temp.op;
        this.left = temp.left;
        this.right = temp.right;
    }

    /**
     * Multiply this Complex function the the right function.
     * @param right the function which will multiply with this complex function.
     */
    @Override
    public void mul(function right) {
        if (this.right == null) {
            this.right = right;
            this.op = Operation.Times;
            return;
        }
        ComplexFunction temp = new ComplexFunction();
        temp.op = Operation.Times;
        temp.left = new ComplexFunction(this);
        temp.right = right;
        this.op = temp.op;
        this.left = temp.left;
        this.right = temp.right;
    }

    /**
     * Divide this Complex function with right function.
     * @param right the function which will be Divide with this Complex function.
     */
    @Override
    public void div(function right) {
        if (this.right == null) {
            this.right = right;
            this.op = Operation.Divid;
            return;
        }
        ComplexFunction temp = new ComplexFunction();
        temp.op = Operation.Divid;
        temp.left = new ComplexFunction(this);
        temp.right = right;
        this.op = temp.op;
        this.left = temp.left;
        this.right = temp.right;
    }

    /**
     * Computes the maximum over this complex function and the right function.
     * @param right the function which will be compared with this complex function to compute to maximum.
     */
    @Override
    public void max(function right) {
        if (this.right == null) {
            this.right = right;
            this.op = Operation.Max;
            return;
        }
        ComplexFunction temp = new ComplexFunction();
        temp.op = Operation.Max;
        temp.left = new ComplexFunction(this);
        temp.right = right;
        this.op = temp.op;
        this.left = temp.left;
        this.right = temp.right;
    }

    /**
     * Computes the minimum over this complex function and the right function.
     * @param right the function which will be compared with this complex function to compute to minimum,
     */
    @Override
    public void min(function right) {
        if (this.right == null) {
            this.right = right;
            this.op = Operation.Min;
            return;
        }
        ComplexFunction temp = new ComplexFunction();
        temp.op = Operation.Min;
        temp.left = new ComplexFunction(this);
        temp.right = right;
        this.op = temp.op;
        this.left = temp.left;
        this.right = temp.right;

    }

    /**
     * this method wrap the right function with this function : this.f(right.f(x)).
     * @param right function.
     */
    @Override
    public void comp(function right) {
        if (this.right == null) {
            this.right = right;
            this.op = Operation.Comp;
            return;
        }
        ComplexFunction temp = new ComplexFunction();
        temp.op = Operation.Comp;
        temp.left = new ComplexFunction(this);
        temp.right = right;
        this.op = temp.op;
        this.left = temp.left;
        this.right = temp.right;
    }

    /**
     * return the left side of this complex function , if the function is valid will never be null.
     * @return a function represent the left side of this Complex function.
     */
    @Override
    public function left() {
        return this.left;
    }

    /**
     * return the right side of this Complex function, this side could be null.
     * @return function represent the right side of this complex function .(if it have right side.)
     */
    @Override
    public function right() {
        return this.right;
    }

    /**
     * ComplexFunction operations :PLUS,TIMES,DIVID,COMP,MAX,MIN,ERROR,NONE,
     * @return Operation of this Complex function.
     */
    @Override
    public Operation getOp() {
        return this.op;
    }

    /**
     * this calculate the Complex function value at point.
     * @param x is a parameter that we want see the value of the function at this x.
     * @return the value of the function at point x.
     */
    @Override
    public double f(double x) {
        if (this.op == Operation.Times) return this.left.f(x) * this.right.f(x);
        if (this.op == Operation.Plus) return this.left.f(x) + this.right.f(x);
        if (this.op == Operation.Divid) return this.left.f(x) / this.right.f(x);
        if (this.op == Operation.Max) return Math.max((this.left.f(x)), this.right.f(x));
        if (this.op == Operation.Min) return Math.min(this.left.f(x), this.right.f(x));
        if (this.op == Operation.Comp) return this.left.f(this.right.f(x));
        else return this.left.f(x);

    }

    @Override
    public function initFromString(String s) {
        ComplexFunction temp = new ComplexFunction();
        function ans = temp.initRecursive(s);
        return ans;
    }

    private function initRecursive(String s) {
        if (!s.contains("(") || !s.contains(")")) {
            return new Polynom(s);
        }
        String help = "";
        int i = s.indexOf('(');
        help = s.substring(0, i);
        int Index = getPsik(s);
        function left = initRecursive(s.substring(i + 1, Index));
        function right = initRecursive(s.substring(Index + 1, s.length() - 1));

        function ans = new ComplexFunction(help, left, right);
        return ans;
    }

    private int getPsik(String s) {
        int count = 0;
        int ans = 0;
        for (int i = s.length() - 1; i > -0; i--) {
            if (s.charAt(i) == ')') count++;
            if (s.charAt(i) == '(') count--;
            if (s.charAt(i) == ',' && count == 1) {
                ans = i;
                break;
            }
        }
        return ans;
    }

    @Override
    public function copy() {
        function ans = new ComplexFunction(this);
        return ans;
    }

    public String toString() {
        if(this.op==Operation.None&& this.left!=null) return this.left.toString();
        return this.checkOP(this.op) + this.left.toString() + "," + this.right.toString() + ")";
    }

    private Operation opFromString(String s) {
        switch (s) {
            case "plus":
                return Operation.Plus;
            case "div":
                return Operation.Divid;
            case "mul":
                return Operation.Times;
            case "max":
                return Operation.Max;
            case "min":
                return Operation.Min;
            case "comp":
                return Operation.Comp;
            default:
                return Operation.None;
        }
    }

    private String checkOP(Operation op) {
        switch (op) {
            case Plus:
                return "plus(";

            case Times:
                return "mul(";

            case Divid:
                return "div(";

            case Max:
                return "max(";

            case Min:
                return "min(";

            case Comp:
                return "comp(";

            case None:

            default:
                return "";
        }
    }

    public boolean equals(Object obj) {
        function cf = (function) obj;
        int x=-100;
        while (x<=100) {
            double s1=(double)Math.round(this.f(x)*10000000000000000d/10000000000000000d);
            double s2 = (double)Math.round(cf.f(x)*10000000000000000d/10000000000000000d);
            if (Math.abs(s1-s2)>Monom.EPSILON) return false;

            x++;
        }
        return true;
    }

}
