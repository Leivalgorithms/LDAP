package com.ulatina.ldapservicios;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;
import javax.persistence.Query;
import com.ulatina.ldap.Aplicaciones;
import com.ulatina.ldap.Herramientas;
import com.ulatina.ldap.Usuarios;

public class ServicioUsuario extends Servicio implements CRUD<Usuarios> {

    public ServicioUsuario(EntityManager em) {
        super(em);
    }

    public Usuarios demeUsuario(EntityManager em,String nombre, String contrasena) {
        Usuarios usuario = null;
        try {
            // Inicia la transacción
            getEm().getTransaction().begin();

            TypedQuery<Usuarios> query = getEm().createNamedQuery("Usuario.findByNombreAndContrasena", Usuarios.class);
            query.setParameter("nombre", nombre);
            query.setParameter("contrasena", contrasena);

            // Intenta obtener un solo resultado
            usuario = query.getSingleResult();

            // Cierra la transacción
            getEm().getTransaction().commit();
        } catch (NoResultException e) {
            // No se encontró el usuario
            usuario = null;
            getEm().getTransaction().rollback();
        } catch (Exception e) {
            // Manejo de cualquier otra excepción
            e.printStackTrace();
            getEm().getTransaction().rollback();
        }
        return usuario;
    }


    @Override
    public void insert(Usuarios t) {
        getEm().getTransaction().begin();
        getEm().persist(t);
        getEm().getTransaction().commit();
    }

    @Override
    public void update(Usuarios t) {
        getEm().getTransaction().begin();
        getEm().merge(t);
        getEm().getTransaction().commit();
    }

    @Override
    public void delete(Integer id) {
        Usuarios usuario = getEm().find(Usuarios.class, id);

        if (usuario != null) {
            getEm().getTransaction().begin();
            getEm().remove(usuario);
            getEm().getTransaction().commit();
        } else {
            System.out.println("No se encontró el usuario con el ID: " + id);
        }
    }

    public Usuarios findUsuariosByNombreFetchApplications(String nombre) {
        CriteriaBuilder cb = getEm().getCriteriaBuilder();
        CriteriaQuery<Usuarios> query = cb.createQuery(Usuarios.class);
        Root<Usuarios> root = query.from(Usuarios.class);

        root.fetch("aplicaciones", JoinType.LEFT);

        query.select(root).where(cb.equal(root.get("NOMBRE"), nombre));

        TypedQuery<Usuarios> typedQuery = getEm().createQuery(query);
        return typedQuery.getSingleResult();
    }
    
    
    public void insertarUsuarioAplicacion(Usuarios usuarioid, Aplicaciones aplicacionid) {
        try {
            getEm().getTransaction().begin();

            // Crear la consulta SQL nativa para insertar en la tabla de asociación
            String sql = "INSERT INTO usuario_aplicacion (usuario_id, aplicacion_id) VALUES (:usuarioid, :aplicacionid)";
            getEm().createNativeQuery(sql)
                    .setParameter("usuarioid", usuarioid)
                    .setParameter("aplicacionid", aplicacionid)
                    .executeUpdate();

            getEm().getTransaction().commit();
        } catch (Exception e) {
            getEm().getTransaction().rollback();
            e.printStackTrace();
        }
    }
    

    
    public Usuarios login(String nombre, String contrasena) {
        
        Usuarios usuario = null;
        getEm().getTransaction().begin();
        
        try {
            //em = getEntityManager(); 
            TypedQuery<Usuarios> query = getEm().createNamedQuery("Usuario.findByNombreAndContrasena", Usuarios.class);
            query.setParameter("nombre", nombre);
            query.setParameter("contrasena", contrasena);
            usuario = query.getSingleResult();

        } catch (NoResultException e) {
            usuario = null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return usuario;
    }
    
    public List<Object[]> perfilUsuario(String nombreUsuario, String contraseñaUsuario) {
    	String sql = "SELECT aplicaciones.NOMBRE, usuarios.NOMBRE " +
                "FROM usuarios JOIN usuario_aplicacion ON usuarios.ID = usuario_aplicacion.usuario_id " +
                "JOIN aplicaciones ON aplicaciones.ID = usuario_aplicacion.aplicacion_id " +
                "WHERE usuarios.NOMBRE = ?1 AND usuarios.CONTRASENA = ?2";

       
        
        Query query = getEm().createNativeQuery(sql);
        query.setParameter(1, nombreUsuario);
        query.setParameter(2, contraseñaUsuario);
        
        List<Object[]> resultados = query.getResultList();     
        
     // Imprimir las aplicaciones asociadas al usuario
        for (Object[] row : resultados) {
            
            String nombreUsuario1 = (String) row[1];
            System.out.println("Nombre de usuario: " + nombreUsuario1);
            String nombreAplicacion = (String) row[0];
            System.out.println("Nombre de aplicacion: " + nombreAplicacion);
            
            System.out.println("---------------------------------------");
        }
        
        return resultados;
    }
    
    public Usuarios obtenerUsuarioConAplicaciones(String nombreUsuario, String contraseñaUsuario) {
        Query query = getEm().createNamedQuery("obtenerUsuarioConAplicaciones");
        query.setParameter("nombreUsuario", nombreUsuario);
        query.setParameter("contraseñaUsuario", contraseñaUsuario);

        try {
            return (Usuarios) query.getSingleResult();
        } catch (NoResultException e) {
            // No se encontró ningún usuario con las credenciales proporcionadas
            return null;
        }
    }



}
