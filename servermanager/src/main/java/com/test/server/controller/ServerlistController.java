package com.test.server.controller;

import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
	public String helloWorld(Model model) throws Exception {
		List<Serverlist> list = mapper.queryAll();
		int count = mapper.queryCount();
		model.addAttribute("serverlist", list);
		model.addAttribute("count", count);
		return "/index";
	}

	@GetMapping("/index")
	public String idnex(Model model) throws Exception {
		List<Serverlist> list = mapper.queryAll();
		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			Serverlist serverlist = (Serverlist) iterator.next();
			System.out.println(serverlist.toString());
		}
		model.addAttribute("serverlist", list);
		return "/index";
	}

	@GetMapping("/server/add")
	public String addpages(Model model) throws Exception {
		List<Serverlist> list = mapper.queryAll();
		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			Serverlist serverlist = (Serverlist) iterator.next();
			System.out.println(serverlist.toString());
		}
		model.addAttribute("serverlist", list);
		return "/add";
	}

	@GetMapping("/server/addnewserver")
	public String add(HttpServletRequest r, Model model) throws Exception {
		Serverlist server = new Serverlist();

		server.setServername(r.getParameter("servername"));
		server.setType(r.getParameter("type"));
		server.setLocation(r.getParameter("location"));
		server.setGputype(r.getParameter("gputype"));
		server.setUser(r.getParameter("user"));
		mapper.insert(server);
		return "redirect:/";
	}

	@GetMapping("/server/del")
	public String del(HttpServletRequest r) throws Exception {
		int id = Integer.parseInt(r.getParameter("id"));
		mapper.deleteById(id);
		return "redirect:/";
	}

	// 增
	// 删
	// 改

}