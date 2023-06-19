package DAO;

import javax.persistence.EntityTransaction;

import DTO.Customer;

public class CustomerDAO {

	public void saveCustomer(Customer customer) {
		// TODO Auto-generated method stub
		EntityTransaction entityTransaction = ProvideEM.entityManager.getTransaction();
		entityTransaction.begin();
		
		ProvideEM.entityManager.persist(customer);
		entityTransaction.commit();
		
	}
	
}
