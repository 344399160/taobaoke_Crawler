package com.qbin.crawlers.common;

import java.util.ArrayList;
import java.util.List;

/**
 * 描述：常量定义
 * author qiaobin   2016/10/10 15:03.
 */
public final class RuleConst {
    //淘客助手首页
    public final static String TAOKEZHUSHOU = "http://www.taokezhushou.com/";
    //1女装
    public final static String NVZHUANG = "http://www.taokezhushou.com/cate/1";
    //2男装
    public final static String NANZHUANG = "http://www.taokezhushou.com/cate/2";
    //3鞋包
    public final static String XIEBAO = "http://www.taokezhushou.com/cate/3";
    //5家居
    public final static String JIAJU = "http://www.taokezhushou.com/cate/5";
    //6文体
    public final static String WENTI = "http://www.taokezhushou.com/cate/6";
    //7电器
    public final static String DIANQI = "http://www.taokezhushou.com/cate/7";
    //8配饰
    public final static String PEISHI = "http://www.taokezhushou.com/cate/8";
    //9数码
    public final static String SHUMA = "http://www.taokezhushou.com/cate/9";
    //10美食
    public final static String MEISHI = "http://www.taokezhushou.com/cate/10";
    //11美妆
    public final static String MEIZHUANG = "http://www.taokezhushou.com/cate/11";
    //12母婴
    public final static String MUYING = "http://www.taokezhushou.com/cate/12";
    //13其他
    public final static String QITA = "http://www.taokezhushou.com/cate/13";

    //商品类别列表
    public final static List<String> MAINPAGELIST = new ArrayList<>();

    static {
        MAINPAGELIST.add(NVZHUANG);
        MAINPAGELIST.add(NANZHUANG);
        MAINPAGELIST.add(XIEBAO);
        MAINPAGELIST.add(JIAJU);
        MAINPAGELIST.add(WENTI);
        MAINPAGELIST.add(DIANQI);
        MAINPAGELIST.add(PEISHI);
        MAINPAGELIST.add(SHUMA);
        MAINPAGELIST.add(MEISHI);
        MAINPAGELIST.add(MEIZHUANG);
        MAINPAGELIST.add(MUYING);
        MAINPAGELIST.add(QITA);
    }

    //首推商品规则（4条）
    public final static String TOP4RULE = "//div[@class='goods clearfix']/div/ul[@class='mg clearfix']/li/div/a";
    //展示的商品（100条）
    public final static String GOODSLISTRULE = "//div[@class='goods']/div/ul/li/div/a";
//    //最大页号
//    public final static String LASTPAGENO = "//div[@class='pages wth']/ul/li[last()]/a/text()";
    //最大页号链接（多个需要拿出倒数第二个）
    public final static String LASTPAGEURL = "//div[@class='pages wth']/ul/li/a";
//    //最大页号链接
//    public final static String LASTPAGEURL = "//div[@class='pages wth']/ul/li[last()-1]//a/@href";
    //分页规则
    public final static String PAGEREGEX = "http://www.taokezhushou.com/cate/[\\d\\d]+\\?page=[\\d\\d]+#new";

    //####################商品########################
    //标题
    public final static String TITLE = "//div[@class='goods-intro fr']/div/div/h3/text()";
    //商品描述
    public final static String DESCRIBE = "//div[@class='goods-intro fr']/div[@class='intro']/p/text()";
    //券后价
    public final static String QUANHOUJIA = "//div[@class='intro1']/ul/li[@class='tro1 fl']/span/text()";
    //在售价
    public final static String ZAISHOUJIA = "//div[@class='intro1']/ul/li[@class='tro2 fl']/text()";
    //优惠券
    public final static String YOUHUIQUAN = "//div[@class='intro2 clearfix']/p[@class='int1 fl']/span/text()";
    //优惠券备注
    public final static String YOUHUIQUANBEIZHU = "//div[@class='intro2 clearfix']/p[@class='int2 fl']/text()";
    //佣金
    public final static String YONGJIN = "//div[@class='intro4-left fl']/ul/li[@class='intr1']/span/text()";
    //优惠券PC
    public final static String PCYOUHUIHREF = "//div[@class='intro4-left fl']/p[1]/a[1]/@href";
    //优惠券手机
    public final static String PHONEYOUHUIHREF = "//div[@class='intro4-left fl']/p[1]/a[2]/@href";
    //商品链接
    public final static String GOODSHREF = "//div[@class='intro4-left fl']/p[2]/a/text()";
    //文案
    public final static String WENAN = "//div[@id='wenan']";


}
