package repository;

import db.JPAUtil;
import db.KursDBException;
import model.Dozent;

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

    public void persist(Dozent entity) throws KursDBException {
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

    public void remove(Dozent entity) throws KursDBException {
        EntityManager em = JPAUtil.getEMF().createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.remove(entity);
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

    public Dozent find(int id) throws KursDBException {
        EntityManager em = JPAUtil.getEMF().createEntityManager();
        try {
            TypedQuery<Dozent> q = em.createQuery("select d from Dozent d where dozId = :id order by dozId", Dozent.class);
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
