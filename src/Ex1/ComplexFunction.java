package Ex1;

public class ComplexFunction implements  complex_function {
    private Operation op=Operation.None;
    private function left,right=null;

    public ComplexFunction(String s,function left,function right){
        switch(s){
            case "plus":
                this.op=Operation.Plus;
                this.left=left;
                this.right=right;
                break;

            case "div":
                this.op=Operation.Divide;
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
            this.op=Operation.Divide;
            return;
        }
        ComplexFunction temp = new ComplexFunction();
        temp.op=Operation.Divide;
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
        return 0;
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
        for (int i = 0; i <s.length() ; i++) {
            if(s.charAt(i)== '('){
                help=s.substring(0,i);
                this.op=opFromString(help);
                int Index = getPsik(s.substring(i+1,s.length()-1));
                this.left=initRecursive(s.substring(i+1,Index));
                this.right=initRecursive(s.substring(Index+1,s.length()-1));
            }
        }
        function ans = new ComplexFunction(this);
        return ans;
    }
    private int getPsik(String s){
        int count = 0;
        int ans=0;
        for (int i = s.length()-1; i <=0 ; i--) {
            if(s.charAt(i) == ')') count++;
            if(s.charAt(i) == '(') count --;
            if(s.charAt(i)== ',' && count==0){
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
            case "div": return Operation.Divide;
            case "mul": return Operation.Times;
            case "max": return Operation.Max;
            case "min": return Operation.Min;
            default:return Operation.None;
        }
    }

    private String checkOP(Operation op){
        switch(op){
            case Plus:
                return "plus(";

            case Times:
                return "mul(";

            case Divide:
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


//    private static class Node{
//        private Operation op=Operation.None;
//        private function left,right;
//        public Polynom polinom = null;
//        public Node(){
//
//            this.right=null;
//            this.left=null;
//        }
//        public Node(function f){
//            this.left= f;
//            this.right=null;
//        }
//        public Node(Operation op, function f, function f1){
//            this.op = op;
//            this.left=f;
//            this.right= f1;
//        }
//        public Node(Operation op){
//            this.op =op;
//            this.left=this.right=null;
//        }
//        public Node(Node s){
//            this.op=s.op;
//            this.left=s.left;
//            this.right=s.right;
//        }
//       public Operation get_op(){
//            return this.op;
//        }
//        public function get_right(){
//            return this.right;
//        }
//        public function get_left(){
//            return this.left;
//        }
//
//    }

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
        e.plus(m);
        Polynom s = new Polynom("x^2+3x-5");
        e.div(s);
        System.out.println(e.toString());
        String check= e.toString();
        function new1= e.initFromString(check);
        System.out.println(new1.toString());

//        System.out.println(e.toString());
//        System.out.println("after add");
//        System.out.println(e.right());
//        System.out.println(e.getOp());
//        System.out.println(e.left().toString());
//        function f1= e.left;
//        ComplexFunction f2 =
//        System.out.println((ComplexFunction)f1.op);
//        System.out.println(f1.left);
//        System.out.println(f1.right);


    }

}
