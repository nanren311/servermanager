package com.test.server.controller;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;
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
import com.test.server.mapper.*;
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
    GpulistMapper gpumapper;
    @Autowired
    ServerlistHisMapper hismapper;
    @Autowired
    GpulistHisMapper gpuhismapper;


    @GetMapping("")
    public String helloWorld(HttpServletRequest request, Model model, Serverlist server,Gpulist gpu) throws Exception {
        // List<Serverlist> list = mapper.queryAll();
        //int count = mapper.queryCount();
        String location = request.getParameter("location");
        String type = request.getParameter("type");
        String gputype = request.getParameter("gputype");
        String isHis = request.getParameter("isHis");
        String newStart = request.getParameter("serverstarttime");
        
        if (newStart =="" ) {
        	server.setStatus("空闲");
        	
        }else {
        	server.setStatus("使用中");
        	
        }
        List<Gpulist> gpu1 = gpumapper.queryAll();
        int countgpu = gpumapper.queryCount1();


        
        List<Serverlist> list;
        List<ServerlistHis> listHis;
        if(!"his".equals(isHis)){
            if(StringUtils.hasText(location) && StringUtils.hasText(type)&& StringUtils.hasText(gputype)){
                list = mapper.queryListBy3Param(location,type,gputype);
            }else if (StringUtils.hasText(location) && !StringUtils.hasText(type) && !StringUtils.hasText(gputype)){
                list = mapper.queryListByLocation(location);
            }else if (!StringUtils.hasText(location) && !StringUtils.hasText(gputype) && StringUtils.hasText(type)){
                list = mapper.queryListByType(type);
            }else if (!StringUtils.hasText(location) && !StringUtils.hasText(type) && StringUtils.hasText(gputype)){
                list = mapper.queryListByGputype(gputype);
            }else if (StringUtils.hasText(location) && StringUtils.hasText(type) && !StringUtils.hasText(gputype)){
                list = mapper.queryListByParam(location,type);
            }else if (!StringUtils.hasText(location) && StringUtils.hasText(type) && StringUtils.hasText(gputype)){
                list = mapper.queryListBy2Param(type,gputype);
            }else if (StringUtils.hasText(location) && !StringUtils.hasText(type) && StringUtils.hasText(gputype)){
                list = mapper.queryListBy21Param(location,gputype);
            }
            
            else{
                list = mapper.queryAll();
            }
            model.addAttribute("serverlist", list);
            model.addAttribute("count", list.size());
            model.addAttribute("gpulist", gpu1);
            model.addAttribute("countgpu", gpu1.size());
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
        model.addAttribute("gputype", gputype);
        model.addAttribute("isHis", isHis);
        mapper.updateById(server);
        return "/index";
    }

    @GetMapping("/index")
    public String index(Model model, Serverlist server,Gpulist gpu ) throws Exception {

        List<Serverlist> list = mapper.queryAll();
        for (Iterator iterator = list.iterator(); iterator.hasNext(); ) {

            Serverlist serverlist = (Serverlist) iterator.next();
          
            System.out.println(serverlist.toString());
        }
        List<Gpulist> gpulist = gpumapper.queryAll();

        model.addAttribute("serverlist", list);
        mapper.updateById(server);
        model.addAttribute("gpulist",gpu);
        gpumapper.updateById(gpu);
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
    	Date date = new Date();
    	SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
   	
 
    	String dateStr = simpleDateFormat.format(date);

        String newdate = dateStr.replaceAll("[[\\s-:punct:]]","");
        int dateNum = Integer.parseInt(newdate);
  
        Serverlist server = new Serverlist();
        String preid = r.getParameter("id");
        int id = Integer.parseInt(preid);
        server.setId(id);
        server.setServername(r.getParameter("servername"));
        server.setServeruser(r.getParameter("serveruser"));
        server.setStatus(r.getParameter("status"));
        server.setType(r.getParameter("type"));
        server.setLocation(r.getParameter("location"));
        server.setServerreservetime(r.getParameter("serverreservetime"));

        server.setServerstarttime(r.getParameter("serverstarttime"));
        server.setServerendtime(r.getParameter("serverendtime"));
        server.setGputype(r.getParameter("gputype"));
        server.setGpuuser(r.getParameter("gpuuser"));
        server.setHealthystatus(r.getParameter("healthystatus"));
        
        Gpulist gpu = new Gpulist();
        gpu.setId(id);
        gpu.setGpulocation(r.getParameter("gpulocation"));
        gpu.setGpunm(r.getParameter("gpunm"));
        gpu.setGputype(r.getParameter("gputype"));
        gpu.setGpuuser(r.getParameter("gpuuser"));
        gpu.setHealthystatus(r.getParameter("healthystatus"));
        gpu.setGpustatus(r.getParameter("gpustatus"));
        gpu.setGpustarttime(r.getParameter("gpustarttime"));
        gpu.setGpuendtime(r.getParameter("gpuendtime"));
        
        
        model.addAttribute("server", server);
        model.addAttribute("gpu", gpu);
        return "/add";
    }

    @GetMapping("/server/update1")
    public String update1(HttpServletRequest r, Model model) throws Exception {
    	Date date = new Date();
    	SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
   	
 
    	String dateStr = simpleDateFormat.format(date);

        String newdate = dateStr.replaceAll("[[\\s-:punct:]]","");
        int dateNum = Integer.parseInt(newdate);
  

        String preid = r.getParameter("id");
        int id = Integer.parseInt(preid);
        
        Gpulist gpu = new Gpulist();
        gpu.setId(id);
        gpu.setGpulocation(r.getParameter("gpulocation"));
        gpu.setGpunm(r.getParameter("gpunm"));
        gpu.setGputype(r.getParameter("gputype"));
        gpu.setGpuuser(r.getParameter("gpuuser"));
        gpu.setHealthystatus(r.getParameter("healthystatus"));
        gpu.setGpustatus(r.getParameter("gpustatus"));
        gpu.setGpustarttime(r.getParameter("gpustarttime"));
        gpu.setGpuendtime(r.getParameter("gpuendtime"));
        
        
        model.addAttribute("gpu", gpu);
        return "/addgpu";
    }

    @GetMapping("/server/addnewserver")
    public String add(HttpServletRequest r, Model model) throws Exception {
    	Date date = new Date();
    	SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    	System.out.println("当前时间"+ simpleDateFormat.format(date));
        String preid = r.getParameter("id");
        boolean flag = false;//false means update
        if (preid != "") {
            int id = Integer.parseInt(preid);
            Serverlist tempServer =  mapper.selectById(id);
            //每次更新保存到历史表，重新生产id
            ServerlistHis hisServer = new ServerlistHis();
            hisServer.setServername(tempServer.getServername());
            hisServer.setServeruser(tempServer.getServeruser());
            hisServer.setStatus(tempServer.getStatus());
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
            server.setStatus(r.getParameter("status"));
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
            server.setStatus(r.getParameter("status"));
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
    @GetMapping("/server/addnewgpu")
    public String addgpu(HttpServletRequest r, Model model) throws Exception {
    	Date date = new Date();
    	SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    	System.out.println("当前时间"+ simpleDateFormat.format(date));
        String preid = r.getParameter("id");
        boolean flag = false;//false means update
        if (preid != "") {
            int id = Integer.parseInt(preid);
          
            Gpulist tempGpu = gpumapper.selectById(id);
            GpulistHis hisGpu = new GpulistHis();
            hisGpu.setGpulocation(tempGpu.getGpulocation());
            hisGpu.setGpunm(tempGpu.getGpunm());
            hisGpu.setGputype(tempGpu.getGputype());
            hisGpu.setGpuuser(tempGpu.getGpuuser());
            hisGpu.setHealthystatus(tempGpu.getHealthystatus());
            hisGpu.setGpustatus(tempGpu.getGpustatus());
            hisGpu.setGpustarttime(tempGpu.getGpustarttime());
            hisGpu.setGpuendtime(tempGpu.getGpuendtime());
            gpuhismapper.insert(hisGpu);
            
            Gpulist gpu = new Gpulist();
            gpu.setId(id);
            gpu.setGpulocation(r.getParameter("gpulocation"));
            gpu.setGpunm(r.getParameter("gpunm"));
            gpu.setGputype(r.getParameter("gputype"));
            gpu.setGpuuser(r.getParameter("gpuuser"));
            gpu.setHealthystatus(r.getParameter("healthystatus"));
            gpu.setGpustatus(r.getParameter("gpustatus"));
            gpu.setGpustarttime(r.getParameter("gpustarttime"));
            gpu.setGpuendtime(r.getParameter("gpuendtime"));
            gpumapper.updateById(gpu);








        } else {
        	Gpulist gpu = new Gpulist();
            gpu.setGpulocation(r.getParameter("gpulocation"));
            gpu.setGpunm(r.getParameter("gpunm"));
            gpu.setGputype(r.getParameter("gputype"));
            gpu.setGpuuser(r.getParameter("gpuuser"));
            gpu.setHealthystatus(r.getParameter("healthystatus"));
            gpu.setGpustatus(r.getParameter("gpustatus"));
            gpu.setGpustarttime(r.getParameter("gpustarttime"));
            gpu.setGpuendtime(r.getParameter("gpuendtime"));
            gpumapper.insert(gpu);

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
        
        String newStart = r.getParameter("serverstarttime");
     
        System.out.println(newStart);
        if (newStart =="" ) {
        	server.setStatus("空闲");
        	
        } 
        else {
        
        Date date = new Date();
    	SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    	
    	System.out.println("当前时间"+ simpleDateFormat.format(date));
    	String dateStr = simpleDateFormat.format(date);

        String newdate = dateStr.replaceAll("[[\\s-:punct:]]","");
        int dateNum = Integer.parseInt(newdate);
        
      String endTime = r.getParameter("serverendtime");
      String newEnd = endTime.replaceAll("[[\\s-:punct:]]","");
      
  //    System.out.println(startTime);
      int endNum = Integer.parseInt(newEnd);

      
       if(endNum<dateNum) {
        	server.setStatus("空闲");
        }
        
        else{
        	server.setStatus("使用中");
        }
        }

        System.out.println(server.status);
        System.out.println("加油");
        System.out.println(newStart);
        server.setServerendtime(r.getParameter("serverendtime"));
        server.setGputype(r.getParameter("gputype"));
        server.setGpuuser(r.getParameter("gpuuser"));
        server.setHealthystatus(r.getParameter("healthystatus"));
        model.addAttribute("server", server);
        mapper.updateById(server);
        return "/chakan";
    }
    
    @GetMapping("/server/chakangpu")
    public String chakangpu(HttpServletRequest r, Model model) throws Exception {
        Gpulist gpu = new Gpulist();
        String preid = r.getParameter("id");
        int id = Integer.parseInt(preid);
        gpu.setId(id);
        gpu.setGpulocation(r.getParameter("gpulocation"));
        gpu.setGpunm(r.getParameter("gpunm"));

        gpu.setGputype(r.getParameter("gputype"));
        gpu.setGpuuser(r.getParameter("gpuuser"));
        gpu.setHealthystatus(r.getParameter("healthystatus"));
        gpu.setGpustatus(r.getParameter("gpustatus"));
        
        String newStart = r.getParameter("gpustarttime");
     
        System.out.println(newStart);
        if (newStart =="" ) {
        	gpu.setGpustatus("空闲");
        	
        } 
        else {
        
        Date date = new Date();
    	SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    	
    	System.out.println("当前时间"+ simpleDateFormat.format(date));
    	String dateStr = simpleDateFormat.format(date);

        String newdate = dateStr.replaceAll("[[\\s-:punct:]]","");
        int dateNum = Integer.parseInt(newdate);
        
      String endTime = r.getParameter("gpuendtime");
      String newEnd = endTime.replaceAll("[[\\s-:punct:]]","");
      
  //    System.out.println(startTime);
      int endNum = Integer.parseInt(newEnd);

      
       if(endNum<dateNum) {
    	   gpu.setGpustatus("空闲");
        }
        
        else{
        	gpu.setGpustatus("使用中");
        }
        }

        System.out.println(gpu.gpustatus);
        System.out.println("加油");
        gpu.setGpustarttime(r.getParameter("gpustarttime"));

        gpu.setGpuendtime(r.getParameter("gpuendtime"));
       
        model.addAttribute("gpu", gpu);
        gpumapper.updateById(gpu);
        return "/chakangpu";
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
        
    	System.out.println(hismapper.queryListByServername(request.getParameter("servername")));
        System.out.println("-------------------------------");
        model.addAttribute("serverlistHis", listHis);
        model.addAttribute("count", listHis.size());
    	return "/chakana";
    	
    }
    
    @GetMapping("/server/chakanagpu")
    public String chankanagpu(HttpServletRequest request, Model model) {
        List<GpulistHis> gpulistHis = gpuhismapper.queryListByGpunm(request.getParameter("gpunm"));
        System.out.println("-------------------------------");
        System.out.println(gpuhismapper.queryListByGpunm(request.getParameter("gpunm")));
//    	System.out.println(hismapper.queryListByServername(request.getParameter("servername")));
        model.addAttribute("gpulistHis", gpulistHis);
        model.addAttribute("count", gpulistHis.size());
    	return "/chakanagpu";
    	
    }

    // 增
    // 删
    // 改

    
    

}
