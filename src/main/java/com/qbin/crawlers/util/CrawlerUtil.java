package com.qbin.crawlers.util;

import com.qbin.crawlers.common.RuleConst;
import us.codecraft.webmagic.Page;

import java.util.ArrayList;
import java.util.List;

/**
 * 描述：爬虫工具类
 * author qiaobin   2016/10/10 15:37.
 */
public final class CrawlerUtil {

    /**
      * 功能描述：根据xpath查询符合条件列表
      */
    public final static List<String> getLinksList(Page page, String xpath) {
        return page.getHtml().xpath(xpath).links().all();
    }

    /**
     * 功能描述：根据xpath查询值
     */
    public final static String getValue(Page page, String xpath) {
        return page.getHtml().xpath(xpath).get();
    }

    /**
     * 功能描述：是否是主页面
     */
    public static boolean isMainPage(Page page) {
        if (RuleConst.MAINPAGELIST.contains(page.getUrl().toString())) {
            return true;
        }
        return false;
    }

    /**
     * 功能描述：返回商品类型
     */
    public static int goodsType(Page page) {
        int type = 0;
        switch (page.getUrl().toString()) {
            case RuleConst.NVZHUANG : type =  1; break;
            case RuleConst.NANZHUANG : type =  2; break;
            case RuleConst.XIEBAO : type =  3; break;
            case RuleConst.JIAJU : type =  5; break;
            case RuleConst.WENTI : type =  6; break;
            case RuleConst.DIANQI : type =  7; break;
            case RuleConst.PEISHI : type =  8; break;
            case RuleConst.SHUMA : type =  9; break;
            case RuleConst.MEISHI : type =  10; break;
            case RuleConst.MEIZHUANG : type =  11; break;
            case RuleConst.MUYING : type =  12; break;
        }
        return type;
    }

    /**
     * 功能描述：返回分页链接
     */
    public static List<String> getPageUrls(int lastPageNo, String lastPageUrl) {
        List<String> list = new ArrayList<>();
        String head = lastPageUrl.substring(0,lastPageUrl.indexOf("page=")+5);
        String tail = lastPageUrl.substring(lastPageUrl.lastIndexOf("#new"));
        for (int i=1 ; i<=lastPageNo; i++) {
            list.add(head + i + tail);  //拼出所有分页链接
        }
        return list;
    }

    /**
     * 功能描述：最后一页链接
     */
    public static String getLastPageUrl(Page page, String regex) {
        List<String> urls = CrawlerUtil.getLinksList(page, regex);
        return urls.get(urls.size() - 2);
    }

    /**
     * 功能描述：最后一页号
     */
    public static int getLastPageNo(String lastPageUrl) {
        String pageNo = lastPageUrl.substring(lastPageUrl.lastIndexOf("page=")+5,lastPageUrl.lastIndexOf("#new"));
        return Integer.parseInt(pageNo);
    }

}
