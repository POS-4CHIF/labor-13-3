package repository;

import db.JPAUtil;
import db.KursDBException;
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
    public void persist(Kurstyp entity) throws KursDBException {
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

    public void remove(Kurstyp entity) throws KursDBException {
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
            TypedQuery<Kurstyp> q = em.createQuery("select k from Kurstyp k order by typId", Kurstyp.class);
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
