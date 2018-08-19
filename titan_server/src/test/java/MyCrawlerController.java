import com.cjwx.titan.crawler.crawler.CrawlConfig;
import com.cjwx.titan.crawler.crawler.CrawlController;

public class MyCrawlerController {

    public static void main(String[] args) {

        //并发线程数
        final int numberOfCrawlers = 5;

        CrawlConfig config = new CrawlConfig();

        final CrawlController crawlController = new CrawlController(config);

        crawlController.addSeed("http://bbs.cs090.com/");
        crawlController.start(MyCrawler.class, numberOfCrawlers);

    }

}
