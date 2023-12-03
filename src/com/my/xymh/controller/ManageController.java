package com.my.xymh.controller;
import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
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
 * @date - 2020年02月18日 12时25分55秒
 */


@Controller
@RequestMapping("/manage")
public class ManageController extends BaseController {
	
	/**
	 * 依赖注入 start dao/service/===
	 */
	@Autowired
	private ManageService manageService;
	
	// --------------------------------------- 华丽分割线 ------------------------------
	
	/**
	 * 分页查询 返回list对象(通过对象)
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/findByObj.do")
	public String findByObj(Manage manage, Model model, HttpServletRequest request, HttpServletResponse response) {
		//分页查询
		Pager<Manage> pagers = manageService.findByEntity(manage);
		model.addAttribute("pagers", pagers);
		//存储查询条件
		model.addAttribute("obj", manage);
		return "manage/manage";
	}
	
	
	/**
	 * 分页查询 返回list对象(通过Map)
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/findByMap.do")
	public String findByMap(Manage manage, Model model, HttpServletRequest request, HttpServletResponse response) {
		//通过map查询
		Map<String,Object> params = new HashMap<String,Object>();
        if(!isEmpty(manage.getUserName())){
        	params.put("userName", manage.getUserName());
		}
        if(!isEmpty(manage.getPassWord())){
        	params.put("passWord", manage.getPassWord());
		}
        if(!isEmpty(manage.getNickName())){
        	params.put("nickName", manage.getNickName());
		}
        if(!isEmpty(manage.getIsDelete())){
        	params.put("isDelete", manage.getIsDelete());
		}
		//分页查询
		Pager<Manage> pagers = manageService.findByMap(params);
		model.addAttribute("pagers", pagers);
		//存储查询条件
		model.addAttribute("obj", manage);
		return "manage/manage";
	}
	
	
	/**
	 * 跳至添加页面
	 * @return
	 */
	@RequestMapping(value = "/add.do")
	public String add() {
		return "manage/add";
	}

	
	/**
	 * 添加执行
	 * @return
	 */
	@RequestMapping(value = "/exAdd.do")
	public String exAdd(Manage manage, Model model, HttpServletRequest request, HttpServletResponse response) {
		manageService.insert(manage);
		return "redirect:/manage/findByObj.do";
	}
	
	
	/**
	 * 跳至修改页面
	 * @return
	 */
	@RequestMapping(value = "/update.do")
	public String update(Integer id,Model model) {
		Manage obj = manageService.load(id);
		model.addAttribute("obj",obj);
		return "manage/update";
	}
	
	/**
	 * 添加修改
	 * @return
	 */
	@RequestMapping(value = "/exUpdate.do")
	public String exUpdate(Manage manage, Model model, HttpServletRequest request, HttpServletResponse response) {
		manageService.update(manage);
		return "redirect:/manage/findByObj.do";
	}
	
	/**
	 * 删除通过主键
	 * @return
	 */
	@RequestMapping(value = "/delete.do")
	public String delete(Integer id, Model model, HttpServletRequest request, HttpServletResponse response) {
		//真正删除
		manageService.deleteById(id);
		//通过参数删除
        //Map<String,Object> params = new HashMap<String,Object>();
		//params.put("id", id);
		//manageService.deleteBySqId("deleteBySql", params);
		//状态删除
		//Manage load = manageService.load(id);
		//load.setIsDelete(1);
		//manageService.update(load);
		return "redirect:/manage/findByObj.do";
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
	public String findByObjByEntity(Manage manage, Model model, HttpServletRequest request, HttpServletResponse response) {
		//分页查询
		Pager<Manage> pagers = manageService.findByEntity(manage);
		JSONObject jsonObject = JsonUtil2.getJsonObject();
		jsonObject.put("pagers", pagers);
		jsonObject.put("obj", manage);
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
	public String findByMapMap(Manage manage, Model model, HttpServletRequest request, HttpServletResponse response) {
		//通过map查询
		Map<String,Object> params = new HashMap<String,Object>();
        if(!isEmpty(manage.getUserName())){
        	params.put("userName", manage.getUserName());
		}
        if(!isEmpty(manage.getPassWord())){
        	params.put("passWord", manage.getPassWord());
		}
        if(!isEmpty(manage.getNickName())){
        	params.put("nickName", manage.getNickName());
		}
        if(!isEmpty(manage.getIsDelete())){
        	params.put("isDelete", manage.getIsDelete());
		}
		//分页查询
		Pager<Manage> pagers = manageService.findByMap(params);
		JSONObject jsonObject = JsonUtil2.getJsonObject();
		jsonObject.put("pagers", pagers);
		jsonObject.put("obj", manage);
		return jsonObject.toString();
	}
	
	
	/**
	 * ajax 添加
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/exAdd.json", method = RequestMethod.POST)
	@ResponseBody
	public String exAddJson(Manage manage, Model model, HttpServletRequest request, HttpServletResponse response) {
		manageService.insert(manage);
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
	public String exUpdateJson(Manage manage, Model model, HttpServletRequest request, HttpServletResponse response) {
		manageService.update(manage);
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
		manageService.deleteById(id);
		//通过参数删除
        //Map<String,Object> params = new HashMap<String,Object>();
		//params.put("id", id);
		//manageService.deleteBySqId("deleteBySql", params);
		//状态删除
		//Manage load = manageService.load(id);
		//load.setIsDelete(1);
		//manageService.update(load);
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
