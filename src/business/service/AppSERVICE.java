package business.service;

import java.util.List;

public interface AppSERVICE<T>{
    public int getTotalPage() ;
    public List<T> getDataPag(int page) ;
    public boolean insert(T t) ;
    public boolean update(T t) ;
    public boolean delete(T t) ;
    public T findById(int id) ;


}
