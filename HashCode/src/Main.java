import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Map<Alumno, Double> map = new HashMap<>();
        map.put(new Alumno("Sergio", 2312312L, 21), 0.0);
        map.put(new Alumno("Paco", 47097046L, 21), 10.0);
        map.put(new Alumno("Rodrigo", 32424933L, 21), Math.PI);
        map.put(new Alumno("Álvaro", 19348293472L, 21), 2*Math.E);
        System.out.println(map.get(new Alumno("Sergio", 2312312L, 25)));
    }
}
