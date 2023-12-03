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
 * @date - 2020年02月18日 12时25分56秒
 */


@Controller
@RequestMapping("/schoolJob")
public class SchoolJobController extends BaseController {
	
	/**
	 * 依赖注入 start dao/service/===
	 */
	@Autowired
	private GzService gzService;
	@Autowired
	private SchoolJobService schoolJobService;
	@Autowired
	private SchoolJobDao schoolJobDao;
	@Autowired
	private UserJobDao userJobDao;
	@Autowired
	private UserJobCommentDao userJobCommentDao;
	@Autowired
	private UserJobService userJobService;

	//参加活动
	@RequestMapping(value = "/joinJob.do")
	public String joinJob(HttpServletRequest request,UserJob userJob,Model model){
		User user= (User) request.getSession().getAttribute("user");
		if(user==null){
			model.addAttribute("msg","需要登录后才能进行操作");
			//return "redirect:../schoolJob/addJobComment.do";
			return "error";
		}
		Map params=new HashMap();
		params.put("uid",user.getId());
		params.put("jid",userJob.getJid());
		List list=userJobDao.list(params);
		if(list.size()>0){
			model.addAttribute("msg","该活动已经参加,不能重复进行参加");
			return "error";
		}
		if (user.getCredit()<=0){
			model.addAttribute("msg","你的信誉值太低，无法报名");
			return "error";
		}
		params.put("id",userJob.getJid());
		SchoolJob s=(SchoolJob)schoolJobDao.list(params).get(0);
		userJob.setTitle(s.getTitle());
		userJob.setUid(user.getId());
		userJob.setUsername(user.getUserName());
		userJob.setCreatetime(new Date());
		userJobDao.insert(userJob);
		return findUserJob(request,model);
	}

	//查询已参加的活动
	@RequestMapping(value = "/myJoinJob.do")
	public String findUserJob(HttpServletRequest request,Model model){
		User user= (User) request.getSession().getAttribute("user");
		Map params=new HashMap();
		params.put("uid",user.getId());
		List<UserJob> list=userJobDao.list(params);
		model.addAttribute("list",list);
		return "schoolJob/myJoinJobs";
	}

	//添加活动评论
	@RequestMapping(value = "/addJobComment.do")
	public String addJobComment(HttpServletRequest request,UserJobComment userJobComment,Model model){
		User user= (User) request.getSession().getAttribute("user");
		if(user==null){
			return detail(userJobComment.getJid(),model);
		}
		userJobComment.setUid(user.getId());
		userJobComment.setUsername(user.getUserName());
		userJobComment.setTime(new Date());
		userJobCommentDao.insert(userJobComment);
		return detail(userJobComment.getJid(),model);
	}





    private Map fail(String msg){
		Map result=new HashMap();
		result.put("msg",msg);
		result.put("flag",false);
		return result;
	}
	private Map success(){
		Map result=new HashMap();
		result.put("flag",true);
		return result;
	}



	// --------------------------------------- 华丽分割线 ------------------------------
	
