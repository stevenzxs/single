package com.steven.dev.modular.log.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import com.steven.common.enums.CommonSortOrderEnum;
import com.steven.common.page.CommonPageRequest;
import com.steven.dev.modular.log.entity.DevLog;
import com.steven.dev.modular.log.enums.DevLogCategoryEnum;
import com.steven.dev.modular.log.mapper.DevLogMapper;
import com.steven.dev.modular.log.param.DevLogDeleteParam;
import com.steven.dev.modular.log.param.DevLogPageParam;
import com.steven.dev.modular.log.result.DevLogOpBarChartDataResult;
import com.steven.dev.modular.log.result.DevLogOpPieChartDataResult;
import com.steven.dev.modular.log.result.DevLogVisLineChartDataResult;
import com.steven.dev.modular.log.result.DevLogVisPieChartDataResult;
import com.steven.dev.modular.log.service.DevLogService;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 日志Service接口实现类
 */
@Service
public class DevLogServiceImpl extends ServiceImpl<DevLogMapper, DevLog> implements DevLogService {

    @Override
    public Page<DevLog> page(DevLogPageParam devLogPageParam) {
        QueryWrapper<DevLog> queryWrapper = new QueryWrapper<>();
        if(ObjectUtil.isNotEmpty(devLogPageParam.getCategory())) {
            queryWrapper.lambda().eq(DevLog::getCategory, devLogPageParam.getCategory());
        }
        if(ObjectUtil.isNotEmpty(devLogPageParam.getSearchKey())) {
            queryWrapper.lambda().like(DevLog::getName, devLogPageParam.getSearchKey());
        }
        if(ObjectUtil.isAllNotEmpty(devLogPageParam.getSortField(), devLogPageParam.getSortOrder())) {
            CommonSortOrderEnum.validate(devLogPageParam.getSortOrder());
            queryWrapper.orderBy(true, devLogPageParam.getSortOrder().equals(CommonSortOrderEnum.ASC.getValue()),
                    StrUtil.toUnderlineCase(devLogPageParam.getSortField()));
        } else {
            queryWrapper.lambda().orderByDesc(DevLog::getCreateTime);
        }
        return this.page(CommonPageRequest.defaultPage(), queryWrapper);
    }

    @Override
    public void delete(DevLogDeleteParam devLogDeleteParam) {
        this.remove(new LambdaQueryWrapper<DevLog>().eq(DevLog::getCategory, devLogDeleteParam.getCategory()));
    }

    @Override
    public List<DevLogVisLineChartDataResult> visLogLineChartData() {
        DateTime lastWeek = DateUtil.lastWeek();
        DateTime now = DateTime.now();
        Map<String, List<JSONObject>> listMap = this.list(new LambdaQueryWrapper<DevLog>().in(DevLog::getCategory, DevLogCategoryEnum.LOGIN.getValue(),
                DevLogCategoryEnum.LOGOUT.getValue()).between(DevLog::getOpTime, lastWeek, now).orderByAsc(DevLog::getOpTime))
                .stream().map(devLog -> JSONUtil.parseObj(devLog).set("date", DateUtil.formatDate(devLog.getOpTime())))
                .collect(Collectors.groupingBy(jsonObject -> jsonObject.getStr("date")));
        long between = DateUtil.between(lastWeek, now, DateUnit.DAY);
        List<DevLogVisLineChartDataResult> resultList = CollectionUtil.newArrayList();
        for(int i = 1; i<= between; i++) {
            DevLogVisLineChartDataResult devLogVisLineChartDataResult = new DevLogVisLineChartDataResult();
            String date = DateUtil.formatDate(DateUtil.offsetDay(lastWeek, i));
            devLogVisLineChartDataResult.setDate(date);
            List<JSONObject> jsonObjectList = listMap.get(date);
            if(ObjectUtil.isNotEmpty(jsonObjectList)) {
                devLogVisLineChartDataResult.setLoginCount(jsonObjectList.stream().filter(jsonObject -> jsonObject.getStr("category")
                        .equals(DevLogCategoryEnum.LOGIN.getValue())).count());
                devLogVisLineChartDataResult.setLogoutCount(jsonObjectList.stream().filter(jsonObject -> jsonObject.getStr("category")
                        .equals(DevLogCategoryEnum.LOGOUT.getValue())).count());
            } else {
                devLogVisLineChartDataResult.setLoginCount(0L);
                devLogVisLineChartDataResult.setLogoutCount(0L);
            }
            resultList.add(devLogVisLineChartDataResult);
        }
        return resultList;
    }

