package tutorial.misionTIC.seguridad.Modelos;
import lombok.Data;//Dependency
import org.springframework.data.annotation.Id;//El atributo de la clase de mongo
import org.springframework.data.mongodb.core.mapping.Document;//Que identifique que es un documento de mongo
@Data
@Document()//coleccion de mongo

public class Rol {
    @Id//identificador
    private String _id;
    private String nombre;
    private String descripcion;

    public Rol(String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public String get_id() {
        return _id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
