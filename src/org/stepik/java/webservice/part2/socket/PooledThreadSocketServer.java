package org.stepik.java.webservice.part2.socket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class PooledThreadSocketServer implements Runnable {
    private ExecutorService threadPool;
    private int port = 5050;
    private boolean isStopped;
    private ServerSocket serverSocket;
    private List<Future<Stopped>> executed = new ArrayList<>(10);

    public PooledThreadSocketServer(int nThreads) {
        this.threadPool = Executors.newFixedThreadPool(nThreads);
    }

    @Override
    public void run() {
//        synchronized(this){
//            this.runningThread = Thread.currentThread();
//        }
        openServerSocket();
        while (!isStopped()) {
            Socket clientSocket = null;
            try {
                clientSocket = this.serverSocket.accept();
            } catch (IOException e) {
                if (isStopped()) {
                    System.out.println("Server Stopped.");
                    break;
                }
                throw new RuntimeException("Error accepting client connection", e);
            }
            this.threadPool.execute(new SocketDataExchanger(clientSocket));
            executed.add(threadPool.submit(new SocketDataExchanger(clientSocket), new Stopped(true)));
        }
        while (!executed.stream().allMatch(Future::isDone)) {
            this.threadPool.shutdown();
        }
        System.out.println("Thread Pooled Server Stopped.");
    }

    private synchronized boolean isStopped() {
        return this.isStopped;
    }

    public synchronized void stop() {
        this.isStopped = true;
        try {
            this.serverSocket.close();
        } catch (IOException e) {
            throw new RuntimeException("Error closing server", e);
        }
    }

    private void openServerSocket() {
        try {
            this.serverSocket = new ServerSocket(this.port);
            System.out.println("ServerSocket opened. " + LocalDateTime.now());
        } catch (IOException e) {
            throw new RuntimeException("Cannot open port " + port, e);
        }
    }
}
