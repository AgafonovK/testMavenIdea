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

import com.xvr.entities.EquipmentEntity;

public class EquipmentEntityManagedBean {
    final public static String SELECT_ALL_ENTITIES_SQL = "SELECT o FROM EquipmentEntity AS o";

    private EquipmentEntity myEntity;

    private EntityManagerFactory myEntityManagerFactory;

    private ListDataModel myList;
    private ListDataModel myReferencesEntities; // M-N and M-1 references

    public EquipmentEntityManagedBean() {
        myEntityManagerFactory = Persistence.createEntityManagerFactory("Persistence.xml");
    }

    private EntityManagerFactory getEntityManagerFactory() {
        return myEntityManagerFactory;
    }

    public EquipmentEntity getEntity() {
        return myEntity;
    }

    public void setEntity(EquipmentEntity entity) {
        myEntity = entity;
    }

    // add new EquipmentEntity
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

        return "equipmentEntityList";
    }

    // save edited EquipmentEntity
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
        return "equipmentEntityList";
    }

    // delete EquipmentEntity
    public String delete() {
        EntityManager entityManager = getEntityManagerFactory().createEntityManager();
        try {
            entityManager.getTransaction().begin();
            EquipmentEntity entity = getCurrentEntity();
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

        return "equipmentEntityList";
    }

    public DataModel getReferencedEntities() {
        return myReferencesEntities;
    }

    public void setReferencedEntities(Collection<EquipmentEntity> entities) {
        myReferencesEntities = new ListDataModel(new ArrayList<EquipmentEntity>(entities));
    }

    public String startCreate() {
        myEntity = new EquipmentEntity();
        return "createEquipmentEntity";
    }

    public String startView() {
        setEntityFromRequestParam();
        return "viewEquipmentEntity";
    }

    public String startEdit() {
        setEntityFromRequestParam();
        return "editEquipmentEntity";
    }

    public EquipmentEntity getCurrentEntity() {
        EquipmentEntity entity = getEntityFromRequestParam();

        return entity == null ? myEntity : entity;
    }

    public EquipmentEntity getEntityFromRequestParam() {
        if (myList == null) return null;

        EntityManager entityManager = getEntityManagerFactory().createEntityManager();
        EquipmentEntity entity = (EquipmentEntity) myList.getRowData();
        entity = entityManager.merge(entity);
        entityManager.close();

        return entity;
    }

    public void setEntityFromRequestParam() {
        myEntity = getCurrentEntity();
    }

    public EquipmentEntity findEntity(int id) {
        EntityManager entityManager = getEntityManagerFactory().createEntityManager();

        EquipmentEntity entity = entityManager.find(EquipmentEntity.class, id);

        entityManager.close();

        return entity;
    }

    public DataModel getAllEntities() {
        myList = new ListDataModel(getEntities());

        return myList;
    }

    public SelectItem[] getAllEntitiesAsSelectedItems() {
        List<EquipmentEntity> entities = getEntities();
        SelectItem selectItems[] = new SelectItem[entities.size()];
        int i = 0;
        for (EquipmentEntity entity : entities) {
            selectItems[i++] = new SelectItem(entity);
        }
        return selectItems;
    }

    public List<EquipmentEntity> getEntities() {
        EntityManager entityManager = getEntityManagerFactory().createEntityManager();

        List<EquipmentEntity> entities = (List<EquipmentEntity>) entityManager.createQuery(SELECT_ALL_ENTITIES_SQL).getResultList();

        entityManager.close();

        return entities;
    }
}
