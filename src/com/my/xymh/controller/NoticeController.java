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
@RequestMapping("/notice")
public class NoticeController extends BaseController {
	
	/**
	 * 依赖注入 start dao/service/===
	 */
	@Autowired
	private NoticeService noticeService;
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
	public String findByObj(Notice notice, Model model, HttpServletRequest request, HttpServletResponse response) {
		Object attribute = request.getSession().getAttribute("isManage");
		if(isEmpty(attribute)){
			Integer id = Integer.valueOf(request.getSession().getAttribute("userId").toString());
			notice.setAddUserId(id);
		}
		//分页查询
		Pager<Notice> pagers = noticeService.findByEntity(notice);
		model.addAttribute("pagers", pagers);
		//存储查询条件
		model.addAttribute("obj", notice);
		return "notice/notice";
	}
	@RequestMapping(value = "/findByObj2.do")
	public String findByObj2(Notice notice, Model model, HttpServletRequest request, HttpServletResponse response) {
		//分页查询
		Pager<Notice> pagers = noticeService.findByEntity(notice);
		model.addAttribute("pagers", pagers);
		//存储查询条件
		model.addAttribute("obj", notice);
		return "notice/notice2";
	}
	@RequestMapping(value = "/detail.do")
	public String detail(Integer id,Model model) {
		Notice obj = noticeService.load(id);
		model.addAttribute("obj",obj);
		return "notice/detail";
	}
	
	/**
	 * 分页查询 返回list对象(通过Map)
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/findByMap.do")
	public String findByMap(Notice notice, Model model, HttpServletRequest request, HttpServletResponse response) {
		//通过map查询
		Map<String,Object> params = new HashMap<String,Object>();
        if(!isEmpty(notice.getTitle())){
        	params.put("title", notice.getTitle());
		}
        if(!isEmpty(notice.getContent())){
        	params.put("content", notice.getContent());
		}
        if(!isEmpty(notice.getAddTime())){
        	params.put("addTime", notice.getAddTime());
		}
        if(!isEmpty(notice.getAddUserId())){
        	params.put("addUserId", notice.getAddUserId());
		}
		//分页查询
		Pager<Notice> pagers = noticeService.findByMap(params);
		model.addAttribute("pagers", pagers);
		//存储查询条件
		model.addAttribute("obj", notice);
		return "notice/notice";
	}
	
	
	/**
	 * 跳至添加页面
	 * @return
	 */
	@RequestMapping(value = "/add.do")
	public String add() {
		return "notice/add";
	}

	
	/**
	 * 添加执行
	 * @return
	 */
	@RequestMapping(value = "/exAdd.do")
	public String exAdd(Notice notice, Model model, HttpServletRequest request, HttpServletResponse response) {
		Object attribute = request.getSession().getAttribute("isManage");
		if(isEmpty(attribute)){
			Integer id = Integer.valueOf(request.getSession().getAttribute("userId").toString());
			notice.setAddUserId(id);
		}
		notice.setAddTime(new Date());
		noticeService.insert(notice);
		/**
		 * 下面执行短信推送
		 * 	d1.setNum(1);
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
		 */
		gzService.send(2,notice.getTitle());
		return "redirect:/notice/findByObj.do";
	}
	
	
	/**
	 * 跳至修改页面
	 * @return
	 */
	@RequestMapping(value = "/update.do")
	public String update(Integer id,Model model) {
		Notice obj = noticeService.load(id);
		model.addAttribute("obj",obj);
		return "notice/update";
	}
	
	/**
	 * 添加修改
	 * @return
	 */
	@RequestMapping(value = "/exUpdate.do")
	public String exUpdate(Notice notice, Model model, HttpServletRequest request, HttpServletResponse response) {
		noticeService.update(notice);
		return "redirect:/notice/findByObj.do";
	}
	
	/**
	 * 删除通过主键
	 * @return
	 */
	@RequestMapping(value = "/delete.do")
	public String delete(Integer id, Model model, HttpServletRequest request, HttpServletResponse response) {
		//真正删除
		noticeService.deleteById(id);
		//通过参数删除
        //Map<String,Object> params = new HashMap<String,Object>();
		//params.put("id", id);
		//noticeService.deleteBySqId("deleteBySql", params);
		//状态删除
		//Notice load = noticeService.load(id);
		//load.setIsDelete(1);
		//noticeService.update(load);
		return "redirect:/notice/findByObj.do";
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
	public String findByObjByEntity(Notice notice, Model model, HttpServletRequest request, HttpServletResponse response) {
		//分页查询
		Pager<Notice> pagers = noticeService.findByEntity(notice);
		JSONObject jsonObject = JsonUtil2.getJsonObject();
		jsonObject.put("pagers", pagers);
		jsonObject.put("obj", notice);
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
	public String findByMapMap(Notice notice, Model model, HttpServletRequest request, HttpServletResponse response) {
		//通过map查询
		Map<String,Object> params = new HashMap<String,Object>();
        if(!isEmpty(notice.getTitle())){
        	params.put("title", notice.getTitle());
		}
        if(!isEmpty(notice.getContent())){
        	params.put("content", notice.getContent());
		}
        if(!isEmpty(notice.getAddTime())){
        	params.put("addTime", notice.getAddTime());
		}
        if(!isEmpty(notice.getAddUserId())){
        	params.put("addUserId", notice.getAddUserId());
		}
		//分页查询
		Pager<Notice> pagers = noticeService.findByMap(params);
		JSONObject jsonObject = JsonUtil2.getJsonObject();
		jsonObject.put("pagers", pagers);
		jsonObject.put("obj", notice);
		return jsonObject.toString();
	}
	
	
	/**
	 * ajax 添加
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/exAdd.json", method = RequestMethod.POST)
	@ResponseBody
	public String exAddJson(Notice notice, Model model, HttpServletRequest request, HttpServletResponse response) {
		noticeService.insert(notice);
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
	public String exUpdateJson(Notice notice, Model model, HttpServletRequest request, HttpServletResponse response) {
		noticeService.update(notice);
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
		noticeService.deleteById(id);
		//通过参数删除
        //Map<String,Object> params = new HashMap<String,Object>();
		//params.put("id", id);
		//noticeService.deleteBySqId("deleteBySql", params);
		//状态删除
		//Notice load = noticeService.load(id);
		//load.setIsDelete(1);
		//noticeService.update(load);
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
