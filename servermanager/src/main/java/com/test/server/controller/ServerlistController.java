package com.test.server.controller;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.test.server.entity.ServerlistHis;
import com.test.server.mapper.ServerlistHisMapper;
import freemarker.template.utility.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.test.server.entity.Serverlist;
import com.test.server.mapper.ServerlistMapper;
import com.test.server.mapper.ServerlistHisMapper;
import com.test.server.entity.*;
/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author chaowu
 * @since 2021-09-30
 */
@Controller
@RequestMapping
public class ServerlistController {
    // 查

    @Autowired
    ServerlistMapper mapper;

    @Autowired
    ServerlistHisMapper hismapper;



    @GetMapping("")
    public String helloWorld(HttpServletRequest request, Model model, Serverlist server) throws Exception {
        // List<Serverlist> list = mapper.queryAll();
        //int count = mapper.queryCount();
        String location = request.getParameter("location");
        String type = request.getParameter("type");
        String isHis = request.getParameter("isHis");
        List<Serverlist> list;
        List<ServerlistHis> listHis;
        if(!"his".equals(isHis)){
            if(StringUtils.hasText(location) && StringUtils.hasText(type)){
                list = mapper.queryListByParam(location,type);
            }else if (StringUtils.hasText(location) && !StringUtils.hasText(type)){
                list = mapper.queryListByLocation(location);
            }else if (!StringUtils.hasText(location) && StringUtils.hasText(type)){
                list = mapper.queryListByType(type);
            }else{
                list = mapper.queryAll();
            }
            model.addAttribute("serverlist", list);
            model.addAttribute("count", list.size());
        }else {
            if (StringUtils.hasText(location) && StringUtils.hasText(type)) {
                listHis = hismapper.queryListByParam(location, type);
            } else if (StringUtils.hasText(location) && !StringUtils.hasText(type)) {
                listHis = hismapper.queryListByLocation(location);
            } else if (!StringUtils.hasText(location) && StringUtils.hasText(type)) {
                listHis = hismapper.queryListByType(type);
            } else {
                listHis = hismapper.queryAll();
            }
            model.addAttribute("serverlist", listHis);
            model.addAttribute("count", listHis.size());
        }


        model.addAttribute("location", location);
        model.addAttribute("type", type);
        model.addAttribute("isHis", isHis);
        return "/index";
    }

    @GetMapping("/index")
    public String index(Model model) throws Exception {
        List<Serverlist> list = mapper.queryAll();
        for (Iterator iterator = list.iterator(); iterator.hasNext(); ) {
            Serverlist serverlist = (Serverlist) iterator.next();
            System.out.println(serverlist.toString());
        }
        model.addAttribute("serverlist", list);
        return "/index";
    }

//	@GetMapping("/server/add")
//	public String addpages(Model model) throws Exception {
//		List<Serverlist> list = mapper.queryAll();
//		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
//			Serverlist serverlist = (Serverlist) iterator.next();
//			System.out.println(serverlist.toString());
//		}
//		model.addAttribute("serverlist", list);
//		return "/add";
//	}
//	


    @GetMapping("/server/update")
    public String update(HttpServletRequest r, Model model) throws Exception {
        Serverlist server = new Serverlist();
        String preid = r.getParameter("id");
        int id = Integer.parseInt(preid);
        server.setId(id);
        server.setServername(r.getParameter("servername"));
        server.setServeruser(r.getParameter("serveruser"));
        server.setType(r.getParameter("type"));
        server.setLocation(r.getParameter("location"));
        server.setServerreservetime(r.getParameter("serverreservetime"));
        server.setServerstarttime(r.getParameter("serverstarttime"));
        server.setServerendtime(r.getParameter("serverendtime"));
        server.setGputype(r.getParameter("gputype"));
        server.setGpuuser(r.getParameter("gpuuser"));
        server.setHealthystatus(r.getParameter("healthystatus"));
        model.addAttribute("server", server);
        return "/add";
    }


