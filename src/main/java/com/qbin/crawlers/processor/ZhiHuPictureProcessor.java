package com.qbin.crawlers.processor;

import com.qbin.crawlers.util.PicDownloader;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 描述：TODO
 * author qiaobin   2016/9/29 17:34.
 */
public class ZhiHuPictureProcessor implements PageProcessor {

    private Site site = Site.me().setRetryTimes(10).setSleepTime(1000);

    private static String keyword = "java";
    private static int num = 0;
    @Override
    public void process(Page page) {
        if (page.getUrl().regex("https://www\\.zhihu\\.com/search\\?type=people&q=[\\s\\S]+").match()) {
            page.addTargetRequests(page.getHtml().xpath("//ul/li/div/div/div/a").links().all());
        } else {
            num ++;
            String imgUrl = page.getHtml().xpath("//div[@class='body clearfix']/img/@src").get().toString();
            PicDownloader.download(imgUrl, "pic"+Math.random()+".jpg", "E:/picture");
            page.putField("imgUrl", imgUrl);
        }
    }

    @Override
    public Site getSite() {
        return this.site;
    }

    public static void main(String[] args) {
        Spider.create(new ZhiHuPictureProcessor()).addUrl("https://www.zhihu.com/search?type=people&q="+keyword)
                .thread(5).run();
        //
        System.out.println(num+"~~~~");
    }
}
