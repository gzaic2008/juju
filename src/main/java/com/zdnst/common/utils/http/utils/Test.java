package com.zdnst.common.utils.http.utils;
public class Test {     
    public static void main(String[] args) {     
        try {     
            HttpRequester request = new HttpRequester();     
            HttpRespons hr = request.sendGet("http://www.csdn.net");     
      
            System.out.println(hr.getUrlString());     
            System.out.println(hr.getProtocol());     
            System.out.println(hr.getHost());     
            System.out.println(hr.getPort());     
            System.out.println(hr.getContentEncoding());     
            System.out.println(hr.getMethod());     
            System.out.println(hr.getContent());     
        } catch (Exception e) {     
            e.printStackTrace();     
        }     
    }     
} 