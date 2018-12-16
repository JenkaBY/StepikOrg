package org.stepik.java.webservice.part2.socket;

public class Start {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("number thread: " + Integer.parseInt(args[0]));
        PooledThreadSocketServer pooledServer = new PooledThreadSocketServer(Integer.parseInt(args[0]));
        Thread pool = new Thread(pooledServer);
//        System.out.println("Pool ran. " + LocalDateTime.now());
        pool.start();
        System.out.println("Server started");
        long oneMinute = 60 * 1000;
        pool.join(oneMinute);
//        System.out.println("Pool stopped: " + LocalDateTime.now());
        pooledServer.stop();
    }
}
