package com.test.gpu.service.impl;

import com.test.gpu.entity.Gpu;
import com.test.gpu.mapper.GpuMapper;
import com.test.gpu.service.IGpuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author chaowu
 * @since 2021-10-12
 */
@Service
public class GpuServiceImpl extends ServiceImpl<GpuMapper, Gpu> implements IGpuService {

}
