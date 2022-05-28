package com.company.parse;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

public class BitcoinPriceParse implements Parse {
    private static final String SOURCE_LINK = "https://api.coindesk.com/";

    private static final String PAGE_LINK = String.format("%s/v1/bpi/currentprice.json", SOURCE_LINK);

    public static void main(String[] args) throws IOException {
        Connection connection = Jsoup.connect(PAGE_LINK);
        Document document = connection.get();
        Elements rows = document.select("word-wrap: break-word; white-space: pre-wrap;");
        System.out.println(rows.text());
    }
}
