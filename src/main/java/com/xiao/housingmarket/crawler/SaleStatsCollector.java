package com.xiao.housingmarket.crawler;

import com.xiao.housingmarket.beans.SaleStats;
import com.xiao.housingmarket.config.GlobalConfig;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * Created by wanyg on 17/8/3.
 */
public class SaleStatsCollector {
    private static Logger log = LoggerFactory.getLogger(SaleStatsCollector.class);//日志
    public static Map<String,List<SaleStats>> crawlingSaleData(){
        Document doc = null;
        Map<String,List<SaleStats>> saleData = null;
        try {
            //File input = new File("20170803.aspx");
            //doc = Jsoup.parse(input, "UTF-8");
            doc = Jsoup.connect(GlobalConfig.SALE_STATS_URL).get();
            Elements statsTables = doc.select("div[class=rightContent]").select("table[width=550px;]");//two tables
            saleData =  new HashMap<>();
            for (int i = 0; i < statsTables.size(); i++) {
                //type
                Elements tds = statsTables.get(i).select("td[class=yellow]");
                String type = tds.get(0).text();

                List<SaleStats> saleStatsList = new ArrayList<>();
                Elements districts = statsTables.get(i).select("tr[bgcolor=#FFFFFF]");
                for (int j = 0; j < districts.size(); j++) {
                    Elements details = districts.get(j).select("td");
                    SaleStats saleStats = buildSaleStatsFromJsoupElements(details);
                    if(saleStats!=null){
                        saleStatsList.add(saleStats);
                    }
                }
                saleData.put(type,saleStatsList);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return saleData;
    }

    private static SaleStats buildSaleStatsFromJsoupElements(Elements details){
        SaleStats saleStats = null;
        if(details!=null &&details.size() ==4){
            saleStats = new SaleStats();
            saleStats.setZone(details.get(0).text());
            saleStats.setArea(Double.parseDouble(details.get(1).text()));
            saleStats.setHousing_num(Integer.parseInt(details.get(2).text()));
            saleStats.setHousing_area(Double.parseDouble(details.get(3).text()));
        }else{
            log.error("the format of saledata is changed!");
        }
        return saleStats;
    }
}
