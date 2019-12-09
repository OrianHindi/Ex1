package Ex1;

public class ComplexFunction implements  complex_function {
    private Operation op=Operation.None;
    private function left,right=null;

    public ComplexFunction(String s,function left,function right){
        s=s.toLowerCase();
        switch(s){
            case "plus":
                this.op=Operation.Plus;
                this.left=left;
                this.right=right;
                break;

            case "div":
                this.op=Operation.Divid;
                this.left=left;
                this.right=right;
                break;

            case "mul":
                this.op=Operation.Times;
                this.left=left;
                this.right=right;
                break;

            case "max":
                this.op=Operation.Max;
                this.left=left;
                this.right=right;
                break;

            case "min":
                this.op=Operation.Min;
                this.left=left;
                this.right=right;
                break;

            case "comp":
                this.op=Operation.Comp;
                this.left=left;
                this.right=right;
                break;

            default:
                System.err.println("Wrong Operation,CF didnt built.");
                return;
        }
    }
    public ComplexFunction(ComplexFunction f){
        this.op=f.op;
        this.left=f.left;
        this.right=f.right;
    }

    public ComplexFunction(){
        this.left=this.right=null;
    }

    public ComplexFunction(function left){
        this.left=left;
        this.right=null;
    }


    @Override
    public void plus(function right) {
        if(this.right==null){
            this.right=right;
            this.op=Operation.Plus;
            return;
        }
        ComplexFunction temp = new ComplexFunction();
        temp.op=Operation.Plus;
        temp.left= new ComplexFunction(this);
        temp.right=right;
        this.op=temp.op;
        this.left=temp.left;
        this.right=temp.right;
    }

    @Override
    public void mul(function right) {
        if(this.right==null){
            this.right=right;
            this.op=Operation.Times;
            return;
        }
        ComplexFunction temp = new ComplexFunction();
        temp.op=Operation.Times;
        temp.left= new ComplexFunction(this);
        temp.right=right;
        this.op=temp.op;
        this.left=temp.left;
        this.right=temp.right;
    }

    @Override
    public void div(function right) {
        if(this.right==null){
            this.right=right;
            this.op=Operation.Divid;
            return;
        }
        ComplexFunction temp = new ComplexFunction();
        temp.op=Operation.Divid;
        temp.left= new ComplexFunction(this);
        temp.right=right;
        this.op=temp.op;
        this.left=temp.left;
        this.right=temp.right;
    }

    @Override
    public void max(function right) {
      if(this.right==null){
          this.right=right;
          this.op=Operation.Max;
          return;
      }
        ComplexFunction temp = new ComplexFunction();
        temp.op=Operation.Max;
        temp.left= new ComplexFunction(this);
        temp.right=right;
        this.op=temp.op;
        this.left=temp.left;
        this.right=temp.right;
    }


    @Override
    public void min(function right) {
        if(this.right==null){
            this.right=right;
            this.op=Operation.Min;
            return;
        }
        ComplexFunction temp = new ComplexFunction();
        temp.op=Operation.Min;
        temp.left= new ComplexFunction(this);
        temp.right=right;
        this.op=temp.op;
        this.left=temp.left;
        this.right=temp.right;

    }

    @Override
    public void comp(function right) {
        if(this.right==null){
            this.right=right;
            this.op=Operation.Comp;
            return;
        }
        ComplexFunction temp = new ComplexFunction();
        temp.op=Operation.Comp;
        temp.left= new ComplexFunction(this);
        temp.right=right;
        this.op=temp.op;
        this.left=temp.left;
        this.right=temp.right;
    }

    @Override
    public function left() {
        return this.left;
    }

    @Override
    public function right() {
        return this.right;
    }

    @Override
    public Operation getOp() {
        return this.op;
    }

    @Override
    public double f(double x) {
        if(this.op== Operation.Times) return this.left.f(x)*this.right.f(x);
        if(this.op==Operation.Plus) return this.left.f(x)+this.right.f(x);
        if(this.op==Operation.Divid) return this.left.f(x)/this.right.f(x);
        if(this.op==Operation.Max) return Math.max((this.left.f(x)),this.right.f(x));
        if(this.op==Operation.Min) return Math.min(this.left.f(x),this.right.f(x));
        if(this.op==Operation.Comp) return this.left.f(this.right.f(x));
        else return this.left.f(x);

    }

    @Override
    public function initFromString(String s) {
        ComplexFunction temp = new ComplexFunction();
        function ans = temp.initRecursive(s);
        return ans;
    }
    private function initRecursive(String s){
        if(!s.contains("(") || !s.contains(")")){
            return new Polynom(s);

        }
        String help = "";
        int i = s.indexOf('(');
        help=s.substring(0,i);
        int Index = getPsik(s);
        function left=initRecursive(s.substring(i+1,Index));
        function right=initRecursive(s.substring(Index+1,s.length()-1));

        function ans = new ComplexFunction(help,left,right);
        return ans;
    }
    private int getPsik(String s){
        int count = 0;
        int ans=0;
        for (int i =s.length()-1;i>-0;i--) {
            if(s.charAt(i) == ')') count++;
            if(s.charAt(i) == '(') count --;
            if(s.charAt(i)== ',' && count==1){
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
    public String toString(){
        return this.checkOP(this.op) + this.left.toString() + "," + this.right.toString() + ")";
    }
    private Operation opFromString(String s){
        switch(s){
            case "plus": return Operation.Plus;
            case "div": return Operation.Divid;
            case "mul": return Operation.Times;
            case "max": return Operation.Max;
            case "min": return Operation.Min;
            case "comp": return Operation.Comp;
            default:return Operation.None;
        }
    }

    private String checkOP(Operation op){
        switch(op){
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


    public static void main(String[] args) {
        ComplexFunction e = new ComplexFunction("plus",new Monom(4,3),new Monom(4,2));
        function e1= e.copy();
        System.out.println(e1);
        System.out.println(e);
        e1=(ComplexFunction)e1;
        System.out.println(((ComplexFunction) e1).op);
        System.out.println(((ComplexFunction) e1).right);
        System.out.println(((ComplexFunction) e1).left);
        System.out.println("NOW E -------------");
        System.out.println(e.left());
        System.out.println(e.right());
        System.out.println(e.getOp());
        System.out.println( "be4 add");
        Monom m = new Monom(4,0);

        Polynom s = new Polynom("x^2+3x-5");
        e.div(s);
        Polynom s2= new Polynom("x^3+x^2+x");
        e.comp(s2);
        System.out.println(e.toString());
        String check= e.toString();
        function new1= e.initFromString(e.toString());
        System.out.println(new1.toString());
        System.out.println(e.f(2));
        e.plus(new Monom(4,2));
        System.out.println(e.toString());
        System.out.println(new1.toString());
        ComplexFunction r = new ComplexFunction("plus", new Monom(2,4),new Monom(4,1));

        String q = "mul(div(mul(8,8),4x^2),div(10,5))";
        function f = r.initFromString(q);
        System.out.println(f.toString());
        // r.pt.printInOrder();
        // double x = r.f(1);
        //  System.out.println(x);
        // System.out.println("t");

        //ComplexFunction s = (ComplexFunction)r.copy();
         //ComplexFunction s = new ComplexFunction(r.copy());


    }

}
