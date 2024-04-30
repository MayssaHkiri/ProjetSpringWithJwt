package com.example.spring.camping.services;

import java.util.ArrayList;
import java.util.List;

public interface ICrud<T> {
    List<T> getAll();

    T getById (long id);
    T  add(T t);

    void delete(long id);
    T update(T t);
}
