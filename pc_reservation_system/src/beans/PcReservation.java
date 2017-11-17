package beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity(name = "pc_reservations")
public class PcReservation {
	
	@Id
	@Column(name = "borrowId")
	int borrowID;
	
	@Column(name = "userID")
	int userID;
	
	@Column(name = "pcID")
	int pcID;
	
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
	
	public PcReservation() {}

	public PcReservation(int pcID, Date dateTimeStart, Date dateTimeEnd) {
		super();
		this.userID = -1;
		this.pcID = pcID;
		this.dateTimeStart = dateTimeStart;
		this.dateTimeEnd = dateTimeEnd;
		this.checkInTime = null;
		this.reserveTime = null;
		this.adminConfirmed = false;
		this.eventName = "None";
	}

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}



	public void setAdminConfirmed(boolean adminConfirmed) {
		this.adminConfirmed = adminConfirmed;
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

	public int getBorrowID() {
		return borrowID;
	}

	public int getUserID() {
		return userID;
	}

	public int getPcID() {
		return pcID;
	}

	public boolean isAdminConfirmed() {
		return adminConfirmed;
	}

	@Override
	public String toString() {
		return "PcReservation [borrowID=" + borrowID + ", userID=" + userID + ", pcID=" + pcID + ", dateTimeStart="
				+ dateTimeStart + ", dateTimeEnd=" + dateTimeEnd + ", checkInTime=" + checkInTime + ", reserveTime="
				+ reserveTime + ", adminConfirmed=" + adminConfirmed + "]";
	}
	
	
}
