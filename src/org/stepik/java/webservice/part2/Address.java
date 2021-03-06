package org.stepik.java.webservice.part2;

import java.util.concurrent.atomic.AtomicInteger;

public class Address {
    private final static AtomicInteger abonentIdCreator = new AtomicInteger(0);
    final private int abonentId;

    public Address() {
        abonentId = abonentIdCreator.incrementAndGet();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Address address = (Address) o;

        return abonentId == address.abonentId;
    }

    @Override
    public int hashCode() {
        return abonentId;
    }
}
