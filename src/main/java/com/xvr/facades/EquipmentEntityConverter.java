package com.xvr.facades;

import javax.faces.convert.Converter;
import javax.faces.context.FacesContext;
import javax.faces.component.UIComponent;

import com.xvr.entities.EquipmentEntity;

public class EquipmentEntityConverter implements Converter {

    public EquipmentEntityConverter() {
    }

    public Object getAsObject(FacesContext facesContext, UIComponent uIComponent, String string) {
        if (string == null || string.trim().length() == 0) {
            return null;
        }

        EquipmentEntityManagedBean managedBean = (EquipmentEntityManagedBean) facesContext.getApplication().getVariableResolver().resolveVariable(
                facesContext, "equipmentEntity");

        final int id = Integer.parseInt(string);

        return managedBean.findEntity(id);
    }

    public String getAsString(FacesContext facesContext, UIComponent uIComponent, Object object) {
        if (object == null) return null;

        if (object instanceof EquipmentEntity) {
            EquipmentEntity entity = (EquipmentEntity) object;

            final String pk = String.valueOf(entity.getId());

            return pk;
        } else {
            throw new IllegalArgumentException("Incorrect object type: " + object.getClass().getName() + "; must be: EquipmentEntity");
        }
    }
}
