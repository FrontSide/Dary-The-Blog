package dao;
import java.util.List;
import play.db.ebean.Model;

public interface DAO {

    public void create(Model model);
    public void deleteById(Long id);
    public Model getById(Long id);
    public List<Model> getAll();

}