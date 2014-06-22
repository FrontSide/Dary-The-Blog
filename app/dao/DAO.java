package dao;
import java.util.List;
import play.db.ebean.Model;

public interface DAO<E> {

    public void create(E model);
    public void deleteById(Long id);
    public E getById(Long id);
    public List<E> getAll();

}