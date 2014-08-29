
package dao;
import models.BlogEntity;
import java.util.List;
import play.db.ebean.Model.*;

/* Implemented by DAOImpl 
   Main Purpose: Typing */
public interface DAO<T extends BlogEntity> {

    public DAO setFind(Finder<Long, T> find);

    void create(T model);
    void deleteById(Long id);
    void update(T model);
    BlogEntity getById(Long id);
    List<T> getAll();

}
