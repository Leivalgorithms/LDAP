package com.ulatina.ldapservicios;

import java.util.Optional;
import javax.persistence.EntityManager;
import com.ulatina.ldap.Acciones;
import com.ulatina.ldap.Aplicaciones;

import javax.persistence.TypedQuery;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;


public class ServicioAccion extends Servicio implements CRUD<Acciones> {

	public ServicioAccion(EntityManager em) {
        super(em);
    }
	
	@Override
	public void insert(Acciones t) {
	    getEm().getTransaction().begin();
	    getEm().persist(t);
	    getEm().getTransaction().commit();
	}

	@Override
	public void update(Acciones t) {
		// TODO Auto-generated method stub
		getEm().getTransaction().begin();
		getEm().merge(t);
		getEm().getTransaction().commit();
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		getEm().getTransaction().begin();
		getEm().createNamedQuery("deleteAccion")
		.setParameter("id", id)
        .executeUpdate();
		getEm().getTransaction().commit();
	}

	
	public List<Acciones> findAccionesByNombre(String nombre) {
	    CriteriaBuilder cb = getEm().getCriteriaBuilder();
	    CriteriaQuery<Acciones> cq = cb.createQuery(Acciones.class);
	    Root<Acciones> root = cq.from(Acciones.class);
	    String nombreNormalizado = nombre.trim().toLowerCase();
	    cq.where(cb.equal(cb.lower(root.get("NOMBRE")), nombreNormalizado));

	    TypedQuery<Acciones> query = getEm().createQuery(cq);
	    return query.getResultList();
	}
	public Acciones obtenerAccionPorId(int id) {
        EntityManager em = getEntityManager();

        try {
            return em.find(Acciones.class, id);
        } finally {
            em.close();
        }
    }
	
}
			
