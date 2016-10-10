package com.qbin.crawlers.controller;

import com.qbin.crawlers.Service.CrawlerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 描述：爬虫控制器
 * author qiaobin   2016/9/28 11:12.
 */
@RestController
public class CrawlerController {

    @Autowired
    private CrawlerService crawlerService;

    /**
     *
     * */
    @RequestMapping(value = "/start", method = RequestMethod.GET)
    public void startup() {
        crawlerService.startGripping();
    }
}
