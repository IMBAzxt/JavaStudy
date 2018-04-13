/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.http;

/**
 *
 * @author ThinkPad
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.Scanner;

public class NewClass1 {

    String tracert = "tracert -h 10 "; //模拟tracert命令

    String ping = "ping";//模拟 ping 命令

    String routePrint = "route print -4";//模拟route print命令

    public static void main(String args[]) {

        String input = null;

        @SuppressWarnings("resource")
        Scanner scanner = new Scanner(System.in);
        //利用while循环接收输入的命令行参数
        while (true) {

            System.out.println("Please input destination server IP address ：\n");

            input = scanner.next();

            NewClass1 host = new NewClass1();

            host.tracert = host.tracert + " " + input;

            host.ping = host.ping + " " + input;

            try {

                host.command(host.routePrint);

            } catch (IOException exception) {

                exception.printStackTrace();
            }

            try {

                host.command(host.tracert);

            } catch (IOException exception) {

                exception.printStackTrace();
            }

            try {

                host.command(host.ping);

            } catch (IOException exception) {

                exception.printStackTrace();
            }

            InetAddress ipAddress;

            try {

                ipAddress = InetAddress.getByName(input);

                System.out.println("IP address : " + ipAddress);

            } catch (UnknownHostException exception) {

                exception.printStackTrace();
            }

            URL url;

            try {
                url = new URL("http", input, 80, "index.html");

                System.out.println();//输出服务器地址

                System.out.println("Get the Server-Name# : " + url.getHost());

                System.out.println();//输出首页文件

                System.out.println("Get the default file# : " + url.getFile());

                System.out.println();//输出首页协议和端口

                System.out.println("Get the protocol# : " + url.getProtocol() + " " + url.getPort());

                System.out.println();

            } catch (MalformedURLException e) {

                e.printStackTrace();
            }

            System.out.println();

            try {

                System.out.println("Get serverName & IPAddress：" + InetAddress.getByName(input));

            } catch (UnknownHostException e) {

                e.printStackTrace();
            }

            long freeMemory = Runtime.getRuntime().freeMemory();

            System.out.println("Surplus memory of JVM: " + freeMemory + "B");

        }
    }

    StringBuffer commandResult = null;

    private void command(String tracerCommand) throws IOException {
        //第一步：创建进程(是接口不必初始化)

        //1.通过Runtime类的getRuntime().exec()传入需要运行的命令参数
        System.out.println();

        System.out.println(InetAddress.getByName("localhost") + " is tracking the destination server...");

        Process process = Runtime.getRuntime().exec(tracerCommand);

        readResult(process.getInputStream());

        process.destroy();
    }
        //第二步：通过输入流来将命令执行结果输出到控制台

    private void readResult(InputStream inputStream) throws IOException {

        commandResult = new StringBuffer();  //初始化命令行

        String commandInfo = null; //定义用于接收命令行执行结果的字符串

        BufferedReader bufferedReader
                = new BufferedReader(new InputStreamReader(inputStream));

        while ((commandInfo = bufferedReader.readLine()) != null) {

            System.out.println(commandInfo);
        }
        bufferedReader.close();
    }
}
