package com.jap.sales;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SalesDataAnalyzer {
   public List<SalesRecord> readFile(String fileName) {
       List<SalesRecord> salesRecords= new ArrayList<>();
       try {
           FileReader fileReader=new FileReader(fileName);
           BufferedReader bufferedReader=new BufferedReader(fileReader);
           String line=bufferedReader.readLine();
           while ((line=bufferedReader.readLine())!=null){
               String[] split =line.split(",");
               String data = split[0];
               int customer_id = Integer.parseInt(split[1]);
               int product_category = Integer.parseInt(split[2]);
               String payment_method = split[3];
               double amount = Double.parseDouble(split[4]);
               double time_on_site = Double.parseDouble(split[5]);
               int clicks_in_site = Integer.parseInt(split[6]);

               salesRecords.add(new SalesRecord(data, customer_id, product_category, payment_method, amount, time_on_site, clicks_in_site));
           }
       }catch (FileNotFoundException e){
           e.printStackTrace();
       }catch (IOException e){
           e.printStackTrace();
       }
       return salesRecords;
   }


    public List<SalesRecord> getAllCustomersSortedByPurchaseAmount(List<SalesRecord> salesData){
      Comparator<SalesRecord> purchaseAmountComparator=(n1,n2)-> (int) (n2.getAmount()- n1.getAmount());
      salesData.sort(purchaseAmountComparator);
       return salesData;
    }
    public SalesRecord getTopCustomerWhoSpentMaxTimeOnSite(List<SalesRecord> salesData){
        Comparator<SalesRecord> timeComparator=(t1,t2)-> (int)(t2.getTime_on_site()- t1.getTime_on_site());
        salesData.sort(timeComparator);
       return salesData.get(0);
    }




}
