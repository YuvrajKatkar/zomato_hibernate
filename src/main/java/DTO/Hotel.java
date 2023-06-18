package DTO;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
@Entity
public class Hotel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private String address;
	private long gst;
	@OneToMany
	List<Food> hotelFoods;
	@OneToMany
	List<Customer> hotelCustomers;
	public Hotel(String name, String address, long gst, List<Food> hotelFoods, List<Customer> hotelCustomers) {
		super();
		this.name = name;
		this.address = address;
		this.gst = gst;
		this.hotelFoods = hotelFoods;
		this.hotelCustomers = hotelCustomers;
	}
	public Hotel(String name, String address, long gst) {
		super();
		this.name = name;
		this.address = address;
		this.gst = gst;
	}
	public Hotel() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public long getGst() {
		return gst;
	}
	public void setGst(long gst) {
		this.gst = gst;
	}
	public List<Food> getHotelFoods() {
		return hotelFoods;
	}
	public void setHotelFoods(List<Food> hotelFoods) {
		this.hotelFoods = hotelFoods;
	}
	public List<Customer> getHotelCustomers() {
		return hotelCustomers;
	}
	public void setHotelCustomers(List<Customer> hotelCustomers) {
		this.hotelCustomers = hotelCustomers;
	}
	@Override
	public String toString() {
		return "Hotel [id=" + id + ", name=" + name + ", address=" + address + ", gst=" + gst + "]";
	}
	
	 
}
