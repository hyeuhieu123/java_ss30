package business.dao;

import java.util.List;

public interface AppDAO <T>{
    public int getTotalPage() ;
    public List<T> getDataPag(int page) ;
    public boolean insert(T t) ;
    public boolean update(T t) ;
    public boolean delete(T t) ;
    public T findById(int id) ;

}
