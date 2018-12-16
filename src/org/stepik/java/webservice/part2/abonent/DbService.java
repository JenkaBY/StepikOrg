package org.stepik.java.webservice.part2.abonent;

import org.stepik.java.webservice.part2.Address;
import org.stepik.java.webservice.part2.MessageSystem;
import org.stepik.java.webservice.part2.model.Account;
import testCases.stepic.HW07;

public class DbService implements Abonent, Runnable {
    private Address address;
    protected MessageSystem messageSystem;

    public DbService(Address address, MessageSystem messageSystem) {
        this.address = address;
        this.messageSystem = messageSystem;
    }

    @Override
    public Address getAddress() {
        return address;
    }

    public Account auth(String name, String password, String sessionId) {
        return new Account(name, password);
    }

    @Override
    public void run() {
        while (true) {
            try {
                HW07 hw07 = new HW07();
                messageSystem.execForAbonent(this);
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
