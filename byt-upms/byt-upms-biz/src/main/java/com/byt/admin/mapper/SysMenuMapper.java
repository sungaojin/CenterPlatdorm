/*
 *
 *      Copyright (c) 2018-2025, liyang All rights reserved.
 *
 *  Redistribution and use in source and binary forms, with or without
 *  modification, are permitted provided that the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice,
 *  this list of conditions and the following disclaimer.
 *  Redistributions in binary form must reproduce the above copyright
 *  notice, this list of conditions and the following disclaimer in the
 *  documentation and/or other materials provided with the distribution.
 *  Neither the name of the byt.com developer nor the names of its
 *  contributors may be used to endorse or promote products derived from
 *  this software without specific prior written permission.
 *  Author: liyang (wangiegie@gmail.com)
 *
 */

package com.byt.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.byt.admin.api.entity.SysMenu;
import com.byt.admin.api.vo.MenuVO;

import java.util.List;

/**
 * <p>
 * 菜单权限表 Mapper 接口
 * </p>
 *
 * @author liyang
 * @since 2017-10-29
 */
public interface SysMenuMapper extends BaseMapper<SysMenu> {

	/**
	 * 通过角色编号查询菜单
	 *
	 * @param roleId 角色ID
	 * @return
	 */
	List<MenuVO> listMenusByRoleId(Integer roleId);

	/**
	 * 通过角色ID查询权限
	 *
	 * @param roleIds Ids
	 * @return
	 */
	List<String> listPermissionsByRoleIds(String roleIds);
}
