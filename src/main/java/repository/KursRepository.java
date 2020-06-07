package repository;

import db.JPAUtil;
import db.KursDBException;
import model.Kurs;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * @author Michael König
 */
public class KursRepository implements AutoCloseable {

    private final static KursRepository INSTANCE = new KursRepository();

    // private Constructor
    private KursRepository() {
    }

    public static KursRepository getINSTANCE() {
        return INSTANCE;
    }

    public boolean persist(Kurs entity) throws KursDBException {
        EntityManager em = JPAUtil.getEMF().createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(entity);
            tx.commit();
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            if (tx.isActive()) {
                tx.rollback();
            }
            return false;
        } finally {
            em.close();
        }
    }

    public boolean remove(Kurs entity) throws KursDBException {
        EntityManager em = JPAUtil.getEMF().createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.remove(entity);
            tx.commit();
            return true;
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

    public Kurs find(int id) throws KursDBException {
        EntityManager em = JPAUtil.getEMF().createEntityManager();
        try {
            TypedQuery<Kurs> q = em.createQuery("select k from Kurs k where kursId = :id", Kurs.class);
            q.setParameter("id", id);
            Kurs result = q.getSingleResult();
            em.close();
            return result;
        } catch (Exception ex) {
            throw new KursDBException(ex.getMessage());
        } finally {
            em.close();
        }

    }

    public List<Kurs> findAll() throws KursDBException {
        EntityManager em = JPAUtil.getEMF().createEntityManager();
        try {
            TypedQuery<Kurs> q = em.createQuery("select k from Kurs k", Kurs.class);
            List<Kurs> result = q.getResultList();
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
