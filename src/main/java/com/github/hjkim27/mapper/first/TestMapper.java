package com.github.hjkim27.mapper.first;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.sql.Timestamp;

@Mapper
public interface TestMapper {

    @Select("select now()")
    public Timestamp getNow();

    @Select("select current_database();")
    public String getDATABASE_NAME();

}