	/**
	 * 分页查询 返回list对象(通过对象)
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/findByObj.do")
	public String findByObj(SchoolJob schoolJob, Model model, HttpServletRequest request, HttpServletResponse response) {
		Object attribute = request.getSession().getAttribute("isManage");
 		if(isEmpty(attribute)){
 			Integer id2 = Integer.valueOf(request.getSession().getAttribute("userId").toString());
 			schoolJob.setAddUserId(id2);
 		}
		//分页查询
		Pager<SchoolJob> pagers = schoolJobService.findByEntity(schoolJob);
		model.addAttribute("pagers", pagers);
		//存储查询条件
		model.addAttribute("obj", schoolJob);
		return "schoolJob/schoolJob";
	}
	
	@RequestMapping(value = "/findByObj2.do")
	public String findByObj2(SchoolJob schoolJob, Model model, HttpServletRequest request, HttpServletResponse response) {
		//分页查询
		Pager<SchoolJob> pagers = schoolJobService.findByEntity(schoolJob);
		model.addAttribute("pagers", pagers);
		//存储查询条件
		model.addAttribute("obj", schoolJob);
		return "schoolJob/schoolJob2";
	}
	@RequestMapping(value = "/detail.do")
	public String detail(Integer id,Model model) {
		SchoolJob obj = schoolJobService.load(id);
		model.addAttribute("obj",obj);
		//查询某活动下的评论信息
		Map params=new HashMap();
		params.put("jid",id);
		List<UserJobComment> comments=userJobCommentDao.list(params);
		model.addAttribute("comments",comments);
		return "schoolJob/detail";
	}
	/**
	 * 分页查询 返回list对象(通过Map)
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/findByMap.do")
	public String findByMap(SchoolJob schoolJob, Model model, HttpServletRequest request, HttpServletResponse response) {
		//通过map查询
		Map<String,Object> params = new HashMap<String,Object>();
        if(!isEmpty(schoolJob.getTitle())){
        	params.put("title", schoolJob.getTitle());
		}
        if(!isEmpty(schoolJob.getContent())){
        	params.put("content", schoolJob.getContent());
		}
        if(!isEmpty(schoolJob.getAddTime())){
        	params.put("addTime", schoolJob.getAddTime());
		}
        if(!isEmpty(schoolJob.getAddUserId())){
        	params.put("addUserId", schoolJob.getAddUserId());
		}
		//分页查询
		Pager<SchoolJob> pagers = schoolJobService.findByMap(params);
		model.addAttribute("pagers", pagers);
		//存储查询条件
		model.addAttribute("obj", schoolJob);
		return "schoolJob/schoolJob";
	}
	
	
	/**
	 * 跳至添加页面
	 * @return
	 */
	@RequestMapping(value = "/add.do")
	public String add() {
		return "schoolJob/add";
	}

	
	/**
	 * 添加执行
	 * @return
	 */
	@RequestMapping(value = "/exAdd.do")
	public String exAdd(SchoolJob schoolJob, Model model, HttpServletRequest request, HttpServletResponse response) {
		 Object attribute = request.getSession().getAttribute("isManage");
	 		if(isEmpty(attribute)){
	 			Integer id2 = Integer.valueOf(request.getSession().getAttribute("userId").toString());
	 			schoolJob.setAddUserId(id2);
	 		}
		schoolJob.setAddTime(new Date());
		schoolJobService.insert(schoolJob);
		gzService.send(3,schoolJob.getTitle());
		return "redirect:/schoolJob/findByObj.do";
	}

	/**
	 * 添加执行
	 * @return
	 */
	@RequestMapping(value = "/exAdd2.do")
	public String exAdd2(SchoolJob schoolJob, Model model, HttpServletRequest request, HttpServletResponse response) {
		Object attribute = request.getSession().getAttribute("isManage");
		if(isEmpty(attribute)){
			Integer id2 = Integer.valueOf(request.getSession().getAttribute("userId").toString());
			schoolJob.setAddUserId(id2);
		}
		schoolJob.setAddTime(new Date());
		schoolJobService.insert(schoolJob);
		gzService.send(3,schoolJob.getTitle());
		return "redirect:/schoolJob/findByObj2.do";
	}
	
	
	/**
	 * 跳至修改页面
	 * @return
	 */
	@RequestMapping(value = "/update.do")
	public String update(Integer id,Model model) {
		SchoolJob obj = schoolJobService.load(id);
		model.addAttribute("obj",obj);
		return "schoolJob/update";
	}
	
	/**
	 * 添加修改
	 * @return
	 */
	@RequestMapping(value = "/exUpdate.do")
	public String exUpdate(SchoolJob schoolJob, Model model, HttpServletRequest request, HttpServletResponse response) {
		schoolJobService.update(schoolJob);
		return "redirect:/schoolJob/findByObj.do";
	}
	
