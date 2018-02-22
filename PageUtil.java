package com.trace.app.framework.utils;
import java.util.List;

/**pageUtil类用来处理分页的各种参数
 * core sql:SELECT * FROM Table LIMIT (currentPage-1)*pageSize,pageSize;
 * Created by Jin on 2016/07/08 .
 */
public class PageUtil{
    private List records; //db传过来
    private int totalRecord;//db传过来
    private  int currentPage;//前台传过来

    private  int pageSize ; //前台传---(每页显示x条,默认设定10,可调用时修改)
    private  int totalPage;//totalRecord%pageSize==0?totalRecord/pageSize:(totalRecord/pageSize+1)
    private  int indexPage;//sql语句limit开始的数字-->(currentPage-1)*pageSize

    //默认的构造方法,传入需要的参数然后计算部分数据
    public PageUtil(int totalRecord, int currentPage,int pageSize) {
        this.totalRecord = totalRecord;
        this.currentPage = currentPage;
        this.pageSize = pageSize;
        //calc totalPage & indexPage
        totalPage= totalRecord%pageSize==0?totalRecord/pageSize:(totalRecord/pageSize+1);
        indexPage=(currentPage-1)*pageSize;
    }

    public PageUtil() {
    }

    public List getRecords() {
        return records;
    }

    public void setRecords(List records) {
        this.records = records;
    }

    public int getTotalRecord() {
        return totalRecord;
    }

    public void setTotalRecord(int totalRecord) {
        this.totalRecord = totalRecord;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getIndexPage() {
        return indexPage;
    }

    public void setIndexPage(int indexPage) {
        this.indexPage = indexPage;
    }
}
