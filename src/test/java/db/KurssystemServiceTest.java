package db;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Michael König
 */
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

    @AfterAll
    static void afterAll() {
        service.close();
    }
}
