package com.hkay.weifei.controller;  
  
import javax.annotation.Resource;  
import javax.servlet.http.HttpServletRequest;  
  
import org.springframework.stereotype.Controller;  
import org.springframework.ui.Model;  
import org.springframework.web.bind.annotation.RequestMapping;  
  
import com.hkay.weifei.pojo.User;  
import com.hkay.weifei.service.UserService;  
  
@Controller  
@RequestMapping("/user")  
public class UserController {  
    @Resource  
    private UserService userService;  
      
    @RequestMapping("/showUser")  
    public String toIndex(HttpServletRequest request,Model model){ 
    	int userId = Integer.parseInt(request.getParameter("id"));
    	User user=new User();
    	user.setId(userId);
        User userList = this.userService.getUserById(user);  
        model.addAttribute("user", userList);  
        return "showUser"; 
    }

    @RequestMapping("/showUser11")
    public String toIndex11(HttpServletRequest request,Model model){
        int userId = Integer.parseInt(request.getParameter("id"));
        User user=new User();
        user.setId(userId);
        User userList = this.userService.getUserById(user);
        model.addAttribute("user", userList);
        return "showUser";
    }
}