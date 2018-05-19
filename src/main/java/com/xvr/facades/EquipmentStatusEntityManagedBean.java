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

import com.xvr.entities.EquipmentStatusEntity;

public class EquipmentStatusEntityManagedBean {
    final public static String SELECT_ALL_ENTITIES_SQL = "SELECT o FROM EquipmentStatusEntity AS o";

    private EquipmentStatusEntity myEntity;

    private EntityManagerFactory myEntityManagerFactory;

    private ListDataModel myList;
    private ListDataModel myReferencesEntities; // M-N and M-1 references

    public EquipmentStatusEntityManagedBean() {
        myEntityManagerFactory = Persistence.createEntityManagerFactory("Persistence.xml");
    }

    private EntityManagerFactory getEntityManagerFactory() {
        return myEntityManagerFactory;
    }

    public EquipmentStatusEntity getEntity() {
        return myEntity;
    }

    public void setEntity(EquipmentStatusEntity entity) {
        myEntity = entity;
    }

    // add new EquipmentStatusEntity
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

        return "equipmentStatusEntityList";
    }

    // save edited EquipmentStatusEntity
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
        return "equipmentStatusEntityList";
    }

    // delete EquipmentStatusEntity
    public String delete() {
        EntityManager entityManager = getEntityManagerFactory().createEntityManager();
        try {
            entityManager.getTransaction().begin();
            EquipmentStatusEntity entity = getCurrentEntity();
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

        return "equipmentStatusEntityList";
    }

    public DataModel getReferencedEntities() {
        return myReferencesEntities;
    }

    public void setReferencedEntities(Collection<EquipmentStatusEntity> entities) {
        myReferencesEntities = new ListDataModel(new ArrayList<EquipmentStatusEntity>(entities));
    }

    public String startCreate() {
        myEntity = new EquipmentStatusEntity();
        return "createEquipmentStatusEntity";
    }

    public String startView() {
        setEntityFromRequestParam();
        return "viewEquipmentStatusEntity";
    }

    public String startEdit() {
        setEntityFromRequestParam();
        return "editEquipmentStatusEntity";
    }

    public EquipmentStatusEntity getCurrentEntity() {
        EquipmentStatusEntity entity = getEntityFromRequestParam();

        return entity == null ? myEntity : entity;
    }

    public EquipmentStatusEntity getEntityFromRequestParam() {
        if (myList == null) return null;

        EntityManager entityManager = getEntityManagerFactory().createEntityManager();
        EquipmentStatusEntity entity = (EquipmentStatusEntity) myList.getRowData();
        entity = entityManager.merge(entity);
        entityManager.close();

        return entity;
    }

    public void setEntityFromRequestParam() {
        myEntity = getCurrentEntity();
    }

    public EquipmentStatusEntity findEntity(int id) {
        EntityManager entityManager = getEntityManagerFactory().createEntityManager();

        EquipmentStatusEntity entity = entityManager.find(EquipmentStatusEntity.class, id);

        entityManager.close();

        return entity;
    }

    public DataModel getAllEntities() {
        myList = new ListDataModel(getEntities());

        return myList;
    }

    public SelectItem[] getAllEntitiesAsSelectedItems() {
        List<EquipmentStatusEntity> entities = getEntities();
        SelectItem selectItems[] = new SelectItem[entities.size()];
        int i = 0;
        for (EquipmentStatusEntity entity : entities) {
            selectItems[i++] = new SelectItem(entity);
        }
        return selectItems;
    }

    public List<EquipmentStatusEntity> getEntities() {
        EntityManager entityManager = getEntityManagerFactory().createEntityManager();

        List<EquipmentStatusEntity> entities = (List<EquipmentStatusEntity>) entityManager.createQuery(SELECT_ALL_ENTITIES_SQL).getResultList();

        entityManager.close();

        return entities;
    }
}
