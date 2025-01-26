package com.qwh.controller;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;
import com.annotation.IgnoreAuth;
import com.dev.constant.CaptchaConstant;
import com.dev.util.IpUtil;
import com.dev.util.KeysUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: iQNRen
 * @Description: 验证码接口
 * @Date: 2024/9/18 20:13 周三
 * @Version: 1.0
 */
@RestController
@RequestMapping("/Vcode")
public class VcodeController {

    @GetMapping
    @IgnoreAuth
    public void getCode(HttpServletRequest request, HttpServletResponse response) {
        // HuTool定义图形验证码的长和宽,验证码的位数，干扰线的条数
        // 验证码工具类
        LineCaptcha lineCaptcha = CaptchaUtil.createLineCaptcha(120, 45, 4, 80);

        String key = CaptchaConstant.LOGIN_V_CODE.concat("_" + IpUtil.getClientIp(request));
        // 将验证码存储到CaptchaStore中，并设置1分钟过期时间
        KeysUtil.addCaptcha(key, lineCaptcha.getCode(), 60);

        response.setContentType("image/jpeg");
        try {
            ServletOutputStream outputStream = response.getOutputStream();
            lineCaptcha.write(outputStream);
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

