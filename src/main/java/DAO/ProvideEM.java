package DAO;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class ProvideEM {
	public static EntityManager entityManager;
	public static EntityManager getEntityManager() {
		return Persistence.createEntityManagerFactory("yuvraj").createEntityManager();
	}
}
