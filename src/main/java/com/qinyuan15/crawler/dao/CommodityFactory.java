package com.qinyuan15.crawler.dao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * Class to query/create SimpleCommodity
 * Created by qinyuan on 14-12-27.
 */
public class CommodityFactory extends AbstractPersistObjectFactory<Commodity> {

    private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    private String startTime;
    private String endTime;

    public CommodityFactory() {
        Date now = new Date();
        this.endTime = this.format.format(now);
        this.startTime = this.format.format(now.getTime() - 1000L * 3600 * 24 * 90);
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    private PriceHistory mockPriceHistory(double currentPrice) {
        List<String> times = new ArrayList<String>();
        List<Double> prices = new ArrayList<Double>();
        try {
            long start = format.parse(startTime).getTime();
            long end = format.parse(endTime).getTime();

            Random rnd = new Random();
            double diff = currentPrice * 0.25;
            while (start <= end) {
                times.add(format.format(start));
                prices.add(currentPrice - diff + rnd.nextInt(10) * diff / 5);
                start += 24 * 3600 * 1000;
            }

            PriceHistory priceHistory = new PriceHistory();
            priceHistory.setTimes(times);
            priceHistory.setPrices(prices);
            return priceHistory;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private List<String> mockPictures() {
        List<String> list =new ArrayList<String>();
        list.add("resources/images/723489172.png");
        list.add("resources/images/25791837403.png");
        return list;
    }

    @Override
    public List<Commodity> getInstances() {
        List<Commodity> list = new ArrayList<Commodity>();
        List<SimpleCommodity> simpleCommodities = new SimpleCommodityFactory().getInstances();
        for (SimpleCommodity simpleCommodity : simpleCommodities) {
            Commodity commodity = new Commodity();
            commodity.setId(simpleCommodity.getId());
            commodity.setName(simpleCommodity.getName());
            commodity.setPrice(simpleCommodity.getPrice());
            commodity.setBranch(simpleCommodity.getBranch());
            commodity.setCategoryId(simpleCommodity.getCategoryId());

            commodity.setPictures(mockPictures());
            commodity.setLowPrice(commodity.getPrice() * 0.75);
            commodity.setOnShelfTime("2014-08-01");
            commodity.setOriginalPrice(commodity.getPrice() * 2);
            commodity.setLowestInThreeMonth(false);
            commodity.setTrend(mockPriceHistory(commodity.getPrice()));

            list.add(commodity);
        }
        return list;
    }
}
