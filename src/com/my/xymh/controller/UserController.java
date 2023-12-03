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
 * @date - 2020年02月18日 12时25分56秒
 */


@Controller
@RequestMapping("/user")
public class UserController extends BaseController {
	
	/**
	 * 依赖注入 start dao/service/===
	 */
	@Autowired
	private UserService userService;
	@Autowired
	private RoleService roleService;
	// --------------------------------------- 华丽分割线 ------------------------------
	
	/**
	 * 分页查询 返回list对象(通过对象)
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/findByObj.do")
	public String findByObj(User user, Model model, HttpServletRequest request, HttpServletResponse response) {
		//分页查询
		Pager<User> pagers = userService.findByEntity(user);
		model.addAttribute("pagers", pagers);
		//存储查询条件
		model.addAttribute("obj", user);
		return "user/user";
	}
	
	
	/**
	 * 分页查询 返回list对象(通过Map)
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/findByMap.do")
	public String findByMap(User user, Model model, HttpServletRequest request, HttpServletResponse response) {
		//通过map查询
		Map<String,Object> params = new HashMap<String,Object>();
        if(!isEmpty(user.getUserName())){
        	params.put("userName", user.getUserName());
		}
        if(!isEmpty(user.getNickName())){
        	params.put("nickName", user.getNickName());
		}
        if(!isEmpty(user.getPassWord())){
        	params.put("passWord", user.getPassWord());
		}
        if(!isEmpty(user.getCode())){
        	params.put("code", user.getCode());
		}
        if(!isEmpty(user.getZy())){
        	params.put("zy", user.getZy());
		}
        if(!isEmpty(user.getNj())){
        	params.put("nj", user.getNj());
		}
        if(!isEmpty(user.getIsDelete())){
        	params.put("isDelete", user.getIsDelete());
		}
        if(!isEmpty(user.getType())){
        	params.put("type", user.getType());
		}
		//分页查询
		Pager<User> pagers = userService.findByMap("findByNickName", params);
		
		model.addAttribute("pagers", pagers);
		//存储查询条件
		model.addAttribute("obj", user);
		if(user.getType() == 1){
			return "user/user1";
		}
		if(user.getType() == 2){
			return "user/user2";
		}
		if(user.getType() == 3){
			return "user/user3";
		}
		return "user/user";
	}
	
	
	/**
	 * 跳至添加页面
	 * @return
	 */
	@RequestMapping(value = "/add.do")
	public String add(Integer type) {
		if(type == 1){
			return "user/add1";
		}
		if(type == 2){
			return "user/add2";
		}
		if(type == 3){
			return "user/add3";
		}
		return "user/add";
	}

	
	/**
	 * 添加执行
	 * @return
	 */
	@RequestMapping(value = "/exAdd.do")
	public String exAdd(User user, Model model, HttpServletRequest request, HttpServletResponse response) {
		user.setIsDelete(0);
		userService.insert(user);
		return "redirect:/user/findByMap.do?type="+user.getType();
	}
	//注册

	@RequestMapping(value = "/exAdd2.do")
	public String exAdd2(User user, Model model, HttpServletRequest request, HttpServletResponse response) {
		user.setIsDelete(0);
		user.setCredit(100);
		userService.insert(user);
		return "redirect:../login/index.do";
	}
	
	@RequestMapping(value = "/jsFp.do")
	public String jsFp(Integer id,Model model,Integer type) {
		User obj = userService.load(id);
		model.addAttribute("obj",obj);
		List<Role> list = roleService.list(MapUtils.getMap());
		model.addAttribute("list", list);
		return "user/jsFp";
	}
	
	@RequestMapping(value = "/exUpdateFp.do")
	public String exUpdateFp(User user,Integer js, Model model, HttpServletRequest request, HttpServletResponse response) {
		user.setRoleId(js);
		userService.update(user);
		User load = userService.load(user.getId());
		return "redirect:/user/findByMap.do?type="+load.getType();
	}
	
