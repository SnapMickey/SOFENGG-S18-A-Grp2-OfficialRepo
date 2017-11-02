package beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;

@Entity(name = "pc_reservation")
public class PcReservation {

	@Id
	@Column(name = "borrowId")
	int borrowID;
	
	@Column(name = "userID")
	int userID;
	
	@Column(name = "pcID")
	int pcID;
	
	@Column(name = "dateTimeStart")
	Timestamp dateTimeStart;
	
	@Column(name = "dateTimeEnd")
	Timestamp dateTimeEnd;
	
	@Column(name = "checkInTime")
	Timestamp checkInTime;
	
	@Column(name = "reserveTime")
	Timestamp reserveTime;
	
	@Column(name = "eConfirmedTime")
	Timestamp eConfirmTime;
	
	public void setCheckInTime(Timestamp checkInTime) {
		this.checkInTime = checkInTime;
	}

	public void seteConfirmTime(Timestamp eConfirmTime) {
		this.eConfirmTime = eConfirmTime;
	}

	public void setEmailConfirmed(boolean emailConfirmed) {
		this.emailConfirmed = emailConfirmed;
	}

	public void setAdminConfirmed(boolean adminConfirmed) {
		this.adminConfirmed = adminConfirmed;
	}

	@Column(name = "emailConfirmed")
	boolean emailConfirmed;
	
	@Column(name = "adminConfirmed")
	boolean adminConfirmed;

	public int getBorrowID() {
		return borrowID;
	}

	public int getUserID() {
		return userID;
	}

	public int getPcID() {
		return pcID;
	}

	public Timestamp getDateTimeStart() {
		return dateTimeStart;
	}

	public Timestamp getDateTimeEnd() {
		return dateTimeEnd;
	}

	public Timestamp getCheckInTime() {
		return checkInTime;
	}

	public Timestamp getReserveTime() {
		return reserveTime;
	}

	public Timestamp geteConfirmTime() {
		return eConfirmTime;
	}

	public boolean isEmailConfirmed() {
		return emailConfirmed;
	}

	public boolean isAdminConfirmed() {
		return adminConfirmed;
	}
}
