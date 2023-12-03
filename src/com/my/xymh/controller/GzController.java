package com.my.xymh.controller;
import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.my.xymh.base.BaseController;
import com.my.xymh.utils.JsonUtil2;
import com.my.xymh.utils.MapUtils;
import com.my.xymh.utils.Pager;
import net.sf.json.JSONObject;
import java.util.*;

import com.my.xymh.entity.*;
import com.my.xymh.dao.*;
import com.my.xymh.service.*;

/**
 * @ClassName:  
 * @Description: 
 * @author administrator
 * @date - 2020年02月18日 17时44分11秒
 */


@Controller
@RequestMapping("/gz")
public class GzController extends BaseController {
	
	/**
	 * 依赖注入 start dao/service/===
	 */
	@Autowired
	private GzService gzService;
	
	// --------------------------------------- 华丽分割线 ------------------------------
	
	/**
	 * 分页查询 返回list对象(通过对象)
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/findByObj.do")
	public String findByObj(Gz gz, Model model, HttpServletRequest request, HttpServletResponse response) {
		//分页查询
		Pager<Gz> pagers = gzService.findByEntity(gz);
		model.addAttribute("pagers", pagers);
		//存储查询条件
		model.addAttribute("obj", gz);
		return "gz/gz";
	}
	
	
	/**
	 * 分页查询 返回list对象(通过Map)
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/findByMap.do")
	public String findByMap(Gz gz, Model model, HttpServletRequest request, HttpServletResponse response) {
		//通过map查询
		Map<String,Object> params = new HashMap<String,Object>();
        if(!isEmpty(gz.getType())){
        	params.put("type", gz.getType());
		}
        if(!isEmpty(gz.getUserId())){
        	params.put("userId", gz.getUserId());
		}
		//分页查询
		Pager<Gz> pagers = gzService.findByMap(params);
		model.addAttribute("pagers", pagers);
		//存储查询条件
		model.addAttribute("obj", gz);
		return "gz/gz";
	}
	
	
	/**
	 * 跳至添加页面
	 * @return
	 */
	@RequestMapping(value = "/add.do")
	public String add(Model model, HttpServletRequest request, HttpServletResponse response) {
		List<GzDto> dtos = new ArrayList<GzDto>();
		GzDto d1 = new GzDto();
		d1.setNum(1);
		d1.setName("新闻快讯");
		dtos.add(d1);
		GzDto d2 = new GzDto();
		d2.setNum(2);
		d2.setName("通知公告");
		dtos.add(d2);
		GzDto d3 = new GzDto();
		d3.setNum(3);
		d3.setName("活动列表");
		dtos.add(d3);
		GzDto d4 = new GzDto();
		d4.setNum(4);
		d4.setName("校园风采");
		dtos.add(d4);
		
		Integer id = Integer.valueOf(request.getSession().getAttribute("userId").toString());
		Map<String, Object> map = MapUtils.getMap();
		map.put("userId", id);
		List<Gz> list = gzService.list(map);
		List<Integer> types = new ArrayList<>();
		List<GzDto> listNew = new ArrayList<>();
		
		if(!isEmpty(list)){
			for(Gz g: list){
				types.add(g.getType());
			}
		}
			for(GzDto dt :dtos){
				if(types.contains(dt.getNum())){
					dt.setIsCheck(1);
				}else{
					dt.setIsCheck(0);
				}
				listNew.add(dt);
			}
		model.addAttribute("lise", listNew);
		return "gz/add";
	}

	/**
	 * 跳至添加页面
	 * @return
	 */
	@RequestMapping(value = "/public.do")
	public String publicPage() {
		return "gz/public";
	}



	/**
	 * 添加执行
	 * @return
	 */
	@RequestMapping(value = "/exAdd.do")
	public String exAdd(Integer[] mgz, Model model, HttpServletRequest request, HttpServletResponse response) {
		//1新闻 2公告3招聘4.校园风采
			Integer id = Integer.valueOf(request.getSession().getAttribute("userId").toString());
			if(!isEmpty(mgz)){
				//首先删除用户关注历史
				Map<String, Object> map = MapUtils.getMap();
				map.put("userId", id);
				gzService.deleteBySqId("deleteBySqId", map);
				//遍历添加
				for(Integer mid : mgz){
					Gz gz = new Gz();
					gz.setType(mid);
					gz.setUserId(id);
					gzService.insert(gz);
				}
			}
		return "redirect:/login/index.do";
	}
	
	
	/**
	 * 跳至修改页面
	 * @return
	 */
	@RequestMapping(value = "/update.do")
	public String update(Integer id,Model model) {
		Gz obj = gzService.load(id);
		model.addAttribute("obj",obj);
		return "gz/update";
	}
	
	/**
	 * 添加修改
	 * @return
	 */
	@RequestMapping(value = "/exUpdate.do")
	public String exUpdate(Gz gz, Model model, HttpServletRequest request, HttpServletResponse response) {
		gzService.update(gz);
		return "redirect:/gz/findByObj.do";
	}
	
