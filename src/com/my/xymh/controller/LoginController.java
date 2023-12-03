package com.my.xymh.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.my.xymh.base.BaseController;
import com.my.xymh.entity.Action;
import com.my.xymh.entity.Manage;
import com.my.xymh.entity.News;
import com.my.xymh.entity.Notice;
import com.my.xymh.entity.RoleAction;
import com.my.xymh.entity.SchoolInfo;
import com.my.xymh.entity.SchoolJob;
import com.my.xymh.entity.User;
import com.my.xymh.entity.WebCount;
import com.my.xymh.entity.Xl;
import com.my.xymh.service.ActionService;
import com.my.xymh.service.ManageService;
import com.my.xymh.service.NewsService;
import com.my.xymh.service.NoticeService;
import com.my.xymh.service.SchoolInfoService;
import com.my.xymh.service.SchoolJobService;
import com.my.xymh.service.UserService;
import com.my.xymh.service.WebCountService;
import com.my.xymh.service.XlService;
import com.my.xymh.utils.Pager;
import com.my.xymh.utils.UserUtils;

/**
 * 登陆相关
 * @author 
 *
 */
@Controller
@RequestMapping("/login")
public class LoginController extends BaseController{
	@Autowired
	private WebCountService webCountService;
	@Autowired
	private ManageService manageService;
	@Autowired
	private UserService userService;
	@Autowired
	private ActionService actionService;
	
	@Autowired
	private NewsService newsService;
	@Autowired
	private NoticeService noticeService;
	@Autowired
	private SchoolInfoService schoolInfoService;
	@Autowired
	private SchoolJobService schoolJobService;
	@Autowired
	private XlService xlService;
	/**
	 * 跳转登陆
	 * @return
	 */
	@RequestMapping(value = "/login.do")
	public String login(){
		return "login/login";
		
	}
	/**
	 * 用户首页
	 * @return
	 */
	@RequestMapping(value = "/index.do")
	public String index(Model model){
//		@Autowired
//		private NewsService newsService;
//		@Autowired
//		private NoticeService noticeService;
//		@Autowired
//		private SchoolInfoService schoolInfoService;
//		@Autowired
//		private SchoolJobService schoolJobService;
		
		//5张图片
		List<SchoolInfo> phs = schoolInfoService.listAll();
		//9个新闻
		List<News> news = newsService.listAll();
		//5个公告
		List<Notice> notices = noticeService.listAll();
		//9个招聘
		List<SchoolJob> sjs = schoolJobService.listAll();
		//5个校历
		List<Xl> xls = xlService.listAll();
		model.addAttribute("phs", phs);
		model.addAttribute("news", news);
		model.addAttribute("notices", notices);
		model.addAttribute("sjs", sjs);
		model.addAttribute("xls", xls);
		WebCount load = webCountService.load(1);
		load.setCountAll(load.getCountAll()+1);
		webCountService.update(load);
		model.addAttribute("count", load.getCountAll()+1);
		return "login/index";
		
	}
	@RequestMapping(value = "/welcome.do")
	public String welcome(){
		return "login/welcome";
		
	}
	@RequestMapping(value = "/toLogin.do")
	public String findByObj(String userName,String passWord,Integer type, Model model, HttpServletRequest request, HttpServletResponse response,HttpSession session) {
		 
		if(type == null){
			return "login/login";
		}
		//type == 管理员 2 社团 3 教师 4用户
		session.removeAttribute("isManage");
		session.removeAttribute("user");
		session.removeAttribute("userId");
		//private Integer type;// 1老师 2 社团3.用户 
		if(type == 1){
			Manage manage = new Manage();
			manage.setUserName(userName);
			manage.setPassWord(passWord);
			Manage man = manageService.loadBySqlId("login", manage);
			if(!isEmpty(man)){
				request.getSession().setAttribute("isManage", 1);
				return "login/mIndex";
			}else{
				return "login/login";
			}
		}else if(type == 2){
			User user = new User();
			user.setUserName(userName);
			user.setPassWord(passWord);
			user.setType(type);
			User use = userService.loadBySqlId("login",user);
			
			if(!isEmpty(use)){
				roleAct(model, use);
				request.getSession().setAttribute("user", use);
				request.getSession().setAttribute("userId", use.getId());
				return "login/mIndex";
			}else{
				return "login/login";
			}
		}else if(type == 3){
			User user = new User();
			user.setUserName(userName);
			user.setPassWord(passWord);
			user.setType(1);
			User use = userService.loadBySqlId("login",user);
			if(!isEmpty(use)){
				roleAct(model, use);
				request.getSession().setAttribute("user", use);
				request.getSession().setAttribute("userId", use.getId());
				return "login/mIndex";
			}else{
				return "login/login";
			}
		}else{
			//5张图片
			List<SchoolInfo> phs = schoolInfoService.listAll();
			//9个新闻
			List<News> news = newsService.listAll();
			//5个公告
			List<Notice> notices = noticeService.listAll();
			//9个招聘
			List<SchoolJob> sjs = schoolJobService.listAll();
			//5个校历
			List<Xl> xls = xlService.listAll();
			model.addAttribute("phs", phs);
			model.addAttribute("news", news);
			model.addAttribute("notices", notices);
			model.addAttribute("sjs", sjs);
			model.addAttribute("xls", xls);
			User user = new User();
			user.setUserName(userName);
			user.setPassWord(passWord);
			user.setType(3);
			User use = userService.loadBySqlId("login",user);
			if(!isEmpty(use)){
				request.getSession().setAttribute("user", use);
				request.getSession().setAttribute("userId", use.getId());
			}
		}
		return "login/index";
	}

	private void roleAct(Model model, User user) {
		if(!isEmpty(user.getRole())){
			List<Action> actions = new ArrayList<Action>();
			List<RoleAction> roleActions = user.getRole().getRoleActions();
			if(!isEmpty(roleActions)){
				for (RoleAction ra: roleActions) {
					actions.add(actionService.load(ra.getActionId()));
				}
			}
			model.addAttribute("actions", actions);
		}
	}
	
	/**
	 * 退出
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/tuichu.do")
	  public String tuichu(HttpSession session){
        session.invalidate();
        return "login/login";
    }
	@RequestMapping(value = "/utu.do")
	  public String utu(HttpSession session){
      session.invalidate();
      return "redirect:/login/index.do";
  }
	

}
