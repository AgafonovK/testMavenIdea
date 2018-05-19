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
        <title>Create RequestEntity</title>
    </head>
    <body>
        <f:view>
            <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
            <h1>Create RequestEntity</h1>
            <h:form>
                <h:panelGrid columns="2">
                                                            <h:outputText value="id:"/>
                                                    <h:inputText value="#{requestEntity.entity.id}" title="id" />
                                                                                                        <h:outputText value="requestDescription:"/>
                                                    <h:inputText value="#{requestEntity.entity.requestDescription}" title="requestDescription" />
                                                                                                        <h:outputText value="requestStartDate:"/>
                                                    <h:inputText value="#{requestEntity.entity.requestStartDate}" title="requestStartDate" />
                                                                                                        <h:outputText value="requestEndDate:"/>
                                                    <h:inputText value="#{requestEntity.entity.requestEndDate}" title="requestEndDate" />
                                                                            </h:panelGrid>

                <h:commandButton action="#{requestEntity.create}" value="Save" />
                <h:commandButton action="requestEntityList" value="Cancel"/>
                <br>
            </h:form>
        </f:view>
    </body>
</html>
