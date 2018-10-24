package com.kunyao.dubbo.config.base;


import com.kunyao.dubbo.config.annotation.ServiceExtension;

@ServiceExtension(timeout=30000,version="1.0.0",protocol={"dubbo"})
public abstract class DefaultConfigService {

}
