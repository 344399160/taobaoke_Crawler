package com.qbin.crawlers;

import com.qbin.crawlers.util.PicDownloader;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CrawlerApplicationTests {

	@Test
	public void contextLoads() {
		String url = "https://ss2.baidu.com/73Z1bjeh1BF3odCf/it/u=3890329721,966988116&fm=202";
		PicDownloader.download(url, "a.jpg", "E:/picture");
	}

	@Test
	public void tee() {
		String lastPageUrl = "http://www.taokezhushou.com/cate/2?page=3#new";
		String test = lastPageUrl.substring(lastPageUrl.lastIndexOf("page=")+5,lastPageUrl.lastIndexOf("#new"));
		String head = lastPageUrl.substring(0,lastPageUrl.indexOf("page=")+5);
		String tail = lastPageUrl.substring(lastPageUrl.lastIndexOf("#new"));
		System.out.println(test);
		System.out.println(head);
		System.out.println(tail);
	}

}
