package beans;

import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import beans.Pc;

@Entity(name = "lab")
public class Lab {
	
	@Id
	@Column(name = "locationID")
	int locationID;
	
	@Column(name = "name")
	String name;
	
	@Column(name = "building")
	String building;
	
	@Column(name = "isAvailable")
	boolean isAvailable;
	
	ArrayList<Pc> computers;
	
	public ArrayList<Pc> getComputers() {
		return computers;
	}
	public void setComputers(ArrayList<Pc> computers) {
		this.computers = computers;
	}
	public int getLocationID() {
		return locationID;
	}
	public String getName() {
		return name;
	}
	public String getBuilding() {
		return building;
	}
	public boolean isAvailable() {
		return isAvailable;
	}
	
	@Override
	public String toString() {
		return "Lab [locationID=" + locationID + ", name=" + name + ", building=" + building + ", isAvailable="
				+ isAvailable + "]";
	}
}
