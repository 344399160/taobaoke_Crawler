package com.qbin.crawlers.Service.CralerServiceImpl;

import com.qbin.crawlers.Service.CrawlerService;
import org.springframework.stereotype.Service;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.processor.example.GithubRepoPageProcessor;

/**
 * 描述：爬虫业务实现类
 * author qiaobin   2016/9/28 11:13.
 */
@Service
public class CrawlerServiceImpl implements CrawlerService{

    private Site site = Site.me().setRetryTimes(3).setSleepTime(1000);

    @Override
    public void startGripping() {
        Spider.create(new GithubRepoPageProcessor()).addUrl("https://github.com/code4craft").thread(5).run();
    }


}
