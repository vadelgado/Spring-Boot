package tutorial.misionTIC.seguridad.Repositorios;
import tutorial.misionTIC.seguridad.Modelos.Rol;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RepositorioRol extends MongoRepository<Rol,String>{
}
