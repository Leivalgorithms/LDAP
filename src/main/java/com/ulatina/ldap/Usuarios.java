package com.ulatina.ldap;

import java.io.Serializable;
import java.lang.Integer;
import java.lang.String;
import java.util.ArrayList;

import java.util.List;


import javax.persistence.*;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;


/**
 * Entity implementation class for Entity: Usuarios
 *
 */
@NamedQueries({
	@NamedQuery(name = "findUsuarioByNombre", query = "SELECT u FROM Usuarios u WHERE u.NOMBRE = :nombre"),
    @NamedQuery(name = "deleteUsuario", query = "DELETE FROM Usuarios a WHERE a.ID = :id"),
    @NamedQuery(name = "Usuario.findByNombreAndContrasena", query = "SELECT u FROM Usuarios u WHERE u.NOMBRE = :nombre AND u.CONTRASENA = :contrasena")
})

@NamedNativeQuery(
	    name = "obtenerUsuarioConAplicaciones",
	    query = "SELECT " +
	                "u.*, " +
	                "ap.*, " +
	                "h.*, " +
	                "ac.* " +
	            "FROM " +
	                "usuarios u " +
	            "LEFT JOIN " +
	                "usuario_aplicacion ua ON u.ID = ua.usuario_id " +
	            "LEFT JOIN " +
	                "aplicaciones ap ON ua.aplicacion_id = ap.ID " +
	            "LEFT JOIN " +
	                "aplicacionherramienta ah ON ap.ID = ah.idaplicacion AND ah.idusuario = u.ID " +
	            "LEFT JOIN " +
	                "herramientas h ON ah.idherramienta = h.ID " +
	            "LEFT JOIN " +
	                "herramientaaccion ha ON h.ID = ha.idherramienta AND ha.idusuario = u.ID " +
	            "LEFT JOIN " +
	                "acciones ac ON ha.idaccion = ac.ID " +
	            "WHERE " +
	                "u.NOMBRE = :nombreUsuario AND u.CONTRASENA = :contraseñaUsuario",
	    resultClass = Usuarios.class
	)

@Entity
public class Usuarios implements Serializable {

	   
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer ID;
	private String NOMBRE;
	private String DESCRIPCION;
	private boolean ESTADO;
	private String CONTRASENA;
	
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(
	    name = "usuario_aplicacion", 
	    joinColumns = @JoinColumn(name = "usuario_id"), 
	    inverseJoinColumns = @JoinColumn(name = "aplicacion_id")
	)
	@Fetch(FetchMode.JOIN)
	
	private List<Aplicaciones> aplicaciones = new ArrayList<>();
	
	
    private static final long serialVersionUID = 1L;
	public Usuarios() {
		super();
	}   
	public Integer getID() {
		return this.ID;
	}

	public void setID(Integer ID) {
		this.ID = ID;
	}   
	public String getNOMBRE() {
		return this.NOMBRE;
	}

	public void setNOMBRE(String NOMBRE) {
		this.NOMBRE = NOMBRE;
	}   
	public String getDESCRIPCION() {
		return this.DESCRIPCION;
	}

	public void setDESCRIPCION(String DESCRIPCION) {
		this.DESCRIPCION = DESCRIPCION;
	}   
	public boolean getESTADO() {
		return this.ESTADO;
	}

	public void setESTADO(boolean ESTADO) {
		this.ESTADO = ESTADO;
	}
	
	public List<Aplicaciones> getAplicaciones()
	{
		return aplicaciones;
	}
	
	public void setAplicaciones(List<Aplicaciones> aplicaciones) 
	{
		this.aplicaciones = aplicaciones;	
	}
	
	public void addAplicacion(Aplicaciones aplicacion)
	{
		if(!this.aplicaciones.contains(aplicacion)) 
		{
			this.aplicaciones.add(aplicacion);
			aplicacion.getUsuarios().add(this);
			
		}
	}
	public String getContrasena() {
		return CONTRASENA;
	}
	public void setContrasena(String CONTRASENA) {
		this.CONTRASENA = CONTRASENA;
	}
	
	@Override
	public String toString() {
	    return "Usuarios [ID=" + ID + ", NOMBRE=" + NOMBRE + ", DESCRIPCION=" + DESCRIPCION + ", ESTADO=" + ESTADO + "]";
	}
   
}
