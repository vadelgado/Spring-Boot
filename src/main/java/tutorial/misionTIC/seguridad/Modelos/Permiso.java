package tutorial.misionTIC.seguridad.Modelos;
import lombok.Data;//Dependency
import org.springframework.data.annotation.Id;//El atributo de la clase de mongo
import org.springframework.data.mongodb.core.mapping.Document;//Que identifique que es un documento de mongo
@Data
@Document()//coleccion de mongo
public class Permiso {
    @Id//identificador
    private String _id;
    private String url;
    private String metodo;

    public Permiso(String url, String metodo) {
        this.url = url;
        this.metodo = metodo;
    }

    public String get_id() {
        return _id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMetodo() {
        return metodo;
    }

    public void setMetodo(String metodo) {
        this.metodo = metodo;
    }
}
