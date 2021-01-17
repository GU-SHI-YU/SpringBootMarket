<%@ page contentType="text/html; ISO-8859-1; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>购买产品测试</title>
    <script type="text/javascript" src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js"></script>
</head>
<script type="text/javascript">
    const params = {
        userId: 1,
        productId: 1,
        quantity: 3
    };
    $.post("./purchase", params, function (result) {
        alert(result.message)
    });
</script>
</html>