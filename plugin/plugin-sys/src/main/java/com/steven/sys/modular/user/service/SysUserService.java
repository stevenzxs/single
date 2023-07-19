package com.steven.sys.modular.user.service;

import cn.hutool.core.lang.tree.Tree;
import cn.hutool.json.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;
import com.steven.sys.modular.org.entity.SysOrg;
import com.steven.sys.modular.position.entity.SysPosition;
import com.steven.sys.modular.role.entity.SysRole;
import com.steven.sys.modular.user.entity.SysUser;
import com.steven.sys.modular.user.param.*;
import com.steven.sys.modular.user.result.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 用户Service接口
 **/
public interface SysUserService extends IService<SysUser> {

    /**
     * 根据id获取用户信息，查不到则返回null
     */
    SysLoginUser getUserById(String id);

    /**
     * 根据账户获取用户信息，查不到则返回null
     */
    SysLoginUser getUserByAccount(String account);

    /**
     * 根据手机号获取用户信息，查不到则返回null
     */
    SysLoginUser getUserByPhone(String phone);

    /**
     * 根据邮箱获取用户信息，查不到则返回null
     */
    SysLoginUser getUserByEmail(String email);

    /**
     * 获取用户分页
     */
    Page<SysUser> page(SysUserPageParam sysUserPageParam);

    /**
     * 添加用户
     */
    void add(SysUserAddParam sysUserAddParam);

    /**
     * 编辑用户
     */
    void edit(SysUserEditParam sysUserEditParam);

    /**
     * 删除用户
     */
    void delete(List<SysUserIdParam> sysUserIdParamList);

    /**
     * 获取用户详情
     */
    SysUser detail(SysUserIdParam sysUserIdParam);

    /**
     * 获取用户详情
     **/
    SysUser queryEntity(String id);

    /**
     * 禁用用户
     **/
    void disableUser(SysUserIdParam sysUserIdParam);

    /**
     * 启用用户
     **/
    void enableUser(SysUserIdParam sysUserIdParam);

    /**
     * 重置用户密码
     **/
    void resetPassword(SysUserIdParam sysUserIdParam);

    /**
     * 获取图片验证码
     **/
    SysUserPicValidCodeResult getPicCaptcha();

    /**
     * 找回密码获取手机验证码
     **/
    String findPasswordGetPhoneValidCode(SysUserGetPhoneValidCodeParam sysUserGetPhoneValidCodeParam);

    /**
     * 找回密码获取邮箱验证码
     **/
    String findPasswordGetEmailValidCode(SysUserGetEmailValidCodeParam sysUserGetEmailValidCodeParam);

    /**
     * 通过手机号找回用户密码
     **/
    void findPasswordByPhone(SysUserFindPwdByPhoneParam sysUserFindPwdByPhoneParam);

    /**
     * 通过邮箱找回用户密码
     **/
    void findPasswordByEmail(SysUserFindPwdByEmailParam sysUserFindPwdByEmailParam);

    /**
     * 修改用户密码
     **/
    void updatePassword(SysUserUpdatePwdParam sysUserUpdatePwdParam);

    /**
     * 修改用户头像返回base64
     **/
    String updateAvatar(MultipartFile file);

    /**
     * 修改用户签名图片返回base64
     **/
    void updateSignature(SysUserSignatureParam sysUserSignatureParam);

    /**
     * 更新用户的登录时间和登录ip等信息
     */
    void updateUserLoginInfo(String userId, String device);

    /**
     * 获取用户拥有菜单
     */
    List<Tree<String>> ownMenu(SysUserIdParam sysUserIdParam);

    /**
     * 获取用户拥有移动端菜单
     */
    List<Tree<String>> ownMobileMenu(SysUserIdParam sysUserIdParam);

    /**
     * 获取用户拥有角色
     */
    List<String> ownRole(SysUserIdParam sysUserIdParam);

    /**
     * 给用户授权角色
     **/
    void grantRole(SysUserGrantRoleParam sysUserGrantRoleParam);

    /**
     * 获取用户拥有资源
     */
    SysUserOwnResourceResult ownResource(SysUserIdParam sysUserIdParam);

    /**
     * 给用户授权资源
     **/
    void grantResource(SysUserGrantResourceParam sysUserGrantResourceParam);

    /**
     * 获取用户拥有权限
     */
    SysUserOwnPermissionResult ownPermission(SysUserIdParam sysUserIdParam);

    /**
     * 给用户授权权限
     **/
    void grantPermission(SysUserGrantPermissionParam sysUserGrantPermissionParam);

    /**
     * 获取用户组织树
     */
    List<Tree<String>> loginOrgTree(SysUserIdParam sysUserIdParam);

    /**
     * 编辑个人信息
     */
    void updateUserInfo(SysUserUpdateInfoParam sysUserUpdateInfoParam);

    /**
     * 编辑个人工作台
     */
    void updateUserWorkbench(SysUserUpdateWorkbenchParam sysUserUpdateWorkbenchParam);

    /**
     * 获取用户工作台数据
     */
    String loginWorkbench(SysUserIdParam sysUserIdParam);

    /**
     * 获取角色集合
     **/
    List<JSONObject> getRoleList(String userId);

    /**
     * 获取按钮编码集合
     **/
    List<String> getButtonCodeList(List<String> userAndRoleIdList);

    /**
     * 获取移动端按钮编码集合
     **/
    List<String> getMobileButtonCodeList(List<String> userAndRoleIdList);

    /**
     * 获取权限集合
     **/
    List<JSONObject> getPermissionList(List<String> userAndRoleIdList, String orgId);

    /**
     * 下载用户导入模板
     **/
    void downloadImportUserTemplate(HttpServletResponse response) throws IOException;

    /**
     * 用户导入
     **/
    JSONObject importUser(MultipartFile file);

    /**
     * 用户导出
     **/
    void exportUser(SysUserExportParam sysUserExportParam, HttpServletResponse response) throws IOException;

    /**
     * 导出用户个人信息
     **/
    void exportUserInfo(SysUserIdParam sysUserIdParam, HttpServletResponse response) throws IOException;

    /**
     * 获取登录用户的职位信息
     **/
    List<SysUserPositionResult> loginPositionInfo(SysUserIdParam sysUserIdParam);

    /**
     * 获取缓存的所有用户选择器
     **/
    List<SysUser> getCachedAllUserSelectorList();

    /* ====用户部分所需要用到的选择器==== */

    /**
     * 获取组织树选择器
     */
    List<Tree<String>> orgTreeSelector();

    /**
     * 获取组织列表选择器
     **/
    List<SysOrg> orgListSelector(SysUserSelectorOrgListParam sysUserSelectorOrgListParam);

    /**
     * 获取职位选择器
     */
    List<SysPosition> positionSelector(SysUserSelectorPositionParam sysUserSelectorPositionParam);

    /**
     * 获取角色选择器
     */
    List<SysRole> roleSelector(SysUserSelectorRoleParam sysUserSelectorRoleParam);

    /**
     * 获取用户选择器
     */
    List<SysUser> userSelector(SysUserSelectorUserParam sysUserSelectorUserParam);

    /**
     * 获取登录用户的站内信分页
     */
    Page<SysUserMessageResult> loginMessagePage(SysUserMessagePageParam sysUserMessagePageParam);

    /**
     * 读取登录用户站内信详情
     */
    SysUserMessageDetailResult loginMessageDetail(SysUserMessageIdParam sysUserMessageIdParam);
}
