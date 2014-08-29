package dao;

import play.Logger;

import factories.*;
import java.util.List;
import java.lang.UnsupportedOperationException;
import com.avaje.ebean.Ebean; 
import play.db.ebean.Model.*;
import models.BlogEntity;

public abstract class DAOImpl<T extends BlogEntity> implements DAO<T> {

    protected final static Logger.ALogger logger = Logger.of(DAOImpl.class);

    protected Finder<Long, T> find;
    public DAO setFind(Finder<Long, T> find) {        
        this.find = find;
        return this;
    }

    public void create(T model) {
        Ebean.save(model);
    }
    
    public void deleteById(Long id) {
        throw new UnsupportedOperationException("Object cannot be deleted");
    }
    
    public void update(T model) {
        throw new UnsupportedOperationException("Object cannot be updated");
    }
    
    public T getById(Long id) {
        return this.find.where().eq("id", id).findUnique();
    }
    
    public List<T> getAll() {
        throw new UnsupportedOperationException("Cannot get all objects");
    }

}
