package com.ulatina.ldap;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: AplicacionHerramientaID
 *
 */
@Embeddable
public class AplicacionHerramientaID implements Serializable {


	private int idusuario;
	private int idaplicacion;
	private int idherramienta;
	private static final long serialVersionUID = 1L;

	public AplicacionHerramientaID() {
		super();
	}   
	public int getIdusuario() {
		return this.idusuario;
	}

	public void setIdusuario(int idusuario) {
		this.idusuario = idusuario;
	}   
	public int getIdaplicacion() {
		return this.idaplicacion;
	}

	public void setIdaplicacion(int idaplicacion) {
		this.idaplicacion = idaplicacion;
	}   
	public int getIdherramienta() {
		return this.idherramienta;
	}

	public void setIdherramienta(int idherramienta) {
		this.idherramienta = idherramienta;
	}
	
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AplicacionHerramientaID that = (AplicacionHerramientaID) o;
        return idusuario == that.idusuario &&
                idaplicacion == that.idaplicacion &&
                idherramienta == that.idherramienta;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idusuario, idaplicacion, idherramienta);
    }
   
}
