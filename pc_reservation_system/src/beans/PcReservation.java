package beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity(name = "pc_reservations")
public class PcReservation {
	
	@Id
	@Column(name = "pcReservationsID")
	int pcReservationID;
	
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
	

	public PcReservation() {
		this.userID = -1;
		this.pcID = -1;
		this.dateTimeStart = null;
		this.dateTimeEnd = null;
		this.checkInTime = null;
		this.reserveTime = null;
		this.adminConfirmed = false;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public int getPcID() {
		return pcID;
	}

	public void setPcID(int pcID) {
		this.pcID = pcID;
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
	
	public int getPcReservationID() {
		return pcReservationID;
	}

	@Override
	public String toString() {
		return "PcReservation [pcReservationID=" + pcReservationID + ", userID=" + userID + ", pcID=" + pcID
				+ ", dateTimeStart=" + dateTimeStart + ", dateTimeEnd=" + dateTimeEnd + ", checkInTime=" + checkInTime
				+ ", reserveTime=" + reserveTime + ", adminConfirmed=" + adminConfirmed + "]";
	}
	
}
