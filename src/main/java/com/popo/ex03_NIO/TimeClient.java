/**
 * Project Name:ExamplesOfNetty
 * File Name:TimeClient.java
 * Package Name:com.popo.ex03_NIO
 * Date:2014年8月14日下午11:03:47
 * Copyright (c) 2014, liutianhao.pro@gmail.com All Rights Reserved.
 *
 */

package com.popo.ex03_NIO;
/**
 * ClassName:TimeClient <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2014年8月14日 下午11:03:47 <br/>
 * @author liutianhao.pro@gmail.com
 * @version
 * @since JDK 1.7
 * @see
 */
public class TimeClient {

    public static void main(String[] args) {
        int port = 8080;
        if (args != null && args.length > 0) {
            try {
                port = Integer.valueOf(args[0]);
            } catch (NumberFormatException e) {
                System.out.println("参数错误！！！" + e.getMessage());
            }
        }
        
        new Thread(new TimeClientHandle("127.0.0.1", port), "TimeClient-001").start();
    }

}

	