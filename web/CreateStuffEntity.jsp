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
        <title>Create StuffEntity</title>
    </head>
    <body>
        <f:view>
            <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
            <h1>Create StuffEntity</h1>
            <h:form>
                <h:panelGrid columns="2">
                                                            <h:outputText value="id:"/>
                                                    <h:inputText value="#{stuffEntity.entity.id}" title="id" />
                                                                                                        <h:outputText value="firstName:"/>
                                                    <h:inputText value="#{stuffEntity.entity.firstName}" title="firstName" />
                                                                                                        <h:outputText value="secondName:"/>
                                                    <h:inputText value="#{stuffEntity.entity.secondName}" title="secondName" />
                                                                                                        <h:outputText value="middleName:"/>
                                                    <h:inputText value="#{stuffEntity.entity.middleName}" title="middleName" />
                                                                                                        <h:outputText value="stuffPhone:"/>
                                                    <h:inputText value="#{stuffEntity.entity.stuffPhone}" title="stuffPhone" />
                                                                                                        <h:outputText value="dateOfBirth:"/>
                                                    <h:inputText value="#{stuffEntity.entity.dateOfBirth}" title="dateOfBirth" />
                                                                            </h:panelGrid>

                <h:commandButton action="#{stuffEntity.create}" value="Save" />
                <h:commandButton action="stuffEntityList" value="Cancel"/>
                <br>
            </h:form>
        </f:view>
    </body>
</html>