    @GetMapping("/server/addnewserver")
    public String add(HttpServletRequest r, Model model) throws Exception {
        String preid = r.getParameter("id");
        boolean flag = false;//false means update
        if (preid != "") {
            int id = Integer.parseInt(preid);
            Serverlist tempServer =  mapper.selectById(id);
            //每次更新保存到历史表，重新生产id
            ServerlistHis hisServer = new ServerlistHis();
            hisServer.setServername(tempServer.getServername());
            hisServer.setServeruser(tempServer.getServeruser());
            hisServer.setType(tempServer.getType());
            hisServer.setLocation(tempServer.getLocation());
            hisServer.setServerreservetime(tempServer.getServerreservetime());
            hisServer.setServerstarttime(tempServer.getServerstarttime());
            hisServer.setServerendtime(tempServer.getServerendtime());
            hisServer.setGputype(tempServer.getGputype());
            hisServer.setGpuuser(tempServer.getGpuuser());
            hisServer.setHealthystatus(tempServer.getHealthystatus());
            hismapper.insert(hisServer);

//            //每次更新保存到历史表，重新生产id
//            ServerlistHis hisServer = new ServerlistHis();
//            hisServer.setServername(r.getParameter("servername"));
//            hisServer.setServeruser(r.getParameter("serveruser"));
//            hisServer.setType(r.getParameter("type"));
//            hisServer.setLocation(r.getParameter("location"));
//            hisServer.setServerreservetime(r.getParameter("serverreservetime"));
//            hisServer.setServerstarttime(r.getParameter("serverstarttime"));
//            hisServer.setServerendtime(r.getParameter("serverendtime"));
//            hisServer.setGputype(r.getParameter("gputype"));
//            hisServer.setGpuuser(r.getParameter("gpuuser"));
//            hisServer.setHealthystatus(r.getParameter("healthystatus"));
//            hismapper.insert(hisServer);

            Serverlist server = new Serverlist();
            server.setId(id);
            server.setServername(r.getParameter("servername"));
            server.setServeruser(r.getParameter("serveruser"));
            server.setType(r.getParameter("type"));
            server.setLocation(r.getParameter("location"));
            server.setServerreservetime(r.getParameter("serverreservetime"));
            server.setServerstarttime(r.getParameter("serverstarttime"));
            server.setServerendtime(r.getParameter("serverendtime"));
            server.setGputype(r.getParameter("gputype"));
            server.setGpuuser(r.getParameter("gpuuser"));
            server.setHealthystatus(r.getParameter("healthystatus"));
            mapper.updateById(server);




        } else {
            Serverlist server = new Serverlist();
            server.setServername(r.getParameter("servername"));
            server.setServeruser(r.getParameter("serveruser"));
            server.setType(r.getParameter("type"));
            server.setLocation(r.getParameter("location"));
            server.setServerreservetime(r.getParameter("serverreservetime"));
            server.setServerstarttime(r.getParameter("serverstarttime"));
            server.setServerendtime(r.getParameter("serverendtime"));
            server.setGputype(r.getParameter("gputype"));
            server.setGpuuser(r.getParameter("gpuuser"));
            server.setHealthystatus(r.getParameter("healthystatus"));
            mapper.insert(server);

        }

        return "redirect:/";
    }

    @GetMapping("/server/del")
    public String del(HttpServletRequest r) throws Exception {
        int id = Integer.parseInt(r.getParameter("id"));
        mapper.deleteById(id);
        return "redirect:/";
    }

    @GetMapping("/server/chakan")
    public String chakan(HttpServletRequest r, Model model) throws Exception {
        Serverlist server = new Serverlist();
        String preid = r.getParameter("id");
        int id = Integer.parseInt(preid);
        server.setId(id);
        server.setServername(r.getParameter("servername"));
        server.setServeruser(r.getParameter("serveruser"));
        server.setType(r.getParameter("type"));
        server.setLocation(r.getParameter("location"));
        server.setServerreservetime(r.getParameter("serverreservetime"));
        server.setServerstarttime(r.getParameter("serverstarttime"));
        server.setServerendtime(r.getParameter("serverendtime"));
        server.setGputype(r.getParameter("gputype"));
        server.setGpuuser(r.getParameter("gpuuser"));
        server.setHealthystatus(r.getParameter("healthystatus"));
        model.addAttribute("server", server);
        return "/chakan";
    }

    //	@GetMapping("/server/search")
//	public String searchlist(HttpServletRequest r, Model model) throws Exception {
//		Serverlist server = new Serverlist();
//		String preid = r.getParameter("id");
//			int id = Integer.parseInt(preid);
//				server.setId(id);
//		server.setServername(r.getParameter("servername"));
//		server.setServeruser(r.getParameter("serveruser"));
//		server.setType(r.getParameter("type"));
//		server.setLocation(r.getParameter("location"));
//		server.setServerreservetime(r.getParameter("serverreservetime"));
//		server.setServerstarttime(r.getParameter("serverstarttime"));
//		server.setServerendtime(r.getParameter("serverendtime"));
//		server.setGputype(r.getParameter("gputype"));
//		server.setGpuuser(r.getParameter("gpuuser"));
//		server.setHealthystatus(r.getParameter("healthystatus"));
//		Map<String, Object> searchParam = new HashMap<>();
//		searchParam.put("type",type);
//		model.addAttribute("server", server);
//		return "redirect:/";
//		
//	}
    @GetMapping("/server/search")
    public String searchlist(HttpServletRequest request) {
        System.out.println("11");
        mapper.queryListByParam(request.getParameter("location"), request.getParameter("type"));
        return "/search";
    }

    @GetMapping("/server/chakana")
    public String chankana(HttpServletRequest request, Model model) {
        List<ServerlistHis> listHis = hismapper.queryListByServername(request.getParameter("servername"));
//    	System.out.println(hismapper.queryListByServername(request.getParameter("servername")));
        model.addAttribute("serverlistHis", listHis);
        model.addAttribute("count", listHis.size());
    	return "/chakana";
    	
    }

    // 增
    // 删
    // 改

    
    

}
