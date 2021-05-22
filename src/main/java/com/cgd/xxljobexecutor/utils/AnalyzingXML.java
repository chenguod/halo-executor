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
     * @param url
     * @return
     * @throws Exception
     */
    public static List<XmlDTO> AnalyzingXML(String url) throws Exception{
        Document doc = null;
        String xml = HttpRequestUtil.sendGet(url,"");
        List<XmlDTO> list = new ArrayList<>();
        try {
            // 将字符串转为XML
            doc = DocumentHelper.parseText(xml);
            // 获取根节点
            Element Response = doc.getRootElement();
            Iterator<?> Report = Response.elementIterator("url");
            while (Report.hasNext()) {
                Element tableItem = (Element) Report.next();
                String loc = tableItem.elementTextTrim("loc");
                String lastmod = tableItem.elementTextTrim("lastmod").substring(0,10);
                list.add(new XmlDTO(loc,lastmod));
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }
}
