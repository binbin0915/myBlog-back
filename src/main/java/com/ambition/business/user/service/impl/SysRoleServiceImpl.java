package com.ambition.business.user.service.impl;

import com.ambition.business.user.domain.SysRole;
import com.ambition.business.user.mapper.SysRoleMapper;
import com.ambition.business.user.service.ISysRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ambition.common.constants.Constants;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import com.ambition.common.util.R;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import java.util.List;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;

/**
 * <p>
 * 后台角色表 服务实现类
 * </p>
 *
 * @author wuys
 * @since 2019-12-11
 */
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements ISysRoleService {

    @Override
    @Transactional
    public R findListByPage(int page, int pageSize,Long groupId){
     if (pageSize == 0) {
        pageSize = Constants.DEFAULT_PAGESIZE;
     }
     Page<SysRole> dictPage = new Page<>(page, pageSize);
     IPage<SysRole> sysDictIPage = null;
     LambdaQueryWrapper<SysRole> lambdaQueryWrapper = Wrappers.<SysRole>lambdaQuery();
     sysDictIPage = baseMapper.selectPage(dictPage, lambdaQueryWrapper.select(SysRole.class, i -> true));
     return R.ok(sysDictIPage);
    }

    @Override
    public R deleteByIds(List<Long> ids){
     int result = this.baseMapper.deleteBatchIds(ids);
     if(result > 0){
        return R.ok();
     }
     return R.error();
    }

    @Override
    public R getById(Long id){
      SysRole e = super.getById(id);
      return R.ok(e);
    }

    @Override
    public R saveSysRole(SysRole entity){
      boolean r = this.save(entity);
      if(r){
        return R.ok();
       }
      return R.error();
    }

    @Override
    public R updateSysRoleById(SysRole entity){
        boolean r = this.updateById(entity);
        if(r){
            return R.ok();
        }
        return R.error();
    }

}
