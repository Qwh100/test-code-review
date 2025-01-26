package com.qwh.controller;


import com.annotation.IgnoreAuth;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.dev.entity.UserVoucher;
import com.dev.entity.Voucher;
import com.dev.service.IUserVoucherService;
import com.dev.service.IVoucherService;
import com.dev.util.Result;
import com.entity.HuiyuandengjiEntity;
import com.service.HuiyuandengjiService;
import com.service.YonghuService;
import com.utils.R;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;


@RestController
@RequestMapping("/voucher")
public class VoucherController {

    @Resource
    private IVoucherService voucherService;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Resource
    private HuiyuandengjiService huiyuandengjiService;

    @Resource
    private YonghuService yonghuService;

    @Resource
    private IUserVoucherService userVoucherService;

    /**
     * 新增普通券
     * @param voucher 优惠券信息
     * @return 优惠券id
     */
    @PostMapping
    public Result addVoucher(@RequestBody Voucher voucher) {
        voucherService.insert(voucher);
        return Result.ok(voucher.getId());
    }

    /**
     * 新增秒杀券
     * @param voucher 优惠券信息，包含秒杀信息
     * @return 优惠券id
     */
    @PostMapping("seckill")
    public Result addSeckillVoucher(@RequestBody Voucher voucher) {
        voucherService.addSeckillVoucher(voucher);
        return Result.ok(voucher.getId());
    }


    /**
     * 获取普通优惠卷列表
     */
    @IgnoreAuth
    @GetMapping("/public")
    public R getPublicVoucher() {
        List<Voucher> voucherList = voucherService.selectList(new EntityWrapper<Voucher>().eq("type", 0));
        return R.ok().put("data", voucherList);
    }

    /**
     * 获取秒杀优惠卷列表
     */
    @GetMapping("/seckill")
    @IgnoreAuth
    public R getSeckillVoucher() {
        List<Voucher> secKillList = voucherService.getVoucherAndSecKill();
        return R.ok().put("data", secKillList);
    }

    /**
     * 获取会员等级列表
     */
    @GetMapping("/vip")
    @IgnoreAuth
    public R getVipList() {

        List<HuiyuandengjiEntity> huiyuandengjiEntities = huiyuandengjiService.selectList(null);
        return R.ok().put("data", huiyuandengjiEntities);
    }


    /**
     * 抢购页面签到
     */
    @PostMapping("/sign")
    public R VoucherSign(){
        return yonghuService.VoucherSign();
    }


    /**
     * 判断今天是否签到
     */
    @GetMapping("isSign")
    public R isSign(){
        return voucherService.isSign();
    }


    /**
     * 获取我的优惠券列表
     */

    @GetMapping("/myVouchers")
    public R getMyVouchers(){
        return userVoucherService.getMyVouchers();
    }


    /**
     * 根据user_voucherId获取优惠卷金额
     */

    @GetMapping("/getVoucherValue")
    public R getVoucherDetail(Long id,String orderId){
        UserVoucher userVoucher = userVoucherService.selectById(id);
        if (userVoucher==null){
            String voucherId = stringRedisTemplate.opsForValue().get("voucherValue:"+ orderId);
            if (StringUtils.isNotBlank(voucherId)){
                Voucher voucher = voucherService.selectById(voucherId);
                return R.ok().put("data", voucher.getActualValue());
            }
            return R.ok().put("data", 0);
        }
        Voucher voucher = voucherService.selectById(userVoucher.getVoucherId());
        return R.ok().put("data", voucher.getActualValue());
    }




}
