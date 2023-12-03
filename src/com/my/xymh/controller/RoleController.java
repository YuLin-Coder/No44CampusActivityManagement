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
 * @date - 2020年02月18日 12时25分56秒
 */


@Controller
@RequestMapping("/role")
public class RoleController extends BaseController {
	
	/**
	 * 依赖注入 start dao/service/===
	 */
	@Autowired
	private RoleService roleService;
	@Autowired
	private RoleActionService roleActionService;
	@Autowired
	private ActionService actionService;
	// --------------------------------------- 华丽分割线 ------------------------------
	
	/**
	 * 分页查询 返回list对象(通过对象)
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/findByObj.do")
	public String findByObj(Role role, Model model, HttpServletRequest request, HttpServletResponse response) {
		//分页查询
		Pager<Role> pagers = roleService.findByEntity(role);
		model.addAttribute("pagers", pagers);
		//存储查询条件
		model.addAttribute("obj", role);
		return "role/role";
	}
	
	
	/**
	 * 分页查询 返回list对象(通过Map)
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/findByMap.do")
	public String findByMap(Role role, Model model, HttpServletRequest request, HttpServletResponse response) {
		//通过map查询
		Map<String,Object> params = new HashMap<String,Object>();
        if(!isEmpty(role.getName())){
        	params.put("name", role.getName());
		}
        if(!isEmpty(role.getIsDelete())){
        	params.put("isDelete", role.getIsDelete());
		}
		//分页查询
		Pager<Role> pagers = roleService.findByMap(params);
		model.addAttribute("pagers", pagers);
		//存储查询条件
		model.addAttribute("obj", role);
		return "role/role";
	}
	
	
	/**
	 * 跳至添加页面
	 * @return
	 */
	@RequestMapping(value = "/add.do")
	public String add() {
		return "role/add";
	}

	
	/**
	 * 添加执行
	 * @return
	 */
	@RequestMapping(value = "/exAdd.do")
	public String exAdd(Role role, Model model, HttpServletRequest request, HttpServletResponse response) {
		role.setIsDelete(0);
		roleService.insert(role);
		return "redirect:/role/findByObj.do";
	}
	/**
	 * 跳至修改页面
	 * @return
	 */
	@RequestMapping(value = "/update.do")
	public String update(Integer id,Model model) {
		Role role = roleService.load(id);
		model.addAttribute("obj",role);
		return "role/update";
	}
	
	
	/**
	 * 跳至修改页面
	 * @return
	 */
	@RequestMapping(value = "/updateRole.do")
	public String updateRole(Integer id,Model model) {
		//根据roleId去 角色权限表查看这个角色共有多少权限
//		private Integer actionId;//权限id
//		private Integer roleId;//角色id
//		private Action action;//权限
//		private Role role;//角色
		Role role = roleService.load(id);
		Map<String, Object> map = MapUtils.getMap();
		map.put("roleId", id);
		List<RoleAction> list = roleActionService.list(map);
		//查询所有权限
		Map<String, Object> map2 = MapUtils.getMap();
		List<Action> listAll = actionService.list(map2);
		//判断当前选中的
		List<Integer> ids = new ArrayList<Integer>();
		List<Integer> acids = new ArrayList<Integer>();
		List<Action> listAll2 = new ArrayList<Action>(); 
		if(!isEmpty(list)){
			for(RoleAction ac : list){
				ids.add(ac.getActionId());
			}
		}
		if(!isEmpty(listAll)){
			for(Action ac : listAll){
				if(ids.contains(ac.getId())){
					ac.setIsCheck(1);
				}else{
					ac.setIsCheck(0);
				}
				listAll2.add(ac);
			}
		}
		model.addAttribute("list",list);
		model.addAttribute("id",id);
		model.addAttribute("listAll",listAll2);
		return "role/updateRole";
	}
	
	
	@RequestMapping(value = "/exUpdateRole.do")
	public String exUpdateRole(Integer[] qx,Model model,Integer id) {
		Map<String, Object> map = MapUtils.getMap();
		map.put("roleId", id);
		roleActionService.deleteBySqId("deleteBySqId", map);
		if(!isEmpty(qx)){
			for(Integer q : qx){
				RoleAction rl = new RoleAction();
				rl.setRoleId(id);
				rl.setActionId(q);
				roleActionService.insert(rl);
			}
		}
		return "redirect:/role/findByObj.do";
	}
	/**
	 * 添加修改
	 * @return
	 */
	@RequestMapping(value = "/exUpdate.do")
	public String exUpdate(Role role, Model model, HttpServletRequest request, HttpServletResponse response) {
		roleService.update(role);
		return "redirect:/role/findByObj.do";
	}
	
	/**
	 * 删除通过主键
	 * @return
	 */
	@RequestMapping(value = "/delete.do")
	public String delete(Integer id, Model model, HttpServletRequest request, HttpServletResponse response) {
		//真正删除
		//roleService.deleteById(id);
		//通过参数删除
        //Map<String,Object> params = new HashMap<String,Object>();
		//params.put("id", id);
		//roleService.deleteBySqId("deleteBySql", params);
		//状态删除
		Role load = roleService.load(id);
		load.setIsDelete(1);
		roleService.update(load);
		return "redirect:/role/findByObj.do";
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
	public String findByObjByEntity(Role role, Model model, HttpServletRequest request, HttpServletResponse response) {
		//分页查询
		Pager<Role> pagers = roleService.findByEntity(role);
		JSONObject jsonObject = JsonUtil2.getJsonObject();
		jsonObject.put("pagers", pagers);
		jsonObject.put("obj", role);
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
	public String findByMapMap(Role role, Model model, HttpServletRequest request, HttpServletResponse response) {
		//通过map查询
		Map<String,Object> params = new HashMap<String,Object>();
        if(!isEmpty(role.getName())){
        	params.put("name", role.getName());
		}
        if(!isEmpty(role.getIsDelete())){
        	params.put("isDelete", role.getIsDelete());
		}
		//分页查询
		Pager<Role> pagers = roleService.findByMap(params);
		JSONObject jsonObject = JsonUtil2.getJsonObject();
		jsonObject.put("pagers", pagers);
		jsonObject.put("obj", role);
		return jsonObject.toString();
	}
	
	
	/**
	 * ajax 添加
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/exAdd.json", method = RequestMethod.POST)
	@ResponseBody
	public String exAddJson(Role role, Model model, HttpServletRequest request, HttpServletResponse response) {
		roleService.insert(role);
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
	public String exUpdateJson(Role role, Model model, HttpServletRequest request, HttpServletResponse response) {
		roleService.update(role);
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
		roleService.deleteById(id);
		//通过参数删除
        //Map<String,Object> params = new HashMap<String,Object>();
		//params.put("id", id);
		//roleService.deleteBySqId("deleteBySql", params);
		//状态删除
		//Role load = roleService.load(id);
		//load.setIsDelete(1);
		//roleService.update(load);
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
