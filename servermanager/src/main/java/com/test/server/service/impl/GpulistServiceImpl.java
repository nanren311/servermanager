package com.test.server.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.test.server.entity.Gpulist;
import com.test.server.mapper.GpulistMapper;
import com.test.server.service.IGpulistService;
/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author chaowu
 * @since 2021-09-30
 */
@Service
public class GpulistServiceImpl
		extends
			ServiceImpl<GpulistMapper, Gpulist>
		implements
			IGpulistService {



}
