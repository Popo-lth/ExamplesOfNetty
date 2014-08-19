/**
 * Project Name:ExamplesOfNetty
 * File Name:ReadCompletionHandler.java.java
 * Package com.popo.ex04_AIO
 * Date:2014年8月15日 下午1:03:33
 * Copyright (c) 2014, liutianhao.pro@gmail.com All Rights Reserved.
 *
 */
package com.popo.ex04_AIO;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.Date;

/**
 * ClassName:ReadCompletionHandler.java
 * @date 2014年8月15日
 *
 * @author popo
 * 		  email: liutianhao.pro@gmail.com
 * @version 1.0.0
 */
public class ReadCompletionHandler implements CompletionHandler<Integer, ByteBuffer>{
	
	private AsynchronousSocketChannel channel;

	public ReadCompletionHandler(AsynchronousSocketChannel channer) {
		if(this.channel == null) {
			this.channel = channer;
		}
	}
	
	@Override
	public void completed(Integer result, ByteBuffer attachment) {
		attachment.flip();
		byte [] body = new byte[attachment.remaining()];
		attachment.get(body);
		
		try {
			String req = new String(body, "UTF-8");
			System.out.println("The time sever receive order : " + req);
			String currentTime = "QUERY TIME ORDEY".equalsIgnoreCase(req) ? new Date(
                    System.currentTimeMillis()).toString() : "BAD ORDER";
            doWirte(currentTime);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
	
	private void doWirte(String currentTime) {
		if (currentTime != null && currentTime.trim().length() > 0) {
			byte [] bytes = currentTime.getBytes();
			ByteBuffer writeBuffer = ByteBuffer.allocate(bytes.length);
			writeBuffer.put(bytes);
			writeBuffer.flip();
			
			channel.write(writeBuffer, writeBuffer, 
					new CompletionHandler<Integer, ByteBuffer>() {

						@Override
						public void completed(Integer result,
								ByteBuffer attachment) {
							// 如果没有发送完成，继续发送
							if (attachment.hasRemaining()) {
								channel.write(attachment, attachment, this);
							}
						}

						@Override
						public void failed(Throwable exc, ByteBuffer attachment) {
							try {
								channel.close();
							} catch (IOException e) {
								// ingnore on close
							}
						}
						   
					});
		}
	}

	@Override
	public void failed(Throwable exc, ByteBuffer attachment) {
		try {
			this.channel.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
