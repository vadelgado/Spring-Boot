package tutorial.misionTIC.seguridad.Modelos;
import lombok.Data;//Dependency
import org.springframework.data.annotation.Id;//El atributo de la clase de mongo
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;//Que identifique que es un documento de mongo
@Data
@Document()//coleccion de mongo
public class Usuario {
    @Id//identificador
    private String _id;
    private String seudonimo;
    private String correo;
    private String contrasena;
    @DBRef
    private Rol rol;

    public Usuario(String seudonimo, String correo, String contrasena) {//Constructor no se agrega ID por que lo administra Mongo
        this.seudonimo = seudonimo;
        this.correo = correo;
        this.contrasena = contrasena;
    }
    //Consultores y Modificadores se elimina el set id por que es generado por la bd
    public String get_id() {
        return _id;
    }

    public String getSeudonimo() {
        return seudonimo;
    }

    public void setSeudonimo(String seudonimo) {
        this.seudonimo = seudonimo;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

 /*   public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    } */
}
