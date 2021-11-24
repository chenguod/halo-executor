package com.cgd.xxljobexecutor.utils;

import lombok.extern.slf4j.Slf4j;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;

/**
 * @author 晓果冻
 * @version 1.0
 * @date 2021/10/24 14:25
 */
@Slf4j
public class CheckLinks {

    public static String UrlWithTime(String urlString, int timeOutMillSeconds) throws Exception {
        long lo = System.currentTimeMillis();
        URL url = new URL(urlString);
        URLConnection co = url.openConnection();
        co.setConnectTimeout(timeOutMillSeconds);
        co.connect();
        return "访问网站：" + urlString + " 需要" + (System.currentTimeMillis() - lo) + "毫秒";

    }

    public static String isValidUrl(String incommingString) throws Exception {
        URL urlObj = new URL(incommingString);
        URI uriObj = new URI(urlObj.getProtocol(), urlObj.getHost(), urlObj.getPath(), urlObj.getQuery(), null);
        String scheme = uriObj.getScheme();
        if (!urlHasAcceptableScheme(incommingString)) {
            if (scheme != null) {
                throw new URISyntaxException("", "");
            }
        }
        return incommingString;
    }

    private static boolean urlHasAcceptableScheme(String url) {
        if (url == null) {
            return false;
        }
        for (int i = 0; i < acceptableSchemes.length; i++) {
            if (url.startsWith(acceptableSchemes[i])) {
                return true;
            }
        }
        return false;
    }

    private static final String acceptableSchemes[] = {
            "http:",
            "https:",
            "file:"
    };
}