    @Override
    public List<DevLogVisPieChartDataResult> visLogPieChartData() {
        List<DevLogVisPieChartDataResult> resultList = CollectionUtil.newArrayList();
        DevLogVisPieChartDataResult devLogLoginPieChartDataResult = new DevLogVisPieChartDataResult();
        devLogLoginPieChartDataResult.setType("登录");
        devLogLoginPieChartDataResult.setValue(Convert.toLong(this.count(new LambdaQueryWrapper<DevLog>()
                .eq(DevLog::getCategory, DevLogCategoryEnum.LOGIN.getValue()))));
        resultList.add(devLogLoginPieChartDataResult);

        DevLogVisPieChartDataResult devLogLogoutPieChartDataResult = new DevLogVisPieChartDataResult();
        devLogLogoutPieChartDataResult.setType("登出");
        devLogLogoutPieChartDataResult.setValue(Convert.toLong(this.count(new LambdaQueryWrapper<DevLog>()
                .eq(DevLog::getCategory, DevLogCategoryEnum.LOGOUT.getValue()))));
        resultList.add(devLogLogoutPieChartDataResult);
        return resultList;
    }

    @Override
    public List<DevLogOpBarChartDataResult> opLogBarChartData() {
        DateTime lastWeek = DateUtil.lastWeek();
        DateTime now = DateTime.now();
        Map<String, List<JSONObject>> listMap = this.list(new LambdaQueryWrapper<DevLog>().in(DevLog::getCategory, DevLogCategoryEnum.OPERATE.getValue(),
                DevLogCategoryEnum.EXCEPTION.getValue()).between(DevLog::getOpTime, lastWeek, now).orderByAsc(DevLog::getOpTime))
                .stream().map(devLog -> JSONUtil.parseObj(devLog).set("date", DateUtil.formatDate(devLog.getOpTime())))
                .collect(Collectors.groupingBy(jsonObject -> jsonObject.getStr("date")));
        long between = DateUtil.between(lastWeek, now, DateUnit.DAY);
        List<DevLogOpBarChartDataResult> resultList = CollectionUtil.newArrayList();
        for(int i = 1; i<= between; i++) {
            String date = DateUtil.formatDate(DateUtil.offsetDay(lastWeek, i));
            DevLogOpBarChartDataResult devLogOperateBarChartDataResult = new DevLogOpBarChartDataResult();
            devLogOperateBarChartDataResult.setDate(date);
            devLogOperateBarChartDataResult.setName("操作日志");
            DevLogOpBarChartDataResult devLogExceptionBarChartDataResult = new DevLogOpBarChartDataResult();
            devLogExceptionBarChartDataResult.setDate(date);
            devLogExceptionBarChartDataResult.setName("异常日志");
            List<JSONObject> jsonObjectList = listMap.get(date);
            if(ObjectUtil.isNotEmpty(jsonObjectList)) {
                devLogOperateBarChartDataResult.setCount(jsonObjectList.stream().filter(jsonObject -> jsonObject.getStr("category")
                        .equals(DevLogCategoryEnum.OPERATE.getValue())).count());
                devLogExceptionBarChartDataResult.setCount(jsonObjectList.stream().filter(jsonObject -> jsonObject.getStr("category")
                        .equals(DevLogCategoryEnum.EXCEPTION.getValue())).count());
            } else {
                devLogOperateBarChartDataResult.setCount(0L);
                devLogExceptionBarChartDataResult.setCount(0L);
            }
            resultList.add(devLogOperateBarChartDataResult);
            resultList.add(devLogExceptionBarChartDataResult);
        }
        return resultList;
    }

    @Override
    public List<DevLogOpPieChartDataResult> opLogPieChartData() {
        List<DevLogOpPieChartDataResult> resultList = CollectionUtil.newArrayList();
        DevLogOpPieChartDataResult devLogOperatePieChartDataResult = new DevLogOpPieChartDataResult();
        devLogOperatePieChartDataResult.setType("操作日志");
        devLogOperatePieChartDataResult.setValue(Convert.toLong(this.count(new LambdaQueryWrapper<DevLog>()
                .eq(DevLog::getCategory, DevLogCategoryEnum.OPERATE.getValue()))));
        resultList.add(devLogOperatePieChartDataResult);

        DevLogOpPieChartDataResult devLogExceptionPieChartDataResult = new DevLogOpPieChartDataResult();
        devLogExceptionPieChartDataResult.setType("异常日志");
        devLogExceptionPieChartDataResult.setValue(Convert.toLong(this.count(new LambdaQueryWrapper<DevLog>()
                .eq(DevLog::getCategory, DevLogCategoryEnum.EXCEPTION.getValue()))));
        resultList.add(devLogExceptionPieChartDataResult);
        return resultList;
    }
}
