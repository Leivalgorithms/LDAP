package com.ulatina.ldap;

import java.io.Serializable;

/**
 * ID class for entity: HerramientaAccion
 *
 */ 
public class HerramientaAccionPK  implements Serializable {   
   
	         
	private int idusuario;         
	private int idherramienta;         
	private int idaccion;
	private static final long serialVersionUID = 1L;

	public HerramientaAccionPK() {}

	

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
	
   
	/*
	 * @see java.lang.Object#equals(Object)
	 */	
	public boolean equals(Object o) {
		if (o == this) {
			return true;
		}
		if (!(o instanceof HerramientaAccionPK)) {
			return false;
		}
		HerramientaAccionPK other = (HerramientaAccionPK) o;
		return true
			&& getIdusuario() == other.getIdusuario()
			&& getIdherramienta() == other.getIdherramienta()
			&& getIdaccion() == other.getIdaccion();
	}
	
	/*	 
	 * @see java.lang.Object#hashCode()
	 */	
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + getIdusuario();
		result = prime * result + getIdherramienta();
		result = prime * result + getIdaccion();
		return result;
	}
   
   
}
