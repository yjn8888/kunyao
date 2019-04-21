package com.kunyao.data;

import java.io.Serializable;
import java.util.List;

public interface IQueryCondition extends Serializable {

    /**
     * 添加升序规则
     * @param propertyName
     * @return
     */
    IQueryCondition addAscOrder(String propertyName);

    /**
     * 添加降序规则
     * @param propertyName
     * @return
     */
    IQueryCondition addDescOrder(String propertyName) ;

    IQueryCondition andIsNull(String propertyName);

    IQueryCondition andIsNotNull(String propertyName);

    IQueryCondition andIsEmpty(String propertyName) ;

    IQueryCondition andIsNotEmpty(String propertyName);

    IQueryCondition andLike(String propertyName, Object value);

    IQueryCondition andEqual(String propertyName, Object value);

    IQueryCondition andBetween(String propertyName, Object... values);

    IQueryCondition andIn(String propertyName, List<Object> values) ;

    IQueryCondition andIn(String propertyName, Object... values);

    IQueryCondition andNotIn(String propertyName, List<Object> values);

    IQueryCondition orNotIn(String propertyName, Object... values);


    IQueryCondition andNotEqual(String propertyName, Object value) ;

    IQueryCondition andGreaterThan(String propertyName, Object value);

    IQueryCondition andGreaterEqual(String propertyName, Object value);

    IQueryCondition andLessThan(String propertyName, Object value);

    IQueryCondition andLessEqual(String propertyName, Object value);


    IQueryCondition orIsNull(String propertyName) ;

    IQueryCondition orIsNotNull(String propertyName) ;

    IQueryCondition orIsEmpty(String propertyName) ;

    IQueryCondition orIsNotEmpty(String propertyName);

    IQueryCondition orLike(String propertyName, Object value);

    IQueryCondition orEqual(String propertyName, Object value);

    IQueryCondition orBetween(String propertyName, Object... values) ;

    IQueryCondition orIn(String propertyName, List<Object> values);

    IQueryCondition orIn(String propertyName, Object... values);

    IQueryCondition orNotEqual(String propertyName, Object value);

    IQueryCondition orGreaterThan(String propertyName, Object value);

    IQueryCondition orGreaterEqual(String propertyName, Object value);

    IQueryCondition orLessThan(String propertyName, Object value);

    IQueryCondition orLessEqual(String propertyName, Object value);

    /**
     * 分页
     * @param pageNo
     * @param pageSize
     * @return
     */
    IQueryCondition paging(final int pageNo,final int pageSize);


}
