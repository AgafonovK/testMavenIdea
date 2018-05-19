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
        <title>StuffEntity View</title>
    </head>
    <body>
        <f:view>
            <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
            <h1>StuffEntity View</h1>
            <h:form>
                <h:panelGrid columns="2">
                                                           <h:outputText value="id:"/>
                                                   <h:outputText value="#{stuffEntity.entity.id}" title="id" />
                                                                                                       <h:outputText value="firstName:"/>
                                                   <h:outputText value="#{stuffEntity.entity.firstName}" title="firstName" />
                                                                                                       <h:outputText value="secondName:"/>
                                                   <h:outputText value="#{stuffEntity.entity.secondName}" title="secondName" />
                                                                                                       <h:outputText value="middleName:"/>
                                                   <h:outputText value="#{stuffEntity.entity.middleName}" title="middleName" />
                                                                                                       <h:outputText value="stuffPhone:"/>
                                                   <h:outputText value="#{stuffEntity.entity.stuffPhone}" title="stuffPhone" />
                                                                                                       <h:outputText value="dateOfBirth:"/>
                                                   <h:outputText value="#{stuffEntity.entity.dateOfBirth}" title="dateOfBirth" />
                                                                            </h:panelGrid>

                <h:commandButton action="editStuffEntity" value="Edit" />
                <br>
                <h:commandButton action="stuffEntityList" value="Show All"/>
                <br>
            </h:form>
        </f:view>
    </body>
</html>
