package com.ride.dao;

import java.io.Serializable;

import javax.persistence.EntityManager;


import com.ride.entity.Viaje;
import com.ride.utils.JPAUtils;


public class ViajeFacade extends AbstractFacade<Viaje> implements Serializable {


	private static final long serialVersionUID = 1L;

	public ViajeFacade() {
		super(Viaje.class);
		getEntityManager();
	}

	EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		if (em==null) { 
			em = JPAUtils.getEntityManagerFactory().createEntityManager();  
		}									
		return em;
	}
	
}
