package me.ilt.basedao;

import java.io.Serializable;

public interface BaseDao<T> {
    public int add(T t);
    public int del(Serializable id);
    public int update(T t);
    
}
