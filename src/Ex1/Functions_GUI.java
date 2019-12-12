package Ex1;
import java.awt.*;
import java.io.*;
import java.text.ParseException;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * @authors Orian hindi and Shani hayik.
 */

public class Functions_GUI implements functions {
    private LinkedList<function> fList = new LinkedList<>();
    static ComplexFunction getInList = new ComplexFunction();
    public static Color[] Colors = {Color.blue,Color.cyan,Color.MAGENTA,Color.ORANGE,Color.red,Color.GREEN,Color.PINK};


    public Functions_GUI(){
    }
    @Override
    public void initFromFile(String file) throws IOException {

        File functionFile = new File(file);

        Scanner scan = new Scanner(functionFile);
        String s = "";
        while (scan.hasNext()) {
            s = scan.nextLine();
            if (!s.contains("(") || !s.contains(")")) {
                this.fList.add(new Polynom(s));
            }
            else this.fList.add(getInList.initFromString(s));
        }
    }




    @Override
    public void saveToFile(String file) throws IOException {

        FileWriter File = new FileWriter(file);
        Iterator<function> it = this.fList.iterator();
        StringBuilder SB = new StringBuilder();
        while (it.hasNext()) {
            SB.append(it.next() + "\n");
        }
        File.write(SB.toString());
        File.close();
    }

    public void drawFunctions(){
        Range rx = new Range(-15,15);
        Range ry= new Range(-15,15);
        this.drawFunctions(1000,600,rx,ry,200);

    }
    @Override
    public void drawFunctions(int width, int height, Range rx, Range ry, int resolution) {
        int n = resolution;
        StdDraw.setCanvasSize(width,height);
        StdDraw.setPenColor(Color.LIGHT_GRAY);
        StdDraw.setPenRadius(0.0005);
        StdDraw.setXscale(rx.get_min(),rx.get_max());
        StdDraw.setYscale(ry.get_min(),ry.get_max());
        for (int i = (int) rx.get_min(); i <rx.get_max() ; i++) {
            StdDraw.line(i,ry.get_max(),i,ry.get_min());
        }
        for (int i = (int)ry.get_min(); i <ry.get_max() ; i++) {
            StdDraw.line(rx.get_max(),i,rx.get_min(),i);
        }
        StdDraw.setPenColor(Color.BLACK);
        StdDraw.setPenRadius(0.01);
        StdDraw.setXscale(rx.get_min(),rx.get_max());
        StdDraw.setYscale(ry.get_min(),ry.get_max());
        StdDraw.line(rx.get_min(),0,rx.get_max(),0);
        StdDraw.line(0,ry.get_min(),0,ry.get_max());
        for (int i = (int)rx.get_min(); i <=rx.get_max() ; i++) {
            StdDraw.text(i,-1,"" + i );
        }
        for (int i =(int) ry.get_min() ; i <=ry.get_max() ; i++) {
            StdDraw.text(0.5,i, "" +i );
        }

        int size= this.fList.size();
        double[] x = new double[n+1];
        double[][] valueAtFx= new double[size][n+1];
        double step = (rx.get_max()-rx.get_min())/n;
        double x0=rx.get_min();
        for(int i = 0;i<=n;i++){
            x[i]=x0;
            for (int j = 0; j <size ; j++) {
                valueAtFx[j][i] = this.fList.get(j).f(x[i]);
            }
            x0+=step;
        }
        for (int i = 0; i < size; i++) {
            int c = i%Colors.length;
            StdDraw.setPenColor(Colors[c]);
            StdDraw.setPenRadius(0.005);
            System.out.println(i+ ") " + Colors[c] + "f(x)= " + this.fList.get(i).toString());
            for (int j = 0; j < n ; j++) {
                StdDraw.line(x[j],valueAtFx[i][j],x[j+1],valueAtFx[i][j+1]);
            }
        }
    }

    @Override
    public void drawFunctions(String json_file) {
        Gson gson = new Gson();
        try{
            FileReader reader = new FileReader(json_file);
            params parmaters= gson.fromJson(reader,params.class);
            Range rx = new Range(parmaters.Range_X[0],parmaters.Range_X[1]);
            Range ry = new Range(parmaters.Range_Y[0],parmaters.Range_Y[1]);
            drawFunctions(parmaters.Width,parmaters.Height,rx,ry,parmaters.Resolution);

        }
        catch(FileNotFoundException| IllegalArgumentException |com.google.gson.JsonSyntaxException| com.google.gson.JsonIOException e){
            if(e instanceof  IllegalArgumentException){
                System.out.println("Canvas problems,open a default canvas. ");
                this.drawFunctions();
            }
            else if(e instanceof com.google.gson.JsonSyntaxException){
                this.drawFunctions();
                System.out.println("print default canvas.");
                throw new com.google.gson.JsonSyntaxException("com.google.gson.JsonSyntaxException.");
            }
            else if(e instanceof  com.google.gson.JsonIOException){
                this.drawFunctions();
                System.out.println("print default canvas.");
                throw new com.google.gson.JsonIOException("com.google.gson.JsonIOException");
            }
            else{
                this.drawFunctions();
                System.out.println("File wasnt found, print default canvas.");
                e.printStackTrace();
            }

        }

    }

    @Override
    public int size() {
        return this.fList.size();
    }

    @Override
    public boolean isEmpty() {
        return this.fList.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return this.fList.contains(o);
    }

    @Override
    public Iterator<function> iterator() {
        Iterator<function> it = this.fList.iterator();
        return it;
    }

    @Override
    public Object[] toArray() {
        return this.fList.toArray();
    }

    @Override
    public <T> T[] toArray(T[] ts) {
        return this.fList.toArray(ts);
    }

    @Override
    public boolean add(function function) {
        return this.fList.add(function);
    }

    @Override
    public boolean remove(Object o) {
        return this.fList.remove(o);
    }

    @Override
    public boolean containsAll(Collection<?> collection) {
        return this.fList.containsAll(collection);
    }

    @Override
    public boolean addAll(Collection<? extends function> collection) {
        return this.fList.addAll(collection);
    }

    @Override
    public boolean removeAll(Collection<?> collection) {
        return this.fList.removeAll(collection);
    }

    @Override
    public boolean retainAll(Collection<?> collection) {
        return this.fList.retainAll(collection);
    }

    @Override
    public void clear() {
        this.fList.clear();

    }
    public function get(int i){
        return this.fList.get(i);
    }

}