	/**
	 * 跳至修改页面
	 * @return
	 */
	@RequestMapping(value = "/update.do")
	public String update(Integer id,Model model,Integer type) {
		User obj = userService.load(id);
		model.addAttribute("obj",obj);
		if(type == 1){
			return "user/update1";
		}
		if(type == 2){
			return "user/update2";
		}
		if(type == 3){
			return "user/update3";
		}
		return "";
		
	}
	
	/**
	 * 添加修改
	 * @return
	 */
	@RequestMapping(value = "/exUpdate.do")
	public String exUpdate(User user, Model model, HttpServletRequest request, HttpServletResponse response) {
		userService.update(user);
		return "redirect:/user/findByMap.do?type="+user.getType();
	}
	
	/**
	 * 删除通过主键
	 * @return
	 */
	@RequestMapping(value = "/delete.do")
	public String delete(Integer id, Model model, HttpServletRequest request, HttpServletResponse response) {
		//真正删除
		//userService.deleteById(id);
		//通过参数删除
        //Map<String,Object> params = new HashMap<String,Object>();
		//params.put("id", id);
		//userService.deleteBySqId("deleteBySql", params);
		//状态删除
		User load = userService.load(id);
		load.setIsDelete(1);
		userService.update(load);
		User obj = userService.load(id);
		return "redirect:/user/findByMap.do?type="+obj.getType();
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
	public String findByObjByEntity(User user, Model model, HttpServletRequest request, HttpServletResponse response) {
		//分页查询
		Pager<User> pagers = userService.findByEntity(user);
		JSONObject jsonObject = JsonUtil2.getJsonObject();
		jsonObject.put("pagers", pagers);
		jsonObject.put("obj", user);
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
	public String findByMapMap(User user, Model model, HttpServletRequest request, HttpServletResponse response) {
		//通过map查询
		Map<String,Object> params = new HashMap<String,Object>();
        if(!isEmpty(user.getUserName())){
        	params.put("userName", user.getUserName());
		}
        if(!isEmpty(user.getNickName())){
        	params.put("nickName", user.getNickName());
		}
        if(!isEmpty(user.getPassWord())){
        	params.put("passWord", user.getPassWord());
		}
        if(!isEmpty(user.getCode())){
        	params.put("code", user.getCode());
		}
        if(!isEmpty(user.getZy())){
        	params.put("zy", user.getZy());
		}
        if(!isEmpty(user.getNj())){
        	params.put("nj", user.getNj());
		}
        if(!isEmpty(user.getIsDelete())){
        	params.put("isDelete", user.getIsDelete());
		}
        if(!isEmpty(user.getType())){
        	params.put("type", user.getType());
		}
		//分页查询
		Pager<User> pagers = userService.findByMap(params);
		JSONObject jsonObject = JsonUtil2.getJsonObject();
		jsonObject.put("pagers", pagers);
		jsonObject.put("obj", user);
		return jsonObject.toString();
	}
	
	
	/**
	 * ajax 添加
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/exAdd.json", method = RequestMethod.POST)
	@ResponseBody
	public String exAddJson(User user, Model model, HttpServletRequest request, HttpServletResponse response) {
		userService.insert(user);
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
	public String exUpdateJson(User user, Model model, HttpServletRequest request, HttpServletResponse response) {
		userService.update(user);
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
		userService.deleteById(id);
		//通过参数删除
        //Map<String,Object> params = new HashMap<String,Object>();
		//params.put("id", id);
		//userService.deleteBySqId("deleteBySql", params);
		//状态删除
		//User load = userService.load(id);
		//load.setIsDelete(1);
		//userService.update(load);
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

	@RequestMapping(value = "updateCredit.do",method = RequestMethod.POST)
	public String updateCredit(Integer uid,Integer credit,Model model){
		model.addAttribute("uid",uid);
		User user=new User();
		user.setId(uid);
		User load = userService.load(uid);
		user.setCredit(load.getCredit()-credit);
		userService.update(user);
		model.addAttribute("msg","扣减信誉值成功！");
		return "schoolJob/reduce";
	}
	
}
