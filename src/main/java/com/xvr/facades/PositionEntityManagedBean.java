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

import com.xvr.entities.PositionEntity;

public class PositionEntityManagedBean {
    final public static String SELECT_ALL_ENTITIES_SQL = "SELECT o FROM PositionEntity AS o";

    private PositionEntity myEntity;

    private EntityManagerFactory myEntityManagerFactory;

    private ListDataModel myList;
    private ListDataModel myReferencesEntities; // M-N and M-1 references

    public PositionEntityManagedBean() {
        myEntityManagerFactory = Persistence.createEntityManagerFactory("Persistence.xml");
    }

    private EntityManagerFactory getEntityManagerFactory() {
        return myEntityManagerFactory;
    }

    public PositionEntity getEntity() {
        return myEntity;
    }

    public void setEntity(PositionEntity entity) {
        myEntity = entity;
    }

    // add new PositionEntity
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

        return "positionEntityList";
    }

    // save edited PositionEntity
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
        return "positionEntityList";
    }

    // delete PositionEntity
    public String delete() {
        EntityManager entityManager = getEntityManagerFactory().createEntityManager();
        try {
            entityManager.getTransaction().begin();
            PositionEntity entity = getCurrentEntity();
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

        return "positionEntityList";
    }

    public DataModel getReferencedEntities() {
        return myReferencesEntities;
    }

    public void setReferencedEntities(Collection<PositionEntity> entities) {
        myReferencesEntities = new ListDataModel(new ArrayList<PositionEntity>(entities));
    }

    public String startCreate() {
        myEntity = new PositionEntity();
        return "createPositionEntity";
    }

    public String startView() {
        setEntityFromRequestParam();
        return "viewPositionEntity";
    }

    public String startEdit() {
        setEntityFromRequestParam();
        return "editPositionEntity";
    }

    public PositionEntity getCurrentEntity() {
        PositionEntity entity = getEntityFromRequestParam();

        return entity == null ? myEntity : entity;
    }

    public PositionEntity getEntityFromRequestParam() {
        if (myList == null) return null;

        EntityManager entityManager = getEntityManagerFactory().createEntityManager();
        PositionEntity entity = (PositionEntity) myList.getRowData();
        entity = entityManager.merge(entity);
        entityManager.close();

        return entity;
    }

    public void setEntityFromRequestParam() {
        myEntity = getCurrentEntity();
    }

    public PositionEntity findEntity(int id) {
        EntityManager entityManager = getEntityManagerFactory().createEntityManager();

        PositionEntity entity = entityManager.find(PositionEntity.class, id);

        entityManager.close();

        return entity;
    }

    public DataModel getAllEntities() {
        myList = new ListDataModel(getEntities());

        return myList;
    }

    public SelectItem[] getAllEntitiesAsSelectedItems() {
        List<PositionEntity> entities = getEntities();
        SelectItem selectItems[] = new SelectItem[entities.size()];
        int i = 0;
        for (PositionEntity entity : entities) {
            selectItems[i++] = new SelectItem(entity);
        }
        return selectItems;
    }

    public List<PositionEntity> getEntities() {
        EntityManager entityManager = getEntityManagerFactory().createEntityManager();

        List<PositionEntity> entities = (List<PositionEntity>) entityManager.createQuery(SELECT_ALL_ENTITIES_SQL).getResultList();

        entityManager.close();

        return entities;
    }
}
