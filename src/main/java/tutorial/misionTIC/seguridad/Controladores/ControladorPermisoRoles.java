package tutorial.misionTIC.seguridad.Controladores;
import tutorial.misionTIC.seguridad.Modelos.Permiso;
import tutorial.misionTIC.seguridad.Modelos.Rol;
import tutorial.misionTIC.seguridad.Modelos.PermisosRoles;

import tutorial.misionTIC.seguridad.Repositorios.RepositorioRol;
import tutorial.misionTIC.seguridad.Repositorios.RepositorioPermiso;
import tutorial.misionTIC.seguridad.Repositorios.RepositorioPermisoRoles;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@CrossOrigin //Cuando se quiere hacer una peticion al servicio Permiso poder ser consumida la appi
@RestController // Tipo de ocntrolador
@RequestMapping("/permisos-roles")
public class ControladorPermisoRoles {
    @Autowired
    private RepositorioRol miRepositorioRol;
    @Autowired
    private RepositorioPermiso miRepositorioPermiso;
    @Autowired
    private RepositorioPermisoRoles miRepositorioPermisoRoles;

    @GetMapping("")
    public List<PermisosRoles> index(){
        return this.miRepositorioPermisoRoles.findAll();
    }
    //Implementación método de validación Permiso
    @GetMapping("validar-permiso/rol/{id_rol}")
    public PermisosRoles getPermiso(@PathVariable String id_rol,
                                    @RequestBody Permiso infoPermiso)
    {
        Permiso elPermiso=this.miRepositorioPermiso
                .getPermiso(infoPermiso.getUrl(),
                       infoPermiso.getMetodo());
        Rol elRol=this.miRepositorioRol
                .findById(id_rol)
                .orElse(null);
        if (elPermiso!=null && elRol!=null)
        {
            return this.miRepositorioPermisoRoles.getPermisoRoles(elRol.get_id(),
                    elPermiso.get_id());
        }else{
            return null;
        }
    }


    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("rol/{id_rol}/permiso/{id_permiso}")
    public PermisosRoles create(@PathVariable String id_rol, @PathVariable String id_permiso)
    {
        PermisosRoles nuevo = new PermisosRoles();
        Rol elRol = this.miRepositorioRol
                .findById(id_rol)
                .orElse(null);
        Permiso elPermiso = this.miRepositorioPermiso
                .findById(id_permiso)
                .orElse(null);
        if(elRol!=null && elPermiso !=null)
        {
            nuevo.setPermiso(elPermiso);
            nuevo.setRol(elRol);
            return this.miRepositorioPermisoRoles.save(nuevo);
        }else{
            return null;
        }
    }

    @GetMapping("{id}")
    public PermisosRoles show(@PathVariable String id){
        PermisosRoles permisosRolesActual = this.miRepositorioPermisoRoles
                .findById(id)
                .orElse(null);
        return permisosRolesActual;
    }
    @PutMapping("{id}/rol/{id_rol}/permiso/{id_permiso}")
    public PermisosRoles update(@PathVariable String id, @PathVariable String id_rol, @PathVariable String id_permiso){
        PermisosRoles permisosRolesActual = this.miRepositorioPermisoRoles
                .findById(id)
                .orElse(null);
        Rol elRol = this.miRepositorioRol
                .findById(id)
                .orElse(null);
        Permiso elPermiso = this.miRepositorioPermiso
                .findById(id)
                .orElse(null);
        if(permisosRolesActual!=null && elRol!= null && elPermiso !=null){
            permisosRolesActual.setRol(elRol);
            permisosRolesActual.setPermiso(elPermiso);
            return this.miRepositorioPermisoRoles.save(permisosRolesActual);
        }else{
            return null;
        }
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping({"id"})
    public void delete(@PathVariable String id){
        PermisosRoles permisoRolesActual =this.miRepositorioPermisoRoles
                .findById(id)
                .orElse(null);
        if(permisoRolesActual!=null){
            this.miRepositorioPermisoRoles.delete(permisoRolesActual);
        }
    }
}
