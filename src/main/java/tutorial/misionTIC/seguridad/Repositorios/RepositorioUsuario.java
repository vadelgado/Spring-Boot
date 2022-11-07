package tutorial.misionTIC.seguridad.Repositorios;

import org.springframework.data.mongodb.repository.Query;
import tutorial.misionTIC.seguridad.Modelos.Usuario;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RepositorioUsuario extends MongoRepository<Usuario,String> {//Modelo,Tipo id
    @Query("{'correo': ?0}")// ?0 : hace referencia al correo que se le esta enviando Busca el recurso correo
    public Usuario getUserByEmail(String correo);
}
