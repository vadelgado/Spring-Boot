package tutorial.misionTIC.seguridad.Controladores;
import tutorial.misionTIC.seguridad.Modelos.Usuario;
import tutorial.misionTIC.seguridad.Modelos.Rol;
import tutorial.misionTIC.seguridad.Repositorios.RepositorioUsuario;
import tutorial.misionTIC.seguridad.Repositorios.RepositorioRol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
@CrossOrigin
@RestController
@RequestMapping("/usuarios")
public class ControladorUsuario {
    @Autowired
    private RepositorioUsuario miRepositorioUsuario;
    @Autowired
    private RepositorioRol miRepositorioRol;

    @PutMapping("{id}/rol/{id_rol}")
    public Usuario asignarRolAUsuario(@PathVariable String id,@PathVariable String id_rol)
    {
        Usuario usuarioActual = this.miRepositorioUsuario
                .findById(id)
                .orElse(null);

        Rol rolActual = this.miRepositorioRol
                .findById(id_rol)
                .orElse(null);
        if(usuarioActual != null && rolActual != null)
        {
            usuarioActual.setRol(rolActual);
            return this.miRepositorioUsuario.save(usuarioActual);
        }else{
            return null;
        }
    }

    @GetMapping("")
    public List<Usuario> index()
    {
        return this.miRepositorioUsuario.findAll();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Usuario create(@RequestBody Usuario infoUsuario)
    {
        infoUsuario.setContrasena(convertirSHA256(infoUsuario.getContrasena()));
        return this.miRepositorioUsuario.save(infoUsuario);
    }
    //Implementación método de validación usuarios
    @PostMapping("/validate")
    public Usuario validate (@RequestBody Usuario infoUsuario, final HttpServletResponse response) throws IOException //throws IOException: Listar los tipos (S)
    {
        Usuario usuarioActual= this.miRepositorioUsuario
                .getUserByEmail(infoUsuario.getCorreo());
        String contrasena = convertirSHA256(infoUsuario.getContrasena());
        if (usuarioActual != null && usuarioActual.getContrasena().equals(contrasena)){
            usuarioActual.setContrasena("");
            return usuarioActual;
        }else{
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
            return null;
        }
    }

    @GetMapping("{id}")
    public Usuario show(@PathVariable String id)
    {
        Usuario usuarioActual = this.miRepositorioUsuario
                .findById(id)
                .orElse(null);
        return usuarioActual;
    }
    @PutMapping("{id}")
    public Usuario update(@PathVariable String id,@RequestBody Usuario infoUsuario){
        Usuario usuarioActual = this.miRepositorioUsuario
                .findById(id)
                .orElse(null);
        if(usuarioActual != null) {
            usuarioActual.setSeudonimo(infoUsuario.getSeudonimo());
            usuarioActual.setCorreo(infoUsuario.getCorreo());
            usuarioActual.setContrasena(convertirSHA256(infoUsuario.getContrasena()));
            return this.miRepositorioUsuario.save(usuarioActual);
        }else{
            return null;
        }
    }
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("{id}")
    public void delete(@PathVariable String id)
    {
        Usuario usuarioAEliminar=this.miRepositorioUsuario
                .findById(id)
                .orElse(null);
        if(usuarioAEliminar != null)
        {
            this.miRepositorioUsuario.delete(usuarioAEliminar);
        }
    }

    public String convertirSHA256(String password)
    {
        MessageDigest md = null;
        try{
            md = MessageDigest.getInstance("SHA-256");
        }catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
            return null;
        }
        byte[] hash = md.digest(password.getBytes());
        StringBuffer sb = new StringBuffer();
        for(byte b : hash)
        {
            sb.append(String.format("%02x",b));
        }
        return sb.toString();
    }
}