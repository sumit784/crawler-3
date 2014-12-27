package com.qinyuan15.crawler.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class BranchController extends JsonController {

    private Map<Integer, Map> allMaps = new HashMap<Integer, Map>();

    private Map getMap(int id, String name) {
        Map map = new HashMap();
        map.put("id", id);
        map.put("name", name);
        map.put("logoUrl", "resources/images/438179439.png");
        allMaps.put(id, map);
        return map;
    }

    @ResponseBody
    @RequestMapping("/branch")
    public String get(@RequestParam("pretty") String pretty, @RequestParam("id") int id) {
        List list = new ArrayList();
        list.add(getMap(1, "美特斯邦威"));
        list.add(getMap(2, "欧莱雅"));
        list.add(getMap(3, "路易威登"));
        list.add(getMap(4, "XXXX"));
        list.add(getMap(5, "YYYY"));
        list.add(getMap(6, "ZZZZ"));

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
