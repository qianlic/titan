import java.util.Set;
import java.util.regex.Pattern;

import com.cjwx.titan.crawler.WebCrawler;
import com.cjwx.titan.crawler.fetcher.Page;
import com.cjwx.titan.crawler.parser.HtmlParseData;
import com.cjwx.titan.crawler.url.WebURL;

public class MyCrawler extends WebCrawler{

	private static final Pattern filters = Pattern.compile(".*(\\.(css|js|mid|mp2|mp3|mp4|wav|avi|mov|mpeg|ram|m4v|pdf|rm|smil|wmv|swf|wma|zip|rar|gz))$");
	
	//根据url进行网页的解析，对返回为TRUE的网页进行抓取。
	@Override
	public boolean shouldVisit(Page referringPage, WebURL weburl) {
		String domain = referringPage.getWebURL().getDomain().toLowerCase();
		String href = weburl.getUrl().toLowerCase();

	    if (filters.matcher(href).matches()) {
	        return false;
	    }else{
			if(weburl.getDomain().toLowerCase().indexOf(domain)==-1) {
				return false;
			}
			return true;
	    }
	}

	//解析网页内容，利用page方法得到网页的内容和属性
	@Override
	public void visit(Page page) {
        String url = page.getWebURL().getUrl();
        System.out.println("URL: " + url);

        if (page.getParseData() instanceof HtmlParseData) {
            HtmlParseData htmlParseData = (HtmlParseData) page.getParseData();
            String title = htmlParseData.getTitle();
            Set<WebURL> links = htmlParseData.getOutgoingUrls();
            System.out.println("Title: " + title);
            System.out.println("Number of outgoing links: " + links.size());
        }
	}
	
}
