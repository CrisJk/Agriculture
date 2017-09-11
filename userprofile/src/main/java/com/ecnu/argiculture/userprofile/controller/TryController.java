package com.ecnu.argiculture.userprofile.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by kuangjun on 9/6/17.
 */
@Controller
public class TryController {
        @RequestMapping("/index")
        public String index(@RequestParam(value="name",required=false,defaultValue="ECNUer") String name, Model model){
                model.addAttribute("name",name);
                return  "index";
        }

}
