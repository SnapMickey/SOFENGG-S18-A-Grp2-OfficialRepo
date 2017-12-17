package services;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.Date;
import beans.*;

/**
 * The SystemService class is used to connect to the SQL database and execute
 * statements on the pc_reserve schema
 * 
 * @version 2.0
 * @since 2017-11-30
 */
public class SystemService {

	/**
	 * Method returns a User object based on a userID. If User does not exist,
	 * returns null.
	 * 
	 * @param id
	 *            - UserID
	 * @return User
	 */
	public static User getUser(int id) {
		User user = null;

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("db");
		EntityManager em = emf.createEntityManager();
		EntityTransaction trans = em.getTransaction();

		trans.begin();
		user = em.find(User.class, id);
		trans.commit();

		em.close();
		emf.close();

		return user;
	}

	/**
	 * 
	 * Method returns a Faculty object based on a userID. If Faculty does not exist,
	 * returns null.
	 * 
	 * @param id
	 *            - userID
	 * @return Faculty
	 */
	public static Faculty getFaculty(int id) {
		Faculty faculty = null;

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("db");
		EntityManager em = emf.createEntityManager();
		EntityTransaction trans = em.getTransaction();

		trans.begin();
		faculty = em.find(Faculty.class, id);
		trans.commit();

		em.close();
		emf.close();

		return faculty;
	}

	/**
	 * Method returns a Student object based on a userID. If Student does not exist,
	 * returns null.
	 * 
	 * @param id
	 * @return Student
	 */
	public static Student getStudent(int id) {
		Student student = null;

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("db");
		EntityManager em = emf.createEntityManager();
		EntityTransaction trans = em.getTransaction();

		trans.begin();
		student = em.find(Student.class, id);
		trans.commit();

		em.close();
		emf.close();

		return student;
	}

	/**
	 * 
	 * Method returns a Pc object based on a pcID. If Pc does not exist, returns
	 * null.
	 * 
	 * @param id
	 *            - pcID
	 * @return
	 */
	public static Pc getPc(int id) {
		Pc pc = null;

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("db");
		EntityManager em = emf.createEntityManager();
		EntityTransaction trans = em.getTransaction();

		trans.begin();
		pc = em.find(Pc.class, id);
		trans.commit();

		em.close();
		emf.close();

		return pc;
	}

	/**
	 * 
	 * Method returns a Lab object based on a locationID. If Lab does not exist,
	 * returns null.
	 * 
	 * @param id
	 *            - locationID
	 * @return Lab
	 */
	public static Lab getLab(int id) {
		Lab lab = null;

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("db");
		EntityManager em = emf.createEntityManager();
		EntityTransaction trans = em.getTransaction();

		trans.begin();
		lab = em.find(Lab.class, id);
		trans.commit();

		lab.setComputers(SystemService.getAllPcs(lab.getLocationID()));

		em.close();
		emf.close();
		return lab;
	}

	/**
	 * 
	 * Method returns a Lab object of a specific Pc based on a pcID. If Lab does not
	 * exist, returns null.
	 * 
	 * @param id
	 *            - pcID
	 * @return Lab
	 */
	public static Lab getLabOfPc(int id) {
		Lab lab = null;
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("db");
		EntityManager em = emf.createEntityManager();
		EntityTransaction trans = em.getTransaction();

		String statement = "select lb from lab lb, pc_info pc where " + "pc.pcID = " + id
				+ " and lb.locationID = pc.locationID";

		try {
			trans.begin();

			TypedQuery<Lab> query = em.createQuery(statement, Lab.class);
			lab = query.getResultList().get(0);

			trans.commit();
		} catch (Exception e) {
			if (trans != null) {
				trans.rollback();
			}
			e.printStackTrace();
		} finally {
			if (em != null) {
				em.close();
				emf.close();
			}
		}
		return lab;
	}

