package com.ecnu.agriculture;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by kuangjun on 9/6/17.
 */

@RestController
public class startPage {
        @RequestMapping("/")
        public String index() {
            return "How to Make Money fastest, please visit https://www.ecnu.edu.cn" ;
        }
}
