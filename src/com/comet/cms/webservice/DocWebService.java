package com.comet.cms.webservice;

import javax.jws.WebService;

/**
 * Created by dell on 2014/10/24.
 */
@WebService
public interface DocWebService {
    public String login(String userid,String password) throws Exception ;
}
