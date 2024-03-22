package com.ulatina.ldap;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: AplicacionHerramienta
 *
 */
@Entity

public class AplicacionHerramienta implements Serializable {

	@EmbeddedId
    private AplicacionHerramientaID id;
	
	public AplicacionHerramienta() {
		super();
        this.id = new AplicacionHerramientaID(); 
    }
	
	private static final long serialVersionUID = 1L;
	
	
	@ManyToOne
	@JoinColumn(name = "idaplicacion", insertable = false, updatable = false)	
	private Aplicaciones aplicacion;
	
	@ManyToOne
	@JoinColumn(name = "idherramienta",insertable = false, updatable = false)	
	private Herramientas herramienta;
	
	public AplicacionHerramientaID getId() {
		return id;
	}


	public void setId(AplicacionHerramientaID id) {
		this.id = id;
	}


	public Aplicaciones getAplicacion() {
		return aplicacion;
	}


	public void setAplicacion(Aplicaciones aplicacion) {
		this.aplicacion = aplicacion;
	}


	public Herramientas getHerramienta() {
		return herramienta;
	}


	public void setHerramienta(Herramientas herramienta) {
		this.herramienta = herramienta;
	}


	public Usuarios getUsuario() {
		return usuario;
	}


	public void setUsuario(Usuarios usuario) {
		this.usuario = usuario;
	}

	@ManyToOne
	@JoinColumn(name = "idusuario", insertable = false, updatable = false)	
	private Usuarios usuario;
	
	
	public void setIdCompuesto(int idUsuario, int idAplicacion, int idHerramienta) {
        if (this.id == null) {
            this.id = new AplicacionHerramientaID();
        }
        this.id.setIdusuario(idUsuario);
        this.id.setIdaplicacion(idAplicacion);
        this.id.setIdherramienta(idHerramienta);
    }
	 
   
}
