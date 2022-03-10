package com.kuang.mapper;

import com.kuang.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;


/**
 * Mapper注解的作用：
 *  1.为了把mapper这个DAO交給Spring管理
 *  2.为了不再写mapper映射文件（xml和注解方式都可以）
 *  3.为了给mapper接口 自动根据一个添加@Mapper注解的接口生成一个实现类
 */
@Mapper
@Repository
public interface UserMapper {
    public User queryUserByName(String name);
}
