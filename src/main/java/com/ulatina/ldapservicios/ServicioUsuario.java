package com.ulatina.ldapservicios;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;
import com.ulatina.ldap.Aplicaciones;
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


    public void insertarUsuarioAplicacion(Usuarios idUsuario, Aplicaciones idAplicacion) {
        try {
            getEm().getTransaction().begin();

            // Crear la consulta SQL nativa para insertar en la tabla de asociación
            String sql = "INSERT INTO usuario_aplicacion (usuario_id, aplicacion_id) VALUES (:idUsuario, :idAplicacion)";
            getEm().createNativeQuery(sql)
                    .setParameter("idUsuario", idUsuario)
                    .setParameter("idAplicacion", idAplicacion)
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
}
