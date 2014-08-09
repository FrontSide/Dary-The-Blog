package dao;
import java.util.List;

public interface DAO<E> {

    public void create(E model);
    public void deleteById(Long id);
    public E getById(Long id);
    public List<E> getAll();

}