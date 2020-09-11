package com.cjwx.spark.engine.util;

import com.cjwx.spark.engine.core.constant.HttpConstant;
import com.cjwx.spark.engine.core.exception.ServiceException;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @Description: 字符串操作工具类
 * @Author: qian li
 * @Date: 2018年03月29日 11:02
 */
public class StringUtils {

    public static final String NULL_STRING = "";
    public static final String SPACE_STRING = " ";
    public static final String COMMA_STRING = ",";

    public static final String EMAIL_PATTERN = "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$";
    public static final String NUMBER_PATTERN = "[0-9]*";
    public static final String MOBILE_PATTERN = "^1[358]\\d{9}$";
    public static final String IDCARD_PATTERN = "^(\\d{15}|(\\d{17}[xX\\d]))$";
    public static final String HTTP_PATTERN = "http://";
    public static final String HTTPS_PATTERN = "https://";
    public final static String SPECIAL_PATTERN = "[`~!$%^&*()|''\\<>~\"]";

    /**
     * 判断字符串是否为空字符串
     */
    public static boolean isEmpty(String string) {
        return trim(string).length() == 0 || "null".equalsIgnoreCase(string);
    }

    /**
     * 判断字符串是否为空字符串
     */
    public static boolean isNotEmpty(String string) {
        return !isEmpty(string);
    }

    /**
     * 判断字符串是否为空
     */
    public static boolean isNull(String string) {
        return string == null;
    }

    /**
     * 检查字符串是否为空返回空字符串
     */
    public static String nullToString(String string) {
        return isNull(string) ? NULL_STRING : string;
    }

    /**
     * 去掉字符前后的空格
     */
    public static String trim(String string) {
        return isNull(string) ? NULL_STRING : string.trim();
    }

    /**
     * 字符串字符集转换 UTF-8
     */
    public static String convert(String target) {
        return convert(target, HttpConstant.DEFAULT_CHARSET);
    }

    /**
     * 字符串字符集转换
     */
    public static String convert(String target, String charset) {
        return convert(target, "ISO-8859-1", charset);
    }

    /**
     * 字符串字符集转换
     */
    public static String convert(String target, String charset1, String charset2) {
        try {
            return new String(stringToByte(target, charset1), charset2);
        } catch (UnsupportedEncodingException e) {
            throw new ServiceException("字符串转换字符集 [" + target + COMMA_STRING + charset2 + "] 失败", e);
        }
    }

    public static String encodeURLEncoder(String target) {
        try {
            return URLEncoder.encode(target, HttpConstant.DEFAULT_CHARSET);
        } catch (UnsupportedEncodingException e) {
            throw new ServiceException("字符串 [" + target + "] 编码失败", e);
        }
    }

    public static String decodeURLEncoder(String target) {
        try {
            return URLDecoder.decode(target, HttpConstant.DEFAULT_CHARSET);
        } catch (UnsupportedEncodingException e) {
            throw new ServiceException("字符串 [" + target + "] 编码失败", e);
        }
    }

    /**
     * 字符串首字符大写
     */
    public static String upperCaseFirst(String str) {
        char[] ch = str.toCharArray();
        if (ch[0] >= 'a' && ch[0] <= 'z') {
            ch[0] = (char) (ch[0] - 32);
        }
        return new String(ch);
    }

    /**
     * 判断字符串是否符合表达式
     */
    public static boolean isMatches(String string, String pattern) {
        return isNotEmpty(string) && string.matches(pattern);
    }

    /**
     * 检查字符串是否为邮箱格式
     */
    public static boolean isEmail(String string) {
        return isMatches(string, EMAIL_PATTERN);
    }

    /**
     * 判断字符串是否为数字
     */
    public static boolean isNumber(String string) {
        return isMatches(string, NUMBER_PATTERN);
    }

    /**
     * 判断字符串是否为手机号
     */
    public static boolean isMobile(String string) {
        return isMatches(string, MOBILE_PATTERN);
    }

