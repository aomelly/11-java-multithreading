package com.example.task01;

import java.util.ArrayList;
import java.util.List;

public class Lucky {
    private static final int LAST_TICKET = 999999;
    private static final int THREAD_COUNT = 3;

    private static class LuckyThread extends Thread {
        private final int first;
        private final int last;
        private final List<Integer> tickets = new ArrayList<>();

        LuckyThread(int first, int last) {
            this.first = first;
            this.last = last;
        }

        @Override
        public void run() {
            for (int number = first; number <= last; number++) {
                int right = number % 10 + number / 10 % 10 + number / 100 % 10;
                int left = number / 1000 % 10 + number / 10000 % 10 + number / 100000 % 10;
                if (left == right) {
                    tickets.add(number);
                }
            }
        }
    }

    static List<Integer> findLuckyTickets() throws InterruptedException {
        LuckyThread[] threads = new LuckyThread[THREAD_COUNT];
        for (int i = 0; i < threads.length; i++) {
            int first = LAST_TICKET * i / threads.length + 1;
            int last = LAST_TICKET * (i + 1) / threads.length;
            threads[i] = new LuckyThread(first, last);
            threads[i].start();
        }

        List<Integer> tickets = new ArrayList<>();
        for (LuckyThread thread : threads) {
            thread.join();
            tickets.addAll(thread.tickets);
        }
        return tickets;
    }

    public static void main(String[] args) throws InterruptedException {
        List<Integer> tickets = findLuckyTickets();
        for (int ticket : tickets) {
            System.out.println(ticket);
        }
        System.out.println("Total: " + tickets.size());
    }
}
