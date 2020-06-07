package db;

import model.Dozent;
import model.Kunde;
import model.Kurs;
import model.Kurstyp;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Michael König
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class KurssystemServiceTest {
    private static KurssystemService service;

    @BeforeAll
    static void beforeAll() {
        service = KurssystemService.getINSTANCE();
    }

    /**
     * Erwartet 6 Kunden in der DB
     */
    @Test
    @Order(1)
    void testGetKunden() {
        assertEquals(6, service.getKunden().size());
    }

    /**
     * Erwartet 7 Dozenten in der DB
     */
    @Test
    @Order(2)
    void testGetDozenten() {
        assertEquals(7, service.getDozenten().size());
    }

    /**
     * Erwartet 3 Kurstypen in der DB
     */
    @Test
    @Order(3)
    void testGetKurstypen() {
        assertEquals(3, service.getKurstypen().size());
    }

    /**
     * Erwartet 6 Kurse in der DB
     */
    @Test
    @Order(4)
    void testGetKurse() {
        assertEquals(6, service.getKurse().size());
    }

    /**
     * Fügt einen Kunden in die DB ein und erwartet, dass die Anzahl um 1 steigt
     */
    @Test
    @Order(5)
    void testInsertKunde() {
        long oldCount = service.getKunden().size();
        service.insertKunde(new Kunde("König", "Michael"));
        long newCount = service.getKunden().size();
        assertEquals(oldCount + 1, newCount);
    }

    /**
     * Löscht einen Kunden aus der DB und erwartet, dass die Anzahl um 1 sinkt
     */
    @Test
    @Order(6)
    void testDeleteKunde() {
        Kunde koenig = service.getKunden().stream().filter(kunde -> kunde.getKundeZuname().equals("König")).findFirst().get();
        long oldCount = service.getKunden().size();
        service.deleteKunde(koenig);
        long newCount = service.getKunden().size();
        assertEquals(oldCount - 1, newCount);
    }

    /**
     * Fügt einen Kurs in die DB ein und erwartet, dass die Anzahl um 1 steigt
     */
    @Test
    @Order(7)
    void insertKursTyp() {
        long oldCount = service.getKurstypen().size();
        service.insertKurstyp(new Kurstyp("G", "Geschichte"));
        long newCount = service.getKurstypen().size();
        assertEquals(oldCount + 1, newCount);
    }

    /**
     * Löscht einen Kurstyp aus der DB und erwartet, dass die Anzahl um 1 sinkt
     */
    @Test
    @Order(8)
    void testDeleteKurstyp() {
        Kurstyp geschichte = service.getKurstypen().stream().filter(kurstyp -> kurstyp.getTypId().equals("G")).findFirst().get();
        long oldCount = service.getKurstypen().size();
        service.deleteKurstyp(geschichte);
        long newCount = service.getKurstypen().size();
        assertEquals(oldCount - 1, newCount);
    }

    /**
     * Fügt einen Kurs in die DB ein und erwartet, dass die Anzahl um 1 steigt
     */
    @Test
    @Order(9)
    void testInsertKurs() {
        long oldCount = service.getKurse().size();
        Kurstyp typP = service.getKurstypen().stream().filter(kurstyp -> kurstyp.getTypId().equals("P")).findFirst().get();
        Dozent dozent = service.getDozenten().get(0);
        service.insertKurs(new Kurs(typP, "Rust", null, dozent));
        long newCount = service.getKurse().size();
        assertEquals(oldCount + 1, newCount);
    }

    /**
     * Erwartet 6 Kunden im Kurs OOP mit Java
     */
    @Test
    @Order(10)
    void testGetKundenFromKurs() {
        Kurs kurs = service.getKurse().stream().filter(k -> k.getKursBezeichnung().equals("Objektorientierte Programmierung mit Java")).findFirst().get();
        assertEquals(3, service.getKundenFromKurs(kurs).size());
    }

    /**
     * Bucht für einen Kunden einen Kurs und erwartet, dass der Kurs nun einen Kunden mehr eingeschrieben hat
     */
    @Test
    @Order(11)
    void testBucheKurs() {
        Kurs kurs = service.getKurse().stream().filter(k -> k.getKursBezeichnung().equals("Rust")).findFirst().get();
        long oldCount = service.getKundenFromKurs(kurs).size();
        Kunde kunde = service.getKunden().stream().filter(k -> k.getKundeZuname().equals("Bauer")).findFirst().get();
        service.bucheKurs(kunde, kurs);
        long newCount = service.getKundenFromKurs(kurs).size();
        assertEquals(oldCount + 1, newCount);
    }

    /**
     * Storniert für einen Kunden einen Kurs und erwartet, dass der Kurs nun einen Kunden weniger eingeschrieben hat
     */
    @Test
    @Order(12)
    void testStorniereKurs() {
        Kurs kurs = service.getKurse().stream().filter(k -> k.getKursBezeichnung().equals("Rust")).findFirst().get();
        long oldCount = service.getKundenFromKurs(kurs).size();
        Kunde kunde = service.getKunden().stream().filter(k -> k.getKundeZuname().equals("Bauer")).findFirst().get();
        service.storniereKurs(kunde, kurs);
        long newCount = service.getKundenFromKurs(kurs).size();
        assertEquals(oldCount - 1, newCount);
    }

    @AfterAll
    static void afterAll() {
        service.close();
    }


}
