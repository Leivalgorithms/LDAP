package com.ulatina.ldapservicios;

import java.util.Optional;
import javax.persistence.TypedQuery;
import java.util.List;
import javax.persistence.EntityManager;

import com.ulatina.ldap.Acciones;
import com.ulatina.ldap.Aplicaciones;
import com.ulatina.ldap.Herramientas;
import com.ulatina.ldap.Usuarios;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class ServicioHerramienta extends Servicio implements CRUD<Herramientas> {

	
	public ServicioHerramienta(EntityManager em) {
        super(em);
    }
	
	
	@Override
	public void insert(Herramientas t) {
	    getEm().getTransaction().begin();
	    getEm().persist(t);
	    getEm().getTransaction().commit();
	}


	@Override
	public void update(Herramientas t) {
		// TODO Auto-generated method stub
		getEm().getTransaction().begin();
		getEm().merge(t);
		getEm().getTransaction().commit();
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		getEm().getTransaction().begin();
		getEm().createNamedQuery("deleteHerramienta")
		.setParameter("id", id)
        .executeUpdate();
		getEm().getTransaction().commit();
	}

	
	public List<Herramientas> findHerramientasByNombre(String nombre) {
	    CriteriaBuilder cb = getEm().getCriteriaBuilder();
	    CriteriaQuery<Herramientas> cq = cb.createQuery(Herramientas.class);
	    Root<Herramientas> root = cq.from(Herramientas.class);
	    String nombreNormalizado = nombre.trim().toLowerCase();
	    cq.where(cb.equal(cb.lower(root.get("NOMBRE")), nombreNormalizado));

	    TypedQuery<Herramientas> query = getEm().createQuery(cq);
	    return query.getResultList();
	}

	public Herramientas obtenerHerramientaPorId(int id) {
        EntityManager em = getEntityManager();

        try {
            return em.find(Herramientas.class, id);
        } finally {
            em.close();
        }
    }
	
	
	public void insertarHerramientaAccion(Herramientas herramientaId, Acciones accionId) {
        try {
            getEm().getTransaction().begin();

            // Crear la consulta SQL nativa para insertar en la tabla de asociación
            String sql = "INSERT INTO herramienta_accion (herramienta_id, accion_id) VALUES (:herramientaId, :accionId)";
            getEm().createNativeQuery(sql)
                    .setParameter("herramientaId", herramientaId)
                    .setParameter("accionId", accionId)
                    .executeUpdate();

            getEm().getTransaction().commit();
        } catch (Exception e) {
            getEm().getTransaction().rollback();
            e.printStackTrace();
        }
    }
}
