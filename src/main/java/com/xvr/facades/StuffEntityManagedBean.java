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

import com.xvr.entities.StuffEntity;

public class StuffEntityManagedBean {
    final public static String SELECT_ALL_ENTITIES_SQL = "SELECT o FROM StuffEntity AS o";

    private StuffEntity myEntity;

    private EntityManagerFactory myEntityManagerFactory;

    private ListDataModel myList;
    private ListDataModel myReferencesEntities; // M-N and M-1 references

    public StuffEntityManagedBean() {
        myEntityManagerFactory = Persistence.createEntityManagerFactory("Persistence.xml");
    }

    private EntityManagerFactory getEntityManagerFactory() {
        return myEntityManagerFactory;
    }

    public StuffEntity getEntity() {
        return myEntity;
    }

    public void setEntity(StuffEntity entity) {
        myEntity = entity;
    }

    // add new StuffEntity
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

        return "stuffEntityList";
    }

    // save edited StuffEntity
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
        return "stuffEntityList";
    }

    // delete StuffEntity
    public String delete() {
        EntityManager entityManager = getEntityManagerFactory().createEntityManager();
        try {
            entityManager.getTransaction().begin();
            StuffEntity entity = getCurrentEntity();
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

        return "stuffEntityList";
    }

    public DataModel getReferencedEntities() {
        return myReferencesEntities;
    }

    public void setReferencedEntities(Collection<StuffEntity> entities) {
        myReferencesEntities = new ListDataModel(new ArrayList<StuffEntity>(entities));
    }

    public String startCreate() {
        myEntity = new StuffEntity();
        return "createStuffEntity";
    }

    public String startView() {
        setEntityFromRequestParam();
        return "viewStuffEntity";
    }

    public String startEdit() {
        setEntityFromRequestParam();
        return "editStuffEntity";
    }

    public StuffEntity getCurrentEntity() {
        StuffEntity entity = getEntityFromRequestParam();

        return entity == null ? myEntity : entity;
    }

    public StuffEntity getEntityFromRequestParam() {
        if (myList == null) return null;

        EntityManager entityManager = getEntityManagerFactory().createEntityManager();
        StuffEntity entity = (StuffEntity) myList.getRowData();
        entity = entityManager.merge(entity);
        entityManager.close();

        return entity;
    }

    public void setEntityFromRequestParam() {
        myEntity = getCurrentEntity();
    }

    public StuffEntity findEntity(int id) {
        EntityManager entityManager = getEntityManagerFactory().createEntityManager();

        StuffEntity entity = entityManager.find(StuffEntity.class, id);

        entityManager.close();

        return entity;
    }

    public DataModel getAllEntities() {
        myList = new ListDataModel(getEntities());

        return myList;
    }

    public SelectItem[] getAllEntitiesAsSelectedItems() {
        List<StuffEntity> entities = getEntities();
        SelectItem selectItems[] = new SelectItem[entities.size()];
        int i = 0;
        for (StuffEntity entity : entities) {
            selectItems[i++] = new SelectItem(entity);
        }
        return selectItems;
    }

    public List<StuffEntity> getEntities() {
        EntityManager entityManager = getEntityManagerFactory().createEntityManager();

        List<StuffEntity> entities = (List<StuffEntity>) entityManager.createQuery(SELECT_ALL_ENTITIES_SQL).getResultList();

        entityManager.close();

        return entities;
    }
}
