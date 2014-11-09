package com.comet.cms.webservice;

import javax.jws.WebService;

/**
 * Created by dell on 2014/10/24.
 */
@WebService
public interface DocWebService {
    public String login(String userid,String password) throws Exception ;

    public String reBack(String currentUserLabel, String taskId, String fileId,String commentContent,String whereSQL) throws Exception ;

    public String getfilekind();

    public String getDataMessageList(String corpID, String empId, String type,
                                     String synDate, String rowsOfpage, String numpage, String whereSQL)
            throws Exception;

    public String findDoneTasks2(String empId, String synDate,
                                 String rowsOfpage, String numpage, String whereSQL,
                                 String receiveCorp, String filekind1, String fileTitle,
                                 String fileCode, String stdt1, String stdt2) throws Exception;
}
