package com.cjwx.titan.engine.util.file;

import com.cjwx.titan.engine.core.exception.ServiceException;

/**
 * XmlUtils
 *
 * @author Qian Li
 * @date 2017/3/21
 */
public abstract class XmlUtils {

    /**
     * 获取XML标签之间的属性
     *
     * @param bs  xml内容
     * @param att 标签名称
     * @return 标签之间的属性
     */
    public static String getXMLAtt(String bs, String att) {
        String result = "";
        try {
            String head = "<" + att + ">";
            String tail = "</" + att + ">";
            result = bs.substring(bs.indexOf(head) + head.length(), bs.indexOf(tail));
        } catch (Exception e) {
            throw new ServiceException("获取xml标签 [" + att + "] 失败", e);
        }
        return result;
    }

    /**
     * 获取XML标签以及属性
     *
     * @param bs  xml内容
     * @param att 标签名称
     * @return 标签以及属性
     */
    public static String getXMLAttOut(String bs, String att) {
        String bs1 = getXMLAtt(bs, att);
        return "<" + att + ">" + bs1 + "</" + att + ">";
    }

}