	/**
	 * 
	 * Method returns a PcReservation object based on a pcReservationsID. If
	 * PcReservation does not exist, returns null.
	 * 
	 * @param id
	 *            - pcReservationsID
	 * @return PcReservation
	 */
	public static PcReservation getPcReservation(int id) {
		PcReservation pr = null;

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("db");
		EntityManager em = emf.createEntityManager();
		EntityTransaction trans = em.getTransaction();

		trans.begin();
		pr = em.find(PcReservation.class, id);

		trans.commit();

		em.close();
		emf.close();

		return pr;
	}

	/**
	 * @param date
	 * @param startTime
	 * @param endTime
	 * @param pcID
	 * @return
	 */
	public static PcReservation getPcReservation(Date date, Date startTime, Date endTime, int pcID) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("db");
		EntityManager em = emf.createEntityManager();
		EntityTransaction trans = em.getTransaction();

		PcReservation pr = null;
		String statement = "select pr from pc_reservations pr " + "where pr.pcID = " + pcID
				+ " and HOUR(pr.dateTimeStart) = " + startTime.getHours() + " and HOUR(pr.dateTimeEnd) = "
				+ endTime.getHours() + " and DATE(pr.dateTimeStart) = " + "\'" + (startTime.getYear() + 1900) + "-"
				+ (startTime.getMonth() + 2) + "-" + startTime.getDate() + "\'";

		System.out.println(statement);
		try {
			trans.begin();

			TypedQuery<PcReservation> query = em.createQuery(statement, PcReservation.class);
			pr = query.getResultList().get(0);

			trans.commit();
		} catch (Exception e) {
			if (trans != null) {
				trans.rollback();
			}
		} finally {
			if (em != null) {
				em.close();
				emf.close();
			}
		}

