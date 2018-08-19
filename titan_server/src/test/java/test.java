import com.cjwx.titan.engine.util.EndecryptUtils;
import org.apache.shiro.codec.Base64;
import org.springframework.util.Base64Utils;

import java.security.SecureRandom;

/**
 * @Description:
 * @Author: qian li
 * @Date: 2018年03月30日 16:12
 */
public class test {

    public static void main(String[] args) {
        System.out.println(EndecryptUtils.createSigningKey());
        System.out.println(EndecryptUtils.createSalt());
        System.out.println(EndecryptUtils.md5Password("123456", "b4882e5810755906cbc16785e75f0aca"));
        System.out.println(EndecryptUtils.shaPassword("123456", "b4882e5810755906cbc16785e75f0aca"));

        byte[] bytes = new byte[16];
        new SecureRandom().nextBytes(bytes);
        System.out.println(Base64Utils.encodeToString(bytes));
        System.out.println(Base64.encodeToString(bytes));
    }

}
