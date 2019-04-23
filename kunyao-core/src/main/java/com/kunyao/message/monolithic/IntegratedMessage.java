package com.kunyao.message.monolithic;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Map;

@Data
@ToString
public class IntegratedMessage<T> implements Serializable {

    private static final long serialVersionUID = 2525875463518061109L;

    private Map<String,String> header;

    private T body;
}
