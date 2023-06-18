package DAO;

import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import DTO.Admin;

public class AdminDAO {
	//validate admin
	//add admin
	//remove admin
	//view all admin
	//after validating from admin flow will go to hoteldao
	
	public boolean validateAdmin(Admin admin) {
		Query query= ProvideEM.entityManager.createQuery("Select a from Admin a where a.email=?1 and a.password=?2");
		query.setParameter(1, admin.getEmail());
		query.setParameter(2, admin.getPassword());
		Admin dbAdmin = (Admin) query.getSingleResult();
		if(dbAdmin!=null) {
			return true;
		}
		return false;
	}
	public void saveAdmin(Admin admin) {
		EntityTransaction entityTransaction = ProvideEM.entityManager.getTransaction();
		entityTransaction.begin();
		ProvideEM.entityManager.persist(admin);
		entityTransaction.commit();
	}
	public void removeAdmin(Admin admin) {
		// TODO Auto-generated method stub
		EntityTransaction entityTransaction = ProvideEM.entityManager.getTransaction();
		entityTransaction.begin();
		ProvideEM.entityManager.remove(admin);
		entityTransaction.commit();

	}
	public void displayAll() {
		// TODO Auto-generated method stub
		Query query = ProvideEM.entityManager.createQuery("select a from Admin a");
		System.out.println(query.getResultList());
		
	}
}
