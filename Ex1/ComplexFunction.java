package Ex1;

public class ComplexFunction implements  complex_function {
    private Node head;

    public ComplexFunction(String s,function f,function f1){
        switch(s){
            case "plus":
                this.head= new Node(Operation.Plus,f,f1);
                break;

            case "div":
                this.head= new Node(Operation.Divide,f,f1);
                break;

            case "mul":
                this.head= new Node(Operation.Times,f,f1);
                break;

            case "max":
                this.head= new Node(Operation.Max,f,f1);
                break;

            case "min":
                this.head = new Node(Operation.Min,f,f1);
                break;

            default:
                return;
        }
    }
    public ComplexFunction(ComplexFunction f){
       this.head= new Node(f.head);
    }

    public ComplexFunction(){
        this.head = null;
    }

    public ComplexFunction(function f){
      this.head=new Node(f);
    }


    @Override
    public void plus(function f1) {
        if(this.head.get_right()==null){
            this.head.right=f1;
            this.head.op=Operation.Plus;
            return;
        }
        Node temp = new Node(Operation.Plus);
        temp.left=this;
        temp.right=f1;
        this.head=temp;
        }

    @Override
    public void mul(function f1) {
        if(this.head.get_right()==null){
            this.head.right=f1;
            this.head.op=Operation.Times;
            return;
        }
        Node temp = new Node(Operation.Times);
        temp.left=this;
        temp.right=f1;
        this.head=temp;
    }

    @Override
    public void div(function f1) {
        if(this.head.get_right()==null){
            this.head.right=f1;
            this.head.op=Operation.Divide;
            return;
        }
        Node temp = new Node(Operation.Divide);
        temp.left=this;
        temp.right=f1;
        this.head=temp;
    }

    @Override
    public void max(function f1) {
      if(this.head.get_right()==null){
          this.head.right=f1;
          this.head.op=Operation.Max;
          return;
      }
      Node temp = new Node(Operation.Max);
      temp.left=this;
      temp.right=f1;
      this.head=temp;
    }


    @Override
    public void min(function f1) {
        if(this.head.get_right()==null){
            this.head.right=f1;
            this.head.op=Operation.Min;
            return;
        }
        Node temp = new Node(Operation.Min);
        temp.left= this;
        temp.right = f1;
        this.head=temp;

    }

    @Override
    public void comp(function f1) {
        if(this.head.get_right()==null){
            this.head.right=f1;
            this.head.op=Operation.Comp;
            return;
        }
        Node temp = new Node(Operation.Comp);
        temp.left=this;
        temp.right=f1;
        this.head=temp;
    }

    @Override
    public function left() {
        return this.head.get_left();
    }

    @Override
    public function right() {
        return this.head.get_right();
    }

    @Override
    public Operation getOp() {
        return this.head.get_op();
    }

    @Override
    public double f(double x) {
        return 0;
    }

    @Override
    public function initFromString(String s) {
        return null;
    }

    @Override
    public function copy() {
        function ans = new ComplexFunction(this);
        return ans;
    }
    public String toString(){
    return "";
    }

    private static class Node{
        private Operation op=Operation.None;
        private function left,right;

        public Node(function f){
            this.left= f;
            this.right=null;
        }
        public Node(Operation op, function f, function f1){
            this.op = op;
            this.left=f;
            this.right= f1;
        }
        public Node(Operation op){
            this.op =op;
            this.left=this.right=null;
        }
        public Node(Node s){
            this.op=s.op;
            this.left=s.left;
            this.right=s.right;
        }
       public Operation get_op(){
            return this.op;
        }
        public function get_right(){
            return this.right;
        }
        public function get_left(){
            return this.left;
        }

    }

    public static void main(String[] args) {
        ComplexFunction e = new ComplexFunction(Operation.Plus,new Monom(4,3),new Monom(4,2));
        function e1= e.copy();
        System.out.println(e1);
        System.out.println(e);
        e1=(ComplexFunction)e1;
        System.out.println(((ComplexFunction) e1).head.op);
        System.out.println(((ComplexFunction) e1).head.right);
        System.out.println(((ComplexFunction) e1).head.left);
        System.out.println("NOW E -------------");
        System.out.println(e.left());
        System.out.println(e.right());
        System.out.println(e.getOp());
        System.out.println( "be4 add");
        Monom m = new Monom(4,0);
        e.plus(m);
        System.out.println(e.right());
        System.out.println(e.getOp());
        if(e.left() instanceof ComplexFunction){
            System.out.println(((ComplexFunction) e.left()).getOp());
            System.out.println(((ComplexFunction) e.left()).right());
            System.out.println(((ComplexFunction) e.left()).left().toString());
        }


    }

}
