/**
 * Project Name:ExamplesOfNetty
 * File Name:TimeServer.java
 * Package Name:com.popo.ex02_PseudoAIO
 * Date:2014年8月14日上午1:35:49
 * Copyright (c) 2014, liutianhao.pro@gmail.com All Rights Reserved.
 *
 */

package com.popo.ex02_PseudoAIO;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import com.popo.ex01_BIO.TimeServerHandler;

/**
 * ClassName:TimeServer <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2014年8月14日 上午1:35:49 <br/>
 * @author liutianhao.pro@gmail.com
 * @version
 * @since JDK 1.7
 * @see
 */
public class TimeServer {

    public static void main(String[] args) throws IOException {
        int port = 8080;
        if (args != null && args.length > 0) {
            try {
                port = Integer.valueOf(args[0]);
            } catch (NumberFormatException e) {
                System.out.println("参数错误！！！" + e.getMessage());
            }
        }
        
        ServerSocket server = null;
        try {
            server = new ServerSocket(port);
            System.out.println("The time server is start in port : " + port);
            Socket socket = null;
            
            TimeServerHandlerExecutePool singleExecutor = new TimeServerHandlerExecutePool(50, 10000);
            
            while (true) {
                socket = server.accept();
                singleExecutor.execute(new TimeServerHandler(socket));
            }
        } catch (Exception e) {
            // TODO: handle exception
        } finally {
            if (server != null) {
                System.out.println("The time server close");
                server.close();
                server = null;
            }
        }
    }

}

	