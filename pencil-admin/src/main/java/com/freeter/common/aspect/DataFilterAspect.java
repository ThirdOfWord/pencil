/**
 * Copyright 2018 人人开源 http://www.renren.io
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package com.freeter.common.aspect;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.fileupload.RequestContext;
import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.RequestContextListener;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.freeter.common.annotation.DataFilter;
import com.freeter.common.exception.RRException;
import com.freeter.common.utils.Constant;
import com.freeter.modules.sys.entity.SysUserEntity;
import com.freeter.modules.sys.service.SysDeptService;
import com.freeter.modules.sys.service.SysRoleDeptService;
import com.freeter.modules.sys.service.SysUserRoleService;
import com.freeter.modules.sys.shiro.ShiroUtils;

/**
 * 数据过滤，切面处理类
 *
 * @author Mark sunlightcs@gmail.com
 * @since 3.0.0 2017-09-17
 */
@Aspect
@Component
public class DataFilterAspect {
	@Autowired
	private SysDeptService sysDeptService;
	@Autowired
	private SysUserRoleService sysUserRoleService;
	@Autowired
	private SysRoleDeptService sysRoleDeptService;

	@Pointcut("@annotation(com.freeter.common.annotation.DataFilter)")
	public void dataFilterCut() {

	}

	@Before("dataFilterCut()")
    public void dataFilter(JoinPoint point) throws Throwable {
        Object params = point.getArgs()[0];
       
        SysUserEntity user = ShiroUtils.getUserEntity();
         if(params != null && params instanceof Map){

            //如果不是超级管理员，则进行数据过滤
            if(user.getUserId() != Constant.SUPER_ADMIN){
                Map map = (Map)params;
                map.put(Constant.SQL_FILTER, getSQLFilter(user, point));
            }

            return ;
        }
         if(user.getUserId() != Constant.SUPER_ADMIN){
       RequestContextHolder.currentRequestAttributes().setAttribute(Constant.SQL_FILTER,getSQLFilter(user, point),RequestAttributes.SCOPE_REQUEST );
         }
         }

	/**
	 * 获取数据过滤的SQL
	 */
	private String getSQLFilter(SysUserEntity user, JoinPoint point) {
		MethodSignature signature = (MethodSignature) point.getSignature();
		DataFilter dataFilter = signature.getMethod().getAnnotation(DataFilter.class);
		// 获取表的别名
		String tableAlias = dataFilter.tableAlias();
		if (StringUtils.isNotBlank(tableAlias)) {
			tableAlias += ".";
		}

		// 部门ID列表
		Set<Long> deptIdList = new HashSet<>();

		// 用户角色对应的部门ID列表
		List<Long> roleIdList = sysUserRoleService.queryRoleIdList(user.getUserId());
		if (roleIdList.size() > 0) {
			List<Long> userDeptIdList = sysRoleDeptService
					.queryDeptIdList(roleIdList.toArray(new Long[roleIdList.size()]));
			deptIdList.addAll(userDeptIdList);
		}

		// 用户子部门ID列表
		/*if (dataFilter.subDept()) {
			List<Long> subDeptIdList = sysDeptService.getSubDeptIdList(user.getDeptId());
			deptIdList.addAll(subDeptIdList);
		}*/

		StringBuilder sqlFilter = new StringBuilder();
		sqlFilter.append(" (");

		if (deptIdList.size() > 0) {
			sqlFilter.append(tableAlias).append(dataFilter.deptId()).append(" in(")
					.append(StringUtils.join(deptIdList, ",")).append(")");
		}

		// 没有本部门数据权限，也能查询本人数据
		if (dataFilter.user()) {
			if (deptIdList.size() > 0) {
				sqlFilter.append(" or ");
			}
			sqlFilter.append(tableAlias).append(dataFilter.userId()).append("=").append(user.getUserId());
		}

		sqlFilter.append(")");

		return sqlFilter.toString();
	}
}
