import com.cjwx.titan.crawler.crawler.CrawlConfig;
import com.cjwx.titan.crawler.fetcher.PageFetcher;
import com.cjwx.titan.crawler.util.ThreadUtil;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;

import java.io.*;

/**
 * Created by CJWX on 2016/4/5.
 */
public class SimpleClient {

    private static Boolean continueThread = false;
    private static String username;
    private static String password;
    private static String captcha;

    public static void main(String[] args) throws IOException {

        CrawlConfig config = new CrawlConfig();
        PageFetcher pageFetcher = new PageFetcher(config);

        HttpGet httpGet = new HttpGet("http://tieba.baidu.com/cgi-bin/genimg?captchaservice346562644171366e4c2f2b484c6d644a427447484a2b4f32777842537352464d31542b5078705776796d6f476d4d54577575326b74574f6d6e6b394c4a32566c716d4257624d46627a75384c685a436f71484639335032454c554f534c4a41754a7a643575792b4d765a696c6735326c5263494655676368584a6a5a45484d70725a38633874496b3045444968354d6d377a726472434f4a3059655747713935514e726259577936586a78777a7375733776584d6c35325765416f7031587855767374514b6b764863782b66377941584c5858506d4167662f345a51484e467633726a426d31796739666c4446327a52465a67516a746f6a7a52725461724b2f7566543036783374765570746b773253637847546839383469316f755a6f6d78386375746e73723655543341713978726d584141&t=0.21561589408168358");
        HttpResponse response = pageFetcher.getHttpClient().execute(httpGet);
        ByteArrayOutputStream ops = new ByteArrayOutputStream();
        response.getEntity().writeTo(ops);
        UserLogin userLogin = new UserLogin(ops.toByteArray());
        userLogin.setVisible(true);

        synchronized (continueThread) {
            //如果继续线程为false，则执行循环
            while (continueThread == false) {
                ThreadUtil.sleep(5);
            }
        }

        System.out.println(username + password + captcha);
    }

    public static void setContinueThread(Boolean continueThread) {
        SimpleClient.continueThread = continueThread;
    }

    public static String getUsername() {
        return username;
    }

    public static void setUsername(String username) {
        SimpleClient.username = username;
    }

    public static String getPassword() {
        return password;
    }

    public static void setPassword(String password) {
        SimpleClient.password = password;
    }

    public static String getCaptcha() {
        return captcha;
    }

    public static void setCaptcha(String captcha) {
        SimpleClient.captcha = captcha;
    }
}
