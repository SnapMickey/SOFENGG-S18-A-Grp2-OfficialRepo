package services;

import java.util.ArrayList;

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
		
		return pc;
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
		}
		return pcs;
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
		
		return pr;
	}
	
	public static ArrayList<PcReservation> getAllReservations() {
		ArrayList<PcReservation> reservations = new ArrayList<>();
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("db");
		EntityManager em = emf.createEntityManager();
		EntityTransaction trans = em.getTransaction();
		
		try{
			trans.begin();
			
			TypedQuery<PcReservation> query = em.createQuery("from pc_reservations order by reserveTime", PcReservation.class);
			reservations.addAll(query.getResultList());
			
			trans.commit();
		}catch(Exception e){
			if(trans!=null){
				trans.rollback();
			}
			e.printStackTrace();
		}finally{
			em.close();
		}
		return reservations;
	}
	
	public static ArrayList<Lab> getAllLabs() {
		ArrayList<Lab> labs = new ArrayList<>();
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("db");
		EntityManager em = emf.createEntityManager();
		EntityTransaction trans = em.getTransaction();
		
		try{
			trans.begin();
			
			TypedQuery<Lab> query = em.createQuery("from lab", Lab.class);
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
		}
		return labs;
	}
}
