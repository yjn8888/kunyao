package com.kunyao.dubbo.support;


import com.kunyao.dubbo.boot.autoconfigure.DubboProperties;
import org.apache.dubbo.config.annotation.Service;

@Service(protocol={"dubbo"},retries = 0,timeout = 30000)
public abstract class DubboService {

}
