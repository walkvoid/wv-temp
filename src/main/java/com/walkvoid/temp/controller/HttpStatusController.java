package com.walkvoid.temp.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * @author qingshan1993
 * @version 1.0
 * @date 2023/9/13
 * @desc
 */
@RestController
@RequestMapping("/http-status")
public class HttpStatusController {


    @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "xixihaha")
    @GetMapping("bad-request")
    public String badRequest(){
        return "this is a bad request";
    }
}
