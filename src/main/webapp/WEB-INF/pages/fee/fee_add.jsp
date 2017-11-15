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
            //保存结果的提示
            function showResult() {
                var base_duration = $("#base_duration").val();
                var base_cost = $("#base_cost").val();
                var unit_cost = $("#unit_cost").val();
                if (base_duration==''){
                    base_duration=null
                }
                if (base_cost==''){
                    base_cost=null
                }
                if (unit_cost==''){
                    unit_cost=null
                }
                $.ajax({
                    type:"get",
                    url:"/fee/feeAdd",
                    data:{
                        name:$("#name").val(),
                        cost_type:$("input:radio:checked").val(),
                        base_duration:base_duration,
                        base_cost:base_cost,
                        unit_cost:unit_cost,
                        descr:$("#descr").val()
                    },

                    success:function (result) {
                        alert(result.count)
                        if (result.count>0){
                            showResultDiv(true);
                            window.setTimeout("showResultDiv(false);", 3000);
                        }else {
                            alert(1)

                            $("#nameErr").html(result.map("namemsg"));
                            $("#durationErr").html(result.map("durationmsg"));
                            $("#costErr").html(result.map("costmsg"));
                            $("#ucostErr").html(result.map("ucostmsg"));
                            $("#descErr").html(result.map("descmsg"))

                        }
                    }
                })

            }
            function showResultDiv(flag) {
                var divResult = document.getElementById("save_result_info");
                if (!flag)
                    divResult.style.display = "block";
                else
                    divResult.style.display = "none";
                 location.href="/fee/fee_list"
            }

            //切换资费类型
            function feeTypeChange(type) {
                var inputArray = document.getElementById("main").getElementsByTagName("input");
                if (type == 1) {
                    inputArray[4].readOnly = true;
                    inputArray[4].value = "";
                    inputArray[4].className += " readonly";
                    inputArray[5].readOnly = false;
                    inputArray[5].className = "width100";
                    inputArray[6].readOnly = true;
                    inputArray[6].className += " readonly";
                    inputArray[6].value = "";
                }
                else if (type == 2) {
                    inputArray[4].readOnly = false;
                    inputArray[4].className = "width100";
                    inputArray[5].readOnly = false;
                    inputArray[5].className = "width100";
                    inputArray[6].readOnly = false;
                    inputArray[6].className = "width100";
                }
                else if (type == 3) {
                    inputArray[4].readOnly = true;
                    inputArray[4].value = "";
                    inputArray[4].className += " readonly";
                    inputArray[5].readOnly = true;
                    inputArray[5].value = "";
                    inputArray[5].className += " readonly";
                    inputArray[6].readOnly = false;
                    inputArray[6].className = "width100";
                }
            }
        </script>
    </head>
    <body>
        <!--Logo区域开始-->
        <div id="header">
            <img src="../images/logo.png" alt="logo" class="left"/>
            <a href="#">[退出]</a>            
        </div>
        <!--Logo区域结束-->
        <!--导航区域开始-->
        <div id="navi">
            <ul id="menu">
                <li><a href="/index" class="index_off"></a></li>
                <li><a href="/fee/role_list" class="role_off"></a></li>
                <li><a href="/fee/admin_list" class="admin_off"></a></li>
                <li><a href="/fee/fee_list" class="fee_off"></a></li>
                <li><a href="/fee/account_list" class="account_off"></a></li>
                <li><a href="/fee/service_list" class="service_off"></a></li>
                <li><a href="/fee/bill_list" class="bill_off"></a></li>
                <li><a href="/fee/report_list" class="report_off"></a></li>
                <li><a href="/fee/user_info" class="information_off"></a></li>
                <li><a href="/fee/user_modi_pwd" class="password_on"></a></li>
            </ul>
        </div>
        <!--导航区域结束-->
        <!--主要区域开始-->
        <div id="main">            
            <div id="save_result_info" class="save_fail">保存失败，资费名称重复！</div>
            <form action="" method="post" class="main_form">
                <div class="text_info clearfix"><span>资费名称：</span></div>
                <div class="input_info">
                    <input type="text" class="width300" id="name" />
                    <span class="required">*</span>
                    <div id="nameErr" class="validate_msg_short"></div>
                </div>
                <div class="text_info clearfix"><span>资费类型：</span></div>
                <div class="input_info fee_type">
                    <input type="radio" name="radFeeType" id="monthly" value="1" onclick="feeTypeChange(1);" />
                    <label for="monthly">包月</label>
                    <input type="radio" name="radFeeType" checked="checked" id="package" value="3" onclick="feeTypeChange(2);" />
                    <label for="package">套餐</label>
                    <input type="radio" name="radFeeType" id="timeBased" value="3" onclick="feeTypeChange(3);" />
                    <label for="timeBased">计时</label>
                </div>
                <div class="text_info clearfix"><span>基本时长：</span></div>
                <div class="input_info">
                    <input type="text" id="base_duration" class="width100" />
                    <span class="info">小时</span>
                    <span class="required">*</span>
                    <div  id="durationErr" class="validate_msg_long"></div>
                </div>
                <div class="text_info clearfix"><span>基本费用：</span></div>
                <div class="input_info">
                    <input type="text" id="base_cost" class="width100" />
                    <span class="info">元</span>
                    <span class="required">*</span>
                    <div  id="costErr" class="validate_msg_long error_msg"></div>
                </div>
                <div class="text_info clearfix"><span>单位费用：</span></div>
                <div class="input_info">
                    <input type="text" id="unit_cost" class="width100" />
                    <span class="info">元/小时</span>
                    <span class="required">*</span>
                    <div id="ucostErr" class="validate_msg_long error_msg"></div>
                </div>
                <div class="text_info clearfix"><span>资费说明：</span></div>
                <div class="input_info_high">
                    <textarea class="width300 height70" id="descr"></textarea>
                    <div id="descErr" class="validate_msg_short error_msg"></div>
                </div>                    
                <div class="button_info clearfix">
                    <input type="button" value="保存" class="btn_save" onclick="showResult();"/>
                    <input type="button" value="取消" class="btn_save" />
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
