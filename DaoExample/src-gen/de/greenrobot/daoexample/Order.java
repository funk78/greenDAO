package de.greenrobot.daoexample;

import de.greenrobot.daoexample.DaoSession;
import de.greenrobot.dao.DaoException;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit. 
/**
 * Entity mapped to table ORDERS (schema version 3).
 */
public class Order {

    private Long id;
    private java.util.Date date;
    private long customerId;

    /** Used to resolve relations */
    private DaoSession daoSession;

    /** Used for active entity operations. */
    private OrderDao myDao;

    private Customer customer;
    private Long customer__resolvedKey;


    public Order() {
    }

    public Order(Long id) {
        this.id = id;
    }

    public Order(Long id, java.util.Date date, long customerId) {
        this.id = id;
        this.date = date;
        this.customerId = customerId;
    }

    /** called by internal mechanisms, do not call yourself. */
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getOrderDao() : null;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public java.util.Date getDate() {
        return date;
    }

    public void setDate(java.util.Date date) {
        this.date = date;
    }

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    /** To-one relationship, resolved on first access. */
    public Customer getCustomer() {
        if (customer__resolvedKey == null || !customer__resolvedKey.equals(customerId)) {
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            CustomerDao targetDao = daoSession.getCustomerDao();
            customer = targetDao.load(customerId);
            customer__resolvedKey = customerId;
        }
        return customer;
    }

    public void setCustomer(Customer customer) {
        if (customer == null) {
            throw new DaoException("To-one property 'customerId' has not-null constraint; cannot set to-one to null");
        }
        this.customer = customer;
        customerId = customer.getId();
        customer__resolvedKey = customerId;
    }

    /** Convenient call for {@link AbstractDao#delete(Object)}. Entity must attached to an entity context. */
    public void delete() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }    
        myDao.delete(this);
    }

    /** Convenient call for {@link AbstractDao#update(Object)}. Entity must attached to an entity context. */
    public void update() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }    
        myDao.update(this);
    }

    /** Convenient call for {@link AbstractDao#refresh(Object)}. Entity must attached to an entity context. */
    public void refresh() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }    
        myDao.refresh(this);
    }

}
