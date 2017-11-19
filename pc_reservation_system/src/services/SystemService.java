package services;

import java.util.ArrayList;
import java.util.Date;
import java.sql.Timestamp;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import beans.*;

public class SystemService {

	public static User getUser(int id){
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
	
	public static ArrayList<Pc> getAllFreePcs(Date date, 
												Date startTime, 
												Date endTime,
												String building,
												String room) {
		ArrayList<Pc> pcs = new ArrayList<>();
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("db");
		EntityManager em = emf.createEntityManager();
		EntityTransaction trans = em.getTransaction();
		
		String statement = "select pc from pc_info pc, lab lb";
		
		statement += " where pc.isAvailable = 1";
			
		statement += " and pc.locationID = lb.locationID and lb.isAvailable = 1";
		
		if(building != null) {
			statement += " and lb.building = '" + building + "'";
		}
		
		if(room != null) {
			 statement += " and lb.name = '" + room + "'";
		}
		
		statement += " and pc.pcID not in (select pc.pcID from pc_info pc, pc_reservations pr "
				+ "where pc.pcID = pr.pcID and HOUR(pr.dateTimeStart) = " + startTime.getHours()
					+ " and HOUR(pr.dateTimeEnd) = " + endTime.getHours() 
					+ " and DATE(pr.dateTimeStart) = " + startTime.getDate() + ")";
		
		statement += " and pc.locationID = lb.locationID and lb.locationID not in"
				+ "(select lb.locationID from pc_reservations pr, pc_info pc, lab lb"
				+ " where pr.eventName != 'none' and pr.pcID = pc.pcID and pc.locationID = lb.locationID"
				+ " and HOUR(pr.dateTimeStart) = " + startTime.getHours() 
				+ " and HOUR(pr.dateTimeEnd) = " + endTime.getHours()
				+ " and DATE(pr.dateTimeStart) = " + startTime.getDate() + ")";
		
		
		try{
			trans.begin();
			
			TypedQuery<Pc> query = em.createQuery(statement, Pc.class);
			pcs.addAll(query.getResultList());
			
			trans.commit();
		}catch(Exception e){
			if(trans!=null){
				trans.rollback();
			}
			e.printStackTrace();
		}finally{
			if(em!=null){
				em.close(); 
				emf.close();
			}
		}
		return pcs;
	}
	
	public static ArrayList<Lab> getAllFreeLabs(Date date, 
			Date startTime, 
			Date endTime){
		ArrayList<Lab> labs = new ArrayList();
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("db");
		EntityManager em = emf.createEntityManager();
		EntityTransaction trans = em.getTransaction();
		
		String statement = "select lb from lab lb";
		
		statement += " where lb.isAvailable = 1";
		
		statement += " and lb.locationID not in"
				+ "(select lb.locationID from pc_reservations pr, pc_info pc, lab lb"
				+ " where pr.eventName != 'none' and pr.pcID = pc.pcID and pc.locationID = lb.locationID"
				+ " and HOUR(pr.dateTimeStart) = " + startTime.getHours() 
				+ " and HOUR(pr.dateTimeEnd) = " + endTime.getHours() 
				+ " and DATE(pr.dateTimeStart) = " + startTime.getDate() + ")";
		
		try{
			trans.begin();
			
			TypedQuery<Lab> query = em.createQuery(statement, Lab.class);
			labs.addAll(query.getResultList());
			
			trans.commit();
		}catch(Exception e){
			if(trans!=null){
				trans.rollback();
			}
			e.printStackTrace();
		}finally{
			if(em!=null){
				em.close(); 
				emf.close();
			}
		}

		return labs;
	}
	
	
	
	
	public static ArrayList<Pc> getAllPcs() {
		ArrayList<Pc> pcs = new ArrayList<>();
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("db");
		EntityManager em = emf.createEntityManager();
		EntityTransaction trans = em.getTransaction();
		
		try{
			trans.begin();
			
			TypedQuery<Pc> query = em.createQuery("from pc_info", Pc.class);
			pcs.addAll(query.getResultList());
			
			trans.commit();
		}catch(Exception e){
			if(trans!=null){
				trans.rollback();
			}
			e.printStackTrace();
		}finally{
			em.close(); 
			emf.close();
		}
		return pcs;
	}
	
	public static ArrayList<PcReservation> getUserReservations(int userID){
		ArrayList<PcReservation> reservations = new ArrayList<>();
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("db");
		EntityManager em = emf.createEntityManager();
		EntityTransaction trans = em.getTransaction();
		
		String statement = "select pr from pc_reservations pr where pr.userID = " + userID;
		
		try{
			trans.begin();
			
			TypedQuery<PcReservation> query = em.createQuery(statement, PcReservation.class);
			reservations.addAll(query.getResultList());
			
			trans.commit();
		}catch(Exception e){
			if(trans!=null){
				trans.rollback();
			}
			e.printStackTrace();
		}finally{
			if(em!=null){
				em.close(); 
				emf.close();
			}
		}
		return reservations;
	}
	
	public static Lab getLabOfPc(int pcID) {
		Lab lab = null;
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("db");
		EntityManager em = emf.createEntityManager();
		EntityTransaction trans = em.getTransaction();
		
		String statement = "select lb from lab lb, pc_info pc where "
				+ "pc.pcID = " + pcID + " and lb.locationID = pc.locationID";
		
		try{
			trans.begin();
			
			TypedQuery<Lab> query = em.createQuery(statement, Lab.class);
			lab = query.getResultList().get(0);
			
			trans.commit();
		}catch(Exception e){
			if(trans!=null){
				trans.rollback();
			}
			e.printStackTrace();
		}finally{
			if(em!=null){
				em.close(); 
				emf.close();
			}
		}
		return lab;
	}
	
	public static ArrayList<Pc> getAllPcs(int labID) {
		ArrayList<Pc> pcs = new ArrayList<>();
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("db");
		EntityManager em = emf.createEntityManager();
		EntityTransaction trans = em.getTransaction();
		
		try{
			trans.begin();
			
			TypedQuery<Pc> query = em.createQuery("from pc_info where locationID = " + labID, Pc.class);
			pcs.addAll(query.getResultList());
			
			trans.commit();
		}catch(Exception e){
			if(trans!=null){
				trans.rollback();
			}
			e.printStackTrace();
		}finally{
			em.close(); 
			emf.close();
		}
		return pcs;
	}
	
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
	
	public static ArrayList<PcReservation> getAllSingleReservations(String location) {
		ArrayList<PcReservation> reservations = new ArrayList<>();
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("db");
		EntityManager em = emf.createEntityManager();
		EntityTransaction trans = em.getTransaction();
		
		try{
			trans.begin();
			
			String statement = "select distinct pr from pc_reservations pr, pc_info pc, lab lb"
					+ " where pr.eventName = 'none'";
			
			if(location != null) 
				statement += " and pr.pcID = pc.pdID and pc.locationID = lb.locationID and lb.building like '%"+ location +"%'";
					
			statement += " order by reserveTime";
			
			TypedQuery<PcReservation> query = em.createQuery(statement, PcReservation.class);
			reservations.addAll(query.getResultList());
			
			trans.commit();
		}catch(Exception e){
			if(trans!=null){
				trans.rollback();
			}
			e.printStackTrace();
		}finally{
			em.close(); 
			emf.close();
		}
		return reservations;
	}
	
	public static ArrayList<PcReservation> getAllLabReservations(String location) {
		ArrayList<PcReservation> reservations = new ArrayList<>();
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("db");
		EntityManager em = emf.createEntityManager();
		EntityTransaction trans = em.getTransaction();
		
		try{
			trans.begin();
			
			String statement = "select distinct pr from pc_reservations pr, pc_info pc, lab lb"
					+ " where pr.eventName != 'none'";
			
			if(location != null) 
				statement += " and pr.pcID = pc.pdID and pc.locationID = lb.locationID and lb.building like '% "+ location +"%'";
					
			statement += " group by pr.eventName order by reserveTime";
			
			TypedQuery<PcReservation> query = em.createQuery(statement, PcReservation.class);
			reservations.addAll(query.getResultList());
			
			trans.commit();
		}catch(Exception e){
			if(trans!=null){
				trans.rollback();
			}
			e.printStackTrace();
		}finally{
			em.close(); 
			emf.close();
		}
		return reservations;
	}
	
	
	
	public static ArrayList<Lab> getAllLabs(String building) {
		ArrayList<Lab> labs = new ArrayList<>();
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("db");
		EntityManager em = emf.createEntityManager();
		EntityTransaction trans = em.getTransaction();
		
		try{
			trans.begin();
			
			String statement = "from lab lb where lb.building = " + building;
			
			TypedQuery<Lab> query = em.createQuery(statement, Lab.class);
			labs.addAll(query.getResultList());
			
			for(Lab l : labs) {
				l.setComputers(SystemService.getAllPcs(l.getLocationID()));
			}
			
			trans.commit();
		}catch(Exception e){
			if(trans!=null){
				trans.rollback();
			}
			e.printStackTrace();
		}finally{
			em.close(); 
			emf.close();
		}
		return labs;
	}
	
	public static void addReservation(PcReservation newReservation) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("db");
		EntityManager em = emf.createEntityManager();
		EntityTransaction trans = em.getTransaction();
		
		try{
			trans.begin();
			em.persist(newReservation);
			trans.commit();
		}catch(Exception e){
			if(trans!=null){
				trans.rollback();
			}
			e.printStackTrace();
		}
		// close connections
		em.close(); 
		emf.close();
	}
	
	public static void removeReservation(int id) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("db");
		EntityManager em = emf.createEntityManager();
		EntityTransaction trans = em.getTransaction();
		
		try{
			trans.begin();
			
			PcReservation pr = em.find(PcReservation.class, id);
			em.remove(pr);
			
			trans.commit();
		}catch(Exception e){
			if(trans!=null){
				trans.rollback();
			}
			e.printStackTrace();
		}
		em.close(); 
		emf.close();
	}
	
}
