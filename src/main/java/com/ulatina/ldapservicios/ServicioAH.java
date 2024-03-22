package com.ulatina.ldapservicios;

import javax.persistence.EntityManager;

import com.ulatina.ldap.AplicacionHerramienta;
import com.ulatina.ldap.AplicacionHerramientaID;
import com.ulatina.ldap.Aplicaciones;
import com.ulatina.ldap.Usuarios;

public class ServicioAH extends Servicio implements CRUD<AplicacionHerramienta> {
	
	public ServicioAH(EntityManager em) {
        super(em);
    }
	
	@Override
	public void insert(AplicacionHerramienta t) {
		// TODO Auto-generated method stub
		getEm().getTransaction().begin();
        getEm().persist(t);
        getEm().getTransaction().commit();
		
	}

	@Override
	public void update(AplicacionHerramienta t) {
		// TODO Auto-generated method stub
		getEm().getTransaction().begin();
        getEm().merge(t);
        getEm().getTransaction().commit();
		
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		Usuarios usuario = getEm().find(Usuarios.class, id);

        if (usuario != null) {
            getEm().getTransaction().begin();
            getEm().remove(usuario);
            getEm().getTransaction().commit();
        } else {
            System.out.println("No se encontró el usuario con el ID: " + id);
        }
		
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

}
