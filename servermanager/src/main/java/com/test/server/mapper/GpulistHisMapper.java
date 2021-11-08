package com.test.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.test.server.entity.Serverlist;
import com.test.server.entity.ServerlistHis;
import com.test.server.entity.Gpulist;
import com.test.server.entity.GpulistHis;
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
public interface GpulistHisMapper extends BaseMapper<GpulistHis> {
	@Select(value = {"select * from  gpulist_his;"})
	List<GpulistHis> queryAll();

	@Select("select * from  gpulist_his where gpulocation=#{gpulocation}")

	List<GpulistHis> queryListByLocation(String gpulocation);

	@Select("select * from  gpulist_his where gputype=#{gputype}")

	List<GpulistHis> queryListByType(String gputype);
	
	@Select("select gpunm,gpulocation,gputype,gpuuser,healthystatus,gpustatus,gpustarttime,gpuendtime from  gpulist_his where gpunm=#{gpunm} ORDER BY gpuendtime DESC")

	List<GpulistHis> queryListByGpunm(String gpunm);

	@Select("select * from  gpulist_his where gpulocation=#{gpulocation} and gputype=#{gputype} ORDER BY gpuendtime DESC ")

	List<GpulistHis> queryListByParam(String gpulocation,String gputype);


	@Select(value = {"select count(id)  from  gpulist_his;"})
	int queryCount();

	@Select("select * from  gpulist_his where id=#{id} ")
	GpulistHis selectById(int id);
}
