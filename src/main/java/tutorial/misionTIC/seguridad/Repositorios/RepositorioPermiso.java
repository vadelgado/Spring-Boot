package tutorial.misionTIC.seguridad.Repositorios;
import tutorial.misionTIC.seguridad.Modelos.Permiso;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RepositorioPermiso extends MongoRepository<Permiso,String>{
}