    /**
     * 判断字符串是否为身份证号
     */
    public static boolean isIdCard(String string) {
        //身份证校验位
        String[] check_digit = {"1", "0", "X", "9", "8", "7", "6", "5", "4", "3", "2"};
        //身份证加权因子
        int[] gene = {7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2, 1};
        if (isMatches(string, IDCARD_PATTERN)) {
            if (string.length() == 18) {
                int yearPrefix = Integer.parseInt(string.substring(6, 8));
                if (yearPrefix < 19 || yearPrefix > 21) {
                    return false;
                }
                int month = Integer.parseInt(string.substring(10, 12));
                if (month > 12 || month == 0) {
                    return false;
                }
                int day = Integer.parseInt(string.substring(12, 14));
                if (day > 31 || day == 0) {
                    return false;
                }
                int sum = 0;
                for (int i = 0; i < 17; i++) {
                    sum += Integer.parseInt(string.substring(i, i + 1)) * gene[i];
                }
                String checkDigit = check_digit[sum % 11];
                return checkDigit.equals(string.substring(17, 18).toUpperCase());
            } else if (string.length() == 15) {
                int month = Integer.parseInt(string.substring(8, 10));
                if (month > 12 || month == 0) {
                    return false;
                }
                int day = Integer.parseInt(string.substring(10, 12));
                if (day > 31 || day == 0) {
                    return false;
                }
                return true;
            }
        }
        return false;
    }

    /**
     * 验证url是否是已http://,https://开头
     */
    public static boolean isProtocol(String string) {
        return HTTP_PATTERN.equals(string.substring(0, 7)) || HTTPS_PATTERN.equals(string.substring(0, 8));
    }

    /**
     * 验证string是否包含特殊字符
     */
    public static boolean isSpecialCharacter(String string) {
        return isMatches(string, SPECIAL_PATTERN);
    }

    /**
     * 字符串转Byte  字符集UTF-8
     */
    public static byte[] stringToByte(String source) {
        return stringToByte(source, HttpConstant.DEFAULT_CHARSET);
    }

    /**
     * 字符串转Byte
     */
    public static byte[] stringToByte(String source, String encoding) {
        try {
            return nullToString(source).getBytes(encoding);
        } catch (UnsupportedEncodingException e) {
            throw new ServiceException("字符串转换Byte数组 [" + source + COMMA_STRING + encoding + "] 失败", e);
        }
    }

    /**
     * 字符串转LIST  逗号分隔
     */
    public static List<String> stringToList(String source) {
        return stringToList(source, COMMA_STRING);
    }

    /**
     * 字符串转LIST
     */
    public static List<String> stringToList(String source, String split) {
        if (isNotEmpty(source)) {
            return Arrays.asList(source.split(split));
        }
        return null;
    }

    /**
     * LIST转字符串  逗号分隔
     */
    public static String listToString(List<String> source) {
        return listToString(source, COMMA_STRING);
    }

    /**
     * LIST转字符串
     */
    public static String listToString(List<String> source, String split) {
        return listToString(source, split, split);
    }

    /**
     * LIST转字符串
     */
    public static String listToString(List<String> source, String split, String connect) {
        if (ObjectUtils.isNotEmpty(source)) {
            return source.stream().map(s -> {
                List<String> ss = StringUtils.stringToList(s, split);
                if (ObjectUtils.isNotEmpty(ss)) {
                    return ss.stream().map(StringUtils::trim).collect(Collectors.joining(connect));
                }
                return StringUtils.NULL_STRING;
            }).filter(StringUtils::isNotEmpty).collect(Collectors.joining(connect));
        }
        return NULL_STRING;
    }


    /**
     * 生成随机字符串
     */
    public static String getRandomString(int length, char[] character) {
        Random random = new Random();
        StringBuilder sb = new StringBuilder(length);
        IntStream.range(0, length).forEach(idx -> {
            int n = random.nextInt(character.length);
            sb.append(character[n]);
        });
        return sb.toString();
    }

    /**
     * 生成随机字符串
     */
    public static String getRandomCaptha(int length) {
        char[] character = {
                '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J',
                'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T',
                'U', 'V', 'W', 'X', 'Y', 'Z'
        };
        return getRandomString(length, character);
    }

    /**
     * 生成随机数字字符串
     */
    public static String getNumCaptha(int length) {
        char[] character = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
        return getRandomString(length, character);
    }


    /**
     * 从文件路径中截取出文件名字
     */
    public static String getFileNameFromPath(String fileFullPath) {
        if (fileFullPath.contains("\\")) {
            fileFullPath = fileFullPath.substring(fileFullPath.lastIndexOf("\\") + 1, fileFullPath.length());
        }
        return fileFullPath;
    }

    /**
     * 字符串匹配
     */
    public static String getAttr(String source, String start, String end) {
        Pattern pattern = Pattern.compile("(?<=(" + start + "))[.\\s\\S]*?(?=(" + end + "))");
        Matcher matcher = pattern.matcher(source);
        while (matcher.find()) {
            return matcher.group();
        }
        return NULL_STRING;
    }

    /**
     * 获取UUID
     */
    public static String randomUUID() {
        return UUID.randomUUID().toString();
    }

}
