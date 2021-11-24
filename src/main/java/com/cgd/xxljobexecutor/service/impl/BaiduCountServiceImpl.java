package com.cgd.xxljobexecutor.service.impl;

import com.cgd.xxljobexecutor.dao.executor.SiteTrendAreaDao;
import com.cgd.xxljobexecutor.dao.executor.SiteTrendDao;
import com.cgd.xxljobexecutor.dao.executor.SiteTrendEngineDao;
import com.cgd.xxljobexecutor.dao.executor.SiteTrendSourceDao;
import com.cgd.xxljobexecutor.model.DTO.AreaDTO;
import com.cgd.xxljobexecutor.model.DTO.VisitDTO;
import com.cgd.xxljobexecutor.model.VO.AreaVO;
import com.cgd.xxljobexecutor.model.VO.SourceVO;
import com.cgd.xxljobexecutor.model.VO.VisitVO;
import com.cgd.xxljobexecutor.model.eCharts.*;
import com.cgd.xxljobexecutor.service.BaiduCountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author 晓果冻
 * @version 1.0
 * @date 2021/7/2 18:51
 */
@Service
public class BaiduCountServiceImpl implements BaiduCountService {

    @Autowired
    private SiteTrendDao siteTrendDao;

    @Autowired
    private SiteTrendAreaDao siteTrendAreaDao;

    @Autowired
    private SiteTrendSourceDao siteTrendSourceDao;

    @Autowired
    private SiteTrendEngineDao siteTrendEngineDao;

    @Override
    public VisitVO getVisitData() {
        List<VisitDTO> list = siteTrendDao.selectVisitInfo();
        List<String> monthList = list.stream().map(VisitDTO::getMonth).sorted().collect(Collectors.toList());
        VisitVO vo = new VisitVO();
        vo.setTitle(new Title("站点趋势统计图", "left", "0px", "数据来源于百度统计"));
        vo.setTooltip(new Tooltip("axis"));
        vo.setLegend(new Legend("horizontal",Arrays.asList("浏览量(PV)", "访问次数", "访客数(UV)", "新访客数", "IP数"), "10px","center"));
        vo.setGrid(new Grid("3%", "4%", "3%", true));
        vo.setXAxis(new XAxis("category", false, monthList));
        vo.setYAxis(new YAxis("value"));
        List<Series<Integer>> seriesList = new ArrayList<>();
        List<Integer> pvList = list.stream().map(VisitDTO::getPvCount).collect(Collectors.toList());//浏览量(PV
        List<Integer> visitList = list.stream().map(VisitDTO::getVisitCount).collect(Collectors.toList());//访问次数
        List<Integer> uvList = list.stream().map(VisitDTO::getVisitorCount).collect(Collectors.toList());//访客数(UV)
        List<Integer> newVisitorList = list.stream().map(VisitDTO::getNewVisitorCount).collect(Collectors.toList());//新访客数
        List<Integer> ipList = list.stream().map(VisitDTO::getIpCount).collect(Collectors.toList());//IP数
        seriesList.add(new Series<Integer>("浏览量(PV)", "line", "总量", null, pvList, null));
        seriesList.add(new Series<Integer>("访问次数", "line",  "总量", null,visitList, null));
        seriesList.add(new Series<Integer>("访客数(UV)", "line", "总量",null,  uvList, null));
        seriesList.add(new Series<Integer>("新访客数", "line",  "总量", null,newVisitorList, null));
        seriesList.add(new Series<Integer>("IP数", "line",  "总量",null, ipList, null));
        Feature feature = new Feature(new MagicType(true, Arrays.asList("line", "bar", "stack")), new SaveAsImage("png"), new Restore(true));
        vo.setToolbox(new Toolbox(feature));
        vo.setSeries(seriesList);
        return vo;
    }

    @Override
    public AreaVO getAreaDta() {
        List<AreaDTO> list = siteTrendAreaDao.selectAll();
        AreaVO vo = new AreaVO();
        vo.setTitle(new Title("地域分布Top10", "left", "0px", "数据来源于百度统计"));
        vo.setTooltip(new Tooltip("item"));
        vo.setLegend(new Legend("vertical",null, "60px","left"));
        Feature feature = new Feature(null, new SaveAsImage("png"), new Restore(true));
        vo.setToolbox(new Toolbox(feature));
        List<Series<AreaDTO>> seriesList = new ArrayList<>();
        seriesList.add(new Series<AreaDTO>("来源地区","pie",null,"50%",list,new Emphasis(new ItemStyle(10,0,"rgba(0, 0, 0, 0.5)")),new LabelLine(true,30)));
        vo.setSeries(seriesList);
        return vo;
    }

    @Override
    public SourceVO getSource() {
        List<AreaDTO> dtoList = siteTrendSourceDao.selectAll();
        List<AreaDTO> dtoList1 = siteTrendEngineDao.selectAll();
        SourceVO vo = new SourceVO();
        vo.setTitle(new Title("访问来源", "left", "0px", "数据来源于百度统计"));
        vo.setTooltip(new Tooltip("item","{a} <br/>{b}: {c} ({d}%)"));
        List<String> legend = dtoList1.stream().map(AreaDTO::getName).collect(Collectors.toList());
        vo.setLegend(new Legend("horizontal",legend,"top","120px"));
        List<Series<AreaDTO>> seriesList = new ArrayList<>();
        seriesList.add(new Series<AreaDTO>("访问来源","pie",null,null,dtoList,null,"single",new LabelLine(false,null)));
        seriesList.add(new Series<AreaDTO>("访问来源","pie",null,null,dtoList1,null,null,new LabelLine(true,30)));
        vo.setSeries(seriesList);
        return vo;
    }

    @Override
    public Map getPvCount() {
        return siteTrendDao.selectSitePvCount();
    }
}
