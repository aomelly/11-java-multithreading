package com.example.task01;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class LuckyTest {
    @Test
    void findsEveryLuckyTicketOnceInOrder() throws InterruptedException {
        List<Integer> tickets = Lucky.findLuckyTickets();

        assertEquals(55251, tickets.size());
        assertEquals(1001, tickets.getFirst());
        assertEquals(999999, tickets.getLast());

        int previous = 0;
        for (int ticket : tickets) {
            assertTrue(ticket > previous);
            assertEquals(digitSum(ticket / 1000), digitSum(ticket % 1000));
            previous = ticket;
        }
    }

    private static int digitSum(int number) {
        int sum = 0;
        while (number > 0) {
            sum += number % 10;
            number /= 10;
        }
        return sum;
    }
}
