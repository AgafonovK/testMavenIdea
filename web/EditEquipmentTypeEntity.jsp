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
        <title>Edit EquipmentTypeEntity</title>
    </head>
    <body>
        <f:view>
            <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
            <h1>Edit EquipmentTypeEntity</h1>
            <h:form>
                <h:panelGrid columns="2">
                                    <h:outputText value="id:"/>
                                                                        <h:inputText value="#{equipmentTypeEntity.entity.id}" title="id" />
                                                                                <h:outputText value="typeEquip:"/>
                                                                        <h:inputText value="#{equipmentTypeEntity.entity.typeEquip}" title="typeEquip" />
                                                                            </h:panelGrid>

                <h:commandButton action="#{equipmentTypeEntity.save}" value="Save"/>
                <h:commandButton action="equipmentTypeEntityList" value="Cancel"/>
                <br>
            </h:form>
        </f:view>
    </body>
</html>
