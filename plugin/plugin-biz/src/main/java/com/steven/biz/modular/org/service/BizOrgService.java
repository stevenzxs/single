package com.steven.biz.modular.org.service;

import cn.hutool.core.lang.tree.Tree;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.steven.biz.modular.org.entity.BizOrg;
import com.steven.biz.modular.org.param.*;
import com.steven.biz.modular.user.entity.BizUser;

import java.util.List;

/**
 * 机构Service接口
 **/
public interface BizOrgService extends IService<BizOrg> {

    /**
     * 获取机构分页
     */
    Page<BizOrg> page(BizOrgPageParam bizOrgPageParam);

    /**
     * 获取机构树
     */
    List<Tree<String>> tree();

    /**
     * 添加机构
     */
    void add(BizOrgAddParam bizOrgAddParam);

    /**
     * 编辑机构
     */
    void edit(BizOrgEditParam bizOrgEditParam);

    /**
     * 删除机构
     */
    void delete(List<BizOrgIdParam> bizOrgIdParamList);

    /**
     * 获取机构详情
     */
    BizOrg detail(BizOrgIdParam bizOrgIdParam);

    /**
     * 获取机构详情
     **/
    BizOrg queryEntity(String id);

    /**
     * 获取缓存的所有机构
     **/
    List<BizOrg> getCachedAllOrgList();

    /**
     * 根据机构全名称获取机构id，有则返回，无则创建
     **/
    String getOrgIdByOrgFullNameWithCreate(String orgFullName);

    /**
     * 获取机构树选择器
     */
    List<Tree<String>> orgTreeSelector();

    /**
     * 获取机构列表选择器
     **/
    List<BizOrg> orgListSelector(BizOrgSelectorOrgListParam bizOrgSelectorOrgListParam);

    /**
     * 获取人员选择器
     */
    List<BizUser> userSelector(BizOrgSelectorUserParam bizOrgSelectorUserParam);
}
