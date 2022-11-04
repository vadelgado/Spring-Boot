package tutorial.misionTIC.seguridad.Repositorios;

import tutorial.misionTIC.seguridad.Modelos.Usuario;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RepositorioUsuario extends MongoRepository<Usuario,String> {//Modelo,Tipo id
}
