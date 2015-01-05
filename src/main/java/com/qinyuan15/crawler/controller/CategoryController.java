package com.qinyuan15.crawler.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.qinyuan15.crawler.controller.JsonControllerUtils.emptyListJson;
import static com.qinyuan15.crawler.controller.JsonControllerUtils.toJson;

/**
 * Query commodity category
 * Created by qinyuan on 14-12-27.
 */
@SuppressWarnings("unchecked")
@Controller
public class CategoryController {

    private Map<Integer, Map> allMaps = new HashMap<Integer, Map>();

    private Map getMap(int id, String name) {
        Map map = new HashMap();
        map.put("id", id);
        map.put("name", name);
        allMaps.put(id, map);
        return map;
    }

    private void addChild(Map map, int id, String name) {
        String key = "children";
        if (!map.containsKey(key)) {
            map.put(key, new ArrayList());
        }
        ((List) map.get(key)).add(getMap(id, name));
    }

    @ResponseBody
    @RequestMapping("/category")
    public String get(@RequestParam("pretty") String pretty, @RequestParam("id") int id) {
        List list = new ArrayList();

        Map nuren = getMap(1, "女人");
        addChild(nuren, 2, "女士钱包");
        addChild(nuren, 3, "双肩包");
        addChild(nuren, 4, "旅行箱");
        addChild(nuren, 5, "百搭单鞋");
        addChild(nuren, 6, "XXXXX");
        addChild(nuren, 7, "YYYYY");
        list.add(nuren);

        Map nanren = getMap(10, "男人");
        addChild(nanren, 11, "男士钱包");
        addChild(nanren, 12, "双肩包");
        addChild(nanren, 13, "旅行箱");
        addChild(nanren, 14, "皮鞋");
        addChild(nanren, 15, "XXXXX");
        addChild(nanren, 16, "YYYYY");
        list.add(nanren);

        Map xiaoHai = getMap(20, "小孩");
        addChild(xiaoHai, 21, "AAAAA");
        addChild(xiaoHai, 22, "BBBBB");
        list.add(xiaoHai);

        Map jiadian = getMap(30, "数码家电");
        addChild(jiadian, 31, "DDDD");
        addChild(jiadian, 32, "EEEE");
        list.add(jiadian);

        Map jujia = getMap(40, "居家");
        addChild(jujia, 41, "OOOO");
        addChild(jujia, 42, "PPPP");
        list.add(jujia);


        if (id > 0) {
            if (allMaps.containsKey(id)) {
                return toJson(allMaps.get(id), pretty != null);
            } else {
                return emptyListJson;
            }
        } else {
            return toJson(list, pretty != null);
        }
    }
}
