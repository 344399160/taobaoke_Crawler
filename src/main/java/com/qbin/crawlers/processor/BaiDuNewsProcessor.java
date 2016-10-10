package com.qbin.crawlers.processor;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 描述：TODO
 * author qiaobin   2016/9/29 17:34.
 */
public class BaiDuNewsProcessor implements PageProcessor {

    private Site site = Site.me().setRetryTimes(10).setSleepTime(1000);

    private static String keyword = "java";
    private static int num = 0;
    @Override
    public void process(Page page) {
        if (page.getUrl().regex("http://news\\.baidu\\.com/ns\\?word=[\\s\\S]+&tn=news&from=news&cl=2&rn=20&ct=1").match() ||
                page.getUrl().regex("http://news\\.baidu\\.com/ns\\?word=[\\s\\S]+&pn=[\\d\\d]+&cl=2&ct=1&tn=news&rn=20&ie=utf-8&bt=0&et=0&rsv_page=-1").match()) {
            page.addTargetRequests(page.getHtml().xpath("//div[@id='content_left']/div/div[@class='result']/h3/a").links().all());
            page.addTargetRequests(page.getHtml().xpath("//p[@id='page']/a").links().all());
            //翻页

            List<String> urls = page.getHtml().xpath("//p[@id='page']/a").links().all();
            page.addTargetRequests(urls);
            page.addTargetRequests(page.getHtml().links().all());
            List list = page.getTargetRequests();
            Set set = new HashSet(list);
            System.out.println(set.size());
        } else if (page.getUrl().regex("/ns\\?word=java&pn=[\\d\\d]+&cl=2&ct=1&tn=news&rn=20&ie=utf-8&bt=0&et=0").match()) {
            System.out.println(page.getTargetRequests().size()+"~~~~~~~~~!!!!!!!!!!!!!!!!!!!~~");
            page.addTargetRequests(page.getHtml().xpath("//p[@id='page']/a").links().all());
        } else {
            num ++;
            String title = page.getHtml().xpath("//div[@class='NewsEntity']/h1/text()").get();
            String user = page.getHtml().xpath("//div[@class='NewsEntity']/div/a/text()").get();
            page.putField("title", title);
            page.putField("user", user);
        }
    }

    @Override
    public Site getSite() {
        return this.site;
    }

    public static void main(String[] args) {
        long startTime ,endTime;
        System.out.println("========百度小爬虫【启动】喽！=========");
        //入口为：【https://www.zhihu.com/search?type=people&q=xxx 】，其中xxx 是搜索关键词
        Spider.create(new BaiDuNewsProcessor()).addUrl("http://news.baidu.com/ns?word="+keyword+"&tn=news&from=news&cl=2&rn=20&ct=1")
                .thread(5).run();
        //
        System.out.println(num+"~~~~");
        System.out.println("========百度小爬虫【结束】喽！=========");
    }
}
