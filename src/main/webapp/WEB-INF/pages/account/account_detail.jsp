<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page isELIgnored="false" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>云科技</title>
        <link type="text/css" rel="stylesheet" media="all" href="/resource/styles/global.css" />
        <link type="text/css" rel="stylesheet" media="all" href="/resource/styles/global_color.css" />
        <script src="/resource/js/JQ3.2.1.js"></script>
    </head>
    <body>
        <!--Logo区域开始-->
        <div id="header">
            <img src="../images/logo.png" alt="logo" class="left" />
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
            <form action="" method="" class="main_form">
                <!--必填项-->
                <div class="text_info clearfix"><span>账务账号ID：</span></div>
                <div class="input_info"><input type="text" value="${account.account_id}" readonly class="readonly" /></div>
                <div class="text_info clearfix"><span>姓名：</span></div>
                <div class="input_info"><input type="text" value="${account.real_name}" readonly class="readonly" /></div>
                <div class="text_info clearfix"><span>身份证：</span></div>
                <div class="input_info">
                    <input type="text" value="${account.idcard_no}" readonly class="readonly" />
                </div>
                <div class="text_info clearfix"><span>登录账号：</span></div>
                <div class="input_info">
                    <input type="text" value="${account.login_name}" readonly class="readonly" />
                </div>                   
                <div class="text_info clearfix"><span>电话：</span></div>
                <div class="input_info">
                    <input type="text" class="width200 readonly" readonly value="${account.telephone}" />
                </div>
                <div class="text_info clearfix"><span>推荐人账务账号ID：</span></div>
                <div class="input_info"><input type="text" readonly class="readonly" value="${account.recommender_id}" /></div>
                <div class="text_info clearfix"><span>推荐人身份证号码：</span></div>
                <div class="input_info"><input type="text" readonly class="readonly" value="${account.recommender_idcard}" /></div>
                <div class="text_info clearfix"><span>状态：</span></div>
                <div class="input_info">
                    <select disabled>
                        <option <c:if test="${account.status eq 1}">selected="selected"</c:if> >开通</option>
                        <option <c:if test="${account.status eq 2}">selected="selected"</c:if>>暂停</option>
                        <option <c:if test="${account.status eq 3}">selected="selected"</c:if> >删除</option>
                    </select>                        
                </div>                    
                <div class="text_info clearfix"><span>开通/暂停/删除时间：</span></div>
                <div class="input_info"><input type="text" readonly class="readonly" value="${account.create_date}" /></div>

                <div class="text_info clearfix"><span>上次登录时间：</span></div>
                <div class="input_info"><input type="text" readonly class="readonly" value="${account.last_login_time}" /></div>
                <div class="text_info clearfix"><span>上次登录IP：</span></div>
                <div class="input_info"><input type="text" readonly class="readonly" value="${account.last_login_ip}" /></div>
                <!--可选项数据-->
                <div class="text_info clearfix"><span>生日：</span></div>
                <div class="input_info">
                    <input type="text" readonly class="readonly" value="${account.birthdate}" />
                </div>
                <div class="text_info clearfix"><span>Email：</span></div>
                <div class="input_info">
                    <input type="text" class="width350 readonly" readonly value="${account.email}" />
                </div> 
                <div class="text_info clearfix"><span>职业：</span></div>
                <div class="input_info">
                    <select disabled>
                        <option <c:if test="${account.occupation eq '干部'}">selected="selected"</c:if> >干部</option>
                        <option <c:if test="${account.occupation eq '学生'}">selected="selected"</c:if> >学生</option>
                        <option <c:if test="${account.occupation eq '技术人员'}">selected="selected"</c:if> >技术人员</option>
                        <option <c:if test="${account.occupation eq '其他'}">selected="selected"</c:if>>其他</option>
                    </select>                        
                </div>
                <div class="text_info clearfix"><span>性别：</span></div>
                <div class="input_info fee_type">
                    <input type="radio" name="radSex" <c:if test="${account.gender==1}">checked="checked"</c:if>  id="female" value="1" />
                    <label for="female">女</label>
                    <input type="radio" name="radSex" <c:if test="${account.gender==2}">checked="checked"</c:if>  id="male" value="2" />
                    <label for="male">男</label>
                </div>
                <div class="text_info clearfix"><span>通信地址：</span></div>
                <div class="input_info"><input type="text" class="width350 readonly" readonly value="${account.mailaddress}" /></div>
                <div class="text_info clearfix"><span>邮编：</span></div>
                <div class="input_info"><input type="text" class="readonly" readonly value="${account.zipcode}" /></div>
                <div class="text_info clearfix"><span>QQ：</span></div>
                <div class="input_info"><input type="text" class="readonly" readonly value="${account.qq}" /></div>
                <!--操作按钮-->
                <div class="button_info clearfix">
                    <input type="button" value="返回" class="btn_save" onclick="location.href='/account/account_list';" />
                </div>
            </form>  
        </div>
        <!--主要区域结束-->
        <div id="footer">
            <span>[源自北美的技术，最优秀的师资，最真实的企业环境，最适用的实战项目]</span>
            <br />
            <span>版权所有(C)云科技有限公司 </span>
        </div>
    </body>
</html>
