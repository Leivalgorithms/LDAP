package com.ulatina.ldapservicios;

import javax.persistence.EntityManager;

import com.ulatina.ldap.HerramientaAccion;
import com.ulatina.ldap.Usuarios;

public class ServicioHA extends Servicio implements CRUD<HerramientaAccion> {
	
	public ServicioHA (EntityManager em) 
	{
		super(em);	
	}
	
	@Override
	public void insert(HerramientaAccion t) {
		// TODO Auto-generated method stub
		getEm().getTransaction().begin();
        getEm().persist(t);
        getEm().getTransaction().commit();
		
	}

	@Override
	public void update(HerramientaAccion t) {
		// TODO Auto-generated method stub
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

}
