package com.test.server.mapper;

import java.util.List;

import com.test.server.entity.ServerlistHis;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
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

	@Select("select * from  serverlist where location=#{location}")

	List<Serverlist> queryListByLocation(String location);

	@Select("select * from  serverlist where type=#{type}")

	List<Serverlist> queryListByType(String type);
	@Select("select * from  serverlist where gputype=#{gputype}")

	List<Serverlist> queryListByGputype(String gputype);

	@Select("select * from  serverlist where location=#{location} and type=#{type}")

	List<Serverlist> queryListByParam(String location,String type);
	
	@Select("select * from  serverlist where type=#{type} and gputype like CONCAT('%',#{gputype},'%')")

	List<Serverlist> queryListBy2Param(String type,String gputype);
	
	@Select("select * from  serverlist where location=#{location} and gputype like CONCAT('%',#{gputype},'%')")

	List<Serverlist> queryListBy21Param(String location,String gputype);

	@Select("select * from  serverlist where location=#{location} and type=#{type} and gputype like CONCAT('%',#{gputype},'%')")

	List<Serverlist> queryListBy3Param(String location,String type,String gputype);


	@Select(value = {"select count(id)  from  serverlist;"})
	int queryCount();

	@Select("select * from  serverlist where id=#{id}")
	Serverlist selectById(int id);

}
