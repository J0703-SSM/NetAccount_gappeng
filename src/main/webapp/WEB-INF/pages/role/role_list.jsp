<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page isELIgnored="false" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title></title>
        <link type="text/css" rel="stylesheet" media="all" href="/resource/styles/global.css" />
        <link type="text/css" rel="stylesheet" media="all" href="/resource/styles/global_color.css" />
        <script src="/resource/js/JQ3.2.1.js"></script>
        <script language="javascript" type="text/javascript">
            function deleteRole(role_id) {
                var r = window.confirm("确定要删除此角色吗？");
                $.ajax({
                    type:"post",
                    url:"/role/role_delete",
                    data:{
                        role_id:role_id
                    },success:function (result) {
                        if(result.count > 0){
                            $("#operate_result_info").html("删除成功");
                            document.getElementById("operate_result_info").style.display="block";
                            window.setTimeout('location.href = "/role/role_list"', 2000);
                        }else{
                            $("#operate_result_fail").html("删除失败");
                            document.getElementById("operate_result_fail").style.display="block";

                        }
                    }
                })

            }
        </script>
    </head>
    <body>
        <!--Logo区域开始-->
        <div id="header">
            <img src="../images/logo.png" alt="logo" class="left"/>
            <a href="/">[退出]</a>
        </div>
        <!--Logo区域结束-->
        <!--导航区域开始-->
        <div id="navi">                        
            <ul id="menu">
                <li><a href="/index" class="index_off"></a></li>
                    <c:forEach items="${applicationScope.admin.roles}" var="role">
                        <c:forEach items="${role.modules}" var="modeul">
                            <c:if test="${modeul.module_id eq 1}">
                                <c:if test="${empty a1}">
                                    <li><a href="/role/role_list" class="role_on"></a></li>
                                    <c:set scope="page" value="1" var="a1"/>
                                </c:if>
                            </c:if>
                            <c:if test="${modeul.module_id eq 2}">
                                <c:if test="${empty a2}">
                                    <li><a href="/admin/admin_list" class="admin_off"></a></li>
                                    <c:set scope="page" value="2" var="a2"/>
                                </c:if>
                            </c:if>
                            <c:if test="${modeul.module_id eq 3}">
                                <c:if test="${empty a3}">
                                    <li><a href="/fee/fee_list" class="fee_off"></a></li>
                                    <c:set scope="page" value="3" var="a3"/>
                                </c:if>
                            </c:if>
                            <c:if test="${modeul.module_id eq 4}">
                                <c:if test="${empty a4}">
                                    <li><a href="/account/account_list" class="account_off"></a></li>
                                    <c:set scope="page" value="4" var="a4"/>
                                </c:if>
                            </c:if>
                            <c:if test="${modeul.module_id eq 5}">
                                <c:if test="${empty a5}">
                                    <li><a href="/service/service_list" class="service_off"></a></li>
                                    <c:set scope="page" value="5" var="a5"/>
                                </c:if>
                            </c:if>
                            <c:if test="${modeul.module_id eq 6}">
                                <c:if test="${empty a6}">
                                    <li><a href="/bill/bill_list" class="bill_off"></a></li>
                                    <c:set scope="page" value="6" var="a6"/>
                                </c:if>
                            </c:if>
                            <c:if test="${modeul.module_id eq 7}">
                                <c:if test="${empty a7}">
                                    <li><a href="/report/report_list" class="report_off"></a></li>
                                    <c:set scope="page" value="7" var="a7"/>
                                </c:if>
                            </c:if>
                        </c:forEach>
                    </c:forEach>
                    <li><a href="/user/user_info" class="information_off"></a></li>
                    <li><a href="/user/user_modi_pwd" class="password_off"></a></li>
            </ul>            
        </div>
        <!--导航区域结束-->
        <!--主要区域开始-->
        <div id="main">
            <form action="" method="">
                <!--查询-->
                <div class="search_add">
                    <input type="button" value="增加" class="btn_add" onclick="location.href='role_add';" />
                </div>  
                <!--删除的操作提示-->
                <div id="operate_result_info" class="operate_success">
                    <img src="../images/close.png" onclick="this.parentNode.style.display='none';" />
                </div> <!--删除错误！该角色被使用，不能删除。-->
                <div id="operate_result_fail" class="operate_fail">
                    <img src="../images/close.png" onclick="this.parentNode.style.display='none';" />
                </div>
                <!--数据区域：用表格展示数据-->     
                <div id="data">                      
                    <table id="datalist">
                        <tr>                            
                            <th>角色 ID</th>
                            <th>角色名称</th>
                            <th class="width600">拥有的权限</th>
                            <th class="td_modi"></th>
                        </tr>
                        <c:if test="${pageBean.data != null and pageBean.data.size()>0}">
                            <c:forEach items="${pageBean.data}" var="role">
                        <tr>
                            <td>${role.role_id}</td>
                            <td>${role.name}</td>
                            <td>
                                <c:if test="${role.modules != null and role.modules.size()>0}">
                                    <c:forEach items="${role.modules}" var="module" varStatus="status">
                                        ${module.name}
                                        <c:if test="${!status.last}">
                                            、
                                        </c:if>
                                    </c:forEach>
                                </c:if>
                            </td>
                            <td>
                                <input type="button" value="修改" class="btn_modify" onclick="location.href='role_modi?role_id=${role.role_id}';"/>
                                <input type="button" value="删除" class="btn_delete" onclick="deleteRole(${role.role_id});" />
                            </td>
                        </tr>
                        <tr>
                            </c:forEach>
                        </c:if>
                    </table>
                </div> 
                <!--分页-->
                <div id="pages">
                    <c:if test="${pageBean.pageNum>1}">
                        <a href="/role/role_list?pageNum=${pageBean.pageNum-1}">上一页</a>
                    </c:if>
                    <c:if test="${pageBean.totalPage<=5}">
                        <c:forEach var="i" begin="1" end="${pageBean.totalPage}">
                            <a href="/role/role_list?pageNum=${i}"
                               <c:if test="${pageBean.pageNum==i}">class="current_page" </c:if>>${i}</a>
                        </c:forEach>
                    </c:if>


                    <c:if test="${pageBean.totalPage>5}">
                        <c:if test="${pageBean.pageNum <= 3}">
                            <c:forEach var="i" begin="1" end="5">
                                <a href="/role/role_list?pageNum=${i}"
                                   <c:if test="${pageBean.pageNum == i}">class="current_page"</c:if> >${i}</a>
                            </c:forEach>
                        </c:if>

                        <c:if test="${pageBean.pageNum > 3 and pageBean.pageNum <= pageBean.totalPage -3}">
                            <c:forEach var="i" begin="${pageBean.pageNum-2}" end="${pageBean.pageNum+2}">
                                <a href="/role/role_list?pageNum=${i}"
                                   <c:if test="${pageBean.pageNum == i}">class="current_page"</c:if> >${i}</a>
                            </c:forEach>
                        </c:if>
                        <c:if test="${pageBean.pageNum > 3 and pageBean.pageNum > pageBean.totalPage-3}">
                            <c:forEach var="i" begin="${pageBean.totalPage-4}" end="${pageBean.totalPage}">
                                <a href="/role/role_list?pageNum=${i}"
                                   <c:if test="${pageBean.pageNum == i}">class="current_page"</c:if> >${i}</a>
                            </c:forEach>
                        </c:if>
                    </c:if>
                    <c:if test="${pageBean.pageNum<pageBean.totalPage}">
                        <a href="/role/role_list?pageNum=${pageBean.pageNum+1}">下一页</a>
                        <a href="/role/role_list?pageNum=${pageBean.totalPage}">尾页</a>
                    </c:if>
                </div>
                </form>
            </div>
        <!--主要区域结束-->
        <div id="footer">
            <p>[源自北美的技术，最优秀的师资，最真实的企业环境，最适用的实战项目]</p>
            <p>版权所有(C)云科技有限公司 </p>
        </div>
    </body>
</html>
