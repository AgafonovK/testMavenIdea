package com.xvr.facades;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.xvr.entities.EquipmentTypeEntity;

public class EquipmentTypeEntityManagedBean {
    final public static String SELECT_ALL_ENTITIES_SQL = "SELECT o FROM EquipmentTypeEntity AS o";

    private EquipmentTypeEntity myEntity;

    private EntityManagerFactory myEntityManagerFactory;

    private ListDataModel myList;
    private ListDataModel myReferencesEntities; // M-N and M-1 references

    public EquipmentTypeEntityManagedBean() {
        myEntityManagerFactory = Persistence.createEntityManagerFactory("Persistence.xml");
    }

    private EntityManagerFactory getEntityManagerFactory() {
        return myEntityManagerFactory;
    }

    public EquipmentTypeEntity getEntity() {
        return myEntity;
    }

    public void setEntity(EquipmentTypeEntity entity) {
        myEntity = entity;
    }

    // add new EquipmentTypeEntity
    public String create() {
        EntityManager entityManager = getEntityManagerFactory().createEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(getEntity());
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            try {
                entityManager.getTransaction().rollback();
            } catch (Exception e) {
            }
        }
        entityManager.close();

        return "equipmentTypeEntityList";
    }

    // save edited EquipmentTypeEntity
    public String save() {
        EntityManager entityManager = getEntityManagerFactory().createEntityManager();
        try {
            entityManager.getTransaction().begin();
            myEntity = entityManager.merge(getEntity());
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            try {
                entityManager.getTransaction().rollback();
            } catch (Exception e) {
            }
        }
        entityManager.close();
        return "equipmentTypeEntityList";
    }

    // delete EquipmentTypeEntity
    public String delete() {
        EntityManager entityManager = getEntityManagerFactory().createEntityManager();
        try {
            entityManager.getTransaction().begin();
            EquipmentTypeEntity entity = getCurrentEntity();
            entity = entityManager.merge(entity);
            entityManager.remove(entity);
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            try {
                entityManager.getTransaction().rollback();
            } catch (Exception e) {
            }
        }
        entityManager.close();

        return "equipmentTypeEntityList";
    }

    public DataModel getReferencedEntities() {
        return myReferencesEntities;
    }

    public void setReferencedEntities(Collection<EquipmentTypeEntity> entities) {
        myReferencesEntities = new ListDataModel(new ArrayList<EquipmentTypeEntity>(entities));
    }

    public String startCreate() {
        myEntity = new EquipmentTypeEntity();
        return "createEquipmentTypeEntity";
    }

    public String startView() {
        setEntityFromRequestParam();
        return "viewEquipmentTypeEntity";
    }

    public String startEdit() {
        setEntityFromRequestParam();
        return "editEquipmentTypeEntity";
    }

    public EquipmentTypeEntity getCurrentEntity() {
        EquipmentTypeEntity entity = getEntityFromRequestParam();

        return entity == null ? myEntity : entity;
    }

    public EquipmentTypeEntity getEntityFromRequestParam() {
        if (myList == null) return null;

        EntityManager entityManager = getEntityManagerFactory().createEntityManager();
        EquipmentTypeEntity entity = (EquipmentTypeEntity) myList.getRowData();
        entity = entityManager.merge(entity);
        entityManager.close();

        return entity;
    }

    public void setEntityFromRequestParam() {
        myEntity = getCurrentEntity();
    }

    public EquipmentTypeEntity findEntity(int id) {
        EntityManager entityManager = getEntityManagerFactory().createEntityManager();

        EquipmentTypeEntity entity = entityManager.find(EquipmentTypeEntity.class, id);

        entityManager.close();

        return entity;
    }

    public DataModel getAllEntities() {
        myList = new ListDataModel(getEntities());

        return myList;
    }

    public SelectItem[] getAllEntitiesAsSelectedItems() {
        List<EquipmentTypeEntity> entities = getEntities();
        SelectItem selectItems[] = new SelectItem[entities.size()];
        int i = 0;
        for (EquipmentTypeEntity entity : entities) {
            selectItems[i++] = new SelectItem(entity);
        }
        return selectItems;
    }

    public List<EquipmentTypeEntity> getEntities() {
        EntityManager entityManager = getEntityManagerFactory().createEntityManager();

        List<EquipmentTypeEntity> entities = (List<EquipmentTypeEntity>) entityManager.createQuery(SELECT_ALL_ENTITIES_SQL).getResultList();

        entityManager.close();

        return entities;
    }
}
