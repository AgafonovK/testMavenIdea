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
        <title>EquipmentEntity View</title>
    </head>
    <body>
        <f:view>
            <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
            <h1>EquipmentEntity View</h1>
            <h:form>
                <h:panelGrid columns="2">
                                                           <h:outputText value="id:"/>
                                                   <h:outputText value="#{equipmentEntity.entity.id}" title="id" />
                                                                                                       <h:outputText value="equipmentName:"/>
                                                   <h:outputText value="#{equipmentEntity.entity.equipmentName}" title="equipmentName" />
                                                                                                       <h:outputText value="equipmentSerialNumb:"/>
                                                   <h:outputText value="#{equipmentEntity.entity.equipmentSerialNumb}" title="equipmentSerialNumb" />
                                                                            </h:panelGrid>

                <h:commandButton action="editEquipmentEntity" value="Edit" />
                <br>
                <h:commandButton action="equipmentEntityList" value="Show All"/>
                <br>
            </h:form>
        </f:view>
    </body>
</html>
