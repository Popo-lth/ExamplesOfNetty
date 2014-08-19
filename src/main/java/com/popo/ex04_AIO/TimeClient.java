/**
 * Project Name:ExamplesOfNetty
 * File Name:TimeClient.java.java
 * Package com.popo.ex04_AIO
 * Date:2014年8月15日 下午1:34:29
 * Copyright (c) 2014, liutianhao.pro@gmail.com All Rights Reserved.
 *
 */
package com.popo.ex04_AIO;
/**
 * ClassName:TimeClient.java
 * @date 2014年8月15日
 *
 * @author popo
 * 		  email: liutianhao.pro@gmail.com
 * @version 1.0.0
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
        
        new Thread(new AsyncTimeClientHandler("127.0.0.1", port), "AIO-AsyncTimeClientHandler-001").start();
	}
}
