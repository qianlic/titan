package com.cjwx.titan.crawler.parser;

import com.cjwx.titan.crawler.crawler.Configurable;
import com.cjwx.titan.crawler.crawler.CrawlConfig;
import com.cjwx.titan.crawler.crawler.exceptions.NotAllowedContentException;
import com.cjwx.titan.crawler.crawler.exceptions.ParseException;
import com.cjwx.titan.crawler.fetcher.Page;
import com.cjwx.titan.crawler.url.URLCanonicalizer;
import com.cjwx.titan.crawler.url.WebURL;
import com.cjwx.titan.crawler.util.ConvertUtil;
import com.cjwx.titan.crawler.util.NetUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.tika.language.LanguageIdentifier;
import org.apache.tika.metadata.DublinCore;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.html.HtmlParser;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.HashSet;
import java.util.Set;

@Slf4j
public class Parser extends Configurable {

    private final HtmlParser htmlParser;
    private final ParseContext parseContext;

    public Parser(CrawlConfig config) {
        super(config);
        htmlParser = new HtmlParser();
        parseContext = new ParseContext();
    }

    public void parse(Page page, String contextURL) throws NotAllowedContentException, ParseException {
        if (ConvertUtil.hasBinaryContent(page.getContentType())) {
            BinaryParseData parseData = new BinaryParseData();
            if (config.isIncludeBinaryContentInCrawling()) {
                if (config.isProcessBinaryContentInCrawling()) {
                    parseData.setBinaryContent(page.getContentData());
                } else {
                    parseData.setHtml("<html></html>");
                }
                page.setParseData(parseData);
                if (parseData.getHtml() == null) {
                    throw new ParseException();
                }
                parseData.setOutgoingUrls(NetUtil.extractUrls(parseData.getHtml()));
            } else {
                throw new NotAllowedContentException();
            }
        } else if (ConvertUtil.hasPlainHtmlContent(page.getContentType())) {
            Metadata metadata = new Metadata();
            HtmlContentHandler contentHandler = new HtmlContentHandler();
            try (InputStream inputStream = new ByteArrayInputStream(page.getContentData())) {
                htmlParser.parse(inputStream, contentHandler, metadata, parseContext);
            } catch (Exception e) {
                log.error("{}, while parsing: {}", e.getMessage(), page.getWebURL().getUrl());
                throw new ParseException();
            }
            if (page.getContentCharset() == null) {
                page.setContentCharset(metadata.get("Content-Encoding"));
            }
            HtmlParseData parseData = new HtmlParseData();
            parseData.setText(contentHandler.getBodyText().trim());
            parseData.setTitle(metadata.get(DublinCore.TITLE));
            parseData.setMetaTags(contentHandler.getMetaTags());
            LanguageIdentifier languageIdentifier = new LanguageIdentifier(parseData.getText());
            page.setLanguage(languageIdentifier.getLanguage());
            Set<WebURL> outgoingUrls = new HashSet<>();
            String baseURL = contentHandler.getBaseUrl();
            if (baseURL != null) {
                contextURL = baseURL;
            }
            int urlCount = 0;
            for (ExtractedUrlAnchorPair urlAnchorPair : contentHandler.getOutgoingUrls()) {
                String href = urlAnchorPair.getHref();
                if ((href == null) || href.trim().isEmpty()) {
                    continue;
                }
                String hrefLoweredCase = href.trim().toLowerCase();
                if (!hrefLoweredCase.contains("javascript:")
                        && !hrefLoweredCase.contains("mailto:")
                        && !hrefLoweredCase.contains("@")) {
                    String url = URLCanonicalizer.getCanonicalURL(href, contextURL);
                    if (url != null) {
                        WebURL webURL = new WebURL();
                        webURL.setURL(url);
                        webURL.setTag(urlAnchorPair.getTag());
                        webURL.setAnchor(urlAnchorPair.getAnchor());
                        outgoingUrls.add(webURL);
                        urlCount++;
                        if (urlCount > config.getMaxOutgoingLinksToFollow()) {
                            break;
                        }
                    }
                }
            }
            parseData.setOutgoingUrls(outgoingUrls);
            try {
                if (page.getContentCharset() == null) {
                    parseData.setHtml(new String(page.getContentData()));
                } else {
                    parseData.setHtml(new String(page.getContentData(), page.getContentCharset()));
                }
                page.setParseData(parseData);
            } catch (UnsupportedEncodingException e) {
                log.error("error parsing the html: " + page.getWebURL().getUrl(), e);
                throw new ParseException();
            }
        } else {
            try {
                TextParseData parseData = new TextParseData();
                if (page.getContentCharset() == null) {
                    parseData.setTextContent(new String(page.getContentData()));
                } else {
                    parseData.setTextContent(new String(page.getContentData(), page.getContentCharset()));
                }
                parseData.setOutgoingUrls(NetUtil.extractUrls(parseData.getTextContent()));
                page.setParseData(parseData);
            } catch (Exception e) {
                log.error("{}, while parsing: {}", e.getMessage(), page.getWebURL().getUrl());
                throw new ParseException();
            }
        }
    }
}
