package com.qbin.crawlers.processor;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.JsonFilePipeline;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.List;


/**
 * 描述：TODO
 * author qiaobin   2016/9/28 14:34.
 */
public class TestProcessor implements PageProcessor {
    // 部分一：抓取网站的相关配置，包括编码、抓取间隔、重试次数等
    private Site site = Site.me().setRetryTimes(3).setSleepTime(1000).setTimeOut(50000);

    @Override
    // process是定制爬虫逻辑的核心接口，在这里编写抽取逻辑
    ////div[@id='wrapper']/p/a
    public void process(Page page) {
        // 部分二：定义如何抽取页面信息，并保存下来
        String title = page.getHtml().xpath("//div[@class='result']/h3[@class='c-title']/a/text()").get();
        String title1 = page.getHtml().xpath("//div[@class='sub-article']/h2/text()").get();
//        String title = page.getHtml().xpath("//div[@class='result']/h3[@class='c-title']/a/text()").get();

        System.out.println(title+"~~~");
        System.out.println(title1);
    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
        Spider.create(new TestProcessor())
                //从"https://github.com/code4craft"开始抓
//                .addUrl("http://news.baidu.com/ns?word=%CC%EC&tn=news&from=news&cl=2&rn=20&ct=1")
                .addUrl("http://www.hunan.gov.cn/2015xxgk/szfzcbm/tjbm_7293/hydt/201609/t20160928_3296286.html")
                //开启5个线程抓取
                .thread(5)
                //启动爬虫
                .run();
    }
}
