package Ex1;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;
<<<<<<< HEAD
=======
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
>>>>>>> b553344f271c4f5b6e562a6fe83c34f402bf4e69


public class Functions_GUI implements functions {
    private LinkedList<function> fList = new LinkedList<>();
    static ComplexFunction getInList = new ComplexFunction();

    public Functions_GUI(){
    }
    @Override
    public void initFromFile(String file)  {
        try {
            File functionFile = new File(file);

            Scanner scan = new Scanner(functionFile);
            String s = "";
            while (scan.hasNext()) {
                s = scan.nextLine();
                if (!s.contains("(") || !s.contains(")")) {
                    this.fList.add(new Polynom(s));
                } else this.fList.add(getInList.initFromString(s));
            }
        }
        catch(Exception e){
            System.err.println("Path does not exist");

        }
    }


    @Override
    public void saveToFile(String file)  {
        try {
            FileWriter File = new FileWriter(file);
            Iterator<function> it = this.fList.iterator();
            StringBuilder SB = new StringBuilder();
            while (it.hasNext()) {
                SB.append(it.next() + "\n");
            }
            File.write(SB.toString());
            File.close();
        }
        catch(Exception e){
            System.err.println("Wrong path of file.");
        }
        }


    @Override
    public void drawFunctions(int width, int height, Range rx, Range ry, int resolution) {


    }

    @Override
    public void drawFunctions(String json_file) {
        Object obj = null;
        try{
<<<<<<< HEAD
            //JSONParser jp = new JSONParser();
            
=======
            JsonParser jp = new JsonParser();


>>>>>>> b553344f271c4f5b6e562a6fe83c34f402bf4e69

        }
        catch (Exception e){

        }
<<<<<<< HEAD



=======
>>>>>>> b553344f271c4f5b6e562a6fe83c34f402bf4e69

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
        return null;
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
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends function> collection) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> collection) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> collection) {
        return false;
    }

    @Override
    public void clear() {
        this.fList.clear();

    }

    public static void main(String[] args) throws IOException {
        Functions_GUI fg = new Functions_GUI();
<<<<<<< HEAD
        fg.initFromFile("/Users/yardn/Desktop/123.txt");
=======
        fg.initFromFile("/Users/yardn/Desktop/function_file.txt");
>>>>>>> b553344f271c4f5b6e562a6fe83c34f402bf4e69
        Iterator<function> it = fg.fList.iterator();
        while(it.hasNext()){
            System.out.println(it.next());
        }
        fg.saveToFile("/Users/yardn/Desktop/1234.txt");
    }
}