		return pr;
	}

	/**
	 * 
	 * Method returns a LabReservation object based on a labReservationsID. If
	 * LabReservation does not exist, returns null.
	 * 
	 * @param id
	 *            - labReservationsID
	 * @return LabReservation
	 */
	public static LabReservation getLabReservation(int id) {
		LabReservation lr = null;

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("db");
		EntityManager em = emf.createEntityManager();
		EntityTransaction trans = em.getTransaction();

		trans.begin();
		lr = em.find(LabReservation.class, id);

		trans.commit();

		em.close();
		emf.close();

		return lr;
	}

	public static LabReservation getLabReservation(Date date, Date startTime, Date endTime, String name) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("db");
		EntityManager em = emf.createEntityManager();
		EntityTransaction trans = em.getTransaction();

		LabReservation lr = null;
		String statement = "select lr from lab_reservations lr, lab l"
				+ " where lr.locationID = l.locationID and l.name like %" + name + "% and HOUR(lr.dateTimeStart) = "
				+ startTime.getHours() + " and HOUR(lr.dateTimeEnd) = " + endTime.getHours()
				+ " and DATE(lr.dateTimeStart) = " + "\'" + (startTime.getYear() + 1900) + "-"
				+ (startTime.getMonth() + 2) + "-" + startTime.getDate() + "\'";

		System.out.println(statement);

		try {
			trans.begin();

			TypedQuery<LabReservation> query = em.createQuery(statement, LabReservation.class);
			lr = query.getResultList().get(0);

			trans.commit();
		} catch (Exception e) {
			if (trans != null) {
				trans.rollback();
			}
		} finally {
			if (em != null) {
				em.close();
				emf.close();
			}
		}

		return lr;
	}

	/**
	 * 
	 * Method returns an ArrayList of PcReservations of a specific User based on a
	 * userID. If User does not exist, returns an empty ArrayList.
	 * 
	 * @param id
	 *            - userID
	 * @return ArrayList<PcReservation>
	 */
	public static ArrayList<PcReservation> getUserPcReservations(int id) {
		ArrayList<PcReservation> reservations = new ArrayList<>();
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("db");
		EntityManager em = emf.createEntityManager();
		EntityTransaction trans = em.getTransaction();

		String statement = "select pr from pc_reservations pr where pr.userID = " + id + " and pr.adminConfirmed = 1";

		try {
			trans.begin();

			TypedQuery<PcReservation> query = em.createQuery(statement, PcReservation.class);
			reservations.addAll(query.getResultList());

			trans.commit();
		} catch (Exception e) {
			if (trans != null) {
				trans.rollback();
			}
			e.printStackTrace();
		} finally {
			if (em != null) {
				em.close();
				emf.close();
			}
		}
		return reservations;
	}

	/**
	 * 
	 * Method returns a pending PcReservation of a specific User based on a userID.
	 * If User does not exist, returns null.
	 * 
	 * @param id
	 *            - userID
	 * @return PcReservation
	 */
	public static PcReservation getUserPendingPcReservations(int id) {
		PcReservation reservation = null;
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("db");
		EntityManager em = emf.createEntityManager();
		EntityTransaction trans = em.getTransaction();

		String statement = "select pr from pc_reservations pr where pr.userID = " + id + " and pr.adminConfirmed = 1";

		try {
			trans.begin();

			TypedQuery<PcReservation> query = em.createQuery(statement, PcReservation.class);
			reservation = query.getResultList().get(0);

			trans.commit();
		} catch (Exception e) {
			if (trans != null) {
				trans.rollback();
			}
		} finally {
			if (em != null) {
				em.close();
				emf.close();
			}
		}
		return reservation;
	}

	/**
	 * 
	 * Method returns an ArrayList of LabReservations of a specific User based on a
	 * userID. If Lab does not exist, returns an empty ArrayList.
	 * 
	 * @param id
	 *            - userID
	 * @return ArrayList<LabReservation>
	 */
	public static ArrayList<LabReservation> getUserLabReservations(int id) {
		ArrayList<LabReservation> reservations = new ArrayList<>();
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("db");
		EntityManager em = emf.createEntityManager();
		EntityTransaction trans = em.getTransaction();

		String statement = "select lr from lab_reservations lr where lr.userID = " + id + " and lr.adminConfirmed = 1";

		try {
			trans.begin();

			TypedQuery<LabReservation> query = em.createQuery(statement, LabReservation.class);
			reservations.addAll(query.getResultList());

			trans.commit();
		} catch (Exception e) {
			if (trans != null) {
				trans.rollback();
			}
			e.printStackTrace();
		} finally {
			if (em != null) {
				em.close();
				emf.close();
			}
		}
		return reservations;
	}

	/**
	 * 
	 * Method returns a pending LabReservations of a specific User based on a
	 * userID. If Lab does not exist, returns null.
	 * 
	 * @param id
	 *            - userID
	 * @return LabReservation
	 */
	public static LabReservation getUserPendingLabReservations(int id) {
		LabReservation reservation = null;
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("db");
		EntityManager em = emf.createEntityManager();
		EntityTransaction trans = em.getTransaction();

		String statement = "select lr from lab_reservations lr where lr.userID = " + id + " and lr.adminConfirmed = 0";

		try {
			trans.begin();

			TypedQuery<LabReservation> query = em.createQuery(statement, LabReservation.class);
			reservation = query.getResultList().get(0);

			trans.commit();
		} catch (Exception e) {
			if (trans != null) {
				trans.rollback();
			}
			e.printStackTrace();
		} finally {
			if (em != null) {
				em.close();
				emf.close();
			}
		}
		return reservation;
	}

	/**
	 * Method returns an ArrayList of Pc Objects for all existing Pcs.
	 * 
	 * @return ArrayList<Pc>
	 */
	public static ArrayList<Pc> getAllPcs() {
		ArrayList<Pc> pcs = new ArrayList<>();
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("db");
		EntityManager em = emf.createEntityManager();
		EntityTransaction trans = em.getTransaction();

		try {
			trans.begin();

			TypedQuery<Pc> query = em.createQuery("from pc_info", Pc.class);
			pcs.addAll(query.getResultList());

			trans.commit();
		} catch (Exception e) {
			if (trans != null) {
				trans.rollback();
			}
			e.printStackTrace();
		} finally {
			em.close();
			emf.close();
		}
		return pcs;
	}

	/**
	 * Method returns an ArrayList of Pc Objects for all existing Pcs from a
	 * specific Lab.
	 * 
	 * @param id
	 *            - labID
	 * @return ArrayList<Pc>
	 */
	public static ArrayList<Pc> getAllPcs(int id) {
		ArrayList<Pc> pcs = new ArrayList<>();
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("db");
		EntityManager em = emf.createEntityManager();
		EntityTransaction trans = em.getTransaction();

		try {
			trans.begin();

			TypedQuery<Pc> query = em.createQuery("from pc_info where locationID = " + id, Pc.class);
			pcs.addAll(query.getResultList());

			trans.commit();
		} catch (Exception e) {
			if (trans != null) {
				trans.rollback();
			}
			e.printStackTrace();
		} finally {
			em.close();
			emf.close();
		}
		return pcs;
	}

	/**
	 * This method returns an ArrayList of all Lab objects of existing labs. Method
	 * accepts an input of a string to get labs inside a specific building. If
	 * building is null, returns all Lab objects.
	 * 
	 * @param building
	 * @return
	 */
	public static ArrayList<Lab> getAllLabs(String building) {
		ArrayList<Lab> labs = new ArrayList<>();
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("db");
		EntityManager em = emf.createEntityManager();
		EntityTransaction trans = em.getTransaction();

		try {
			trans.begin();

			String statement = "from lab lb";

			if (building != null) {
				statement += " where lb.building like '%" + building + "%'";
			}

			TypedQuery<Lab> query = em.createQuery(statement, Lab.class);
			labs.addAll(query.getResultList());

			trans.commit();
		} catch (Exception e) {
			if (trans != null) {
				trans.rollback();
			}
			e.printStackTrace();
		} finally {
			em.close();
			emf.close();
		}
		return labs;
	}

	/**
	 * The method returns an ArrayList of Pc objects that are free given the
	 * specific parameters.
	 * 
	 * 
	 * @param date
	 *            - date to check for free Pcs (required)
	 * @param startTime
	 *            - date with time as the startTime for possible PcReservation
	 *            (required)
	 * @param endTime
	 *            - date with time as the endTime for possible PcReservation
	 *            (required)
	 * @param building
	 *            - specific building to check. Can be null; checks all building
	 * @param room
	 *            - specific lab to check. Can be null; checks all labs
	 * @return ArrayList<Pc>
	 */
	public static ArrayList<Pc> getAllFreePcs(Date date, Date startTime, Date endTime, String building, String room) {
		ArrayList<Pc> pcs = new ArrayList<>();
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("db");
		EntityManager em = emf.createEntityManager();
		EntityTransaction trans = em.getTransaction();

		String statement = "select pc from pc_info pc, lab lb";

		statement += " where pc.isAvailable = 1";

		statement += " and pc.locationID = lb.locationID and lb.isAvailable = 1";

		if (building != null) {
			statement += " and lb.building like '%" + building + "%'";
		}

		if (room != null) {
			statement += " and lb.name like '%" + room + "%'";
		}

		statement += " and pc.pcID not in (select pc.pcID from pc_info pc, pc_reservations pr "
				+ "where pc.pcID = pr.pcID and HOUR(pr.dateTimeStart) = " + startTime.getHours()
				+ " and HOUR(pr.dateTimeEnd) = " + endTime.getHours() + " and DATE(pr.dateTimeStart) = " + "\'"
				+ (date.getYear() + 1900) + "-" + (date.getMonth() + 1) + "-" + date.getDate() + "\'" + ")";

		statement += " and pc.locationID = lb.locationID and lb.locationID not in"
				+ "(select lr.locationID from lab_reservations lr" + " where HOUR(lr.dateTimeStart) = "
				+ startTime.getHours() + " and HOUR(lr.dateTimeEnd) = " + endTime.getHours()
				+ " and DATE(lr.dateTimeStart) = " + "\'" + (date.getYear() + 1900) + "-" + (date.getMonth() + 1) + "-"
				+ date.getDate() + "\'" + ")";

		try {
			trans.begin();

			TypedQuery<Pc> query = em.createQuery(statement, Pc.class);
			pcs.addAll(query.getResultList());

			trans.commit();
		} catch (Exception e) {
			if (trans != null) {
				trans.rollback();
			}
			e.printStackTrace();
		} finally {
			if (em != null) {
				em.close();
				emf.close();
			}
		}
		return pcs;
	}

	/**
	 * The method returns an ArrayList of Lab objects that are free given the
	 * specific parameters.
	 * 
	 * @param date
	 *            - date to check for free Labs (required)
	 * @param startTime
	 *            - date with time as the startTime for possible LabReservation
	 *            (required)
	 * @param endTime
	 *            - date with time as the endTime for possible LabReservation
	 *            (required)
	 * @param building
	 *            - specific building to check. Can be null; checks all building
	 * @return ArrayList<Lab>
	 */
	public static ArrayList<Lab> getAllFreeLabs(Date date, Date startTime, Date endTime, String building) {
		ArrayList<Lab> labs = new ArrayList();

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("db");
		EntityManager em = emf.createEntityManager();
		EntityTransaction trans = em.getTransaction();

		String statement = "select lb from lab lb";

		statement += " where lb.isAvailable = 1";

		if (building != null) {
			statement += " and lb.building like '%" + building + "%'";
		}

		statement += " and lb.locationID not in" + "(select lr.locationID from lab_reservations lr"
				+ " where HOUR(lr.dateTimeStart) = " + startTime.getHours() + " and HOUR(lr.dateTimeEnd) = "
				+ endTime.getHours() + " and DATE(lr.dateTimeStart) = " + "\'" + (startTime.getYear() + 1900) + "-"
				+ (startTime.getMonth() + 1) + "-" + startTime.getDate() + "\'" + ")";

		try {
			trans.begin();

			TypedQuery<Lab> query = em.createQuery(statement, Lab.class);
			labs.addAll(query.getResultList());

			trans.commit();
		} catch (Exception e) {
			if (trans != null) {
				trans.rollback();
			}
			e.printStackTrace();
		} finally {
			if (em != null) {
				em.close();
				emf.close();
			}
		}

		return labs;
	}

	/**
	 * This method returns an ArrayList of PcReservation objects for all existing
	 * PcReservations. Method accepts string to search for PcReservations in a
	 * specific location. If location is null, returns an empty ArrayList.
	 * 
	 * @param location
	 * @return ArrayList<PcReservation>
	 */
	public static ArrayList<PcReservation> getAllPcReservations(String startDate, String endDate, String location) {
		ArrayList<PcReservation> reservations = new ArrayList<>();
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("db");
		EntityManager em = emf.createEntityManager();
		EntityTransaction trans = em.getTransaction();

		Date curD = new Date();

		String curDate = "'" + (curD.getYear() + 1900) + "-" + (curD.getMonth()+1) + "-" + curD.getDate() + "'";
		String curTime;
		//String curTime = "'" + curD.getHours() + ":" + curD.getMinutes() + ":" + curD.getSeconds() + "'";
		if(curD.getHours() < 10){
			curTime = "'0" + curD.getHours();
		}else{
			curTime = "'" + curD.getHours();
		}
		
		if(curD.getMinutes() < 10){
			curTime += ":0" + curD.getMinutes();
		}else{
			curTime += ":"+ curD.getMinutes();
		}
		
		if(curD.getSeconds() < 10){
			curTime += ":0" + curD.getSeconds() + "'";
		}else{
			curTime += ":" + curD.getSeconds() + "'";
		}
		

		try {
			trans.begin();

			String statement = "select distinct pr from pc_reservations pr, pc_info pc, lab lb";

			if (location != null)
				statement += " where pr.pcID = pc.pcID and pc.locationID = lb.locationID and lb.building like '%"
						+ location + "%' and";
			else
				statement += " where";

			if (startDate != null && endDate != null) {
				statement += " date(pr.dateTimeStart) between date(" + startDate + ") and date(" + endDate + ")"
						+ " and time(pr.dateTimeStart) < time(" + curTime + ")";
			} else {
				statement += " date(pr.dateTimeStart) <= date(" + curDate + ")" + " or time(pr.dateTimeStart) >= time("
						+ curTime + ")";
			}

			statement += " order by reserveTime";

			System.out.println(statement);
			TypedQuery<PcReservation> query = em.createQuery(statement, PcReservation.class);
			reservations.addAll(query.getResultList());

			trans.commit();
		} catch (Exception e) {
			if (trans != null) {
				trans.rollback();
			}
			e.printStackTrace();
		} finally {
			em.close();
			emf.close();
		}
		return reservations;
	}

	/**
	 * This method returns an ArrayList of LabReservation objects for all existing
	 * LabReservations. Method accepts string to search for LabReservation in a a
	 * specific location. If location is null, returns an empty ArrayList.
	 * 
	 * @param location
	 * @return ArrayList<LabReservation>
	 */
	public static ArrayList<LabReservation> getAllLabReservations(String startDate, String endDate, String location) {
		ArrayList<LabReservation> reservations = new ArrayList<>();
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("db");
		EntityManager em = emf.createEntityManager();
		EntityTransaction trans = em.getTransaction();

		Date curD = new Date();

		//String cur = "'" + (curD.getYear() + 1900) + "-" + curD.getMonth() + "-" + curD.getDate() + " "
				//+ curD.getHours() + ":" + curD.getMinutes() + ":" + curD.getSeconds() + "'";

		String curDate = "'" + (curD.getYear() + 1900) + "-" + (curD.getMonth()+1) + "-" + curD.getDate() + "'";
		String curTime;
		if(curD.getHours() < 10){
			curTime = "'0" + curD.getHours();
		}else{
			curTime = "'" + curD.getHours();
		}
		
		if(curD.getMinutes() < 10){
			curTime += ":0" + curD.getMinutes();
		}else{
			curTime += ":"+ curD.getMinutes();
		}
		
		if(curD.getSeconds() < 10){
			curTime += ":0" + curD.getSeconds() + "'";
		}else{
			curTime += ":" + curD.getSeconds() + "'";
		}
		
		try {
			trans.begin();

			String statement = "select distinct lr from lab_reservations lr, lab lb";

			if (location != null)
				statement += " where lr.locationID = lb.locationID and lb.building like '%" + location + "%' and";
			else
				statement += " where";

			if (startDate != null && endDate != null) {
				statement += " date(lr.dateTimeStart) between date(" + startDate + ") and date(" + endDate + ")"
						+ " and time(lr.dateTimeStart) < time(" + curTime + ")";
			} else {
				statement += " date(lr.dateTimeStart) <= date(" + curDate + ")" + " or time(lr.dateTimeStart) >= time("
						+ curTime + ")";
			}

			statement += " order by reserveTime";

			System.out.println(statement);
			TypedQuery<LabReservation> query = em.createQuery(statement, LabReservation.class);
			reservations.addAll(query.getResultList());

			trans.commit();
		} catch (Exception e) {
			if (trans != null) {
				trans.rollback();
			}
			e.printStackTrace();
		} finally {
			em.close();
			emf.close();
		}
		return reservations;
	}

	/**
	 * This method inserts a new PcReservation into the database.
	 * 
	 * @param newReservation
	 *            - new PcReservation
	 */
	public static void addPcReservation(PcReservation newReservation) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("db");
		EntityManager em = emf.createEntityManager();
		EntityTransaction trans = em.getTransaction();

		try {

			trans.begin();
			em.persist(newReservation);
			trans.commit();

		} catch (Exception e) {
			if (trans != null) {
				trans.rollback();
			}
			e.printStackTrace();
		}

		em.close();
		emf.close();
	}

	/**
	 * This method inserts a new LabReservation into the database.
	 * 
	 * @param newReservation
	 *            - new LabReservation
	 */
	public static void addLabReservation(LabReservation newReservation) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("db");
		EntityManager em = emf.createEntityManager();
		EntityTransaction trans = em.getTransaction();

		try {
			trans.begin();
			em.persist(newReservation);
			trans.commit();
		} catch (Exception e) {
			if (trans != null) {
				trans.rollback();
			}
			e.printStackTrace();
		}
		// close connections
		em.close();
		emf.close();
	}

	/**
	 * This method deletes a PcReservation in the database based on a
	 * pcreservationid.
	 * 
	 * @param id
	 *            - pcreservationid
	 */
	public static void removePcReservation(int id) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("db");
		EntityManager em = emf.createEntityManager();
		EntityTransaction trans = em.getTransaction();

		try {
			trans.begin();

			PcReservation pr = em.find(PcReservation.class, id);
			em.remove(pr);

			trans.commit();
		} catch (Exception e) {
			if (trans != null) {
				trans.rollback();
			}
			e.printStackTrace();
		}
		em.close();
		emf.close();
	}

	/**
	 * This method deletes a LabReservation in the database based on a
	 * labreservationid.
	 * 
	 * @param id
	 *            - labreservationid
	 */
	public static void removeLabReservation(int id) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("db");
		EntityManager em = emf.createEntityManager();
		EntityTransaction trans = em.getTransaction();

		try {
			trans.begin();

			LabReservation lr = em.find(LabReservation.class, id);
			em.remove(lr);

			trans.commit();
		} catch (Exception e) {
			if (trans != null) {
				trans.rollback();
			}
			e.printStackTrace();
		}
		em.close();
		emf.close();
	}

	/**
	 * Method toggles the adminConfirmed status of a PcReservation
	 * 
	 * @param id
	 */
	public static void confirmPcReservation(int id) {
		PcReservation reservation = null;

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("db");
		EntityManager em = emf.createEntityManager();
		EntityTransaction trans = em.getTransaction();

		trans.begin();
		reservation = em.find(PcReservation.class, id);
		if (reservation.isAdminConfirmed())
			reservation.setAdminConfirmed(false);
		else
			reservation.setAdminConfirmed(true);
		trans.commit();

		em.close();
		emf.close();
	}

	/**
	 * Method toggles the adminConfirmed status of a LabReservation
	 * 
	 * @param id
	 */
	public static void confirmLabReservation(int id) {
		LabReservation reservation = null;

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("db");
		EntityManager em = emf.createEntityManager();
		EntityTransaction trans = em.getTransaction();

		trans.begin();
		reservation = em.find(LabReservation.class, id);
		if (reservation.isAdminConfirmed())
			reservation.setAdminConfirmed(false);
		else
			reservation.setAdminConfirmed(true);
		trans.commit();

		em.close();
		emf.close();
	}

	public static ArrayList<String> getReservationDates() {
		ArrayList<String> dates = new ArrayList<>();
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("db");
		EntityManager em = emf.createEntityManager();
		EntityTransaction trans = em.getTransaction();

		try {
			trans.begin();

			String statement = "select distinct DATE(lr.dateTimeEnd)\r\n" + "from lab_reservations lr \r\n" + "\r\n"
					+ "union \r\n" + "\r\n" + "select distinct DATE(pr.dateTimeEnd)\r\n" + "from pc_reservations pr";

			TypedQuery<String> query = em.createQuery(statement, String.class);
			dates.addAll(query.getResultList());

			trans.commit();
		} catch (Exception e) {
			if (trans != null) {
				trans.rollback();
			}
			e.printStackTrace();
		} finally {
			em.close();
			emf.close();
		}

		for (String s : dates) {
			String date[] = s.split("-");
			s = date[1] + "/" + date[2] + "/" + date[0];
		}

		return dates;
	}

	public Lab findLab(String location, String name) {
		Lab lab = null;
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("db");
		EntityManager em = emf.createEntityManager();
		EntityTransaction trans = em.getTransaction();

		try {
			trans.begin();

			String statement = "from lab where building = " + location + " and name like %" + name + "%";

			TypedQuery<Lab> query = em.createQuery(statement, Lab.class);
			lab = query.getResultList().get(0);

			trans.commit();
		} catch (Exception e) {
			if (trans != null) {
				trans.rollback();
			}
			e.printStackTrace();
		} finally {
			em.close();
			emf.close();
		}


		return lab;
	}

}
