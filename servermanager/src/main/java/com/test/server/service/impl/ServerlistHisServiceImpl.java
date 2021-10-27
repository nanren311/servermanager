package com.test.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.test.server.entity.Serverlist;
import com.test.server.entity.ServerlistHis;
import com.test.server.mapper.ServerlistHisMapper;
import com.test.server.mapper.ServerlistMapper;
import com.test.server.service.IServerlistHisService;
import com.test.server.service.IServerlistService;
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
public class ServerlistHisServiceImpl
		extends
			ServiceImpl<ServerlistHisMapper, ServerlistHis>
		implements
		IServerlistHisService {



}
