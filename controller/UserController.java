package com.example.demo.controller;

import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.User;
import com.example.demo.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
//@RequestMapping("/example/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @RequestMapping(value="/create", method = RequestMethod.POST)
    public String create(@RequestParam String name, int pw) {
        User user = new User();
        user.setName(name);
        user.setPw(pw);

        Long userId = userService.save(user);
        return userId + "번 유저 등록 완료";
    }

    @PutMapping("/update")
    public String update(@RequestParam Long id, String name, int pw) {
        Long userId = userService.update(id, name, pw);
        return userId + "번 유저 수정 완료";
    }

    @DeleteMapping("/delete")
    public String delete(@RequestParam Long id) {
        userService.remove(id);
        return id + "번 유저 삭제 완료";
    }

    @GetMapping("/read")
    public String read(@RequestParam @Nullable Long id, String name) {
        if(id != null) {
            return userService.findById(id).toString();
        } else {
            return userService.findByName(name).toString();
        }
    }

    @GetMapping("/readall")
    public String readAll() {
        return userService.findAll().toString();
    }
}