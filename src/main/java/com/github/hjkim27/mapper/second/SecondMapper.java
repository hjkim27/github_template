package com.github.hjkim27.mapper.second;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface SecondMapper {

    @Select("select current_database();")
    public String getDATABASE_NAME();

}