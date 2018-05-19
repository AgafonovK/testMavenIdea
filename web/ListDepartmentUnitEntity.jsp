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
        <title>DepartmentUnitEntity List</title>
    </head>
    <body>
        <f:view>
            <h1>DepartmentUnitEntity List</h1>
            <h:form>
              <h:commandButton action="#{departmentUnitEntity.startCreate}" value="Create"/>

              <h:dataTable value='#{departmentUnitEntity.allEntities}' var='item' border="1" cellpadding="2" cellspacing="0">
                                                       <h:column>
                      <f:facet name="header">
                      <h:outputText value="id"/>
                      </f:facet>
                      <h:outputText value="#{item.id}"/>
                  </h:column>
                                                                        <h:column>
                      <f:facet name="header">
                      <h:outputText value="departmentUnitName"/>
                      </f:facet>
                      <h:outputText value="#{item.departmentUnitName}"/>
                  </h:column>
                                                                        <h:column>
                      <f:facet name="header">
                      <h:outputText value="departmentUnitHead"/>
                      </f:facet>
                      <h:outputText value="#{item.departmentUnitHead}"/>
                  </h:column>
                                                                        <h:column>
                      <f:facet name="header">
                      <h:outputText value="departmentUnitPhone"/>
                      </f:facet>
                      <h:outputText value="#{item.departmentUnitPhone}"/>
                  </h:column>
                                                   <h:column>
                      <h:commandButton value="View" action="#{departmentUnitEntity.startView}">
                          <f:param name="id" value="#{item.id}"/>
                      </h:commandButton>&nbsp;
                      <h:commandButton value="Edit" action="#{departmentUnitEntity.startEdit}">
                          <f:param name="id" value="#{item.id}"/>
                      </h:commandButton>&nbsp;
                      <h:commandButton value="Delete" action="#{departmentUnitEntity.delete}">
                          <f:param name="id" value="#{item.id}"/>
                      </h:commandButton>
                  </h:column>
              </h:dataTable>
            </h:form>
        </f:view>
    </body>
</html>
