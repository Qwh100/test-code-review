package com.qwh.entity.dto;

import lombok.Data;

/**
 * ClassName: ReSetPwdDTO
 * Package: com.dev.entity
 * Description:
 *
 * @Author Qwh
 * @Create 2024/10/16 12:50
 * @Version 1.0
 */
@Data
public class ReSetPwdDTO {

    String username;

    String email;

    String vcode;

}
