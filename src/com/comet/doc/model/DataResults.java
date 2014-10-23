package com.comet.doc.model;

import java.util.List;

/**
 * 结果数据DataResults用于手机移动与协同办公每次交互复杂数据类型转换为Json串的封装
 * 测试Java程序可以将该json串还原为DataResults
 * 对于比较复杂的移动平台提交，移动端也可以传递该复杂json串
 */
public class DataResults implements java.io.Serializable {

    private static final long serialVersionUID = 4892860749631884510L;
    private String resultDataId = null;		//结果数据ID
    private String resultDataName = null;	//结果数据名称
    private String resultDataType = null;	//结果数据分类： 1  -1
    private String resultDataTime = null;	//服务器时间：2012-10-10 00:00:00
    private String resultDataDesc = null;	//结果数据描述  error
    private Integer rowcount = null;	//全部记录数，比如：99
    private Integer resultscount = null;	//每次返回记录数，比如：10 小于等于每页显示数
    private Integer nextpage = null;	//显示下一页页号
    private Object resultList=null;	//结果集数据列表
    private Object resultListInterpreter=null;	//结果集数据字段解释
    private List extendParams=null;	//扩展参数

    /**
     * if (allcount > (_baseDisplayCount * _pageNum)) {
     outInfo.set("nextPage", String.valueOf(_pageNum +1));//下次请求的页码
     }else outInfo.set("nextPage", "0");//下次请求的页码
     * @return
     */
    public List getExtendParams() {
        return extendParams;
    }
    public void setExtendParams(List extendParams) {
        this.extendParams = extendParams;
    }
    public Integer getNextpage() {
        return nextpage;
    }
    public void setNextpage(Integer nextpage) {
        this.nextpage = nextpage;
    }
    public String getResultDataDesc() {
        return resultDataDesc;
    }
    public void setResultDataDesc(String resultDataDesc) {
        this.resultDataDesc = resultDataDesc;
    }
    public String getResultDataId() {
        return resultDataId;
    }
    public void setResultDataId(String resultDataId) {
        this.resultDataId = resultDataId;
    }
    public String getResultDataName() {
        return resultDataName;
    }
    public void setResultDataName(String resultDataName) {
        this.resultDataName = resultDataName;
    }
    public String getResultDataTime() {
        return resultDataTime;
    }
    public void setResultDataTime(String resultDataTime) {
        this.resultDataTime = resultDataTime;
    }
    public String getResultDataType() {
        return resultDataType;
    }
    public void setResultDataType(String resultDataType) {
        this.resultDataType = resultDataType;
    }
    public Object getResultList() {
        return resultList;
    }
    public void setResultList(Object  resultList) {
        this.resultList = resultList;
    }
    public Integer getRowcount() {
        return rowcount;
    }
    public void setRowcount(Integer rowcount) {
        this.rowcount = rowcount;
    }
    public Integer getResultscount() {
        return resultscount;
    }
    public void setResultscount(Integer resultscount) {
        this.resultscount = resultscount;
    }
    public Object getResultListInterpreter() {
        return resultListInterpreter;
    }
    public void setResultListInterpreter(Object resultListInterpreter) {
        this.resultListInterpreter = resultListInterpreter;
    }

}