package db;

import model.*;
import repository.*;

import java.util.List;

/**
 * @author Michael König
 */
public class KurssystemService implements IKurssystemService, AutoCloseable {

    private final static KurssystemService INSTANCE = new KurssystemService();
    private final static DozentRepository dozentRepository = DozentRepository.getINSTANCE();
    private final static KundeRepository kundeRepository = KundeRepository.getINSTANCE();
    private final static KurstypRepository kurstypRepository = KurstypRepository.getINSTANCE();
    private final static KursRepository kursRepository = KursRepository.getINSTANCE();
    private final static KursKundeRepository kurskundeRepository = KursKundeRepository.getINSTANCE();

    // private Constructor
    private KurssystemService() {
    }

    public static KurssystemService getINSTANCE() {
        return INSTANCE;
    }

    @Override
    public List<Kunde> getKunden() throws KursDBException {
        return kundeRepository.findAll();
    }

    @Override
    public void insertKunde(Kunde k) throws KursDBException {
        kundeRepository.persist(k);
    }

    @Override
    public void deleteKunde(Kunde k) throws KursDBException {
        kundeRepository.remove(k);
    }

    @Override
    public List<Dozent> getDozenten() throws KursDBException {
        return dozentRepository.findAll();
    }

    @Override
    public List<Kurstyp> getKurstypen() throws KursDBException {
        return kurstypRepository.findAll();
    }

    @Override
    public List<Kurs> getKurse() throws KursDBException {
        return kursRepository.findAll();
    }

    @Override
    public void insertKurstyp(Kurstyp kt) throws KursDBException {
        kurstypRepository.persist(kt);
    }

    @Override
    public void deleteKurstyp(Kurstyp kt) throws KursDBException {
        kurstypRepository.remove(kt);
    }

    @Override
    public void insertKurs(Kurs kurs) throws KursDBException {
        kursRepository.persist(kurs);
    }

    @Override
    public List<Kunde> getKundenFromKurs(Kurs kurs) throws KursDBException {
        return kundeRepository.findByKurs(kurs);
    }

    @Override
    public void bucheKurs(Kunde kunde, Kurs kurs) throws KursDBException {
        kurskundeRepository.bucheKurs(kunde, kurs);
    }

    @Override
    public void storniereKurs(Kunde kunde, Kurs kurs) throws KursDBException {
        kurskundeRepository.stoniereKurs(kunde, kurs);
    }

    @Override
    public void close() {
        dozentRepository.close();
        kundeRepository.close();
        kurstypRepository.close();
        kursRepository.close();
        kurskundeRepository.close();
    }
}
