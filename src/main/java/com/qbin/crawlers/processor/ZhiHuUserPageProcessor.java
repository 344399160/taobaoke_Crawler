package com.qbin.crawlers.processor;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.JsonFilePipeline;
import us.codecraft.webmagic.processor.PageProcessor;

/**
 * 描述：TODO
 * author qiaobin   2016/9/28 16:23.
 */
public class ZhiHuUserPageProcessor implements PageProcessor {
    //抓取网站的相关配置，包括：编码、抓取间隔、重试次数等
    private Site site = Site.me().setRetryTimes(10).setSleepTime(1000);
    //用户数量
    private static int num = 0;
    //搜索关键词
    private static String keyword = "学生";


    /**
     * process 方法是webmagic爬虫的核心<br>
     * 编写抽取【待爬取目标链接】的逻辑代码在html中。
     */
    @Override
    public void process(Page page) {

        //1. 如果是用户列表页面 【入口页面】，将所有用户的详细页面的url放入target集合中。
//        if(page.getUrl().regex("https://www\\.zhihu\\.com/search\\?type=people&q=[\\s\\S]+").match()){
        if(page.getUrl().regex("https://www\\.zhihu\\.com/search\\?type=people&q=[\\s\\S]+").match()){
            page.addTargetRequests(page.getHtml().xpath("//ul[@class='list users']/li/div/div[@class='body']/div[@class='line']/a").links().all());
        }
        //2. 如果是用户详细页面
        else{
            num++;//用户数++
            /*实例化ZhihuUser，方便持久化存储。*/
            /*从下载到的用户详细页面中抽取想要的信息，这里使用xpath居多*/
            /*为了方便理解，抽取到的信息先用变量存储，下面再赋值给对象*/
            String name = page.getHtml().xpath("//div[@class='title-section']/span[@class='name']/text()").get();
            String identity = page.getHtml().xpath("//div[@class='title-section']/div[@class='bio ellipsis']/@title").get();
            String location = page.getHtml().xpath("//div[@class='item editable-group']/span[@class='info-wrap']/span[@class='location item']/@title").get();
            String profession = page.getHtml().xpath("//div[@class='item editable-group']/span[@class='info-wrap']/span[@class='business item']/@title").get();
            boolean isMale = page.getHtml().xpath("//span[@class='item gender']/i[@class='icon icon-profile-male']").match();
            boolean isFemale = page.getHtml().xpath("//span[@class='item gender']/i[@class='icon icon-profile-female']").match();
            int sex = -1;
            /*因为知乎有一部分人不设置性别 或者 不显示性别。所以需要判断一下。*/
            if(isMale&&!isFemale) sex=1;//1代表男性
            else if(!isMale&&isFemale) sex=0;//0代表女性
            else sex=2;//2代表未知
            String school =  page.getHtml().xpath("//span[@class='education item']/@title").get();
            String major = page.getHtml().xpath("//span[@class='education-extra item']/@title").get();
            String recommend =  page.getHtml().xpath("//span[@class='fold-item']/span[@class='content']/@title").get();
            String picUrl = page.getHtml().xpath("//div[@class='body clearfix']/img[@class='Avatar Avatar--l']/@src").get();
            int agree = Integer.parseInt(page.getHtml().xpath("//span[@class='zm-profile-header-user-agree']/strong/text()").get());
            int thanks = Integer.parseInt(page.getHtml().xpath("//span[@class='zm-profile-header-user-thanks']/strong/text()").get());
            int ask = Integer.parseInt(page.getHtml().xpath("//div[@class='profile-navbar clearfix']/a[2]/span[@class='num']/text()").get());
            int answer = Integer.parseInt(page.getHtml().xpath("//div[@class='profile-navbar clearfix']/a[3]/span[@class='num']/text()").get());
            int article = Integer.parseInt(page.getHtml().xpath("//div[@class='profile-navbar clearfix']/a[4]/span[@class='num']/text()").get());
            int collection = Integer.parseInt(page.getHtml().xpath("//div[@class='profile-navbar clearfix']/a[5]/span[@class='num']/text()").get());
            page.putField("name", name);
            page.putField("identity", identity);
            page.putField("location", location);
            page.putField("profession", profession);
            page.putField("sex", sex);
            page.putField("school", school);

        }
    }

    @Override
    public Site getSite() {
        return this.site;
    }

    public static void main(String[] args) {
        long startTime ,endTime;
        System.out.println("========知乎用户信息小爬虫【启动】喽！=========");
        //入口为：【https://www.zhihu.com/search?type=people&q=xxx 】，其中xxx 是搜索关键词
        Spider.create(new ZhiHuUserPageProcessor()).addUrl("https://www.zhihu.com/search?type=people&q="+keyword)
                .thread(5).run();
        System.out.println("========知乎用户信息小爬虫【结束】喽！=========");
        System.out.println("一共爬到"+num+"个用户信息！用时为：");
    }
}