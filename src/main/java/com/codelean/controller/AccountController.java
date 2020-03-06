package com.codelean.controller;

import com.codelean.model.Account;
import com.codelean.model.Post;
import com.codelean.service.AccountService;
import com.codelean.service.PostService;
import com.codelean.validation.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

@Controller

public class AccountController {
    @Autowired
    private AccountService accountService;
    Account account=new Account();

    @Autowired
    private PostService postService;
    Post post=new Post();

    Validate validate = new Validate();



    ///view thông tin người dùng
    @GetMapping("qlTaiKhoan.html")
    public ModelAndView viewInfor(){
        if(account==null || account.getUserName()==null){
            ModelAndView modelAndView = new ModelAndView("index");
            modelAndView.addObject("errorLogin","Please Login!");
            return modelAndView;
        }
        ModelAndView modelAndView = new ModelAndView("qlTaiKhoan");
        modelAndView.addObject("account",account);
        return modelAndView;
    }

    ///đăng ký tài khoản
    @GetMapping("dangKy.html")
    public ModelAndView showDangKy(){
        ModelAndView modelAndView = new ModelAndView("dangKy");
        modelAndView.addObject("account",new Account());
        return modelAndView;
    }

    @PostMapping("dangKy.html")
    public ModelAndView saveDangKy(@ModelAttribute("account") Account account){
        if (!validate.checkSize(6, 20, account.getUserName().length())) {
            ModelAndView modelAndView = new ModelAndView("/formChinh");
            modelAndView.addObject("account", new Account());
            modelAndView.addObject("message", "The title length must be between 6 and 20 characters!");
            return modelAndView;
        }
        else if(!validate.checkSize(6, 20, account.getPassWord().length())){
            ModelAndView modelAndView = new ModelAndView("/formChinh");
            modelAndView.addObject("account", new Account());
            modelAndView.addObject("message", "The content length must be between 6 and 20 characters!");
            return modelAndView;
        }else
        accountService.save(account);
        ModelAndView modelAndView = new ModelAndView("dangKy");
        modelAndView.addObject("account",new Account());
        modelAndView.addObject("message","New account create successfully");
        return modelAndView;
    }


    //đổi mật khẩu
    @GetMapping("changePassword.html")
    public ModelAndView showInfor(){
        if(account==null || account.getUserName()==null){
            ModelAndView modelAndView = new ModelAndView("index");
            modelAndView.addObject("errorLogin","Please Login!");
            return modelAndView;
        }
        account.setPassWord("");
        ModelAndView modelAndView = new ModelAndView("changePassword.html");
        modelAndView.addObject("account1",account);
        return modelAndView;
    }

    @PostMapping("changePassword.html")
    private ModelAndView editInfor(@ModelAttribute("account1") Account account1 ){
        System.out.println(account1.toString());
        account1.setId(account.getId());
        account1.setUserName(account.getUserName());
        account1.setName(account.getName());
        account1.setEmail(account.getEmail());
        account1.setPhoneNumber(account.getPhoneNumber());
        accountService.save(account1);
        ModelAndView modelAndView = new ModelAndView("changePassword.html");
        modelAndView.addObject("account1",account1);
        modelAndView.addObject("message","Change password successfully");
        return modelAndView;
    }

    //đăng xuất
    @GetMapping("logout")
    public ModelAndView logout( ModelMap model){
        account=null;
        ModelAndView modelAndView = new ModelAndView("/index");
        model.put("errorLogin", "Logout successfully");
        return modelAndView;

    }
    //hiển thị tiêu đề bài viết
    @GetMapping("khoBaiViet.html")
    public ModelAndView viewInforPost(){
        if(account==null || account.getUserName()==null){
            ModelAndView modelAndView = new ModelAndView("index");
            modelAndView.addObject("errorLogin","Please Login!");
            return modelAndView;
        }
        List<Post> post = postService.findAllByUsername(account.getUserName());
        ModelAndView modelAndView = new ModelAndView("khoBaiViet");
        modelAndView.addObject("post",post);
        return modelAndView;
    }

    //hiển thị bài viết
    @GetMapping("hienThiBaiViet/{id}")
    public ModelAndView viewPost(@PathVariable Long id){
        Optional<Post> post = postService.findById(id);
        if(post != null){
            ModelAndView modelAndView = new ModelAndView("hienThiBaiViet");
            modelAndView.addObject("post",post);
            return modelAndView;
        }
        else{
            ModelAndView modelAndView = new ModelAndView("error.404");
            return modelAndView;
        }
    }


