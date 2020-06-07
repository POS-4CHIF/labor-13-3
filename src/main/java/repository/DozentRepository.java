package repository;

import db.JPAUtil;
import db.KursDBException;
import model.Dozent;
import model.Kunde;
import model.Kurstyp;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * @author Michael König
 */
public class DozentRepository implements AutoCloseable {

    private final static DozentRepository INSTANCE = new DozentRepository();

    // private Constructor
    private DozentRepository() {
    }

    public static DozentRepository getINSTANCE() {
        return INSTANCE;
    }

    public boolean persist(Dozent entity) throws KursDBException {
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

    public boolean remove(Dozent entity) throws KursDBException {
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

    public Dozent find(int id) throws KursDBException {
        EntityManager em = JPAUtil.getEMF().createEntityManager();
        try {
            TypedQuery<Dozent> q = em.createQuery("select d from Dozent d where dozId = :id", Dozent.class);
            q.setParameter("id", id);
            Dozent result = q.getSingleResult();
            em.close();
            return result;
        } catch (Exception ex) {
            throw new KursDBException(ex.getMessage());
        } finally {
            em.close();
        }

    }

    public List<Dozent> findAll() throws KursDBException {
        EntityManager em = JPAUtil.getEMF().createEntityManager();
        try {
            TypedQuery<Dozent> q = em.createQuery("select d from Dozent d", Dozent.class);
            List<Dozent> result = q.getResultList();
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
