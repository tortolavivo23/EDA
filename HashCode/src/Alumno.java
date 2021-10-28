import java.util.Objects;

public class Alumno {
    private String nombre;
    private Long nDni;
    private int edad;

    public Alumno(String nombre, Long nDni, int edad) {
        this.nombre = nombre;
        this.nDni = nDni;
        this.edad = edad;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Alumno alumno = (Alumno) o;
        return Objects.equals(nombre, alumno.nombre) && Objects.equals(nDni, alumno.nDni);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre, nDni);
    }
}
