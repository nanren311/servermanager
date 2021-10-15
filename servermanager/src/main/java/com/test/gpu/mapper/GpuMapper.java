package com.test.gpu.mapper;

import com.test.gpu.entity.Gpu;
import com.test.server.entity.Serverlist;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author chaowu
 * @since 2021-10-12
 */
@Mapper
public interface GpuMapper extends BaseMapper<Gpu> {
	@Select(value = {"select * from  gpu;"})
	List<Gpu> queryAll();

}
