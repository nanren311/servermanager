package com.test.server.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.test.server.entity.Serverlist;
import com.test.server.mapper.ServerlistMapper;
import com.test.server.service.IServerlistService;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author chaowu
 * @since 2021-09-30
 */
@Service
public class ServerlistServiceImpl
		extends
			ServiceImpl<ServerlistMapper, Serverlist>
		implements
			IServerlistService {



}
