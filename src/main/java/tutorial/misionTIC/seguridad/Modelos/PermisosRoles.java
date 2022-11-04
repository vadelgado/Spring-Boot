package tutorial.misionTIC.seguridad.Modelos;
import lombok.Data;//Dependency
import org.springframework.data.annotation.Id;//El atributo de la clase de mongo
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;//Que identifique que es un documento de mongo

@Data
@Document()//coleccion de mongo
public class PermisosRoles {

    @Id//identificador
    private String _id;
    @DBRef
    private Rol rol;
    @DBRef
    private Permiso permiso;

    public PermisosRoles()
    {

    }

    public String get_id() {
        return _id;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public Permiso getPermiso() {
        return permiso;
    }

    public void setPermiso(Permiso permiso) {
        this.permiso = permiso;
    }
}
