package com.company.parse;

import org.apache.commons.io.IOUtils;
import org.json.JSONObject;

import java.net.URL;
import java.nio.charset.StandardCharsets;

public class BitcoinPriceParse implements Parse {
    private static final String SOURCE_LINK = "https://api.coindesk.com/v1/bpi/currentprice.json";

    public static float getPrice() {
        float price = 0;
        try {
            String json = IOUtils.toString(new URL(SOURCE_LINK), StandardCharsets.UTF_8);
            JSONObject jsonObject = new JSONObject(json);
            price = jsonObject.getJSONObject("bpi").getJSONObject("USD").getFloat("rate_float");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return price;
    }
}
