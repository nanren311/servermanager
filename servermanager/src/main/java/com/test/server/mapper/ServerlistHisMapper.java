package com.test.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.test.server.entity.Serverlist;
import com.test.server.entity.ServerlistHis;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author chaowu
 * @since 2021-09-30
 */
@Mapper
public interface ServerlistHisMapper extends BaseMapper<ServerlistHis> {
	@Select(value = {"select * from  serverlist_his;"})
	List<ServerlistHis> queryAll();

	@Select("select * from  serverlist_his where location=#{location}")

	List<ServerlistHis> queryListByLocation(String location);

	@Select("select * from  serverlist_his where type=#{type}")

	List<ServerlistHis> queryListByType(String type);
	
	@Select("select servername,serveruser,type,location,gputype,gpuuser,healthystatus,serverstarttime,serverendtime from  serverlist_his where servername=#{servername}")

	List<ServerlistHis> queryListByServername(String servername);

	@Select("select * from  serverlist_his where location=#{location} and type=#{type} ")

	List<ServerlistHis> queryListByParam(String location,String type);


	@Select(value = {"select count(id)  from  serverlist_his;"})
	int queryCount();

	@Select("select * from  serverlist_his where id=#{id}")
	ServerlistHis selectById(int id);
}
