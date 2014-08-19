/**
 * Project Name:ExamplesOfNetty
 * File Name:AcceptCompletionHandler.java.java
 * Package com.popo.ex04_AIO
 * Date:2014年8月15日 下午12:54:12
 * Copyright (c) 2014, liutianhao.pro@gmail.com All Rights Reserved.
 *
 */
package com.popo.ex04_AIO;

import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;

/**
 * ClassName:AcceptCompletionHandler.java
 * @date 2014年8月15日
 *
 * @author popo
 * 		  email: liutianhao.pro@gmail.com
 * @version 1.0.0
 */
public class AcceptCompletionHandler implements	CompletionHandler<AsynchronousSocketChannel, AsyncTimeServerHandler>{

	@Override
	public void completed(AsynchronousSocketChannel result,
			AsyncTimeServerHandler attachment) {
		attachment.asynchronousServerSocketChannel.accept(attachment, this);
		ByteBuffer buffer = ByteBuffer.allocate(1024);
		result.read(buffer, buffer, new ReadCompletionHandler(result));
	}

	@Override
	public void failed(Throwable exc, AsyncTimeServerHandler attachment) {
		exc.printStackTrace();
		attachment.latch.countDown();
	}

}
