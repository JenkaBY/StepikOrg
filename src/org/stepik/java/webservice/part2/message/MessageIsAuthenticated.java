package org.stepik.java.webservice.part2.message;

import org.stepik.java.webservice.part2.Address;
import org.stepik.java.webservice.part2.abonent.FrontendService;
import org.stepik.java.webservice.part2.model.Account;

public class MessageIsAuthenticated extends MessageToFrontend {
    private Account account;
    private String sessionId;

    public MessageIsAuthenticated(Address from, Address to, String sessionId, Account account) {
        super(from, to);
        this.account = account;
        this.sessionId = sessionId;
    }

    @Override
    public void execFrontendService(FrontendService abonent) {

    }
}
