package com.qwh.controller;


import com.dev.service.IVoucherOrderService;
import com.utils.R;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 * 前端控制器
 * </p>
 */
@RestController
@RequestMapping("/voucher-order")
public class VoucherOrderController {

    @Resource
    private IVoucherOrderService voucherOrderService;


    /**
     * 兑换普通代金券
     *
     * @param voucherId
     * @return
     */
    @PostMapping("exchange/{id}")
    public R exchangeVoucher(@PathVariable("id") Long voucherId) {
        return voucherOrderService.exchangeVoucher(voucherId);
    }

    /**
     * 支付普通代金券订单
     *
     * @param orderId
     * @return
     */
    @PostMapping("payVoucherOrder/{orderId}")
    public R payVoucherOrder(@PathVariable("orderId") Long orderId) {
        return voucherOrderService.payVoucherOrder(orderId);
    }

    /**
     * 抢购秒杀卷
     *
     * @param voucherId
     * @return
     */
    @PostMapping("seckill/{id}")
    public R seckillVoucher(@PathVariable("id") Long voucherId) {
        return voucherOrderService.seckillVoucher(voucherId);
    }

    /**
     * 支付秒杀代金券订单
     *
     * @param orderId
     * @return
     */
    @PostMapping("paySeckillVoucherOrder/{orderId}")
    public R paySeckillVoucherOrder(@PathVariable("orderId") Long orderId) {
        return voucherOrderService.paySeckillVoucherOrder(orderId);
    }


}
