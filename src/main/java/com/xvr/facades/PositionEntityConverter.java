package com.xvr.facades;

import javax.faces.convert.Converter;
import javax.faces.context.FacesContext;
import javax.faces.component.UIComponent;

import com.xvr.entities.PositionEntity;

public class PositionEntityConverter implements Converter {

    public PositionEntityConverter() {
    }

    public Object getAsObject(FacesContext facesContext, UIComponent uIComponent, String string) {
        if (string == null || string.trim().length() == 0) {
            return null;
        }

        PositionEntityManagedBean managedBean = (PositionEntityManagedBean) facesContext.getApplication().getVariableResolver().resolveVariable(
                facesContext, "positionEntity");

        final int id = Integer.parseInt(string);

        return managedBean.findEntity(id);
    }

    public String getAsString(FacesContext facesContext, UIComponent uIComponent, Object object) {
        if (object == null) return null;

        if (object instanceof PositionEntity) {
            PositionEntity entity = (PositionEntity) object;

            final String pk = String.valueOf(entity.getIdposition());

            return pk;
        } else {
            throw new IllegalArgumentException("Incorrect object type: " + object.getClass().getName() + "; must be: PositionEntity");
        }
    }
}
