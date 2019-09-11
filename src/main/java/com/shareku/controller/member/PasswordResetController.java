package com.shareku.controller.member;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shareku.entity.auth.User;
import com.shareku.service.auth.UserService;
import com.shareku.util.SendHTMLMail;
import com.shareku.util.Tools;

@Controller
public class PasswordResetController {
	@Autowired
	private UserService userService;
	@RequestMapping("/password/retrieve.shtml")
	public String forgetPass(){
		return "member/forget_password";
	}
	
    @RequestMapping(value = "/password/find.shtml",method=RequestMethod.GET)
    public @ResponseBody Map<String,String> forgetPass(HttpServletRequest request,String loginname,String email){
        User user = this.userService.findUserByLoginname(loginname);
        Map<String,String> map = new HashMap<String,String>();
        String msg = "";
        if(user == null){              //用户名不存在
            msg = "用户名不存在!";
            map.put("msg",msg);
            map.put("status","0");
            return map;
        }
        if(!email.equals(user.getEmail())){
        	msg = "注册邮箱不正确！";
        	map.put("msg",msg);
        	map.put("status","0");
            return map;
        }
        try{
            String encryptKey= UUID.randomUUID().toString();  //密钥
            Timestamp timeout = new Timestamp(System.currentTimeMillis()+30*60*1000);//30分钟后过期
            long date = timeout.getTime()/1000*1000;                  //忽略毫秒数
            user.setEncrypt_key(encryptKey);
            user.setTimeout(timeout);
            this.userService.updateUserBaseInfoByLoginname(user);    //保存到数据库
            String key = user.getLoginname()+"$"+date+"$"+encryptKey;
//            String digitalSignature = MD5Encoder.encode(key.getBytes(Charset.defaultCharset()));                 //数字签名
            String digitalSignature = Tools.getMD5(key);               //数字签名

            String emailTitle = "晒酷网密码找回";
            String path = request.getContextPath();
            String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
            String resetPassHref =  basePath+"password/reset.shtml?sid="+digitalSignature+"&loginname="+user.getLoginname();
            String emailContent = "请勿回复本邮件.点击下面的链接,重设密码<br/><a href="+resetPassHref +" target='_BLANK'>点击我重新设置密码</a>" +
                    "<br/>tips:本邮件超过30分钟,链接将会失效，需要重新申请'找回密码'"+key+"\t"+digitalSignature;
            SendHTMLMail.send(emailTitle,emailContent,user.getEmail());
            msg = "操作成功,已经发送找回密码链接到您邮箱。请在30分钟内重置密码";
            
            map.put("msg",msg);
            map.put("status","1");
            return map;
            //logInfo(request,userName,"申请找回密码");
        }catch (Exception e){
            e.printStackTrace();
            msg="邮箱不存在？未知错误,联系管理员吧。";
        }
        map.put("status","0");
        map.put("msg",msg);
        return map;
    }
    
    @RequestMapping(value = "/password/reset.shtml",method = RequestMethod.GET)
    public String checkResetLink(Model model,String sid,String loginname){
        String msg = "";
        if(sid.equals("") || loginname.equals("")){
            msg="链接不完整,请重新生成";
            model.addAttribute("exception", msg);
            return "error";
        }
        User user = userService.findUserByLoginname(loginname);
        if(user == null){
            msg = "链接错误,无法找到匹配用户,请重新申请找回密码.";
            model.addAttribute("exception", msg);
            return "error";
        }
        Timestamp timeout = user.getTimeout();
        if((timeout.getTime() <= System.currentTimeMillis())){         //表示已经过期
            msg = "链接已经过期,请重新申请找回密码.";
            model.addAttribute("exception", msg);
            return "error";
        }
        String key = user.getLoginname()+"$"+timeout.getTime()+"$"+user.getEncrypt_key();
//        String digitalSignature = MD5Encoder.encode(key.getBytes(Charset.defaultCharset())); 
        String digitalSignature = Tools.getMD5(key);
        if(!digitalSignature.equals(sid)) {
            msg = "链接不正确,是否已经过期了?重新申请吧";
            model.addAttribute("exception", msg);
            return "error";
        }
        model.addAttribute("loginname", loginname);
        return "member/new_password";  //返回到修改密码的界面
    }
    @RequestMapping(value = "/password/reset.shtml",method = RequestMethod.POST)
    public @ResponseBody HashMap<Integer,String> resetPassword(String loginname,String password){
    	HashMap<Integer,String> msg = new HashMap<Integer,String>();
    	User user = new User();
    	user.setLoginname(loginname);
//    	user.setPassword(MD5Encoder.encode(password.getBytes()));
    	user.setPassword(password);
    	try{
    		this.userService.updateUserBaseInfoByLoginname(user);
    	}catch(Exception e){
    		e.printStackTrace();
    		msg.put(0,"密码重置失败！");
    		return msg;
    	}
    	msg.put(1, "密码重置成功！");
    	return msg;
    }
}
