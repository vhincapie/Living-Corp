package co.edu.unbosque.model.persistence;

import java.util.List;

public interface GenericDAO<T, K> {
    T crear(T entidad);
    T leer(K id);
    T actualizar(T entidad);
    void eliminar(T entidad);
    List<T> obtenerTodos();
}
