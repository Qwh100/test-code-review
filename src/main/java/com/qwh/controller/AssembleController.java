package com.qwh.controller;


import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSON;
import com.annotation.IgnoreAuth;
import com.dev.util.RoomNumberGenerator;
import com.entity.JiudiankefangEntity;
import com.service.JiudiankefangService;
import com.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/assemble")
@Slf4j
public class AssembleController {


    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Resource
    private JiudiankefangService jiudiankefangService;


    @PostMapping("/vipInfo")
    @IgnoreAuth
    public R assembleVipInfo(){
        // 定义会员等级的顺序
        Map<String, Integer> levelPriority = new HashMap<>();
        levelPriority.put("普通会员", 1);
        levelPriority.put("铜卡会员", 2);
        levelPriority.put("银卡会员", 3);
        levelPriority.put("金卡会员", 4);
        levelPriority.put("白金会员", 5);
        levelPriority.put("钻石会员", 6);
        levelPriority.put("皇冠会员", 7);
        levelPriority.put("超VIP会员", 8);
        String vipInfo = JSONUtil.toJsonStr(levelPriority);

        String key = "vipInfo";
        stringRedisTemplate.opsForValue().set(key,vipInfo);
        return R.ok("装配成功");
    }

    /**
     * 初始化所有酒店房间号
     */
    @PostMapping("/hotelInit")
    @IgnoreAuth
    public R initializeRooms() {
        try {
            // 1. 获取所有酒店客房数据
            List<JiudiankefangEntity> allRooms = jiudiankefangService.selectList(null);

            // 2. 生成房间号映射
            Map<String, Map<String, List<String>>> hotelRoomMap =
                    RoomNumberGenerator.generateHotelRoomNumbers(allRooms);

            // 3. 存入Redis
            for (Map.Entry<String, Map<String, List<String>>> hotelEntry : hotelRoomMap.entrySet()) {
                String hotelName = hotelEntry.getKey();
                Map<String, List<String>> roomTypeMap = hotelEntry.getValue();

                String redisKey = "hotel:rooms:" + hotelName;

                // 使用hash结构存储，field为房型，value为房间号列表的JSON字符串
                for (Map.Entry<String, List<String>> roomTypeEntry : roomTypeMap.entrySet()) {
                    String roomType = roomTypeEntry.getKey();
                    List<String> roomNumbers = roomTypeEntry.getValue();
                    stringRedisTemplate.opsForHash().put(
                            redisKey,
                            roomType,
                            JSON.toJSONString(roomNumbers)
                    );
                }
            }
            log.info("房间号初始化完成");
            return R.ok("房间号初始化成功");
        } catch (Exception e) {
            log.error("初始化房间号失败", e);
            return R.error("初始化房间号失败");
        }
    }


}
