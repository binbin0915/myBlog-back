package com.ambition.business.user.controller;

import com.ambition.business.user.domain.SysRole;
import com.ambition.business.user.domain.SysUser;
import com.ambition.business.user.service.ISysRoleService;
import com.ambition.common.annotations.AddSysLog;
import com.ambition.common.annotations.CurrentUser;
import com.ambition.common.annotations.LoginedUser;
import com.ambition.common.util.R;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import java.util.List;

/**
 * <p>
 * 后台角色表 前端控制器
 * </p>
 *
 * @author wuys
 * @since 2019-12-11
 */
@RestController
@RequestMapping("/sys-role")
public class SysRoleController {

	private static final Logger LOG = LoggerFactory.getLogger(SysRoleController.class);

	@Autowired
	private ISysRoleService service;

	/**
	 * 查询分页数据
	 */
	@ApiOperation(value = "查询分页数据")
	@GetMapping(value = "/list")
	@LoginedUser
	@AddSysLog(descrption = "分页查询SysRole列表")
	public R findListByPage(@CurrentUser @ApiIgnore SysUser sysUser, @RequestParam(name = "page", defaultValue = "1") int page, @RequestParam(name = "pageSize", defaultValue = "20") int pageSize) {
		return service.findListByPage(page, pageSize, sysUser.getGroupId());
	}


	/**
	 * 根据id查询
	 */
	@ApiOperation(value = "根据id查询数据")
	@GetMapping(value = "/getById")
	@LoginedUser
	@AddSysLog(descrption = "根据id查询SysRole")
	public R getById(@RequestParam("pkid") Long pkid) {
		return service.getById(pkid);
	}

	/**
	 * 新增
	 */
	@ApiOperation(value = "新增数据")
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@LoginedUser
	@AddSysLog(descrption = "新增SysRole")
	public R save(@CurrentUser @ApiIgnore SysUser sysUser, @RequestBody @Valid SysRole entity) {
		return service.saveSysRole(entity);
	}

	/**
	 * 删除
	 */
	@ApiOperation(value = "删除数据")
	@PostMapping(value = "/del")
	@LoginedUser
	@AddSysLog(descrption = "根据id删除SysRole")
	public R delete(@RequestParam("ids") List<Long> ids) {
		return service.deleteByIds(ids);
	}

	/**
	 * 修改
	 */
	@ApiOperation(value = "更新数据")
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@LoginedUser
	@AddSysLog(descrption = "更新SysRole")
	public R update(@CurrentUser @ApiIgnore SysUser sysUser, @RequestBody @Valid SysRole entity) {
		return service.updateSysRoleById(entity);
	}
}
