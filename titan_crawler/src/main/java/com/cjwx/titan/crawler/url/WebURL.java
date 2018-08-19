package com.cjwx.titan.crawler.url;

import lombok.Data;

import java.io.Serializable;

@Data
public class WebURL implements Serializable {
    private static final long serialVersionUID = 1L;
    private String url;

    private int docid;
    private int parentDocid;
    private String parentUrl;
    private short depth;
    private String domain;
    private String subDomain;
    private String path;
    private String anchor;
    private byte priority;
    private String tag;

    public void setURL(String url) {
        this.url = url;

        int domainStartIdx = url.indexOf("//") + 2;
        int domainEndIdx = url.indexOf('/', domainStartIdx);
        domainEndIdx = (domainEndIdx > domainStartIdx) ? domainEndIdx : url.length();
        domain = url.substring(domainStartIdx, domainEndIdx);
        subDomain = "";
        String[] parts = domain.split("\\.");
        if (parts.length > 2) {
            domain = parts[parts.length - 2] + "." + parts[parts.length - 1];
            int limit = 2;
            if (TLDList.getInstance().contains(domain)) {
                domain = parts[parts.length - 3] + "." + domain;
                limit = 3;
            }
            for (int i = 0; i < (parts.length - limit); i++) {
                if (!subDomain.isEmpty()) {
                    subDomain += ".";
                }
                subDomain += parts[i];
            }
        }
        path = url.substring(domainEndIdx);
        int pathEndIdx = path.indexOf('?');
        if (pathEndIdx >= 0) {
            path = path.substring(0, pathEndIdx);
        }
    }

    @Override
    public int hashCode() {
        return url.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if ((o == null) || (getClass() != o.getClass())) {
            return false;
        } else if (this == o) {
            return true;
        }
        WebURL otherUrl = (WebURL) o;
        return (url != null) && url.equals(otherUrl.getUrl());
    }

    @Override
    public String toString() {
        return url;
    }
}
