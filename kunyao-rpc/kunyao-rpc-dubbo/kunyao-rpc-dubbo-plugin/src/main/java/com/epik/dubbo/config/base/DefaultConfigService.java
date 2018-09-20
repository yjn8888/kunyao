package com.epik.dubbo.config.base;


import com.epik.dubbo.config.annotation.ServiceExtension;

@ServiceExtension(timeout=60000,version="1.0.0",protocol={"dubbo"})
public abstract class DefaultConfigService {

}
