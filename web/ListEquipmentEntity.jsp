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
        <title>EquipmentEntity List</title>
    </head>
    <body>
        <f:view>
            <h1>EquipmentEntity List</h1>
            <h:form>
              <h:commandButton action="#{equipmentEntity.startCreate}" value="Create"/>

              <h:dataTable value='#{equipmentEntity.allEntities}' var='item' border="1" cellpadding="2" cellspacing="0">
                                                       <h:column>
                      <f:facet name="header">
                      <h:outputText value="id"/>
                      </f:facet>
                      <h:outputText value="#{item.id}"/>
                  </h:column>
                                                                        <h:column>
                      <f:facet name="header">
                      <h:outputText value="equipmentName"/>
                      </f:facet>
                      <h:outputText value="#{item.equipmentName}"/>
                  </h:column>
                                                                        <h:column>
                      <f:facet name="header">
                      <h:outputText value="equipmentSerialNumb"/>
                      </f:facet>
                      <h:outputText value="#{item.equipmentSerialNumb}"/>
                  </h:column>
                                                   <h:column>
                      <h:commandButton value="View" action="#{equipmentEntity.startView}">
                          <f:param name="id" value="#{item.id}"/>
                      </h:commandButton>&nbsp;
                      <h:commandButton value="Edit" action="#{equipmentEntity.startEdit}">
                          <f:param name="id" value="#{item.id}"/>
                      </h:commandButton>&nbsp;
                      <h:commandButton value="Delete" action="#{equipmentEntity.delete}">
                          <f:param name="id" value="#{item.id}"/>
                      </h:commandButton>
                  </h:column>
              </h:dataTable>
            </h:form>
        </f:view>
    </body>
</html>
