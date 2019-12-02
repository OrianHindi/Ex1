package Ex1;

public class ComplexFunction implements  complex_function {
    private Node head;

    public ComplexFunction(){
        this.head = null;
    }
    public ComplexFunction(Operation op, function f, function f1){
      this.head = new Node(op,f,f1);
    }
    public ComplexFunction(function f){
      this.head=new Node(f);
    }


    @Override
    public void plus(function f1) {



        }





    @Override
    public void mul(function f1) {

    }

    @Override
    public void div(function f1) {

    }

    @Override
    public void max(function f1) {

    }

    @Override
    public void min(function f1) {

    }

    @Override
    public void comp(function f1) {

    }

    @Override
    public function left() {
        return null;
    }

    @Override
    public function right() {
        return null;
    }

    @Override
    public Operation getOp() {
        return null;
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
        return null;
    }

    private static class Node{
        private function f;
        private Operation op;
        private Node left,right;

        public Node(function f){
            this.f=f;
            this.op = Operation.None;
            this.left=this.right=null;
        }
        public Node(Operation op, function f, function f1){
            this.op = op;
            this.f=null;
            this.left.f=f;
            this.right.f= f1;
        }
        public Node(Node s){
            this.f=s.f;
            this.op=s.op;
            this.left=s.left;
            this.right=s.right;
        }

    }


}
