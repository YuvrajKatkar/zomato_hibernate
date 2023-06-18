package Controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Scanner;

import javax.persistence.EntityTransaction;

import DAO.AdminDAO;
import DAO.CustomerDAO;
import DAO.FoodDAO;
import DAO.HotelDAO;
import DAO.ProvideEM;
import DTO.Admin;
import DTO.Customer;
import DTO.Food;
import DTO.Hotel;

public class MainController {
	public static void main(String[] args) {
		ProvideEM.entityManager = ProvideEM.getEntityManager();
		AdminDAO adminDAO = new AdminDAO();
		HotelDAO hotelDAO = new HotelDAO();
		FoodDAO foodDAO = new FoodDAO();
		CustomerDAO customerDAO = new CustomerDAO();
//		Admin admi = new Admin("yuvraj", "yuvrajkatkar.2000@gmail.com", "123");
//		EntityTransaction entityTransaction = ProvideEM.entityManager.getTransaction();
//		entityTransaction.begin();
//		ProvideEM.entityManager.merge(admi);
//		entityTransaction.commit();
		System.out.println("Select whether you want to choose as Customer or Admin: ");
		System.out.println("1. Customer 2. Admin");
		Scanner scanner = new Scanner(System.in);
		if (scanner.nextInt() == 1) {
			System.out.println("Enter your name:");
			String customerName = scanner.next();
			System.out.println("Enter your address:");
			String customerAddress = scanner.next();
			System.out.println("Enter your phone:");
			long customerPhone = scanner.nextLong();
			Customer customer = new Customer(customerName, customerAddress, customerPhone);
			
			//switch to make order 
			System.out.println("Select hotel from following ");
			System.out.println(ProvideEM.entityManager.createQuery("Select h from Hotel h").getResultList());
			
			System.out.println("Enter the id of hotel which you have selected");
			int id = scanner.nextInt();
			Hotel hotel = ProvideEM.entityManager.find(Hotel.class, id);
			HashMap<Food, Integer> hashMap = new HashMap<Food, Integer>();
			int i =1;
			while(i>0) {
				System.out.println("1. Choose food 2. Done ");
				switch (scanner.nextInt()) {
				case 1:
					System.out.println("Select food from following ");
					System.out.println(ProvideEM.entityManager.createQuery("Select h.hotelFoods from Hotel h where h.id=?1")
							.setParameter(1, id).getResultList());
					int foodInt = scanner.nextInt();
					Food food = ProvideEM.entityManager.find(Food.class, foodInt);
					System.out.println("Enter qty for the food ");
					Integer qty= scanner.nextInt();
					hashMap.put(food, qty);
					break;

				case 2:
					i=-1;
					break;
				}
				
				
			}
			customer.setHashMap(hashMap);
			//view bill
			int sr=1;int sum=0;
			for(Entry<Food, Integer> foodEntry:customer.getHashMap().entrySet()) {
				System.out.println(sr++ + " . "+foodEntry.getKey().getName() + "  "+foodEntry.getValue() +" : "+foodEntry.getValue()*foodEntry.getKey().getPrice());
				sum +=  foodEntry.getValue()*foodEntry.getKey().getPrice();
			}
			System.out.println("Total : "+sum);
			System.out.println("________________");
			customerDAO.saveCustomer(customer);
			
		} else {
			System.out.println("Enter your email: ");
			String adminEmail = scanner.next();
			System.out.println("Enter your password: ");
			String adminPassword = scanner.next();
			Admin admin = new Admin(adminEmail, adminPassword);
			if (adminDAO.validateAdmin(admin)) {
				System.out.println("1. Add admin 2. Remove admin 3. Display all Admins ");
				System.out.println("4. Add Hotel 5. Remove Hotel 6. View all Hotel 7. Update Hotel ");
				System.out.println("8. Add Food In Hotel 9.Remove Food from Hotel 10. Update Food Price ");
				System.out.println("11. Exit");
				switch (scanner.nextInt()) {
				case 1:
					System.out.println("Enter name of Admin");
					String name = scanner.next();
					System.out.println("Enter email of Admin");
					String email = scanner.next();
					System.out.println("Enter password of Admin");
					String password = scanner.next();
					Admin admin2 = new Admin(name, email, password);
					adminDAO.saveAdmin(admin2);
					break;
				case 2:
					System.out.println("Enter id of admin to remove");
					int id = scanner.nextInt();
					Admin admin3 = ProvideEM.entityManager.find(Admin.class, id);
					adminDAO.removeAdmin(admin3);
					break;
				case 3:
					adminDAO.displayAll();
					break;
				case 4:
					System.out.println("Enter name of Hotel:");
					String nameHotel = scanner.next();
					System.out.println("Enter address of Hotel:");
					String addressHotel = scanner.next();
					System.out.println("Enter gst no of Hotel:");
					long gst = scanner.nextLong();
					Hotel hotel = new Hotel(nameHotel, addressHotel, gst);
					hotelDAO.saveHotel(hotel);
					break;
				case 5:
					System.out.println("Enter id of hotel to be removed: ");
					int id1 = scanner.nextInt();
					Hotel hotel2 = ProvideEM.entityManager.find(Hotel.class, id1);
					hotelDAO.removeHotel(hotel2);
					break;
				case 6:
					hotelDAO.viewHotel();
					break;
				case 7:
					System.out.println("Enter id of hotel to be update: ");
					int id2 = scanner.nextInt();
					Hotel hotel3 = ProvideEM.entityManager.find(Hotel.class, id2);
					System.out.println("Enter new Hotel name");
					String newName = scanner.next();
					System.out.println("Enter new Hotel Address");
					String newAddress = scanner.next();
					System.out.println("Enter new Hotel gst no");
					long newgst = scanner.nextLong();
					hotel3.setAddress(newAddress);
					hotel3.setGst(newgst);
					hotel3.setName(newName);
					hotelDAO.updateHotel(hotel3);
					break;
				case 8:
					System.out.println("Enter id of hotel to which food is to added: ");
					int id3 = scanner.nextInt();
					Hotel hotel4 = ProvideEM.entityManager.find(Hotel.class, id3);
					List<Food> foods = new ArrayList<Food>();
					int i = 1;
					while (i > 0) {
						System.out.println("1. Add Food 2.Done adding Food");
						switch (scanner.nextInt()) {
						case 1:
							System.out.println("Enter food name: ");
							String foodName = scanner.next();
							System.out.println("Enter food price: ");
							double foodPrice = scanner.nextDouble();
							Food food = new Food(foodName, foodPrice);
							foods.add(food);
							break;
						case 2:
							i = 0;
						}
					}
					hotel4.setHotelFoods(foods);
					foodDAO.addFood(hotel4);
					break;
				case 9:
					break;
				case 10:
					break;
				case 11:
					break;
				}
			} else {
				System.out.println("Wrong credentials");
			}
		}
	}
}
