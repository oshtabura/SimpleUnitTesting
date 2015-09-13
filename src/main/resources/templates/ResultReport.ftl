<!DOCTYPE HTML>
<html>
<head>
    <meta charset="utf-8">
    <title>Tests result</title>
</head>
<body>
<table border="1" style="width:100%">
    <caption>Tests result</caption>
    <tr>
        <th>Test name</th>
        <th>Status</th>
        <th>Error description</th>
    </tr>
<#list tests as test>
    <tr>
        <td>${test.name}</td>
        <td>${test.status}</td>
        <td>${test.errorDescription}</td>
    </tr>
</#list>
</table>
<h5>Total number of tests: ${total}</h5>

<h5>Successful tests: ${successful}</h5>

<h5>Ignored tests: ${ignored}</h5>

<h5>Failed tests: ${failed}</h5>

<h5>Error tests: ${errors}</h5>
</body>
</html>