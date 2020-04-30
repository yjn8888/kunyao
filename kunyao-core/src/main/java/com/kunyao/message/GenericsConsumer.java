package com.kunyao.message;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.lang.reflect.ParameterizedType;

public abstract class GenericsConsumer<T> implements Consumer<T> {

    protected final ObjectMapper objectMapper = new ObjectMapper();

    protected Class<T> clazz;

    @Override
    public boolean receiveMessage(MessageEntity<T> messageEntity) {
        T t = (T)objectMapper.convertValue(messageEntity.getData(),getRealType());
        return process(t);
    }

    protected abstract boolean process(T t);

    /**
     * @Author The little blacksmith
     * @Description 使用反射技术得到T的真实类型
     * @Date 2020/4/16
     * @Param []
     * @return java.lang.Class
     */
    protected Class getRealType(){
        // 获取当前new的对象的泛型的父类类型
        ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass();
        // 获取第一个类型参数的真实类型
        if(this.clazz==null){
            this.clazz = (Class<T>) pt.getActualTypeArguments()[0];
        }
        return clazz;
    }
}
