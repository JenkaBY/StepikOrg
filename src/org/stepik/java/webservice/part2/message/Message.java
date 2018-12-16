package org.stepik.java.webservice.part2.message;

import org.stepik.java.webservice.part2.Address;
import org.stepik.java.webservice.part2.abonent.Abonent;

public abstract class Message {
    private Address from;
    private Address to;

    public Message(Address from, Address to) {
        this.from = from;
        this.to = to;
    }

    public Address getFrom() {
        return from;
    }

    public void setFrom(Address from) {
        this.from = from;
    }

    public Address getTo() {
        return to;
    }

    public void setTo(Address to) {
        this.to = to;
    }

    public abstract void exec(Abonent abonent);
}
