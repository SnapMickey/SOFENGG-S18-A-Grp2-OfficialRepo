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
	
	@Column(name = "eConfirmedTime")
	Date eConfirmTime;
	
	@Column(name = "emailConfirmed")
	boolean emailConfirmed;
	
	@Column(name = "adminConfirmed")
	boolean adminConfirmed;
	
	

	public PcReservation(int userID, int pcID, Date dateTimeStart, Date dateTimeEnd, Date checkInTime, Date reserveTime,
			Date eConfirmTime, boolean emailConfirmed, boolean adminConfirmed) {
		super();
		this.userID = userID;
		this.pcID = pcID;
		this.dateTimeStart = dateTimeStart;
		this.dateTimeEnd = dateTimeEnd;
		this.checkInTime = checkInTime;
		this.reserveTime = reserveTime;
		this.eConfirmTime = eConfirmTime;
		this.emailConfirmed = emailConfirmed;
		this.adminConfirmed = adminConfirmed;
	}

	public void setEmailConfirmed(boolean emailConfirmed) {
		this.emailConfirmed = emailConfirmed;
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

	public Date geteConfirmTime() {
		return eConfirmTime;
	}

	public void seteConfirmTime(Date eConfirmTime) {
		this.eConfirmTime = eConfirmTime;
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

	public boolean isEmailConfirmed() {
		return emailConfirmed;
	}

	public boolean isAdminConfirmed() {
		return adminConfirmed;
	}

	@Override
	public String toString() {
		return "PcReservation [borrowID=" + borrowID + ", userID=" + userID + ", pcID=" + pcID + ", dateTimeStart="
				+ dateTimeStart + ", dateTimeEnd=" + dateTimeEnd + ", checkInTime=" + checkInTime + ", reserveTime="
				+ reserveTime + ", eConfirmTime=" + eConfirmTime + ", emailConfirmed=" + emailConfirmed
				+ ", adminConfirmed=" + adminConfirmed + "]";
	}
	
	
}
