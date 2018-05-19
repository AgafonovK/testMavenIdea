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
        <title>RequestStatusEntity View</title>
    </head>
    <body>
        <f:view>
            <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
            <h1>RequestStatusEntity View</h1>
            <h:form>
                <h:panelGrid columns="2">
                                                           <h:outputText value="id:"/>
                                                   <h:outputText value="#{requestStatusEntity.entity.id}" title="id" />
                                                                                                       <h:outputText value="reqStatus:"/>
                                                   <h:outputText value="#{requestStatusEntity.entity.reqStatus}" title="reqStatus" />
                                                                            </h:panelGrid>

                <h:commandButton action="editRequestStatusEntity" value="Edit" />
                <br>
                <h:commandButton action="requestStatusEntityList" value="Show All"/>
                <br>
            </h:form>
        </f:view>
    </body>
</html>
