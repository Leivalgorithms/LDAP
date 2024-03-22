package com.ulatina.ldap;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import com.ulatina.ldapservicios.Servicio;
import com.ulatina.ldapservicios.ServicioAH;
import com.ulatina.ldapservicios.ServicioAccion;
import com.ulatina.ldapservicios.ServicioAplicacion;
import com.ulatina.ldapservicios.ServicioHA;
import com.ulatina.ldapservicios.ServicioHerramienta;
import com.ulatina.ldapservicios.ServicioUsuario;
import java.util.Scanner;
import java.util.Set;

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
            AplicacionHerramienta aplicacionHerramienta = new AplicacionHerramienta();
            AplicacionHerramientaID id = new AplicacionHerramientaID();
            HerramientaAccion ha = new HerramientaAccion();
            ServicioUsuario servicioUsuario = new ServicioUsuario(em);
            ServicioUsuario servicioUsuario1 = new ServicioUsuario(em);
            ServicioAplicacion servicioAplicacion = new ServicioAplicacion(em);
            Herramientas herramienta = new Herramientas();
            ServicioHerramienta servicioHerramienta = new ServicioHerramienta(em);
            Acciones accion = new Acciones();
            ServicioAccion servicioAccion1 = new ServicioAccion(em);
            ServicioUsuario su = new ServicioUsuario(em);
            ServicioAH servicioah = new ServicioAH(em);
            ServicioHA servicioha = new ServicioHA(em);
            id.setIdusuario(1); // Establece el ID de usuario
            id.setIdaplicacion(2); // Establece el ID de aplicación
            id.setIdherramienta(1); // Establece el ID de herramienta
             // 
            //servicioah.insert(aplicacionHerramienta);

            //su.demeUsuario(null, null);
            
          
            
            usuario.setNOMBRE("Carlos");
            usuario.setDESCRIPCION("Pasante");
            usuario.setContrasena("6669");
            usuario.setESTADO(true);
            //servicioUsuario.insert(usuario);
            
            aplicacion.setNOMBRE("Contabilidad");
            aplicacion.setDESCRIPCION("App para gestionar la contabilidad");
            aplicacion.setESTADO(true);
            //servicioAplicacion.insert(aplicacion);
            //servicioAplicacion.delete(6);
            //servicioAplicacion.update(aplicacion);
            //aplicacion.setNOMBRE("Manufactura");
            //aplicacion.setDESCRIPCION("App para piso de produccion");
            //aplicacion.setESTADO(true);
            //servicioAplicacion.insert(aplicacion);
            herramienta.setNOMBRE("lectura");
            herramienta.setDESCRIPCION("herramienta de lectura");
            herramienta.setESTADO(true);
            //servicioHerramienta.insert(herramienta);
            accion.setNOMBRE("modificar");
            accion.setDESCRIPCION("modificar tiquete");
            accion.setESTADO(true);
            //servicioAccion1.insert(accion);
            
            Usuarios usuariosencontrados = servicioUsuario.findUsuariosByNombreFetchApplications("Mista X");
            Aplicaciones idapp1 =  servicioAplicacion.findAplicacionesByNombreFetchApplications("Contabilidad");
            //servicioUsuario.insertarUsuarioAplicacion(usuariosencontrados, idapp1);
            //aplicacionHerramienta.setIdCompuesto(1, 1, 1);
            //servicioah.insert(aplicacionHerramienta);
            ha.setIdaccion(2);
            ha.setIdusuario(1);
            ha.setIdherramienta(1);
            //servicioha.insert(ha);
            
            //Herramientas herramienta2 = servicioHerramienta.obtenerHerramientaPorId(2);
            //Acciones accns1 = servicioAccion1.obtenerAccionPorId(1);
            //servicioHerramienta.insertarHerramientaAccion(herramienta2, accns1);
            
            
            
            //Herramientas herramienta1 = servicioHerramienta.obtenerHerramientaPorId(2);
          	//Aplicaciones idapp666 =  servicioAplicacion.obtenerAplicacionPorId(2);
          	//servicioAplicacion.insertarAplicacionHerramienta(idapp666, herramienta1);
            
            
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
            
           //Usuarios usuariosencontrados = servicioUsuario.findUsuariosByNombreFetchApplications("Fernando Vargas");
           
            
            
            //Usuarios usuario1 = servicioUsuario.findUsuariosByNombreFetchApplications("Fernando Vargas");
           //Aplicaciones idapp1 =  servicioAplicacion.findAplicacionesByNombreFetchApplications("Contabilidad");
           //Aplicaciones idapp2 =  servicioAplicacion.obtenerAplicacionPorId(2);
           
           
           //System.out.println("Usuarios encontrados:" + usuario1);
           //System.out.println("Aplicaciones encontradas:" + idapp1);
            
           /*
            Usuarios usuarioEncontrado = servicioUsuario.obtenerUsuarioConAplicaciones("Mista X", "5678");
            if (usuarioEncontrado != null) {
                System.out.println("Nombre: " + (usuarioEncontrado.getNOMBRE() != null ? usuarioEncontrado.getNOMBRE() : "null"));
                System.out.println("Descripción: " + (usuarioEncontrado.getDESCRIPCION() != null ? usuarioEncontrado.getDESCRIPCION() : "null"));
                System.out.println("Estado: " + (usuarioEncontrado.getESTADO() != false ? usuarioEncontrado.getESTADO() : "null"));
                System.out.println("Aplicaciones asociadas:");
                
                for (Aplicaciones aplicacion1 : usuarioEncontrado.getAplicaciones()) {
                    System.out.println("- ID: " + (aplicacion1.getID() != null ? aplicacion1.getID() : "null"));
                    System.out.println("  Nombre: " + (aplicacion1.getNOMBRE() != null ? aplicacion1.getNOMBRE() : "null"));
                    System.out.println("  Descripción: " + (aplicacion1.getDESCRIPCION() != null ? aplicacion1.getDESCRIPCION() : "null"));
                    System.out.println("  Estado: " + (aplicacion1.getESTADO() != false ? aplicacion1.getESTADO() : "null"));

                    System.out.println("  Herramientas asociadas:");
                    if (aplicacion1.getAplicacionherramienta1() != null) {
                        for (AplicacionHerramienta aplicacionHerramientx : aplicacion1.getAplicacionherramienta1()) {
                            Herramientas herramientx = aplicacionHerramientx.getHerramienta();
                            System.out.println("  - ID: " + (herramientx.getID() != null ? herramientx.getID() : "null"));
                            System.out.println("    Nombre: " + (herramientx.getNOMBRE() != null ? herramientx.getNOMBRE() : "null"));
                            System.out.println("    Descripción: " + (herramientx.getDESCRIPCION() != null ? herramientx.getDESCRIPCION() : "null"));
                            System.out.println("    Estado: " + (herramientx.getESTADO() != false ? herramientx.getESTADO() : "null"));

                            System.out.println("    Acciones asociadas:");
                            if (herramientx.getHerramientaaccion() != null) {
                                for (HerramientaAccion herramientaAccion : herramientx.getHerramientaaccion()) {
                                    Acciones acxion = herramientaAccion.getAccion();
                                    System.out.println("      - ID: " + (acxion.getID() != null ? acxion.getID() : "null"));
                                    System.out.println("        Nombre: " + (acxion.getNOMBRE() != null ? acxion.getNOMBRE() : "null"));
                                    System.out.println("        Descripción: " + (acxion.getDESCRIPCION() != null ? acxion.getDESCRIPCION() : "null"));
                                }
                            } else {
                                System.out.println("    No hay acciones asociadas a esta herramienta.");
                            }
                        }
                    } else {
                        System.out.println("  No hay herramientas asociadas a esta aplicación.");
                    }
                }
            } else {
                System.out.println("El usuario: aún no tiene aplicaciones asignadas.");
            }
			*/


			
           
           
           
           /*
           Usuarios usuariosencontrados = servicioUsuario.perfilUsuario("Fernando Vargas", "1234");
           
           for (Usuarios usuarioencontrado : usuariosencontrados) {
               System.out.println("ID: " +  usuarioencontrado.getID());
               System.out.println("Nombre: " + usuarioencontrado.getNOMBRE());
               System.out.println("Descripción: " + usuarioencontrado.getDESCRIPCION());
               System.out.println("Estado: " + usuarioencontrado.getESTADO());
               System.out.println("------");
           }
           */
           
           
           
           
           
           //servicioUsuario.insertarUsuarioAplicacion(usuario1, idapp1);
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

	private static ServicioAH ServicioAH(EntityManager em2) {
		// TODO Auto-generated method stub
		return null;
	}


}
