package tutorial.misionTIC.seguridad.Repositorios;
import tutorial.misionTIC.seguridad.Modelos.PermisosRoles;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RepositorioPermisoRoles extends MongoRepository<PermisosRoles,String>{
}
