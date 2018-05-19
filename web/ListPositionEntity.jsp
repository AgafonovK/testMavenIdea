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
        <title>PositionEntity List</title>
    </head>
    <body>
        <f:view>
            <h1>PositionEntity List</h1>
            <h:form>
              <h:commandButton action="#{positionEntity.startCreate}" value="Create"/>

              <h:dataTable value='#{positionEntity.allEntities}' var='item' border="1" cellpadding="2" cellspacing="0">
                                                       <h:column>
                      <f:facet name="header">
                      <h:outputText value="idposition"/>
                      </f:facet>
                      <h:outputText value="#{item.idposition}"/>
                  </h:column>
                                                                        <h:column>
                      <f:facet name="header">
                      <h:outputText value="positionName"/>
                      </f:facet>
                      <h:outputText value="#{item.positionName}"/>
                  </h:column>
                                                   <h:column>
                      <h:commandButton value="View" action="#{positionEntity.startView}">
                          <f:param name="id" value="#{item.idposition}"/>
                      </h:commandButton>&nbsp;
                      <h:commandButton value="Edit" action="#{positionEntity.startEdit}">
                          <f:param name="id" value="#{item.idposition}"/>
                      </h:commandButton>&nbsp;
                      <h:commandButton value="Delete" action="#{positionEntity.delete}">
                          <f:param name="id" value="#{item.idposition}"/>
                      </h:commandButton>
                  </h:column>
              </h:dataTable>
            </h:form>
        </f:view>
    </body>
</html>
