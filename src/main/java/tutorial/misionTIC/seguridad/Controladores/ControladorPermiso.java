package tutorial.misionTIC.seguridad.Controladores;
import tutorial.misionTIC.seguridad.Modelos.Permiso;
import tutorial.misionTIC.seguridad.Repositorios.RepositorioPermiso;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin //Cuando se quiere hacer una peticion al servicio Permiso poder ser consumida la appi
@RestController // Tipo de ocntrolador
@RequestMapping("/permisos")

public class ControladorPermiso {
    @Autowired
    private RepositorioPermiso miRepositorioPermiso;

    @GetMapping("")
    public List<Permiso> index()
    {
        return this.miRepositorioPermiso.findAll();
    }
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Permiso create(@RequestBody Permiso infoPermiso)
    {
        return this.miRepositorioPermiso.save(infoPermiso);
    }

    @GetMapping("{id}")
    public Permiso show(@PathVariable String id)
    {
        Permiso permisoActual = this.miRepositorioPermiso
                .findById(id)
                .orElse(null);
        return permisoActual;
    }

    @PutMapping("{id}")
    public Permiso update(@PathVariable String id,@RequestBody Permiso infoPermiso)
    {
        Permiso permisoActual = this.miRepositorioPermiso
                .findById(id)
                .orElse(null);
        if(permisoActual != null)
        {
            permisoActual.setMetodo(infoPermiso.getMetodo());
            permisoActual.setUrl(infoPermiso.getUrl());
            return this.miRepositorioPermiso.save(permisoActual);
        }else {
            return null;
        }
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("{id}")
    public void delete(@PathVariable String id)
    {
        Permiso permisoAEliminar = this.miRepositorioPermiso
                .findById(id)
                .orElse(null);
        if(permisoAEliminar != null)
        {
            this.miRepositorioPermiso.delete(permisoAEliminar);
        }
    }

}
