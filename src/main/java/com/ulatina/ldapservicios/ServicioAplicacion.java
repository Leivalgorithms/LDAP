package com.ulatina.ldapservicios;

import java.util.Optional;
import javax.persistence.TypedQuery;
import java.util.List;
import javax.persistence.EntityManager;
import com.ulatina.ldap.Aplicaciones;
import com.ulatina.ldap.Herramientas;
import com.ulatina.ldap.Usuarios;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;


public class ServicioAplicacion extends Servicio implements CRUD<Aplicaciones>{

	public ServicioAplicacion(EntityManager em) {
        super(em);
    }
	
	@Override
	public void insert(Aplicaciones t) {
	    getEm().getTransaction().begin();
	    getEm().persist(t);
	    getEm().getTransaction().commit();
	}

	@Override
	public void update(Aplicaciones t) {
		// TODO Auto-generated method stub
		getEm().getTransaction().begin();
		getEm().merge(t);
		getEm().flush();
		getEm().clear();
		getEm().getTransaction().commit();
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		getEm().getTransaction().begin();
		getEm().createNamedQuery("deleteAplicacion")
		.setParameter("id", id)
        .executeUpdate();
		getEm().getTransaction().commit();
	}
		
	public Aplicaciones findAplicacionesByNombreFetchApplications(String nombre) {
        CriteriaBuilder cb = getEm().getCriteriaBuilder();
        CriteriaQuery<Aplicaciones> query = cb.createQuery(Aplicaciones.class);
        Root<Aplicaciones> root = query.from(Aplicaciones.class);

        root.fetch("aplicaciones", JoinType.LEFT);

        query.select(root).where(cb.equal(root.get("NOMBRE"), nombre));

        TypedQuery<Aplicaciones> typedQuery = getEm().createQuery(query);
        return typedQuery.getSingleResult();
    }
	
	public Aplicaciones obtenerAplicacionPorId(int id) {
        EntityManager em = getEntityManager();

        try {
            return em.find(Aplicaciones.class, id);
        } finally {
            em.close();
        }
    }
	public void insertarAplicacionHerramienta(Aplicaciones aplicacionId, Herramientas herramientaId) {
        try {
            getEm().getTransaction().begin();

            // Crear la consulta SQL nativa para insertar en la tabla de asociación
            String sql = "INSERT INTO aplicacion_herramienta (aplicacion_id, herramienta_id) VALUES (:aplicacionId, :herramientaId)";
            getEm().createNativeQuery(sql)
                    .setParameter("aplicacionId", aplicacionId)
                    .setParameter("herramientaId", herramientaId)
                    .executeUpdate();

            getEm().getTransaction().commit();
        } catch (Exception e) {
            getEm().getTransaction().rollback();
            e.printStackTrace();
        }
    }
	

}  
