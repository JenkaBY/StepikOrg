package org.stepik.java.webservice.part2.message;

import org.stepik.java.webservice.part2.Address;
import org.stepik.java.webservice.part2.abonent.Abonent;
import org.stepik.java.webservice.part2.abonent.FrontendService;

public abstract class MessageToFrontend extends Message {
    public MessageToFrontend(Address from, Address to) {
        super(from, to);
    }

    @Override
    public void exec(Abonent abonent) {
        if (abonent instanceof FrontendService) {
            execFrontendService((FrontendService) abonent);
            return;
        }
        System.out.println("Something went wrong in class " + this.getClass().getSimpleName());
    }

    public abstract void execFrontendService(FrontendService abonent);

}
