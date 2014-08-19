/**
 * Project Name:ExamplesOfNetty
 * File Name:TimeServerHandler.java
 * Package Name:com.popo.ex01_BIO
 * Date:2014年8月14日上午1:01:28
 * Copyright (c) 2014, liutianhao.pro@gmail.com All Rights Reserved.
 *
 */

package com.popo.ex01_BIO;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Date;

/**
 * ClassName:TimeServerHandler <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2014年8月14日 上午1:01:28 <br/>
 * @author liutianhao.pro@gmail.com
 * @version
 * @since JDK 1.7
 * @see
 */
public class TimeServerHandler implements Runnable{
    
    private Socket socket;
    
    public TimeServerHandler(Socket socket) {
        this.socket = socket;
    }
    
    @Override
    public void run() {
        BufferedReader in = null;
        PrintWriter out = null;
        try {
            in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
            out = new PrintWriter(this.socket.getOutputStream(), true);
            String currentTime = null;
            String body = null;
            while (true) {
                body = in.readLine();
                if  (body == null) {
                    break;
                }
                System.out.println("The time server receive order : " + body);
                currentTime = "QUERY TIME ORDEY".equalsIgnoreCase(body) ? 
                        new Date(System.currentTimeMillis()).toString() : "BAD ORDER";
                out.println(currentTime);
            }
        } catch (Exception e) {
            if (in != null) {
                try {
                    in.close();
                    in = null;
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            if (out != null) {
                out.close();
                out = null;
            }
            if (this.socket != null) {
                try {
                    this.socket.close();
                    this.socket = null;
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }

}

	