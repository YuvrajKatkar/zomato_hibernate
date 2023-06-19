package DAO;

import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import DTO.Food;
import DTO.Hotel;

public class HotelDAO {

	public void saveHotel(Hotel hotel) {
		// TODO Auto-generated method stub
		EntityTransaction entityTransaction = ProvideEM.entityManager.getTransaction();
		entityTransaction.begin();
		ProvideEM.entityManager.persist(hotel);
		entityTransaction.commit();
	}
	//create hotel
		//add food in hotel from existing food and new food
		//remove food from hotel
		//change price of food ie. update food
		//display all food list

	public void viewHotel() {
		// TODO Auto-generated method stub
		Query query = ProvideEM.entityManager.createQuery("select h from Hotel h");
		System.out.println(query.getResultList());
	}

	public void removeHotel(Hotel hotel2) {
		// TODO Auto-generated method stub
		EntityTransaction entityTransaction = ProvideEM.entityManager.getTransaction();
		entityTransaction.begin();
		ProvideEM.entityManager.remove(hotel2);
		entityTransaction.commit();
		
	}

	public void updateHotel(Hotel hotel3) {
		// TODO Auto-generated method stub
		EntityTransaction entityTransaction = ProvideEM.entityManager.getTransaction();
		entityTransaction.begin();
		for(Food food :hotel3.getHotelFoods()) {
			ProvideEM.entityManager.merge(food);
		}
		ProvideEM.entityManager.merge(hotel3);
		entityTransaction.commit();
	}
	
	//delete hotel
	//update hotel details
	//display single hotel
	//display all hotel
}
