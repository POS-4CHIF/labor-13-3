package repository;

import db.JPAUtil;
import db.KursDBException;
import model.Dozent;
import model.Kurstyp;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * @author Michael König
 */
public class KurstypRepository implements AutoCloseable {

    private final static KurstypRepository INSTANCE = new KurstypRepository();

    // private Constructor
    private KurstypRepository() {
    }

    public static KurstypRepository getINSTANCE() {
        return INSTANCE;
    }

    // Speichert eine neue Station in der Datenbank
    // liefert true bei Erfolg, false bei einem Fehler
    public boolean persist(Kurstyp entity) throws KursDBException {
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

    public boolean remove(Kurstyp entity) throws KursDBException {
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

    public Kurstyp find(int id) throws KursDBException {
        EntityManager em = JPAUtil.getEMF().createEntityManager();
        try {
            TypedQuery<Kurstyp> q = em.createQuery("select k from Kurstyp k where typId = :id", Kurstyp.class);
            q.setParameter("id", id);
            Kurstyp result = q.getSingleResult();
            em.close();
            return result;
        } catch (Exception ex) {
            throw new KursDBException(ex.getMessage());
        } finally {
            em.close();
        }
    }

    public List<Kurstyp> findAll() throws KursDBException {
        EntityManager em = JPAUtil.getEMF().createEntityManager();
        try {
            TypedQuery<Kurstyp> q = em.createQuery("select k from Kurstyp k", Kurstyp.class);
            List<Kurstyp> result = q.getResultList();
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
