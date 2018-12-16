package org.stepik.java.webservice.part2.message;

import org.stepik.java.webservice.part2.Address;
import org.stepik.java.webservice.part2.abonent.Abonent;
import org.stepik.java.webservice.part2.abonent.DbService;

public abstract class MessageToDB extends Message {
    public MessageToDB(Address from, Address to) {
        super(from, to);
    }

    @Override
    public void exec(Abonent abonent) {
        if (abonent instanceof DbService) {
            execDbService((DbService) abonent);
            return;
        }
        System.out.println("Something went wrong in class " + this.getClass().getSimpleName());
    }

    public abstract void execDbService(DbService dbService);
}
