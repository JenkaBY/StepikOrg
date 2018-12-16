package org.stepik.java.webservice.part2.message;

import org.stepik.java.webservice.part2.Address;
import org.stepik.java.webservice.part2.abonent.DbService;

public class MessageAuthenticate extends MessageToDB {
    private String name;
    private String password;
    private String sessionId;

    public MessageAuthenticate(Address from, Address to, String name, String password, String sessionId) {
        super(from, to);
        this.name = name;
        this.password = password;
        this.sessionId = sessionId;
    }

    @Override
    public void execDbService(DbService dbService) {

    }
}
