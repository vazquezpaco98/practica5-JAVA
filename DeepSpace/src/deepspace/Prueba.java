package deepspace;
import java.util.ArrayList;


public class Prueba{
    private static void prueba1(){}

    private ArrayList<Integer> a;

    public Prueba(int d, int b){
            this.a = new ArrayList();
    }

    public ArrayList<Integer> get_a(){
        return a;
    }
    
}

class Examen2Hijo {
    public static void main(String[] args) {
        Prueba p = new Prueba(2, 3);


        p.get_a().add(20);

        System.out.println(p.get_a().get(0));
    }
}

