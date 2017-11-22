<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page isELIgnored="false" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title></title>
    <link type="text/css" rel="stylesheet" media="all" href="/resource/styles/global.css"/>
    <link type="text/css" rel="stylesheet" media="all" href="/resource/styles/global_color.css"/>
    <script src="/resource/js/JQ3.2.1.js"></script>
    <script language="javascript" type="text/javascript">


        //保存成功的提示消息
        function showResult() {
            var module_values = "";
            $(":checkbox:checked").each(function () {
                module_values += $(this).val() + ","
            });
            var bool = true;
            if (module_values.length == 0) {
                $("#moduleErr").css('display', 'block')
                bool = false
            } else {
                $("#moduleErr").css('display', 'none')
            }
            if ($("#roleName").val() == "") {
                $("#nameErr").css('display', 'block')
                bool = false
            } else {
                $("#nameErr").css('display', 'none')
            }
            if (bool) {
                $.ajax({
                    type: "post",
                    url: "/role/role_save",
                    data: {
                        "Ids": module_values,
                        "name": $("#roleName").val()
                    }, success: function (result) {

                        if (result.count > 0) {
                            $("#save_result_info").html("保存成功");
                            document.getElementById("save_result_info").style.display = "block";
                            window.setTimeout('location.href = "/role/role_list"', 2000);
                        }

                    }

                })
            }


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
    <!--保存操作后的提示信息：成功或者失败-->
    <div id="save_result_info" class="save_success"></div>
    <div id="save_result_fail" class="save_fail"></div>
    <form action="" method="" class="main_form">
        <div class="text_info clearfix"><span>角色名称：</span></div>
        <div class="input_info">
            <input type="text" class="width200" id="roleName"/>
            <div class="validate_msg_long error_msg" id="nameErr" style="display: none">不能为空，且为20长度的字母、数字和汉字的组合</div>
        </div>
        <div class="text_info clearfix"><span>设置权限：</span></div>
        <div class="input_info_high">
            <div class="input_info_scroll">
                <ul>
                    <li><input type="checkbox" value="1"/>角色管理</li>
                    <li><input type="checkbox" value="2"/>管理员管理</li>
                    <li><input type="checkbox" value="3"/>资费管理</li>
                    <li><input type="checkbox" value="4"/>账务账号</li>
                    <li><input type="checkbox" value="5"/>业务账号</li>
                    <li><input type="checkbox" value="6"/>账单</li>
                    <li><input type="checkbox" value="7"/>报表</li>
                </ul>
            </div>
            <div class="validate_msg_short error_msg" id="moduleErr" style="display: none">至少选择一个权限</div>
        </div>
        <div class="button_info clearfix">
            <input type="button" value="保存" class="btn_save" onclick="showResult()"/>
            <input type="button" value="取消" class="btn_save" onclick="location.href='/role/role_list'"/>
        </div>
    </form>
</div>
<!--主要区域结束-->
<div id="footer">
    <span>[源自北美的技术，最优秀的师资，最真实的企业环境，最适用的实战项目]</span>
    <br/>
    <span>版权所有(C)云科技有限公司 </span>
</div>
</body>
</html>
