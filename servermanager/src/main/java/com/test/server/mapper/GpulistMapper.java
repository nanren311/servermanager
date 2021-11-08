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
public interface GpulistMapper extends BaseMapper<Gpulist> {
	@Select(value = {"select * from  gpulist;"})
	List<Gpulist> queryAll();

	@Select("select * from  gpulist where gpulocation=#{gpulocation}")

	List<Gpulist> queryListByLocation(String gpulocation);

	@Select("select * from  gpulist where gputype=#{gputype}")

	List<Gpulist> queryListByType(String gputype);
	
	@Select("select * from  gpulist where gpunm=#{gpunm} ORDER BY gpuendtime DESC")

	List<Gpulist> queryListByGpunm(String gpunm);

	@Select("select * from  gpulist where gpulocation=#{gpulocation} and gputype=#{gputype} ORDER BY gpuendtime DESC ")

	List<Gpulist> queryListByParam(String gpulocation,String gputype);


	@Select(value = {"select count(id)  from  gpulist;"})
	int queryCount1();

	@Select("select * from  gpulist where id=#{id} ")
	Gpulist selectById(int id);
}
