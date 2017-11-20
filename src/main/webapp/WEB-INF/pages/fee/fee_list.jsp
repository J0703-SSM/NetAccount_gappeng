<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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

            //启用
            function startFee(cost_id) {
                var r = window.confirm("确定要启用此资费吗？资费启用后将不能修改和删除。");
                $.ajax({
                    type:"get",
                    url:"/fee/startCost",
                    data:{
                        cost_id:cost_id
                    },success:function (result) {
                        if (result.count>0){
                            alert("启用成功");
                            location.href="/fee/fee_list"
                        }else {
                            alert("资费已经启用");
                        }
                    }

                })

            }
            //删除
            function deleteFee(cost_id) {
                var r = window.confirm("确定要删除此资费吗？");
                $.ajax({
                    type:"get",
                    url:"/fee/delById",
                    data:{
                        cost_id:cost_id
                    },
                    success:function (result) {
                        if(result.count>0){
                            document.getElementById("operate_result_info").style.display = "block";
                            window.setTimeout(location.href = "/fee/fee_list", 1000000);

                        }else {
                            alert("资费已经启用,无法删除")
                        }
                    }
                })

            }
            //编辑
            function updateFee(cost_id) {
                $.ajax({
                    type:"get",
                    url:"/fee/before_fee_modi",
                    data:{
                        cost_id:cost_id
                    },success:function (result) {
                        if (result.count>0){
                            location.href = "/fee/fee_modi?cost_id="+cost_id+""
                        }else {
                            alert("资费已经启用,无法修改")
                        }
                    }
                })
            }

            //排序按钮的点击事件
            function sort(btnObj) {
                if (btnObj.className == "sort_desc")
                    btnObj.className = "sort_asc";

                else
                    btnObj.className = "sort_desc";
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
                                <li><a href="/role/role_list" class="role_off"></a></li>
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
                                <li><a href="/fee/fee_list" class="fee_on"></a></li>
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
                <!--排序-->
                <div class="search_add">
                    <div>
                        <!--<input type="button" value="月租" class="sort_asc" onclick="sort(this);" />-->
                        <input type="button" value="基费" class="sort_asc" onclick="sort(this,'');" />
                        <input type="button" value="时长" class="sort_asc" onclick="sort(this);" />
                    </div>
                    <input type="button" value="增加" class="btn_add" onclick="location.href='fee_add';" />
                </div> 
                <!--启用操作的操作提示-->
                <div id="operate_result_info" class="operate_success">
                    <img src="../images/close.png" onclick="this.parentNode.style.display='none';" />
                    删除成功！
                </div>    
                <!--数据区域：用表格展示数据-->     
                <div id="data">            
                    <table id="datalist">
                        <tr>
                            <th>资费ID</th>
                            <th class="width100">资费名称</th>
                            <th>基本时长</th>
                            <th>基本费用</th>
                            <th>单位费用</th>
                            <th>创建时间</th>
                            <th>开通时间</th>
                            <th class="width50">状态</th>
                            <th class="width200"></th>
                        </tr>
                        <c:forEach items="${pageBean.data}" var="c">
                            <tr>
                                <td>${c.cost_id}</td>
                                <td><a href="/fee/fee_detail/${c.cost_id}">${c.name}</a></td>
                                <td>${c.base_duration} 小时</td>
                                <td>${c.base_cost} 元</td>
                                <td>${c.unit_cost} 元/小时</td>
                                <td>${c.creatTime}</td>
                                <td>${c.startTime}</td>
                                <td>
                                    <c:choose>
                                        <c:when test="${c.status==1}">
                                            开通
                                        </c:when>
                                        <c:otherwise>
                                            暂停
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                                <td>
                                    <c:if test="${c.status==0}">

                                        <input type="button" value="启用" class="btn_start" onclick="startFee(${c.cost_id});" />
                                        <input type="button" value="修改" class="btn_modify" onclick="updateFee(${c.cost_id});" />
                                        <input type="button" value="删除" class="btn_delete" onclick="deleteFee(${c.cost_id});" />

                                    </c:if>

                                </td>
                            </tr>
                        </c:forEach>

                    </table>
                    <p>业务说明：<br />
                    1、创建资费时，状态为暂停，记载创建时间；<br />
                    2、暂停状态下，可修改，可删除；<br />
                    3、开通后，记载开通时间，且开通后不能修改、不能再停用、也不能删除；<br />
                    4、业务账号修改资费时，在下月底统一触发，修改其关联的资费ID（此触发动作由程序处理）
                    </p>
                </div>
                <!--分页-->
                <div id="pages">
                    <c:if test="${pageBean.pageNum>1}">
                        <a href="/fee/fee_list?pageNum=${pageBean.pageNum-1}">上一页</a>
                    </c:if>
                    <c:if test="${pageBean.totalPage<=5}">
                        <c:forEach var="i" begin="1" end="${pageBean.totalPage}">
                            <a href="/fee/fee_list?pageNum=${i}"
                               <c:if test="${pageBean.pageNum==i}">class="current_page" </c:if>>${i}</a>
                        </c:forEach>
                    </c:if>


                    <c:if test="${pageBean.totalPage>5}">
                        <c:if test="${pageBean.pageNum <= 3}">
                            <c:forEach var="i" begin="1" end="5">
                                <a href="/fee/fee_list?pageNum=${i}"
                                   <c:if test="${pageBean.pageNum == i}">class="current_page"</c:if> >${i}</a>
                            </c:forEach>
                        </c:if>

                        <c:if test="${pageBean.pageNum > 3 and pageBean.pageNum <= pageBean.totalPage -3}">
                            <c:forEach var="i" begin="${pageBean.pageNum-2}" end="${pageBean.pageNum+2}">
                                <a href="/fee/fee_list?pageNum=${i}"
                                   <c:if test="${pageBean.pageNum == i}">class="current_page"</c:if> >${i}</a>
                            </c:forEach>
                        </c:if>
                        <c:if test="${pageBean.pageNum > 3 and pageBean.pageNum > pageBean.totalPage-3}">
                            <c:forEach var="i" begin="${pageBean.totalPage-4}" end="${pageBean.totalPage}">
                                <a href="/fee/fee_list?pageNum=${i}"
                                   <c:if test="${pageBean.pageNum == i}">class="current_page"</c:if> >${i}</a>
                            </c:forEach>
                        </c:if>

                        <%--<c:if test="${pageBean.totalPage>5}">--%>
                            <%--<c:if test="${pageBean.pageNum <= 3}">--%>
                                <%--<c:forEach var="i" begin="1" end="5">--%>
                                    <%--<a href="/cost_order?pageNum=${i}&sort=${sort}&column=${column}"--%>
                                       <%--<c:if test="${pageBean.pageNum == i}">class="current_page"</c:if> >${i}</a>--%>
                                <%--</c:forEach>--%>
                            <%--</c:if>--%>
                            <%--<c:if test="${pageBean.pageNum > 3 and pageBean.pageNum <= pageBean.totalPage -3}">--%>
                                <%--<c:forEach var="i" begin="${pageBean.pageNum-2}" end="${pageBean.pageNum+2}">--%>
                                    <%--<a href="/cost_order?pageNum=${i}&sort=${sort}&column=${column}"--%>
                                       <%--<c:if test="${pageBean.pageNum == i}">class="current_page"</c:if> >${i}</a>--%>
                                <%--</c:forEach>--%>
                            <%--</c:if>--%>
                            <%--<c:if test="${pageBean.pageNum > 3 and pageBean.pageNum > pageBean.totalPage-3}">--%>
                                <%--<c:forEach var="i" begin="${pageBean.totalPage-4}" end="${pageBean.totalPage}">--%>
                                    <%--<a href="/cost_order?pageNum=${i}&sort=${sort}&column=${column}"--%>
                                       <%--<c:if test="${pageBean.pageNum == i}">class="current_page"</c:if> >${i}</a>--%>
                                <%--</c:forEach>--%>
                            <%--</c:if>--%>
                        <%--</c:if>--%>
                    </c:if>
                    <c:if test="${pageBean.pageNum<pageBean.totalPage}">
                        <a href="/fee/fee_list?pageNum=${pageBean.pageNum+1}">下一页</a>
                    </c:if>

                </div>
            </form>
        </div>
        <!--主要区域结束-->
        <div id="footer">
            <p>[源自北美的技术，最优秀的师资，最真实的企业环境，最适用的实战项目]</p>
            <p>版权所有(C)云科技有限公司</p>
        </div>
    </body>
</html>
