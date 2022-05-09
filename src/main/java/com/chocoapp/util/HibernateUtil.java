package com.chocoapp.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class HibernateUtil {

	private static EntityManagerFactory factory;

	public static Session getSession() {
		if (factory == null) {
			factory = Persistence.createEntityManagerFactory("chocoapp-pu");
		}
		EntityManager entityManager = factory.createEntityManager();
		Session session = entityManager.unwrap(org.hibernate.Session.class);

		SessionFactory factory = session.getSessionFactory();

		return session;
	}
}
