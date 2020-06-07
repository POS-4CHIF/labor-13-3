package db;

import model.Kunde;
import org.junit.jupiter.api.*;

import javax.persistence.EntityManager;

import java.time.LocalDate;

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

    @Test
    @Order(1)
    void testGetKunden() {
        assertEquals(6, service.getKunden().size());
    }

    @Test
    @Order(2)
    void testGetDozenten() {
        assertEquals(7, service.getDozenten().size());
    }

    @Test
    @Order(3)
    void testGetKurstypen() {
        assertEquals(3, service.getKurstypen().size());
    }

    @Test
    @Order(4)
    void testGetKurse() {
        assertEquals(6, service.getKurse().size());
    }

    @Test
    @Order(5)
    void testInsertKunde() {
        long oldCount = service.getKunden().size();
        service.insertKunde(new Kunde("König", "Michael"));
        long newCount = service.getKunden().size();
        assertEquals(oldCount+1, newCount);


    }

    @Test
    @Order(6)
    void testDeleteKunde() {
        Kunde koenig = service.getKunden().stream().filter(kunde -> kunde.getKundeZuname().equals("König")).findFirst().get();
        long oldCount = service.getKunden().size();
        service.deleteKunde(koenig);
        long newCount = service.getKunden().size();
        assertEquals(oldCount-1, newCount);
    }

    @AfterAll
    static void afterAll() {
        service.close();
    }
}