	/**
	 * 删除通过主键
	 * @return
	 */
	@RequestMapping(value = "/delete.do")
	public String delete(Integer id, Model model, HttpServletRequest request, HttpServletResponse response) {
		//真正删除
		gzService.deleteById(id);
		//通过参数删除
        //Map<String,Object> params = new HashMap<String,Object>();
		//params.put("id", id);
		//gzService.deleteBySqId("deleteBySql", params);
		//状态删除
		//Gz load = gzService.load(id);
		//load.setIsDelete(1);
		//gzService.update(load);
		return "redirect:/gz/findByObj.do";
	}
	
	// --------------------------------------- 华丽分割线 ------------------------------
	
	/**
	 * 分页查询 返回list json(通过对象)
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/findByObj.json", method = RequestMethod.GET)
	@ResponseBody
	public String findByObjByEntity(Gz gz, Model model, HttpServletRequest request, HttpServletResponse response) {
		//分页查询
		Pager<Gz> pagers = gzService.findByEntity(gz);
		JSONObject jsonObject = JsonUtil2.getJsonObject();
		jsonObject.put("pagers", pagers);
		jsonObject.put("obj", gz);
		return jsonObject.toString();
	}
	
	  
	/**
	 * 分页查询 返回list json(通过Map)
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/findByMap.json", method = RequestMethod.GET)
	@ResponseBody
	public String findByMapMap(Gz gz, Model model, HttpServletRequest request, HttpServletResponse response) {
		//通过map查询
		Map<String,Object> params = new HashMap<String,Object>();
        if(!isEmpty(gz.getType())){
        	params.put("type", gz.getType());
		}
        if(!isEmpty(gz.getUserId())){
        	params.put("userId", gz.getUserId());
		}
		//分页查询
		Pager<Gz> pagers = gzService.findByMap(params);
		JSONObject jsonObject = JsonUtil2.getJsonObject();
		jsonObject.put("pagers", pagers);
		jsonObject.put("obj", gz);
		return jsonObject.toString();
	}
	
	
	/**
	 * ajax 添加
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/exAdd.json", method = RequestMethod.POST)
	@ResponseBody
	public String exAddJson(Gz gz, Model model, HttpServletRequest request, HttpServletResponse response) {
		gzService.insert(gz);
		JSONObject jsonObject = JsonUtil2.getJsonObject();
		jsonObject.put("message", "添加成功");
		return jsonObject.toString();
	}
	

	/**
	 * ajax 修改
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/exUpdate.json",method = RequestMethod.POST)
	@ResponseBody
	public String exUpdateJson(Gz gz, Model model, HttpServletRequest request, HttpServletResponse response) {
		gzService.update(gz);
		JSONObject jsonObject = JsonUtil2.getJsonObject();
		jsonObject.put("message", "修改成功");
		return jsonObject.toString();
	}

	/**
	 * ajax 删除
	 * @return
	 */
	@RequestMapping(value = "/delete.json", produces = "text/html;charset=UTF-8", method = RequestMethod.GET)
	@ResponseBody
	public String exDeleteJson(Integer id, Model model, HttpServletRequest request, HttpServletResponse response) {
		//真正删除
		gzService.deleteById(id);
		//通过参数删除
        //Map<String,Object> params = new HashMap<String,Object>();
		//params.put("id", id);
		//gzService.deleteBySqId("deleteBySql", params);
		//状态删除
		//Gz load = gzService.load(id);
		//load.setIsDelete(1);
		//gzService.update(load);
		JSONObject jsonObject = JsonUtil2.getJsonObject();
		jsonObject.put("message", "删除成功");
		return jsonObject.toString();
	}
	/**
	 * 单文件上传
	 * @param file
	 * @param request
	 * @param model
	 * @return
	 */
    @RequestMapping(value = "/saveFile")  
    public String saveFile(@RequestParam(value = "file", required = false) MultipartFile file, HttpServletRequest request, Model model) {  
  
        System.out.println("开始");  
        String path = request.getSession().getServletContext().getRealPath("/upload");  
        String fileName = file.getOriginalFilename();  
        System.out.println(path);  
        File targetFile = new File(path, fileName);  
        if(!targetFile.exists()){  
            targetFile.mkdirs();  
        }  
        //保存  
        try {  
            file.transferTo(targetFile);  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
  
        return "";  
    }  
	
	
	/**
	 * springMvc多文件上传
	 * @param files
	 * @param id
	 * @return
	 */
    @RequestMapping(value = "/saveFiles")
    public String saveFiles(@RequestParam("file") CommonsMultipartFile[] files,Integer id,HttpServletRequest request){
		for(int i = 0;i<files.length;i++){
	      	System.out.println("fileName---------->" + files[i].getOriginalFilename());
		  if(!files[i].isEmpty()){
            int pre = (int) System.currentTimeMillis();
	     	try {
			//拿到输出流，同时重命名上传的文件
			 String filePath = request.getRealPath("/upload");
			 File f=new File(filePath);
			 if(!f.exists()){
				f.mkdirs();
			 }
		     String fileNmae=new Date().getTime() + files[i].getOriginalFilename();
		     File file=new File(filePath+"/"+pre + files[i].getOriginalFilename());
			  if(!file.exists()){
				  file.createNewFile();
			 }
			  files[i].transferTo(file);
		     } catch (Exception e) {
				e.printStackTrace();
				System.out.println("上传出错");
			 }
		  }
		}
	  return "";
	}
 // --------------------------------------- 华丽分割线 ------------------------------
	
	
}
