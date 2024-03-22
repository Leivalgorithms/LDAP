
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import com.ulatina.ldapservicios.ServicioUsuario;
import com.ulatina.ldap.Usuarios;
import java.io.IOException;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.faces.context.FacesContext;
import java.io.IOException;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;

import com.sun.corba.se.impl.naming.pcosnaming.NameService;
import java.sql.Date;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManagerFactory;
import javax.servlet.http.HttpServletRequest;
import org.primefaces.PrimeFaces;

@ManagedBean(name = "loginController")
@SessionScoped
public class LoginController {

    public String nombre;
    private String contrasena;
    private Usuarios usuarioAutenticado;

    public void login() {
        EntityManagerFactory entityManagerFactory = JPAUtil.getEntityManagerFactory();

        System.out.println(usuarioAutenticado + "CREDENCIALES");
        //EntityManager em = Persistence.createEntityManagerFactory("LDAP").createEntityManager();
        ServicioUsuario servicioUsuario = new ServicioUsuario(entityManagerFactory.createEntityManager());

        usuarioAutenticado = servicioUsuario.demeUsuario(entityManagerFactory.createEntityManager(), nombre, contrasena);

        if (usuarioAutenticado != null) {
            // Login exitoso, redirige a la página principal o dashboard
            this.redireccionar("/faces/home.xhtml");
        } else {
            // Login fallido, muestra un mensaje de error
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuario o contraseña incorrectos", null));
        }
    }

    public void login2() {
        LoginController.redirectToHome();

    }

    public static void redirectToHome() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        try {
            facesContext.getExternalContext().redirect("home.xhtml");
        } catch (IOException e) {
            // Manejo de error en caso de que la redirección falle
            e.printStackTrace();
        }
    }

    public void redireccionar(String ruta) {
        HttpServletRequest request;
        try {
            request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
            FacesContext.getCurrentInstance().getExternalContext().redirect(request.getContextPath() + ruta);
        } catch (Exception e) {

        }
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public Usuarios getUsuarioAutenticado() {
        return usuarioAutenticado;
    }

    public void setUsuarioAutenticado(Usuarios usuarioAutenticado) {
        this.usuarioAutenticado = usuarioAutenticado;
    }

}
