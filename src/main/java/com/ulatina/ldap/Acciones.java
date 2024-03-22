package com.ulatina.ldap;

import java.io.Serializable;
import java.lang.Integer;
import java.lang.String;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;


/**
 * Entity implementation class for Entity: Acciones
 *
 */

@NamedQueries({
    @NamedQuery(name = "findAccionesByNombre", query = "SELECT a FROM Acciones a WHERE a.NOMBRE = :nombre"),
    @NamedQuery(name = "deleteAccion", query = "DELETE FROM Acciones a WHERE a.ID = :id")
})
@Entity
public class Acciones implements Serializable {

	   
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer ID;
	private String NOMBRE;
	private String DESCRIPCION;
	private boolean ESTADO;
	private Integer GROUPID;
	

	
	@OneToMany(mappedBy="accion", cascade = CascadeType.ALL)
	private Set<HerramientaAccion> herramientaaccion;

	
	private static final long serialVersionUID = 1L;

	public Acciones() {
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
	public Set<HerramientaAccion> getHerramientaaccion() {
		return herramientaaccion;
	}
	public void setHerramientaaccion(Set<HerramientaAccion> herramientaaccion) {
		this.herramientaaccion = herramientaaccion;
	}
	
   
}
