package com.opendev.web.contrller;

import com.opendev.response.ResponseHelper;
import com.opendev.annotation.AccessLimit;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginConterller {


    @RequestMapping("/login")
    @AccessLimit(perSecond = 1.0, timeOut = 500)
    public ResponseHelper Login() {



    }
}
