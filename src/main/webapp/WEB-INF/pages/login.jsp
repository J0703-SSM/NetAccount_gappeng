<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page isELIgnored="false" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title></title>
        <link type="text/css" rel="stylesheet" media="all" href="/resource/styles/global.css" />
        <link type="text/css" rel="stylesheet" media="all" href="/resource/styles/global_color.css" />
        <script src="/resource/js/JQ3.2.1.js"></script>

    </head>
    <body class="index">
        <div class="login_box">
            <form action="/checkLogin" method="post" id="form1">
            <table>
                <tr>
                    <td class="login_info">账号：</td>
                    <td colspan="2"><input id="admin_code" name="admin_code" type="text" class="width150" /></td>
                    <td class="login_error_info"><span id="nameErr" class="required"></span></td>
                </tr>
                <tr>
                    <td class="login_info">密码：</td>
                    <td colspan="2"><input id="password" name="password" type="password" class="width150" /></td>
                    <td><span class="required" id="passwordErr"></span></td>
                </tr>
                <tr>
                    <td class="login_info">验证码：</td>
                    <td class="width70"><input name="verifyCode" type="text" class="width70" /></td>
                    <td><img src="/getVerifyCode" alt="验证码" title="点击更换" id="verifyCodeImage" onclick="changeImage()"/></td>
                    <td><span class="required">${codeErr}</span></td>
                </tr>
                <tr>
                    <td></td>
                    <td class="login_button" colspan="2">
                        <a href="javascript:document:form1.submit()"><img src="/resource/images/login_btn.png" /></a>
                    </td>
                    <td><span class="required">${adminErr}</span></td>
                </tr>
            </table>
         </form>
        </div>
    <script>
         $("#admin_code").blur(function () {
             if($("#admin_code").val().length >0){
                 $.ajax({
                     type:"post",
                     url:"/checkAdmin",
                     data:{
                         admin_code:$("#admin_code").val(),
                     },success:function (result) {
                         $("#nameErr").text(result.message)
                     }
                 })
             }
         });



        function changeImage() {
            $('#verifyCodeImage').attr('src', '/getVerifyCode?date=' + Math.random());
        }
    </script>
    </body>
</html>
