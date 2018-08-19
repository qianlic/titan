package com.cjwx.titan.crawler.url;

//分解URL
public class UrlResolver {
    public static String resolveUrl(final String baseUrl, final String relativeUrl) {
        if (baseUrl == null) {
            throw new IllegalArgumentException("Base URL must not be null");
        }
        if (relativeUrl == null) {
            throw new IllegalArgumentException("Relative URL must not be null");
        }
        final Url url = resolveUrl(parseUrl(baseUrl.trim()), relativeUrl.trim());
        return url.toString();
    }

    private static int indexOf(final String s, final char searchChar, final int beginIndex, final int endIndex) {
        for (int i = beginIndex; i < endIndex; i++) {
            if (s.charAt(i) == searchChar) {
                return i;
            }
        }
        return -1;
    }

    private static Url parseUrl(final String spec) {
        final Url url = new Url();
        int startIndex = 0;
        int endIndex = spec.length();

        final int crosshatchIndex = indexOf(spec, '#', startIndex, endIndex);
        if (crosshatchIndex >= 0) {
            url.fragment_ = spec.substring(crosshatchIndex + 1, endIndex);
            endIndex = crosshatchIndex;
        }

        final int colonIndex = indexOf(spec, ':', startIndex, endIndex);
        if (colonIndex > 0) {
            final String scheme = spec.substring(startIndex, colonIndex);
            if (isValidScheme(scheme)) {
                url.scheme_ = scheme;
                startIndex = colonIndex + 1;
            }
        }

        final int locationStartIndex;
        int locationEndIndex;

        if (spec.startsWith("//", startIndex)) {
            locationStartIndex = startIndex + 2;
            locationEndIndex = indexOf(spec, '/', locationStartIndex, endIndex);
            if (locationEndIndex >= 0) {
                startIndex = locationEndIndex;
            }
        } else {
            locationStartIndex = -1;
            locationEndIndex = -1;
        }

        final int questionMarkIndex = indexOf(spec, '?', startIndex, endIndex);

        if (questionMarkIndex >= 0) {
            if ((locationStartIndex >= 0) && (locationEndIndex < 0)) {
                locationEndIndex = questionMarkIndex;
                startIndex = questionMarkIndex;
            }
            url.query_ = spec.substring(questionMarkIndex + 1, endIndex);
            endIndex = questionMarkIndex;
        }

        final int semicolonIndex = indexOf(spec, ';', startIndex, endIndex);

        if (semicolonIndex >= 0) {
            if ((locationStartIndex >= 0) && (locationEndIndex < 0)) {
                locationEndIndex = semicolonIndex;
                startIndex = semicolonIndex;
            }
            url.parameters_ = spec.substring(semicolonIndex + 1, endIndex);
            endIndex = semicolonIndex;
        }

        if ((locationStartIndex >= 0) && (locationEndIndex < 0)) {
            locationEndIndex = endIndex;
        } else if (startIndex < endIndex) {
            url.path_ = spec.substring(startIndex, endIndex);
        }
        if ((locationStartIndex >= 0) && (locationEndIndex >= 0)) {
            url.location_ = spec.substring(locationStartIndex, locationEndIndex);
        }
        return url;
    }

    private static boolean isValidScheme(final String scheme) {
        final int length = scheme.length();
        if (length < 1) {
            return false;
        }
        char c = scheme.charAt(0);
        if (!Character.isLetter(c)) {
            return false;
        }
        for (int i = 1; i < length; i++) {
            c = scheme.charAt(i);
            if (!Character.isLetterOrDigit(c) && (c != '.') && (c != '+') && (c != '-')) {
                return false;
            }
        }
        return true;
    }

    private static Url resolveUrl(final Url baseUrl, final String relativeUrl) {
        final Url url = parseUrl(relativeUrl);

        if (baseUrl == null) {
            return url;
        }

        if (relativeUrl.isEmpty()) {
            return new Url(baseUrl);
        }

        if (url.scheme_ != null) {
            return url;
        }

        url.scheme_ = baseUrl.scheme_;

        if (url.location_ != null) {
            return url;
        }
        url.location_ = baseUrl.location_;

        if ((url.path_ != null) && ((!url.path_.isEmpty()) && (url.path_.charAt(0) == '/'))) {
            url.path_ = removeLeadingSlashPoints(url.path_);
            return url;
        }

        if (url.path_ == null) {
            url.path_ = baseUrl.path_;

            if (url.parameters_ != null) {
                return url;
            }
            url.parameters_ = baseUrl.parameters_;

            if (url.query_ != null) {
                return url;
            }
            url.query_ = baseUrl.query_;
            return url;
        }

        final String basePath = baseUrl.path_;
        String path = "";

        if (basePath != null) {
            final int lastSlashIndex = basePath.lastIndexOf('/');

            if (lastSlashIndex >= 0) {
                path = basePath.substring(0, lastSlashIndex + 1);
            }
        } else {
            path = "/";
        }
        path = path.concat(url.path_);

        int pathSegmentIndex;

        while ((pathSegmentIndex = path.indexOf("/./")) >= 0) {
            path = path.substring(0, pathSegmentIndex + 1).concat(path.substring(pathSegmentIndex + 3));
        }

        if (path.endsWith("/.")) {
            path = path.substring(0, path.length() - 1);
        }

        while ((pathSegmentIndex = path.indexOf("/../")) > 0) {
            final String pathSegment = path.substring(0, pathSegmentIndex);
            final int slashIndex = pathSegment.lastIndexOf('/');

            if (slashIndex < 0) {
                continue;
            }
            if (!"..".equals(pathSegment.substring(slashIndex))) {
                path = path.substring(0, slashIndex + 1).concat(path.substring(pathSegmentIndex + 4));
            }
        }

        if (path.endsWith("/..")) {
            final String pathSegment = path.substring(0, path.length() - 3);
            final int slashIndex = pathSegment.lastIndexOf('/');

            if (slashIndex >= 0) {
                path = path.substring(0, slashIndex + 1);
            }
        }

        path = removeLeadingSlashPoints(path);

        url.path_ = path;

        return url;
    }

    private static String removeLeadingSlashPoints(String path) {
        while (path.startsWith("/..")) {
            path = path.substring(3);
        }

        return path;
    }

    private static class Url {

        String scheme_;
        String location_;
        String path_;
        String parameters_;
        String query_;
        String fragment_;

        public Url() {
        }

        public Url(final Url url) {
            scheme_ = url.scheme_;
            location_ = url.location_;
            path_ = url.path_;
            parameters_ = url.parameters_;
            query_ = url.query_;
            fragment_ = url.fragment_;
        }

        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder();

            if (scheme_ != null) {
                sb.append(scheme_);
                sb.append(':');
            }
            if (location_ != null) {
                sb.append("//");
                sb.append(location_);
            }
            if (path_ != null) {
                sb.append(path_);
            }
            if (parameters_ != null) {
                sb.append(';');
                sb.append(parameters_);
            }
            if (query_ != null) {
                sb.append('?');
                sb.append(query_);
            }
            if (fragment_ != null) {
                sb.append('#');
                sb.append(fragment_);
            }
            return sb.toString();
        }
    }
}
