package com.erbatyr.crud_app.controller;

import com.erbatyr.crud_app.model.Role;
import com.erbatyr.crud_app.model.User;
import com.erbatyr.crud_app.service.RoleService;
import com.erbatyr.crud_app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
public class UserController {

    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public UserController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/")
    public String usersList(Model model) {
        List<User> userList = userService.userList();
        model.addAttribute("users", userList);
        return "users_list";
    }

    @GetMapping("/admin")
    public String usersListForAdmin(Model model) {
        List<User> users = userService.userList();
        model.addAttribute("users", users);
        return "users_list_for_admin";
    }

    @GetMapping("/user/{id}")
    public String getUser(Model model, @PathVariable("id") int id) {
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        return "users_get";
    }

    @GetMapping("/admin/user/{id}")
    public String getUserForAdmin(Model model, @PathVariable("id") int id) {
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        return "users_get_for_admin";
    }

    @GetMapping("/admin/user/new")
    public String newUserGET(Model model) {
        model.addAttribute("user", new User());
        List<Role> roles = new ArrayList<>(roleService.rolesSet());
        model.addAttribute("roles", roles);
        return "users_new_user";
    }

    @PostMapping("/admin/user/new")
    public String newUserPOST(@RequestParam String username, @RequestParam String password, @RequestParam String[] roles, Model model) {
        Set<Role> rolesSet = new HashSet<>();
        for (String s: roles) {
            int i = Integer.parseInt(s);
            rolesSet.add(roleService.getRoleById(i));
        }
        userService.addUser(new User(username, password, rolesSet));
        return "redirect: /user/";
    }

    @GetMapping("/admin/user/{id}/edit")
    public String editUserGET(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        List<Role> roles = new ArrayList<>(roleService.rolesSet());
        model.addAttribute("roles", roles);
        return "users_edit";
    }

    @PostMapping("/admin/user/{id}/edit")
    public String editUserPOST(@PathVariable("id") int id,@RequestParam String username, @RequestParam String password, @RequestParam String[] roles, Model model) {
        Set<Role> rolesSet = new HashSet<>();
        for (String s: roles) {
            int i = Integer.parseInt(s);
            rolesSet.add(roleService.getRoleById(i));
        }
        User user = new User(id, username, password, rolesSet);
        userService.editUser(user);
        return "redirect: /user/" + id;
    }

    @GetMapping("/admin/user/{id}/delete")
    public String deleteUserGET(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        return "users_delete";
    }

    @PostMapping("/admin/user/{id}/delete")
    public String articleDeletePost(@PathVariable(value = "id") int id, Model model) {
        User user = userService.getUserById(id);
        userService.deleteUser(user);
        return "redirect: /";
    }
}
