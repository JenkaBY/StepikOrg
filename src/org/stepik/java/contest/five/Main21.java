package org.stepik.java.contest.five;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Main21 {
    /*
    *
        Make the following code below thread safe.
        Hint: explicit lock should always be explicitly unlocked.
    * */
    public static class ThreadSafe4 {
        private List<String> recipe = new ArrayList<>();
        private final Lock lock = new ReentrantLock();

        public void add(final String ingredient) {
            lock.lock();
            try {
                recipe.add(ingredient);
            } finally {
                lock.unlock();
            }
        }
    }
}
