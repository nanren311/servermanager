package com.test.server.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.test.server.entity.Serverlist;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author chaowu
 * @since 2021-09-30
 */
@Mapper
public interface ServerlistMapper extends BaseMapper<Serverlist> {
	@Select(value = {"select * from  serverlist;"})
	List<Serverlist> queryAll();
	
	

	
	@Select(value = {"select count(id)  from  serverlist;"})
	int queryCount();
}