    //thêm bài viết

    @GetMapping("formChinh.html")
    public ModelAndView showsavePost(){
        if(account==null || account.getUserName()==null){
            ModelAndView modelAndView = new ModelAndView("index");
            modelAndView.addObject("errorLogin","Please Login!");
            return modelAndView;
        }
        ModelAndView modelAndView = new ModelAndView("/formChinh");
        modelAndView.addObject("post",new Post());
        return modelAndView;
    }
    @PostMapping("formChinh.html")
    public ModelAndView savePost(@ModelAttribute("post") Post post){
        if (!validate.checkSize(10, 50, post.getTitle().length())) {
            ModelAndView modelAndView = new ModelAndView("/formChinh");
            modelAndView.addObject("post", new Post());
            modelAndView.addObject("message", "The title length must be between 10 and 50 characters!");
            return modelAndView;
        }
        else if(!validate.checkSize(10, 500, post.getContent().length())){
            ModelAndView modelAndView = new ModelAndView("/formChinh");
            modelAndView.addObject("post", new Post());
            modelAndView.addObject("message", "The content length must be between 10 and 500 characters!");
            return modelAndView;
        }
        else
        post.setUserName(account.getUserName());
        System.out.println(post.toString());
        postService.save(post);
        ModelAndView modelAndView = new ModelAndView("/formChinh");
        modelAndView.addObject("post",new Post());
        modelAndView.addObject("message","Posts created successfully!");
        return modelAndView;
    }


    //xóa bài viết theo id
    Optional<Post> Posts = Optional.of(new Post());
    @GetMapping("deletePost/{id}")
    public ModelAndView showDelete(@PathVariable Long id){
        Posts = postService.findById(id);
        if(Posts != null){
            ModelAndView modelAndView = new ModelAndView("deletePost");
            modelAndView.addObject("post",post);
            return modelAndView;
        }
        else{
            ModelAndView modelAndView = new ModelAndView("error.404");
            return modelAndView;
        }
    }

    @PostMapping("deletePost")
    public String deletePost(){
        postService.remove(Posts.get().getId());
        return "redirect:khoBaiViet.html";
    }


    //sửa bài viết theo id
    Long idpost;
    @GetMapping("editPost/{id}")
    public ModelAndView showEdit(@PathVariable Long id){
        Posts = postService.findById(id);
        idpost=Posts.get().getId();
        if(Posts !=null){
            ModelAndView modelAndView = new ModelAndView("editPost");
            modelAndView.addObject("post",Posts);
            return modelAndView;
        }
        else{
            ModelAndView modelAndView = new ModelAndView("error.404");
            return modelAndView;
        }

    }

    @PostMapping("editPost.html")
    public ModelAndView updatePost(@ModelAttribute("post") Post post){
        if (!validate.checkSize(10, 50, post.getTitle().length())) {
            ModelAndView modelAndView = new ModelAndView("/formChinh");
            modelAndView.addObject("post", new Post());
            modelAndView.addObject("message", "The title length must be between 10 and 50 characters!");
            return modelAndView;
        }
        else if(!validate.checkSize(10, 500, post.getContent().length())){
            ModelAndView modelAndView = new ModelAndView("/formChinh");
            modelAndView.addObject("post", new Post());
            modelAndView.addObject("message", "The content length must be between 10 and 500 characters!");
            return modelAndView;
        }
        post.setUserName(account.getUserName());
        post.setId(idpost);
        postService.update(post);
        ModelAndView modelAndView = new ModelAndView("editPost");
        modelAndView.addObject("post",post);
        modelAndView.addObject("message","Edit post successfully");
        return modelAndView;

    }
    //đăng nhập
    @PostMapping("login.html")
    private String login(HttpSession session, @RequestParam("username") String user, @RequestParam("password") String password,
                         ModelMap model) {
        if (session.getAttribute("userLogin") != null) {
            return "redirect:/";
        } else {
            account = accountService.findByUsernameAndPassword(user,password);
            if(account!=null){
                session.setAttribute("account",account);
                return "redirect:/formChinh.html";
            }else{
                model.put("errorLogin", "Wrong account or password");
                return  "index.html";
            }

        }
    }

}
