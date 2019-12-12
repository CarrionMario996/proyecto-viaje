package com.ride.dao;

import java.sql.Time;
import java.util.*;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import com.ride.entity.Viaje;

public abstract class AbstractFacade<T> {
	private Class<T> entityClass;

	public AbstractFacade(Class<T> entityClass) {
		this.entityClass = entityClass;
	}

	protected abstract EntityManager getEntityManager();

	@SuppressWarnings("unchecked")
	public List<T> mostrar() {
		// CriteriaQuery<T> cq =
		// getEntityManager().getCriteriaBuilder().createQuery(entityClass);
		return getEntityManager().createNativeQuery("SELECT * FROM ride.viaje where estado=1 ", Viaje.class)
				.getResultList();
	}

	public void create(T entity) {
		EntityManager em = getEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(entity);
			em.getTransaction().commit();
		} catch (Exception e) {
			if (em.getTransaction() == null && em.isOpen()) {
				em.getTransaction().rollback();
			}
		} finally {
			if (em != null && em.isOpen()) {
				em.close();
			}
		}

	}

	public T findId(Object id) {
		return getEntityManager().find(entityClass, id);
	}

	public void delete(T entity) {
		EntityManager em = getEntityManager();
		try {
			em.getTransaction().begin();
			em.remove(entity);
			em.getTransaction().commit();
		} catch (Exception e) {
			if (em.getTransaction() == null && em.isOpen()) {
				em.close();
			}
		}

	}

	public void update(T entity) { // Ultimo Metodo Modificar
		EntityManager em = getEntityManager();
		try {
			em.getTransaction().begin(); 
			em.merge(entity); 
			em.getTransaction().commit(); 
		} catch (Exception e) {
			if (em.getTransaction() == null && em.isOpen()) {
				em.getTransaction().rollback();
			}
		} 
	}

	

}
