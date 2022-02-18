package ru.netology.manager;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Ticket;
import ru.netology.repository.TicketRepository;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class TicketManagerTest {
    static TicketRepository repo = new TicketRepository();
    static TicketManager manager = new TicketManager(repo);
    static Ticket ticket1 = new Ticket(1, 59064, "IKT", "BKK", 330);
    static Ticket ticket2 = new Ticket(2, 26808, "DMK", "DPS", 210);
    static Ticket ticket3 = new Ticket(3, 13602, "JKT", "KUL", 220);
    static Ticket ticket4 = new Ticket(4, 48472, "OVB", "ICN", 320);
    static Ticket ticket5 = new Ticket(5, 37165, "VVO", "IKT", 270);

    @BeforeAll
    static void setUp() {
        manager.add(ticket1);
        manager.add(ticket2);
        manager.add(ticket3);
        manager.add(ticket4);
        manager.add(ticket5);
    }

    @Test
    void shouldSortByPrice() {
        Ticket[] expected = {ticket3, ticket2, ticket5, ticket4, ticket1};
        Ticket[] actual = repo.findAll();
        Arrays.sort(actual);

        assertArrayEquals(expected, actual);
    }

    @Test
    void ShouldFindFlight() {
        Ticket[] expected = {ticket3,};
        Ticket[] actual = manager.findAll("JKT", "KUL");

        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldFindNoOneFlight() {
        Ticket[] expected = {};
        Ticket[] actual = manager.findAll("IKT", "KUL");

        assertArrayEquals(expected, actual);
    }
}