package repository;

import db.JPAUtil;
import db.KursDBException;
import model.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * @author Michael König
 */
public class KundeRepository implements AutoCloseable {

    private final static KundeRepository INSTANCE = new KundeRepository();

    // private Constructor
    private KundeRepository() {
    }

    public static KundeRepository getINSTANCE() {
        return INSTANCE;
    }

    public void persist(Kunde entity) throws KursDBException {
        if(entity.getKundeId() != null)
            throw new IllegalArgumentException("Entity already has an ID set!");
        EntityManager em = JPAUtil.getEMF().createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(entity);
            tx.commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            if (tx.isActive()) {
                tx.rollback();
            }
            throw new KursDBException(ex.getMessage());
        } finally {
            em.close();
        }
    }

    public void remove(Kunde entity) throws KursDBException {
        EntityManager em = JPAUtil.getEMF().createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.remove(em.merge(entity));
            tx.commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            if (tx.isActive()) {
                tx.rollback();
            }
            throw new KursDBException(ex.getMessage());
        } finally {
            em.close();
        }
    }

    public Kunde find(int id) throws KursDBException {
        EntityManager em = JPAUtil.getEMF().createEntityManager();
        try {
            TypedQuery<Kunde> q = em.createQuery("select k from Kunde k where kundeId = :id", Kunde.class);
            q.setParameter("id", id);
            Kunde result = q.getSingleResult();
            em.close();
            return result;
        } catch (Exception ex) {
            throw new KursDBException(ex.getMessage());
        } finally {
            em.close();
        }
    }

    public List<Kunde> findAll() throws KursDBException {
        EntityManager em = JPAUtil.getEMF().createEntityManager();
        try {
            TypedQuery<Kunde> q = em.createQuery("select k from Kunde k", Kunde.class);
            List<Kunde> result = q.getResultList();
            em.close();
            return result;
        } catch (Exception ex) {
            throw new KursDBException(ex.getMessage());
        } finally {
            em.close();
        }
    }

    public List<Kunde> findByKurs(Kurs kurs) throws KursDBException {
        if(kurs == null || kurs.getKursId() == null) {
            throw new IllegalArgumentException("Kurs and Kurs ID may not be null");
        }
        EntityManager em = JPAUtil.getEMF().createEntityManager();
        try {
            TypedQuery<Kunde> q = em.createQuery("select kunde from KursKunde k inner join Kunde kunde on k.kundeId = kunde.kundeId where k.kursId = :kursId", Kunde.class);
            q.setParameter("kursId", kurs.getKursId());
            List<Kunde> result = q.getResultList();
            em.close();
            return result;
        } catch (Exception ex) {
            throw new KursDBException(ex.getMessage());
        } finally {
            em.close();
        }
    }


    @Override
    public void close() {
        JPAUtil.close();
    }

}
