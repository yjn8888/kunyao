package com.kunyao.mybatis.support;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kunyao.core.entity.invoke.Page;
import com.kunyao.data.IRepository;
import com.kunyao.data.IQueryCondition;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.Nullable;
import java.io.Serializable;
import java.util.List;

@Slf4j
public class BaseRepository<M extends BaseMapper<T>, T extends Serializable,PK extends Serializable> extends ServiceImpl<M , T> implements IRepository<T , PK > {

    @Override
    public T queryById(PK id) {
        return baseMapper.selectById(id);
    }

    @Override
    public List<T> queryByCondition(IQueryCondition IQueryCondition) {
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
    public boolean saveBatch(List<T> tList,@Nullable Integer batchSize) {
        if(batchSize==null){
            batchSize = 30;
        }
        return saveBatch(tList,batchSize.intValue());
    }

    @Override
    public int update(T t) {
        return baseMapper.update(t,new UpdateWrapper<T>(t));
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

    @Override
    public Page<T> queryPageByCondition(IQueryCondition IQueryCondition) {
        return null;
    }

    public List<T> query(T t,String... columns) {
        return baseMapper.selectList(new QueryWrapper<T>(t,columns));
    }

}
