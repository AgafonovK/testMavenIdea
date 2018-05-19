package com.xvr.facades;

import javax.faces.convert.Converter;
import javax.faces.context.FacesContext;
import javax.faces.component.UIComponent;

import com.xvr.entities.EquipmentStatusEntity;

public class EquipmentStatusEntityConverter implements Converter {

    public EquipmentStatusEntityConverter() {
    }

    public Object getAsObject(FacesContext facesContext, UIComponent uIComponent, String string) {
        if (string == null || string.trim().length() == 0) {
            return null;
        }

        EquipmentStatusEntityManagedBean managedBean = (EquipmentStatusEntityManagedBean) facesContext.getApplication().getVariableResolver().resolveVariable(
                facesContext, "equipmentStatusEntity");

        final int id = Integer.parseInt(string);

        return managedBean.findEntity(id);
    }

    public String getAsString(FacesContext facesContext, UIComponent uIComponent, Object object) {
        if (object == null) return null;

        if (object instanceof EquipmentStatusEntity) {
            EquipmentStatusEntity entity = (EquipmentStatusEntity) object;

            final String pk = String.valueOf(entity.getId());

            return pk;
        } else {
            throw new IllegalArgumentException("Incorrect object type: " + object.getClass().getName() + "; must be: EquipmentStatusEntity");
        }
    }
}
