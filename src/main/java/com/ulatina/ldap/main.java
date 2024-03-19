package com.ulatina.ldap;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import com.ulatina.ldapservicios.Servicio;
import com.ulatina.ldapservicios.ServicioAccion;
import com.ulatina.ldapservicios.ServicioAplicacion;
import com.ulatina.ldapservicios.ServicioHerramienta;
import com.ulatina.ldapservicios.ServicioUA;
import com.ulatina.ldapservicios.ServicioUsuario;
import java.util.Scanner;

public class main {

    private static EntityManager em = null;
    private static EntityManagerFactory entityManagerFactory = null;

    public static void main(String[] args) {
    		
    	Scanner entrada = new Scanner(System.in);
        try {
        	
            System.out.print("INICIANDO\n");

            
            Servicio.initEntityManagerFactory("LDAP");

            em = Servicio.getEntityManager();

            System.out.print("Tablas creadas exitosamente!!\n");
            
            
            //em.getTransaction().begin();
            
            Usuarios usuario = new Usuarios();
            Aplicaciones aplicacion = new Aplicaciones();
            ServicioUsuario servicioUsuario = new ServicioUsuario(em);
            ServicioUsuario servicioUsuario1 = new ServicioUsuario(em);
            ServicioAplicacion servicioAplicacion = new ServicioAplicacion(em);
            //ServicioUA servicioUA = new ServicioUA();
            Herramientas herramienta = new Herramientas();
            ServicioHerramienta servicioHerramienta = new ServicioHerramienta(em);
            Acciones accion = new Acciones();
            ServicioAccion servicioAccion1 = new ServicioAccion(em);
            
            
          
            usuario.setNOMBRE("Fernando Vargas");
            usuario.setDESCRIPCION("Profesor");
            usuario.setESTADO(true);
            //servicioUsuario.insert(usuario);
            
            aplicacion.setNOMBRE("Reportes");
            aplicacion.setDESCRIPCION("App para gestionar los reportes");
            aplicacion.setESTADO(true);
            //servicioAplicacion.insert(aplicacion);
            //servicioAplicacion.delete(6);
            //servicioAplicacion.update(aplicacion);
            //aplicacion.setNOMBRE("Manufactura");
            //aplicacion.setDESCRIPCION("App para piso de produccion");
            //aplicacion.setESTADO(true);
            //servicioAplicacion.insert(aplicacion);
            herramienta.setNOMBRE("caca");
            herramienta.setDESCRIPCION("Crea una caca");
            herramienta.setESTADO(true);
            //servicioHerramienta.insert(herramienta);
            accion.setNOMBRE("palo");
            accion.setDESCRIPCION("dcba");
            accion.setESTADO(true);
            //servicioAccion1.insert(accion);
            
            Herramientas herramienta2 = servicioHerramienta.obtenerHerramientaPorId(2);
            Acciones accns1 = servicioAccion1.obtenerAccionPorId(1);
            servicioHerramienta.insertarHerramientaAccion(herramienta2, accns1);
            
            
            
            //Herramientas herramienta1 = servicioHerramienta.obtenerHerramientaPorId(1);
          	//Aplicaciones idapp1 =  servicioAplicacion.obtenerAplicacionPorId(2);
          	//servicioAplicacion.insertarAplicacionHerramienta(idapp1, herramienta1);
            
            
            /*
            Aplicaciones aplicacion1 = servicioAplicacion.obtenerAplicacionPorId(1);
            Aplicaciones aplicacion3 = servicioAplicacion.obtenerAplicacionPorId(3);

            if (aplicacion1 != null && aplicacion3 != null) {
               
                asociarUsuarioConAplicacion(em, usuario, aplicacion1);
                asociarUsuarioConAplicacion(em, usuario, aplicacion3);
                System.out.println("Asociación exitosa.");
            } else {
                System.out.println("Al menos una aplicación no existe. Verifica los IDs de aplicaciones.");
            }
			*/
            
            //List<Usuarios> usuariosencontrados = servicioUsuario.findUsuariosByNombreFetchApplications("Satan Gomez");
            /*
            System.out.println("Usuarios encontrados:");
            
            for (Usuarios usuarioencontrado : usuariosencontrados) {
                System.out.println("ID: " +  usuarioencontrado.getID());
                System.out.println("Nombre: " + usuarioencontrado.getNOMBRE());
                System.out.println("Descripción: " + usuarioencontrado.getDESCRIPCION());
                System.out.println("Estado: " + usuarioencontrado.getESTADO());
                System.out.println("------");
            }
            */
            
            
            //Usuarios usuario1 = servicioUsuario.findUsuariosByNombreFetchApplications("Satan Gomez");
           //Aplicaciones idapp1 =  servicioAplicacion.obtenerAplicacionPorId(1);
           //Aplicaciones idapp2 =  servicioAplicacion.obtenerAplicacionPorId(2);
           //servicioUsuario.insertarUsuarioAplicacion(usuario1, idapp2);
            //int usuarioidx = usuario1.getID();
            /*if(usuario1 != null) 
            {
            	//System.out.println("usuario1: "+ usuario1);
            	//Aplicaciones app1 = new Aplicaciones();
            	//System.out.println(app1);
            	//em.getTransaction().begin();
            	
            	
            	//usuario1.addAplicacion(idapp1);
                //usuario1.addAplicacion(idapp2);
                //em.getTransaction().begin();
                //em.persist(usuario1); // Persiste el nuevo usuario en la base de datos
                //em.getTransaction().commit();
            }else 
            
            {
            
            	System.out.println("No se encontro la entidad por el nombre proporcionado");
            	
            }
        */
            
        } catch (Exception e) {
            e.printStackTrace();
        } 
       
    }


}
