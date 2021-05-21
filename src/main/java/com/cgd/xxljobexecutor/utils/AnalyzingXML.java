package com.cgd.xxljobexecutor.utils;

import com.cgd.xxljobexecutor.model.XmlDTO;
import lombok.extern.slf4j.Slf4j;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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
    public static List<XmlDTO> AnalyzingXML(String xml) throws Exception{
        Document doc = null;
        List<XmlDTO> list = null;
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
                list.add(new XmlDTO(url,time));
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }
}
