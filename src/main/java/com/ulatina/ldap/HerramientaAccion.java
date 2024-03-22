package com.ulatina.ldap;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: HerramientaAccion
 *
 */
@Entity

@IdClass(HerramientaAccionPK.class)
public class HerramientaAccion implements Serializable {

	   
	@Id
	private int idusuario;   
	@Id
	private int idherramienta;   
	@Id
	private int idaccion;
	private static final long serialVersionUID = 1L;
	
	
	@ManyToOne
	@JoinColumn(name = "idusuario", insertable = false, updatable = false)	
	private Usuarios usuario;
	
	@ManyToOne
	@JoinColumn(name = "idherramienta", insertable = false, updatable = false)	
	private Herramientas herramienta1;
	
	@ManyToOne
	@JoinColumn(name = "idaccion", insertable = false, updatable = false)	
	private Acciones accion;
	
	
	public HerramientaAccion() {
		super();
	}   
	public Usuarios getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuarios usuario) {
		this.usuario = usuario;
	}
	public Herramientas getHerramienta1() {
		return herramienta1;
	}
	public void setHerramienta1(Herramientas herramienta1) {
		this.herramienta1 = herramienta1;
	}
	public Acciones getAccion() {
		return accion;
	}
	public void setAccion(Acciones accion) {
		this.accion = accion;
	}
	public int getIdusuario() {
		return this.idusuario;
	}

	public void setIdusuario(int idusuario) {
		this.idusuario = idusuario;
	}   
	public int getIdherramienta() {
		return this.idherramienta;
	}

	public void setIdherramienta(int idherramienta) {
		this.idherramienta = idherramienta;
	}   
	public int getIdaccion() {
		return this.idaccion;
	}

	public void setIdaccion(int idaccion) {
		this.idaccion = idaccion;
	}
	
	

   
}
