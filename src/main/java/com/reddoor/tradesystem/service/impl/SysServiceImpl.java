package com.reddoor.tradesystem.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reddoor.tradesystem.domain.authority.SysPermission;
import com.reddoor.tradesystem.domain.authority.SysPermissionExample;
import com.reddoor.tradesystem.domain.authority.SysUser;
import com.reddoor.tradesystem.domain.authority.SysUserExample;
import com.reddoor.tradesystem.mapper.authority.SysPermissionMapper;
import com.reddoor.tradesystem.mapper.authority.SysPermissionMapperCustom;
import com.reddoor.tradesystem.mapper.authority.SysUserMapper;
import com.reddoor.tradesystem.service.SysService;

@Service
public class SysServiceImpl implements SysService{

	@Autowired
	private SysUserMapper sysUserMapper;
	
	@Autowired
	private SysPermissionMapperCustom sysPermissionMapperCustom;
	
	@Autowired
	private SysPermissionMapper sysPermissionMapper;
	
	@Override
	public SysUser getSysUserByName(String username) throws Exception {
		SysUserExample sysUserExample = new SysUserExample();
		SysUserExample.Criteria criteria = sysUserExample.createCriteria();
		criteria.andUsernameEqualTo(username);

		List<SysUser> list = new ArrayList<SysUser>();
		try {
			list = sysUserMapper.selectByExample(sysUserExample);
		}
		catch (Exception e) {
			e.printStackTrace();
		}

		if (list != null && list.size() == 1) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public List<SysPermission> findMenuListByUserId(String userId)
			throws Exception {
		return null;
	}

	@Override
	public List<SysPermission> findPermissionListByUserId(String userId)
			throws Exception {
		
		String permission = this.sysPermissionMapperCustom.findPermissionByUserId(userId);
		if(null != permission){
			String[] permissionIds = permission.split(",");
			List<Long> ids = new ArrayList<Long>();
			for(int i=0;i<permissionIds.length;i++){
				ids.add(Long.valueOf(permissionIds[i]));
			}
			SysPermissionExample example = new SysPermissionExample();
			SysPermissionExample.Criteria criteria = example.createCriteria();
			criteria.andIdIn(ids);
			return sysPermissionMapper.selectByExample(example);
		}
		return null;
	}

}
