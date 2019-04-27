package com.kunyao.dubbo.rest.support;


import org.apache.dubbo.config.annotation.Service;

@Service(protocol={"rest","dubbo"})
public class BaseDubboAndRestService extends BaseRestService{
}
