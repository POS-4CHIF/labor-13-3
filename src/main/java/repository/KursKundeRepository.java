package repository;

import db.JPAUtil;
import db.KursDBException;
import model.Kunde;
import model.Kurs;
import model.KursKunde;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * @author Michael König
 */
public class KursKundeRepository implements AutoCloseable {

    private final static KursKundeRepository INSTANCE = new KursKundeRepository();

    // private Constructor
    private KursKundeRepository() {
    }

    public static KursKundeRepository getINSTANCE() {
        return INSTANCE;
    }

    public boolean persist(KursKunde entity) throws KursDBException {
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

    public boolean remove(KursKunde entity) throws KursDBException {
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

    public boolean bucheKurs(Kunde kunde, Kurs kurs) throws KursDBException {
        EntityManager em = JPAUtil.getEMF().createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(new KursKunde(kunde.getKundeId(), kurs.getKursId()));
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

    public boolean stoniereKurs(Kunde kunde, Kurs kurs) throws KursDBException {
        EntityManager em = JPAUtil.getEMF().createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.remove(new KursKunde(kunde.getKundeId(), kurs.getKursId()));
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

    public KursKunde find(int kursId, int kundeId) throws KursDBException {
        EntityManager em = JPAUtil.getEMF().createEntityManager();
        try {
            TypedQuery<KursKunde> q = em.createQuery("select kk from KursKunde kk where kursId = :kursId and kundeId = :kundeId", KursKunde.class);
            q.setParameter("kursId", kursId);
            q.setParameter("kundeId", kundeId);
            KursKunde result = q.getSingleResult();
            em.close();
            return result;
        } catch (Exception ex) {
            throw new KursDBException(ex.getMessage());
        } finally {
            em.close();
        }

    }

    public List<KursKunde> findAll() throws KursDBException {
        EntityManager em = JPAUtil.getEMF().createEntityManager();
        try {
            TypedQuery<KursKunde> q = em.createQuery("select kk from KursKunde kk", KursKunde.class);
            List<KursKunde> result = q.getResultList();
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
