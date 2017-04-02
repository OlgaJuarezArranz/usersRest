package com.ojuarezarranz.repositories;

import java.io.Serializable;

import javax.persistence.criteria.CriteriaBuilder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import java.lang.reflect.ParameterizedType;

public class GenericRepository <PK extends Serializable, T>{

	private final Class<T> persistentClass;

	@SuppressWarnings("unchecked")
	public GenericRepository() {
		this.persistentClass = (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass())
				.getActualTypeArguments()[1];
	}
	
	@Autowired
	private LocalContainerEntityManagerFactoryBean factoryBean;

	protected CriteriaBuilder getCriteriaBuilder() {
		
		return factoryBean.getNativeEntityManagerFactory().getCriteriaBuilder();
	}

}
