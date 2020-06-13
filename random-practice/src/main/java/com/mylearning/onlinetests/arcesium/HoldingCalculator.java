package com.mylearning.onlinetests.arcesium;

import java.io.*;
import java.util.*;
import java.util.stream.*;

import java.net.MalformedURLException;
import java.net.URL;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

class Result1 {
    /*
     * Complete the 'calculateHoldingValue' function below.
     *
     * The function is expected to return a DOUBLE.
     * The function accepts STRING date (in yyyymmdd format) as parameter.
     */

    static class Holding {
        String securityId;
        Long quantity;

        public String getSecurityId() {
            return securityId;
        }

        public void setSecurityId(String securityId) {
            this.securityId = securityId;
        }

        public Long getQuantity() {
            return quantity;
        }

        public void setQuantity(Long quantity) {
            this.quantity = quantity;
        }
    }

    static class SecurityPrice {
        String securityId;
        Double price;

        public String getSecurityId() {
            return securityId;
        }

        public void setSecurityId(String securityId) {
            this.securityId = securityId;
        }

        public Double getPrice() {
            return price;
        }

        public void setPrice(Double price) {
            this.price = price;
        }
    }

    static class HoldingService {
        public List<Holding> getHoldings(String date) {
            //return new ArrayList<>();
            try {
                String json = RestUtil.readUrl("https://api.myjson.com/bins/vf9ac");
                Gson gson = new Gson();
                List<Holding> holdings = gson.fromJson(json, new TypeToken<List<Holding>>(){}.getType());
                return holdings;
            } catch(Exception e) {
                System.out.println("Exception while fetching security holdings");
                return Collections.emptyList();
            }
        }
    }

    static class PricingService {

        public List<SecurityPrice> getPrices(String date) {
            //return new ArrayList<>();
            try {
                String json = RestUtil.readUrl("https://api.myjson.com/bins/vf9ac");
                Gson gson = new Gson();
                List<SecurityPrice> securityPrices = gson.fromJson(json, new TypeToken<List<SecurityPrice>>(){}.getType());
                return securityPrices;
            } catch(Exception e) {
                System.out.println("Exception while fetching security prices");
                return Collections.emptyList();
            }
        }
    }

    static class RestUtil {
        public static String readUrl(String urlString) throws Exception {
            BufferedReader reader = null;
            try {
                URL url = new URL(urlString);
                reader = new BufferedReader(new InputStreamReader(url.openStream()));
                StringBuffer buffer = new StringBuffer();
                int read;
                char[] chars = new char[1024];
                while ((read = reader.read(chars)) != -1)
                    buffer.append(chars, 0, read);

                return buffer.toString();
            } catch(MalformedURLException e) {
                throw  e;
            } catch(IOException e) {
                throw  e;
            } finally {
                if (reader != null) {
                    reader.close();
                }
            }
        }
    }

    public static double calculateHoldingValue(String date) {
        System.out.println("Start");
        HoldingService holdingService = new HoldingService();
        PricingService pricingService = new PricingService();

        List<Holding> holdings = holdingService.getHoldings(date);
        List<SecurityPrice> securityPrices = pricingService.getPrices(date);

        double valueOfHolding = calculateValue(holdings, securityPrices);
        System.out.println("End");
        return valueOfHolding;
    }

    private static double calculateValue(List<Holding> holdings, List<SecurityPrice> securityPrices) {
        double value = 0.0;

        Map<String, Double> securityPricesMap = securityPrices.stream().collect(Collectors.toMap(SecurityPrice::getSecurityId, SecurityPrice::getPrice));

        for (Holding securityHolding : holdings) {
            long quantity = securityHolding.getQuantity();
            double price = securityPricesMap.getOrDefault(securityHolding.getSecurityId(), 0.0);
            value = value + quantity * price;
        }

        return value;
    }
}
public class HoldingCalculator {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String date = bufferedReader.readLine();

        double result = Result1.calculateHoldingValue(date);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}