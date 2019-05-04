package com.kunyao.dubbo.rest.support;


import org.apache.dubbo.config.annotation.Service;

@Service(protocol={"rest","dubbo"},retries = 0,timeout = 30000)
public class DubboAndRestService extends RestService {
}
