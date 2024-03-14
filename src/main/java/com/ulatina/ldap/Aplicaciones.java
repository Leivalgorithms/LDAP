package com.ulatina.ldap;

import java.io.Serializable;
import java.lang.Integer;
import java.lang.String;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;


/**
 * Entity implementation class for Entity: Aplicaciones
 *
 */
@NamedQueries({
	@NamedQuery(name = "findAplicacionByNombre", query = "SELECT a FROM Aplicaciones a WHERE a.NOMBRE = :nombre"),
	@NamedQuery(name = "deleteAplicacion", query = "DELETE FROM Aplicaciones h WHERE h.ID = :id")
})
@Entity
public class Aplicaciones implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer ID;
    private String DESCRIPCION;
    private String NOMBRE;
    private boolean ESTADO;
    private Integer GROUPID;

    @ManyToMany(cascade = CascadeType.ALL , fetch = FetchType.EAGER)
    @JoinTable(
        name = "aplicacion_herramienta", 
        joinColumns = @JoinColumn(name = "aplicacion_id"), 
        inverseJoinColumns = @JoinColumn(name = "herramienta_id")
    )
    private Set<Herramientas> herramientas = new HashSet<>();
    
    @ManyToMany(mappedBy = "aplicaciones")
    private List<Usuarios> usuarios = new ArrayList<>();


    public Integer getID() {
        return this.ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public String getDESCRIPCION() {
        return this.DESCRIPCION;
    }

    public void setDESCRIPCION(String DESCRIPCION) {
        this.DESCRIPCION = DESCRIPCION;
    }

    public String getNOMBRE() {
        return this.NOMBRE;
    }

    public void setNOMBRE(String NOMBRE) {
        this.NOMBRE = NOMBRE;
    }

    public boolean getESTADO() {
        return this.ESTADO;
    }

    public void setESTADO(boolean ESTADO) {
        this.ESTADO = ESTADO;
    }

    public Integer getGROUPID() {
        return this.GROUPID;
    }

    public void setGROUPID(Integer GROUPID) {
        this.GROUPID = GROUPID;
    }
    
    public List<Usuarios> getUsuarios()
    {
    	return usuarios;
    }
    
    public void setUsuarios(List<Usuarios> usuarios) 
    {
    	this.usuarios = usuarios;
    }
}
