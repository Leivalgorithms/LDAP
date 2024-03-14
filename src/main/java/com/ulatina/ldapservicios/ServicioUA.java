package com.ulatina.ldapservicios;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.ulatina.ldap.Aplicaciones;
import com.ulatina.ldap.Usuarios;

import java.util.List;
import java.util.Set;

public class ServicioUA {
	

	public static void agregarUsuarioAplicacion(EntityManager em, int usuarioId, int aplicacionId) {
        EntityTransaction transaction = em.getTransaction();

        try {
            transaction.begin();

           

            transaction.commit();
            System.out.println("Fila agregada a usuario_aplicacion con éxito.");
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
            System.out.println("Error al agregar fila a usuario_aplicacion.");
        }
    }
}