	/**
	 * 删除通过主键
	 * @return
	 */
	@RequestMapping(value = "/delete.do")
	public String delete(Integer id, Model model, HttpServletRequest request, HttpServletResponse response) {
		//真正删除
		schoolJobService.deleteById(id);
		//通过参数删除
        //Map<String,Object> params = new HashMap<String,Object>();
		//params.put("id", id);
		//schoolJobService.deleteBySqId("deleteBySql", params);
		//状态删除
		//SchoolJob load = schoolJobService.load(id);
		//load.setIsDelete(1);
		//schoolJobService.update(load);
		return "redirect:/schoolJob/findByObj.do";
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
	public String findByObjByEntity(SchoolJob schoolJob, Model model, HttpServletRequest request, HttpServletResponse response) {
		//分页查询
		Pager<SchoolJob> pagers = schoolJobService.findByEntity(schoolJob);
		JSONObject jsonObject = JsonUtil2.getJsonObject();
		jsonObject.put("pagers", pagers);
		jsonObject.put("obj", schoolJob);
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
	public String findByMapMap(SchoolJob schoolJob, Model model, HttpServletRequest request, HttpServletResponse response) {
		//通过map查询
		Map<String,Object> params = new HashMap<String,Object>();
        if(!isEmpty(schoolJob.getTitle())){
        	params.put("title", schoolJob.getTitle());
		}
        if(!isEmpty(schoolJob.getContent())){
        	params.put("content", schoolJob.getContent());
		}
        if(!isEmpty(schoolJob.getAddTime())){
        	params.put("addTime", schoolJob.getAddTime());
		}
        if(!isEmpty(schoolJob.getAddUserId())){
        	params.put("addUserId", schoolJob.getAddUserId());
		}
		//分页查询
		Pager<SchoolJob> pagers = schoolJobService.findByMap(params);
		JSONObject jsonObject = JsonUtil2.getJsonObject();
		jsonObject.put("pagers", pagers);
		jsonObject.put("obj", schoolJob);
		return jsonObject.toString();
	}
	
	
	/**
	 * ajax 添加
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/exAdd.json", method = RequestMethod.POST)
	@ResponseBody
	public String exAddJson(SchoolJob schoolJob, Model model, HttpServletRequest request, HttpServletResponse response) {
		schoolJobService.insert(schoolJob);
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
	public String exUpdateJson(SchoolJob schoolJob, Model model, HttpServletRequest request, HttpServletResponse response) {
		schoolJobService.update(schoolJob);
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
		schoolJobService.deleteById(id);
		//通过参数删除
        //Map<String,Object> params = new HashMap<String,Object>();
		//params.put("id", id);
		//schoolJobService.deleteBySqId("deleteBySql", params);
		//状态删除
		//SchoolJob load = schoolJobService.load(id);
		//load.setIsDelete(1);
		//schoolJobService.update(load);
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

	@RequestMapping(value = "/nameList.do")
	public String nameList(SchoolJob schoolJob, Model model, HttpServletRequest request, HttpServletResponse response) {
		Object attribute = request.getSession().getAttribute("isManage");
		if(isEmpty(attribute)){
			Integer id2 = Integer.valueOf(request.getSession().getAttribute("userId").toString());
			schoolJob.setAddUserId(id2);
		}
		UserJob userJob=new UserJob();
		userJob.setJid(schoolJob.getId());
		//分页查询
		Pager<UserJob> pagers = userJobService.findByEntity(userJob);
		model.addAttribute("pagers", pagers);
		//存储查询条件
		model.addAttribute("obj", schoolJob);
		return "schoolJob/nameList";
	}

	@RequestMapping("/reduce.do")
	public String reduce(Integer uid,Model model){
    	model.addAttribute("uid",uid);
		model.addAttribute("msg","");
    	return "schoolJob/reduce";
	}
}
