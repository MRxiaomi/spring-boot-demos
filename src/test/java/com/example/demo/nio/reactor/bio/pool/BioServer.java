package com.example.demo.nio.reactor.bio.pool;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author liuyumeng
 *
 * BIO线程池模型
 *
 * 主线程维护连接
 *
 * 具体读写操作使用线程池完成
 *
 * 能够利用多核优势
 *
 * 缺点：
 *      1.同步阻塞IO,读写阻塞，线程等待时间长
 *      2.共享数据需要并发控制
 */
public class BioServer implements Runnable {
    private int port;

    private ServerSocket serverSocket;

    private ExecutorService executorService = Executors.newFixedThreadPool(2);

    public BioServer(int port) {
        this.port = port;
        try {
            serverSocket = new ServerSocket();
            serverSocket.bind(new InetSocketAddress(port));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //
    public void run() {
        try {
            while (true) {
                //连接操作
                Socket socket = serverSocket.accept();

                Thread thread = new BioHandler(socket);
                executorService.execute(thread);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
