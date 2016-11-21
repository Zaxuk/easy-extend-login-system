package com.zaxk.study.dao;

import com.zaxk.study.mapper.UserMapper;
import com.zaxk.study.pojo.User;
import com.zaxk.study.pojo.UserExample;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by ZhuXu on 2016/11/18 0018.
 */
@Service
public class UserDao {

    @Autowired
    SqlSession sqlSession;

    public User selectByPrimaryKey(int userId) {
        return getMapper().selectByPrimaryKey(userId);
    }

    public List<User> selectByExample(UserExample userExample) {
        return getMapper().selectByExample(userExample);
    }

    private UserMapper getMapper() {
        return sqlSession.getMapper(UserMapper.class);
    }


}
