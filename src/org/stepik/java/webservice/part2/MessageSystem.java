package org.stepik.java.webservice.part2;

import org.stepik.java.webservice.part2.abonent.Abonent;
import org.stepik.java.webservice.part2.message.Message;

import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class MessageSystem {
    private final Map<Address, ConcurrentLinkedQueue<Message>> messages = new HashMap<>();

    public void sendMessage(Message message) {
        Queue<Message> messageQueue = messages.get(message.getTo());
        messageQueue.add(message);
    }

    public void execForAbonent(Abonent abonent) {
        final Queue<Message> messageQueue = this.messages.get(abonent.getAddress());
        while (!messageQueue.isEmpty()) {
            final Message msg = messageQueue.poll();
            msg.exec(abonent);
        }
    }
}
