package com.ambition.business.relation.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.ambition.business.relation.service.IUserViewLogService;
import com.ambition.business.relation.domain.UserViewLog;
import com.ambition.business.user.domain.SysUser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.ambition.common.util.R;
import com.baomidou.mybatisplus.core.metadata.IPage;
import java.util.List;
import java.util.Date;
import com.ambition.common.annotations.AddSysLog;
import com.ambition.common.annotations.CurrentUser;
import com.ambition.common.annotations.LoginedUser;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wuys
 * @since 2019-12-11
 */
@RestController
@RequestMapping("/user-view-log")
public class UserViewLogController {

     private static final Logger LOG = LoggerFactory.getLogger(UserViewLogController.class);

     @Autowired
     private IUserViewLogService service;

    /**
    * 查询分页数据
    */
    @ApiOperation(value = "查询分页数据")
    @GetMapping(value = "/list")
    @LoginedUser
    @AddSysLog(descrption = "分页查询UserViewLog列表")
    public R findListByPage(@CurrentUser@ApiIgnore SysUser sysUser,@RequestParam(name = "page", defaultValue = "1") int page,@RequestParam(name = "pageSize", defaultValue = "20") int pageSize){
      return service.findListByPage(page,pageSize,sysUser.getGroupId());
    }


     /**
     * 根据id查询
     */
     @ApiOperation(value = "根据id查询数据")
     @GetMapping(value = "/getById")
     @LoginedUser
     @AddSysLog(descrption = "根据id查询UserViewLog")
     public R getById(@RequestParam("pkid") Long pkid){
       return service.getById(pkid);
     }

     /**
     * 新增
     */
     @ApiOperation(value = "新增数据")
     @RequestMapping(value = "/add", method = RequestMethod.POST)
     @LoginedUser
     @AddSysLog(descrption = "新增UserViewLog")
     public R save(@CurrentUser@ApiIgnore SysUser sysUser,@RequestBody @Valid UserViewLog entity){
        return service.saveUserViewLog(entity);
     }

     /**
     * 删除
     */
     @ApiOperation(value = "删除数据")
     @RequestMapping(value = "/del",method = RequestMethod.POST)
     @LoginedUser
     @AddSysLog(descrption = "根据id删除UserViewLog")
     public R delete(@RequestParam("ids") List<Long> ids){
       return service.deleteByIds(ids);
     }

     /**
     * 修改
     */
     @ApiOperation(value = "更新数据")
     @RequestMapping(value = "/update", method = RequestMethod.POST)
     @LoginedUser
     @AddSysLog(descrption = "更新UserViewLog")
     public R update(@CurrentUser@ApiIgnore SysUser sysUser,@RequestBody @Valid UserViewLog entity){
       return service.updateUserViewLogById(entity);
     }
}
