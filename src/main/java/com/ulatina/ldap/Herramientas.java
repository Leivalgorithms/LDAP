package com.ulatina.ldap;

import java.io.Serializable;
import java.lang.Integer;
import java.lang.String;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.*;


/**
 * Entity implementation class for Entity: Herramientas
 *
 */
@NamedQueries({
	@NamedQuery(name = "findHerramientaByNombre", query = "SELECT h FROM Herramientas h WHERE h.NOMBRE = :nombre"),
	@NamedQuery(name = "deleteHerramienta", query = "DELETE FROM Herramientas h WHERE h.ID = :id")
})
@Entity
public class Herramientas implements Serializable {

	   
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer ID;
	private String NOMBRE;
	private String DESCRIPCION;
	private boolean ESTADO;
	private Integer GROUPID;
	
	
	@OneToMany(mappedBy="herramienta", cascade = CascadeType.ALL)
	private Set<AplicacionHerramienta> aplicacionherramienta;
	
	
	public Set<AplicacionHerramienta> getAplicacionherramienta() {
		return aplicacionherramienta;
	}
	public void setAplicacionherramienta(Set<AplicacionHerramienta> aplicacionherramienta) {
		this.aplicacionherramienta = aplicacionherramienta;
	}
	
	
	@OneToMany(mappedBy="herramienta1", cascade = CascadeType.ALL)
	private Set<HerramientaAccion> herramientaaccion;
	

	public Set<HerramientaAccion> getHerramientaaccion() {
		return herramientaaccion;
	}
	public void setHerramientaaccion(Set<HerramientaAccion> herramientaaccion) {
		this.herramientaaccion = herramientaaccion;
	}
	


	private static final long serialVersionUID = 1L;

	public Herramientas() {
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
	public Integer getGROUPID() {
		return GROUPID;
	}
	public void setGROUPID(Integer gROUPID) {
		GROUPID = gROUPID;
	}

}
