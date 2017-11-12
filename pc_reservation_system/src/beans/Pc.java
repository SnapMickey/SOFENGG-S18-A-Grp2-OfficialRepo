package beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "pc_info")
public class Pc {

	
	@Id
	@Column(name = "pcID")
	int pcID;
	
	@Column(name = "locationID")
	int locationID;
	
	@Column(name = "isAvailable")
	boolean isAvailable;

	public int getPcID() {
		return pcID;
	}

	public int getLocationID() {
		return locationID;
	}

	public boolean isAvailable() {
		return isAvailable;
	}
	
}
