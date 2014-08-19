/**
 * Project Name:ExamplesOfNetty
 * File Name:AsyncTimeServerHandler.java.java
 * Package com.popo.ex04_AIO
 * Date:2014年8月15日 下午12:45:09
 * Copyright (c) 2014, liutianhao.pro@gmail.com All Rights Reserved.
 *
 */
package com.popo.ex04_AIO;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.util.concurrent.CountDownLatch;

/**
 * ClassName:AsyncTimeServerHandler.java
 * @date 2014年8月15日
 *
 * @author popo
 * 		  email: liutianhao.pro@gmail.com
 * @version 1.0.0
 */
public class AsyncTimeServerHandler implements Runnable{

	private int port;
	
	CountDownLatch latch;
	AsynchronousServerSocketChannel asynchronousServerSocketChannel;
	
	public AsyncTimeServerHandler(int port) {
		this.port = port;
		try {
			asynchronousServerSocketChannel = AsynchronousServerSocketChannel.open();
			asynchronousServerSocketChannel.bind(new InetSocketAddress(this.port));
			System.out.println("The time server is start in port : " + this.port);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		latch = new CountDownLatch(1);
		doAccept();
		try {
			latch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void doAccept() {
		asynchronousServerSocketChannel.accept(this, new AcceptCompletionHandler());
	}

}
