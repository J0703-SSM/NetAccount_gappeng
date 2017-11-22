<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>云科技</title>
    <link type="text/css" rel="stylesheet" media="all" href="/resource/styles/global.css"/>
    <link type="text/css" rel="stylesheet" media="all" href="/resource/styles/global_color.css"/>
    <script src="/resource/js/JQ3.2.1.js"></script>



    <script language="javascript" type="text/javascript">

        //保存成功的提示信息
        function showResult() {
            $.ajax({
                type: "get",
                url: "/account/accountAdd",
                data: {
                    real_name: $("#name").val(),
                    idcard_no: $("#idcard").val(),
                    login_name: $("#login_name").val(),
                    login_passwd: $("#password").val(),
                    repassword: $("#repassword").val(),
                    telephone: $("#tel").val(),
                    recommender_idcard: $("#recommender_idcard").val(),
                    email: $("#email").val(),
                    occupation: $("#occupation").val(),
                    gender:$("input:radio:checked").val(),
                    mailaddress:$("#mailaddress").val(),
                    zipcode:$("#zipcode").val(),
                    qq:$("#qq").val()
            }, success: function (result) {
                    if (result.count > 0) {
                        $("#operate_result_info").html("保存成功");
                        document.getElementById("operate_result_info").style.display="block";
                        window.setTimeout('location.href = "/account/account_list"', 3000);
                    }else {
                        $("#save_result_info").html("保存失败");
                        document.getElementById("save_result_info").style.display="block";
                        window.setTimeout('location.href = "/account/account_add"', 3000);
                    }

                }
            })

        }

        //显示选填的信息项
        function showOptionalInfo(imgObj) {
            var div = document.getElementById("optionalInfo");
            if (div.className == "hide") {
                div.className = "show";
                imgObj.src = "../images/hide.png";
            }
            else {
                div.className = "hide";
                imgObj.src = "../images/show.png";
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
                        <li><a href="/fee/fee_list" class="fee_off"></a></li>
                        <c:set scope="page" value="3" var="a3"/>
                    </c:if>
                </c:if>
                <c:if test="${modeul.module_id eq 4}">
                    <c:if test="${empty a4}">
                        <li><a href="/account/account_list" class="account_on"></a></li>
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
    <!--保存成功或者失败的提示消息-->
    <div id="operate_result_info" class="operate_success">
        <img src="/resource/images/close.png" onclick="this.parentNode.style.display='none';" />
    </div>
    <div id="save_result_info" class="save_fail"></div>
    <form action="" method="" class="main_form">
        <!--必填项-->
        <div class="text_info clearfix"><span>姓名：</span></div>
        <div class="input_info">
            <input type="text" id="name"/>
            <span class="required">*</span>
            <div class="validate_msg_long">20长度以内的汉字、字母和数字的组合</div>
        </div>
        <div class="text_info clearfix"><span>身份证：</span></div>
        <div class="input_info">
            <input type="text" id="idcard"/>
            <span class="required">*</span>
            <div class="validate_msg_long">正确的身份证号码格式</div>
        </div>
        <div class="text_info clearfix"><span>登录账号：</span></div>
        <div class="input_info">
            <input type="text" id="login_name"/>
            <span class="required">*</span>
            <div class="validate_msg_long">30长度以内的字母、数字和下划线的组合</div>
        </div>
        <div class="text_info clearfix"><span>密码：</span></div>
        <div class="input_info">
            <input type="password" id="password"/>
            <span class="required">*</span>
            <div class="validate_msg_long">30长度以内的字母、数字和下划线的组合</div>
        </div>
        <div class="text_info clearfix"><span>重复密码：</span></div>
        <div class="input_info">
            <input type="password" id="repassword"/>
            <span class="required">*</span>
            <div class="validate_msg_long">两次密码必须相同</div>
        </div>
        <div class="text_info clearfix"><span>电话：</span></div>
        <div class="input_info">
            <input type="text" id="tel" class="width200"/>
            <span class="required">*</span>
            <div class="validate_msg_medium">正确的电话号码格式：手机或固话</div>
        </div>
        <!--可选项-->
        <div class="text_info clearfix"><span>可选项：</span></div>
        <div class="input_info">
            <img src="/resource/images/show.png" alt="展开" onclick="showOptionalInfo(this);"/>
        </div>
        <div id="optionalInfo" class="hide">
            <div class="text_info clearfix"><span>推荐人身份证号码：</span></div>
            <div class="input_info">
                <input type="text" id="recommender_idcard" />
                <div class="validate_msg_long">正确的身份证号码格式</div>
            </div>
            <div class="text_info clearfix"><span>生日：</span></div>
            <div class="input_info">
                <input type="text"  readonly class="readonly"/>
            </div>
            <div class="text_info clearfix"><span>Email：</span></div>
            <div class="input_info">
                <input type="text" id="email" class="width350"/>
                <div class="validate_msg_tiny">50长度以内，合法的 Email 格式</div>
            </div>
            <div class="text_info clearfix"><span>职业：</span></div>
            <div class="input_info">
                <select id="occupation">
                    <option>干部</option>
                    <option>学生</option>
                    <option>技术人员</option>
                    <option>其他</option>
                </select>
            </div>
            <div class="text_info clearfix"><span>性别：</span></div>
            <div class="input_info fee_type">
                <input type="radio" name="radSex" id="female" value="1"/>
                <label for="female">女</label>
                <input type="radio" name="radSex" id="male" value="2"/>
                <label for="male">男</label>
            </div>
            <div class="text_info clearfix"><span>通信地址：</span></div>
            <div class="input_info">
                <input type="text" id="mailaddress" class="width350"/>
                <div class="validate_msg_tiny">50长度以内</div>
            </div>
            <div class="text_info clearfix"><span>邮编：</span></div>
            <div class="input_info">
                <input type="text" id="zipcode"/>
                <div class="validate_msg_long" >6位数字</div>
            </div>
            <div class="text_info clearfix"><span>QQ：</span></div>
            <div class="input_info">
                <input type="text" id="qq"/>
                <div class="validate_msg_long">5到13位数字</div>
            </div>
        </div>
        <!--操作按钮-->
        <div class="button_info clearfix">
            <input type="button" value="保存" class="btn_save" onclick="showResult();"/>
            <input type="button" value="取消" class="btn_save" onclick="location.href='/account/account_list'"/>
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
