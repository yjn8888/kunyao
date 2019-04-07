package com.kunyao.data;

import java.io.Serializable;
import java.util.List;

/**
 * 基础数据API接口
 * 定义基础的CRUD接口
 * @param <T>
 */
public interface IRepository<T extends Serializable, PK extends Serializable>{

    /**
     * 根据唯一键查询
     * @param id 唯一键（唯一键或者缓存key）
     * @return
     */
    T queryById(PK id);


    /**
     *条件查询
     */
    List<T> queryByCondition(QueryCondition queryCondition);

    /**
     * 查询
     * @param t
     * @return
     */
    List<T> query(T t);

    /**
     * 查询只有一个
     * @param t
     * @return
     */
    T queryOne(T t);

    /**
     * 保存
     * @param t
     * @return
     */
    int insert(T t);


    /**
     *  批量插入
     * @param tList
     * @return
     */
    int insertBatch(List<T> tList);


    /**
     * 更新
     * @param t
     * @return
     */
    int update(T t);

    /**
     * 更新
     * @param t
     * @return
     */
    int updateById(T t);

    /**
     * 删除
     * @param t
     * @return
     */
    int delete(T t);

    /**
     * 删除
     * @param id
     * @return
     */
    int deleteById(PK id);

    /**
     * 批量删除
     * @param id
     * @return
     */
    int deleteBatch(List<PK> idList);
}
