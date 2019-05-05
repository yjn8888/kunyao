package com.baomidou.ant.user.mapper;

import com.baomidou.ant.user.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author jobob
 * @since 2019-04-06
 */
public interface UserMapper extends BaseMapper<User> {

    void tsts();

}
