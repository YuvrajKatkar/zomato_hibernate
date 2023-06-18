package DAO;

import javax.persistence.EntityTransaction;

import DTO.Food;
import DTO.Hotel;

public class FoodDAO {

	public void addFood(Hotel hotel4) {
		// TODO Auto-generated method stub
		EntityTransaction entityTransaction = ProvideEM.entityManager.getTransaction();
		entityTransaction.begin();
		for(Food food :hotel4.getHotelFoods()) {
			ProvideEM.entityManager.persist(food);

		}
		ProvideEM.entityManager.merge(hotel4);
		
		entityTransaction.commit();
		
	}
	
}
