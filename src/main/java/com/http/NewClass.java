/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.http;

import com.github.kevinsawicki.http.HttpRequest;
import com.sun.xml.internal.bind.v2.runtime.reflect.opt.Const;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;
import static jdk.nashorn.internal.objects.NativeArray.map;

/**
 *
 * @author ThinkPad
 */
public class NewClass {
    public static void main(String[] args) throws UnknownHostException {
//        HttpClientUtil util = new HttpClientUtil();
//        Response response = util.get("http://blog.csdn.net/u010399009/article/details/51418854", null, null);
//        response.getHeaders();
        //请求参数
//        HttpRequest httpRequest = HttpRequest.get("http://www.baidu.com", null, Boolean.TRUE);
//        String result = httpRequest.body();
//        System.out.println(result);
        //用域名创建 InetAddress对象
        InetAddress address1 = InetAddress.getByName("www.baidu.com");
        //获取的是该网站的ip地址，如果我们所有的请求都通过nginx的，所以这里获取到的其实是nginx服务器的IP地址
        System.out.println(address1.getHostName());//www.wodexiangce.cn
        System.out.println(address1.getHostAddress());//124.237.121.122
        System.out.println("===============");
    }
}
