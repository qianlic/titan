package com.cjwx.titan.crawler.url;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;

/**
 * @Description: TLD域名列表文件
 * @Author: qian li
 * @Date: 2018年03月29日 11:02
 */
@Slf4j
public class TLDList {

    private static final String TLD_NAMES_ONLINE_URL = "https://publicsuffix.org/list/effective_tld_names.dat";
    private static final String TLD_NAMES_TXT_FILENAME = "tld-names.txt";

    private static boolean onlineUpdate = false;
    private final Set<String> tldSet = new HashSet<>(10000);

    private static final TLDList instance = new TLDList();

    private TLDList() {
        if (onlineUpdate) {
            try (InputStream stream = new URL(TLD_NAMES_ONLINE_URL).openStream()) {
                log.debug("Fetching the most updated TLD list online");
                int n = readStream(stream);
                log.info("Obtained {} TLD from URL {}", n, TLD_NAMES_ONLINE_URL);
                return;
            } catch (Exception e) {
                log.error("Couldn't fetch the online list of TLDs from: {}", TLD_NAMES_ONLINE_URL, e);
            }
        }
        try (InputStream tldFile = getClass().getClassLoader().getResourceAsStream(TLD_NAMES_TXT_FILENAME)) {
            int n = readStream(tldFile);
            log.info("Obtained {} TLD from packaged file {}", n, TLD_NAMES_TXT_FILENAME);
        } catch (IOException e) {
            log.error("Couldn't read the TLD list from file");
            throw new RuntimeException(e);
        }
    }

    private int readStream(InputStream stream) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(stream))) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty() || line.startsWith("//")) {
                    continue;
                }
                tldSet.add(line);
            }
        } catch (IOException e) {
            log.warn("Error while reading TLD-list: {}", e.getMessage());
        }
        return tldSet.size();
    }

    public static TLDList getInstance() {
        return instance;
    }

    public static void setUseOnline(boolean online) {
        onlineUpdate = online;
    }

    public boolean contains(String str) {
        return tldSet.contains(str);
    }

}
