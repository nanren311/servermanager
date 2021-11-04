package com.test.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.test.server.entity.Gpulist;
import com.test.server.entity.GpulistHis;
import com.test.server.mapper.GpulistHisMapper;
import com.test.server.mapper.GpulistMapper;
import com.test.server.service.IGpulistHisService;
import com.test.server.service.IGpulistService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author chaowu
 * @since 2021-09-30
 */
@Service
public class GpulistHisServiceImpl
		extends
			ServiceImpl<GpulistHisMapper, GpulistHis>
		implements
		IGpulistHisService {



}
