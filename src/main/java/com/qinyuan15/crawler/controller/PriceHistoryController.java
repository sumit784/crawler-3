package com.qinyuan15.crawler.controller;

import com.qinyuan15.crawler.core.DateUtils;
import com.qinyuan15.crawler.core.price.PriceRecordUtils;
import com.qinyuan15.crawler.dao.PriceRecord;
import com.qinyuan15.crawler.dao.PriceRecordDao;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.Date;
import java.util.*;

import static com.qinyuan15.crawler.controller.JsonControllerUtils.toJson;

/**
 * Query detail information of certain commodity
 * Created by qinyuan on 14-12-27.
 */
@Controller
public class PriceHistoryController {
    @ResponseBody
    @RequestMapping("/priceHistory.json")
    public String get(@RequestParam(value = "pretty", required = false) String pretty,
                      @RequestParam(value = "commodityId", required = false) Integer commodityId,
                      @RequestParam(value = "grabDate", required = false) String grabDate,
                      @RequestParam(value = "startTime", required = false) String startTime,
                      @RequestParam(value = "endTime", required = false) String endTime) {
        if (startTime == null) {
            startTime = DateUtils.threeMonthAgo().toString();
        }
        if (endTime == null) {
            endTime = DateUtils.now().toString();
        }

        List<PriceRecord> priceRecords = PriceRecordDao.factory()
                .setCommodityId(commodityId)
                .setStartTime(startTime)
                .setEndTime(endTime)
                .setGrabDate(grabDate)
                .getInstances();

        Map<Integer, List<PriceRecord>> groupedRecords = PriceRecordUtils.groupByCommodityId(priceRecords);
        return toJson(convert(groupedRecords), pretty != null);
    }

    /**
     * convert PriceRecord of a map to PriceRecordJson
     *
     * @param map map to convert
     * @return converted map
     */
    @SuppressWarnings("unchecked")
    private Map<Integer, Map> convert(Map<Integer, List<PriceRecord>> map) {
        Map<Integer, Map> result = new TreeMap<Integer, Map>();
        for (Map.Entry<Integer, List<PriceRecord>> entry : map.entrySet()) {
            Integer commodityId = entry.getKey();
            if (!result.containsKey(commodityId)) {
                result.put(commodityId, new TreeMap());
                result.get(commodityId).put("prices", new ArrayList<PriceRecordJson>());
                result.get(commodityId).put("lowestPrice", PriceRecordUtils.getLowestPrice(entry.getValue()));
            }

            for (PriceRecord priceRecord : entry.getValue()) {
                PriceRecordJson priceRecordPair = new PriceRecordJson();
                priceRecordPair.date = toString(priceRecord.getRecordTime());
                priceRecordPair.price = priceRecord.getPrice();
                priceRecordPair.grabDate = toString(priceRecord.getGrabTime());
                ((List) result.get(commodityId).get("prices")).add(priceRecordPair);
            }
        }
        return result;
    }

    public String toString(Date date) {
        return date == null ? null : date.toString();
    }

    public static class PriceRecordJson {
        public String date;
        public Double price;
        public String grabDate;
    }
}
