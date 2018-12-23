package com.kunyao.dubbo.config.base;


import org.apache.dubbo.config.annotation.Service;

@Service(timeout=30000,version="1.0.0",protocol={"dubbo"})
public abstract class DefaultConfigService {

}
