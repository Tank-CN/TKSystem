package com.tk.spider.main;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * 主入口
 */
public class Main {

    private static String urll = "http://www.baidu.com/s?wd=";

    private static void getNews(String url, int num){
        try {
            Document doc = Jsoup.connect(url).get();
            Element element = doc.getElementById("content_left");

            for(int i = 1; i < (num + 1); i++){
                Element result = element.getElementById(String.valueOf(i));
                Elements add = result.select("a");

                System.out.println("--------------------- " + i + " ---------------------");
                System.out.println(add.first().text());
                System.out.println(add.first().attr("href"));
                System.out.println();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void getResult(int num, String question){

        String url = "";
        try {
            url = urll + URLEncoder.encode(question, "utf-8") + "&rn=" + num;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        getNews(url, num);

    }

    public static void main(String[] args){
        getResult(10, "seo");
    }

}
