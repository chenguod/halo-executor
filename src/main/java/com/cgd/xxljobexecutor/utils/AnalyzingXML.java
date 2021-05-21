package com.cgd.xxljobexecutor.utils;

import lombok.extern.slf4j.Slf4j;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import java.util.Iterator;

/**
 * @author 晓果冻
 * @version 1.0
 * @date 2021/5/21 7:32
 */
@Slf4j
public class AnalyzingXML {

    /**
     * 解析halo博客xml类型站点地图
     * @param xml
     * @return
     * @throws Exception
     */
    public static Document AnalyzingXML(String xml) throws Exception{
        Document doc = null;
        try {
            // 将字符串转为XML
            doc = DocumentHelper.parseText(xml);
            // 获取根节点
            Element Response = doc.getRootElement();
            Iterator<?> Report = Response.elementIterator("url");
            while (Report.hasNext()) {
                Element tableItem = (Element) Report.next();
                String url = tableItem.elementTextTrim("loc");
                String time = tableItem.elementTextTrim("lastmod");
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }
        return doc;
    }
}
