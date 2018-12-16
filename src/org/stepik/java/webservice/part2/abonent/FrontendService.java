package org.stepik.java.webservice.part2.abonent;

import org.stepik.java.webservice.part2.Address;
import org.stepik.java.webservice.part2.MessageSystem;

public class FrontendService implements Abonent, Runnable {
    private final Address address;
    private MessageSystem messageSystem;

    public FrontendService(MessageSystem messageSystem, Address address) {
        this.address = address;
        this.messageSystem = messageSystem;
    }

    @Override
    public Address getAddress() {
        return address;
    }

    @Override
    public void run() {
        while (true) {
            try {
                messageSystem.execForAbonent(this);
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
