/**
 * Project Name:ExamplesOfNetty
 * File Name:TimeServerHandlerExecutePool.java
 * Package Name:com.popo.ex02_PseudoAIO
 * Date:2014年8月14日上午1:38:31
 * Copyright (c) 2014, liutianhao.pro@gmail.com All Rights Reserved.
 *
 */

package com.popo.ex02_PseudoAIO;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * ClassName:TimeServerHandlerExecutePool <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2014年8月14日 上午1:38:31 <br/>
 * @author liutianhao.pro@gmail.com
 * @version
 * @since JDK 1.7
 * @see
 */
public class TimeServerHandlerExecutePool {

    private ExecutorService executor;
    
    public TimeServerHandlerExecutePool(int maxPoolSize, int queueSize) {
        executor = new ThreadPoolExecutor(Runtime.getRuntime().availableProcessors(), 
                maxPoolSize, 120L, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(queueSize));
    }
    
    public void execute(Runnable task) {
        executor.execute(task);
    }
}

	