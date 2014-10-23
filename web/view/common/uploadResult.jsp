<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../../common/taglibs.jsp" %>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="../common/header_new.jsp" %>
    <script>
        function loadResult(){
            parent.$.jui.get("${juiId}").close("${docId}");
        }
    </script>
</head>
<body onload="loadResult();">
<form:form id="fileUploadForm" modelAttribute="bean" name="fileUploadForm" >
    <table border="0" cellspacing="1" width="100%" class="inputTable">

        <tr class="inputTr">
            <td >
                    ${msg}
            </td>
            <td  class="container">
            </td>
        </tr>
    </table>
</form:form>
</body>

</html>
