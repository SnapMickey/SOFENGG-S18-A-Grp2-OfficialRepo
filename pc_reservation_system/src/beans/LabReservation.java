package beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity(name = "lab_reservations")
public class LabReservation {
	
	@Id
	@Column(name = "labReservationID")
	int labReservationID;
	
	@Column(name = "userID")
	int userID;
	
	@Column(name = "locationID")
	int locationID;
	
	@Column(name = "dateTimeStart")
	Date dateTimeStart;
	
	@Column(name = "dateTimeEnd")
	Date dateTimeEnd;
	
	@Column(name = "checkInTime")
	Date checkInTime;
	
	@Column(name = "reserveTime")
	Date reserveTime;
	
	@Column(name = "adminConfirmed")
	boolean adminConfirmed;
	
	@Column(name = "eventName")
	String eventName;
	
	public LabReservation() {
		this.userID = -1;
		this.locationID = -1;
		this.dateTimeStart = null;
		this.dateTimeEnd = null;
		this.checkInTime = null;
		this.reserveTime = null;
		this.adminConfirmed = false;
		this.eventName = "empty";
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public int getLocationID() {
		return locationID;
	}

	public void setLocationID(int locationID) {
		this.locationID = locationID;
	}

	public Date getDateTimeStart() {
		return dateTimeStart;
	}

	public void setDateTimeStart(Date dateTimeStart) {
		this.dateTimeStart = dateTimeStart;
	}

	public Date getDateTimeEnd() {
		return dateTimeEnd;
	}

	public void setDateTimeEnd(Date dateTimeEnd) {
		this.dateTimeEnd = dateTimeEnd;
	}

	public Date getCheckInTime() {
		return checkInTime;
	}

	public void setCheckInTime(Date checkInTime) {
		this.checkInTime = checkInTime;
	}

	public Date getReserveTime() {
		return reserveTime;
	}

	public void setReserveTime(Date reserveTime) {
		this.reserveTime = reserveTime;
	}

	public boolean isAdminConfirmed() {
		return adminConfirmed;
	}

	public void setAdminConfirmed(boolean adminConfirmed) {
		this.adminConfirmed = adminConfirmed;
	}

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public int getLabReservationID() {
		return labReservationID;
	}

	@Override
	public String toString() {
		return "LabReservation [labReservationID=" + labReservationID + ", userID=" + userID + ", locationID="
				+ locationID + ", dateTimeStart=" + dateTimeStart + ", dateTimeEnd=" + dateTimeEnd + ", checkInTime="
				+ checkInTime + ", reserveTime=" + reserveTime + ", adminConfirmed=" + adminConfirmed + ", eventName="
				+ eventName + "]";
	}
		
}
