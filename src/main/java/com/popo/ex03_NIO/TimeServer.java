/**
 * Project Name:ExamplesOfNetty
 * File Name:TimeServer.java
 * Package Name:com.popo.ex03_NIO
 * Date:2014年8月14日上午1:49:37
 * Copyright (c) 2014, liutianhao.pro@gmail.com All Rights Reserved.
 *
 */

package com.popo.ex03_NIO;
/**
 * ClassName:TimeServer <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2014年8月14日 上午1:49:37 <br/>
 * @author liutianhao.pro@gmail.com
 * @version
 * @since JDK 1.7
 * @see
 */
public class TimeServer {

    public static void main(String[] args) {
        int port = 8080;
        if (args != null && args.length > 0) {
            try {
                port = Integer.valueOf(args[0]);
            } catch (NumberFormatException e) {
                System.out.println("参数错误！！！" + e.getMessage());
            }
        }
        
        MultiplexerTimeServer timeServer = new MultiplexerTimeServer(port);
        
        new Thread(timeServer, "NIO-MultiplexerTimeServer-001").start();
    }

}

	