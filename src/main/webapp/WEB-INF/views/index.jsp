<%--
  Created by IntelliJ IDEA.
  User: sangwookim
  Date: 2021/11/15
  Time: 10:50 오후
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
<script src="../"

<script>
$(document).ready(function(){

    $("#testID").on("click",function(){

        console.log('ddddd');
    })
    $("#testInput").on("change",function (){

        console.log($(this).val());
        console.log('dddddddddd');
        callTest();
    });



});

function callTest(){

    let targetUrl = '/testIndex';

    $.ajax({
        url: targetUrl,
        headers:{'Content-Type':'Application/json'},
        data : {test:'test'},
        method:'POST',
        dataType : 'json'
    }).done(function(response){
        console.log(response);
    }).fail(function(daa){
        console.log(daa);
    });
}
</script>
<html>
<head>
    <title>Title</title>
</head>
<body>

    <div id="testID">ID
        <input type="text" id="testInput"/>
    </div>
</body>
</html>
