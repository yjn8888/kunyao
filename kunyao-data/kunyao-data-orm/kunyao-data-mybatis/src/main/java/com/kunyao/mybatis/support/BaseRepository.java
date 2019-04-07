package com.kunyao.mybatis.support;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kunyao.core.exception.SystemException;
import com.kunyao.data.IRepository;
import com.kunyao.data.QueryCondition;
import com.kunyao.util.ReflectUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Repository
@Scope("prototype")
public class BaseRepository<M extends BaseMapper<T>, T extends Serializable,PK extends Serializable> implements IRepository<T , PK > {

    @Autowired
    private M baseMapper;

    private Map<String,Method> cacheMethod = new HashMap<>();

    @Override
    public T queryById(PK id) {
        return baseMapper.selectById(id);
    }

    @Override
    public List<T> queryByCondition(QueryCondition queryCondition) {
        return null;
    }

    @Override
    public List<T> query(T t) {
        return baseMapper.selectList(new QueryWrapper<T>(t));
    }

    @Override
    public T queryOne(T t) {
        return baseMapper.selectOne(new QueryWrapper<T>(t));
    }

    @Override
    public int insert(T t) {
        return baseMapper.insert(t);
    }

    @Override
    public int insertBatch(List<T> tList) {
        return 0;
    }

    @Override
    public int update(T t) {
        return baseMapper.update(t,new UpdateWrapper<T>(t));
    }

    @Override
    public int updateById(T t) {
        return baseMapper.updateById(t);
    }

    @Override
    public int delete(T t) {
        return baseMapper.delete(new QueryWrapper<>(t));
    }

    @Override
    public int deleteById(PK id) {
        return baseMapper.deleteById(id);
    }

    @Override
    public int deleteBatch(List<PK> idList) {
        return baseMapper.deleteBatchIds(idList);
    }

    public List<T> query(T t,String... columns) {
        return baseMapper.selectList(new QueryWrapper<T>(t,columns));
    }

    /**
     * 通用接口调用
     * @param methodName
     * @param params
     * @return
     */
    public <S> S execute(String methodName,Object... params) {
        try{
            Method method = cacheMethod.get(methodName);
            if(method==null){
                synchronized (cacheMethod){
                    if(!cacheMethod.containsKey(methodName)){
                        method = ReflectUtils.getMethod(baseMapper,methodName,params);
                        cacheMethod.put(methodName,method);
                    }else{
                        method = cacheMethod.get(methodName);
                    }
                }
            }
            return (S)method.invoke(baseMapper,params);
        }catch (Exception e){
            log.error(e.getMessage(),e);
            throw new SystemException(e);
        }
    }
}
