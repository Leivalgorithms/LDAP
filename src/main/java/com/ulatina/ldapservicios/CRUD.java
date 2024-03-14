package com.ulatina.ldapservicios;

import java.util.Optional;

public interface CRUD<T> {
	
	public void insert(T t);
	
	public void update(T t);
	
	public void delete(Integer id);
	
	//Optional<T> findPK(Integer id);
	

}