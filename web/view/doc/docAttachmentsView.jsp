<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="../common/header_new.jsp" %>
</head>
<body>
    <div>
        <table border="0" cellspacing="1" width="100%" class="inputTable">

                <tr class="inputTr">
                     <td align="right" width="20%" nowrap="true">
                        文档ID:
                    </td>
                    <td class="container">
                      ${bean.doc}
                    </td>
                </tr>
                <tr class="inputTr">
                     <td align="right" width="20%" nowrap="true">
                        文件名称:
                    </td>
                    <td class="container">
                      ${bean.name}
                    </td>
                </tr>
                <tr class="inputTr">
                     <td align="right" width="20%" nowrap="true">
                        文件原始名称:
                    </td>
                    <td class="container">
                      ${bean.orginName}
                    </td>
                </tr>
                <tr class="inputTr">
                     <td align="right" width="20%" nowrap="true">
                        上传时间:
                    </td>
                    <td class="container">
                      ${bean.uploadDate}
                    </td>
                </tr>
                <tr class="inputTr">
                     <td align="right" width="20%" nowrap="true">
                        备注:
                    </td>
                    <td class="container">
                      ${bean.remark}
                    </td>
                </tr>
                <tr class="inputTr">
                     <td align="right" width="20%" nowrap="true">
                        :
                    </td>
                    <td class="container">
                      ${bean.filePath}
                    </td>
                </tr>
        </table>
    </div>
</body>
</html>