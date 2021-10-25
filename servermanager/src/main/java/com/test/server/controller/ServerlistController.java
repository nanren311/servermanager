package com.test.server.controller;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

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

    @GetMapping("")
    public String helloWorld(HttpServletRequest request, Model model, Serverlist server) throws Exception {
        // List<Serverlist> list = mapper.queryAll();
        //int count = mapper.queryCount();
        String location = request.getParameter("location");
        String type = request.getParameter("type");
        List<Serverlist> list;

        if(StringUtils.hasText(location) && StringUtils.hasText(type)){
            list = mapper.queryListByParam(location,type);
        }else if (StringUtils.hasText(location) && !StringUtils.hasText(type)){
            list = mapper.queryListByLocation(location);
        }else if (!StringUtils.hasText(location) && StringUtils.hasText(type)){
            list = mapper.queryListByType(type);
        }else{
            list = mapper.queryAll();
        }

        model.addAttribute("location", location);
        model.addAttribute("type", type);
        model.addAttribute("serverlist", list);
        model.addAttribute("count", list.size());
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


    // 增
    // 删
    // 改

}
