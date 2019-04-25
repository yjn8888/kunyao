package com.baomidou.ant.user.service.impl;

import com.baomidou.ant.user.entity.User;
import com.baomidou.ant.user.mapper.UserMapper;
import com.baomidou.ant.user.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kunyao.mybatis.support.BaseRepository;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jobob
 * @since 2019-04-06
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    private BaseRepository<UserMapper,User,Integer> baseRepository;

    public void tets(){
        baseMapper.tsts();

    }
}

