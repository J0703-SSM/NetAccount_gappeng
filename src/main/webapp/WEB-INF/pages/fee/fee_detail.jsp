<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page isELIgnored="false" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title></title>
        <link type="text/css" rel="stylesheet" media="all" href="/resource/styles/global.css" />
        <link type="text/css" rel="stylesheet" media="all" href="/resource/styles/global_color.css" />
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
            <form action="" method="" class="main_form">
                <div class="text_info clearfix"><span>资费ID：</span></div>
                <div class="input_info"><input type="text" class="readonly" readonly value="${cost.cost_id}" /></div>
                <div class="text_info clearfix"><span>资费名称：</span></div>
                <div class="input_info"><input type="text" class="readonly" readonly value="${cost.name}"/></div>
                <div class="text_info clearfix"><span>资费状态：</span></div>
                <div class="input_info">
                    <select class="readonly" disabled>
                        <c:if test="${cost.status==1}"><option>开通</option></c:if>
                        <c:if test="${cost.status==0}"><option>暂停</option></c:if>
                    </select>
                </div>
                <div class="text_info clearfix"><span>资费类型：</span></div>
                <div class="input_info fee_type">
                    <input type="radio" name="radFeeType" id="monthly" disabled="disabled" <c:if test="${cost.cost_type==1}">checked="checked"</c:if>/>
                    <label for="monthly">包月</label>
                    <input type="radio" name="radFeeType" id="package" disabled="disabled" <c:if test="${cost.cost_type==2}">checked="checked"</c:if>/>
                    <label for="package">套餐</label>
                    <input type="radio" name="radFeeType"  id="timeBased" disabled="disabled" <c:if test="${cost.cost_type==3}">checked="checked"</c:if>  />
                    <label for="timeBased">计时</label>
                </div>
                <div class="text_info clearfix"><span>基本时长：</span></div>
                <div class="input_info">
                    <input type="text" class="readonly" readonly value="${cost.base_duration}"  />
                    <span>小时</span>
                </div>
                <div class="text_info clearfix"><span>基本费用：</span></div>
                <div class="input_info">
                    <input type="text"  class="readonly" readonly value="${cost.base_cost}" />
                    <span>元</span>
                </div>
                <div class="text_info clearfix"><span>单位费用：</span></div>
                <div class="input_info">
                    <input type="text"  class="readonly" readonly value="${cost.unit_cost}" />
                    <span>元/小时</span>
                </div>
                <div class="text_info clearfix"><span>创建时间：</span></div>
                <div class="input_info"><input type="text"  class="readonly" readonly value="${cost.creatTime}" /></div>
                <div class="text_info clearfix"><span>启动时间：</span></div>
                <div class="input_info"><input type="text"  class="readonly" readonly value="${cost.startTime}" /></div>
                <div class="text_info clearfix"><span>资费说明：</span></div>
                <div class="input_info_high">
                    <textarea class="width300 height70 readonly" readonly>${cost.descr}</textarea>
                </div>                    
                <div class="button_info clearfix">
                    <input type="button" value="返回" class="btn_save" onclick="location.href='/fee/fee_list';" />
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
