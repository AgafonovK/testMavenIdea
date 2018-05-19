<!--
  Created by IntelliJ IDEA.
  User: Utro
  Date: 19.05.2018
  Time: 19:24
  To change this template use File | Settings | File Templates.
-->
<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <title>Create DepartmentEntity</title>
    </head>
    <body>
        <f:view>
            <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
            <h1>Create DepartmentEntity</h1>
            <h:form>
                <h:panelGrid columns="2">
                                                            <h:outputText value="id:"/>
                                                    <h:inputText value="#{departmentEntity.entity.id}" title="id" />
                                                                                                        <h:outputText value="departmentName:"/>
                                                    <h:inputText value="#{departmentEntity.entity.departmentName}" title="departmentName" />
                                                                                                        <h:outputText value="departmentHead:"/>
                                                    <h:inputText value="#{departmentEntity.entity.departmentHead}" title="departmentHead" />
                                                                                                        <h:outputText value="departmentPhone:"/>
                                                    <h:inputText value="#{departmentEntity.entity.departmentPhone}" title="departmentPhone" />
                                                                            </h:panelGrid>

                <h:commandButton action="#{departmentEntity.create}" value="Save" />
                <h:commandButton action="departmentEntityList" value="Cancel"/>
                <br>
            </h:form>
        </f:view>
    </body>
</html>
