package co.edu.unbosque.model.persistence;

import co.edu.unbosque.model.entity.Recurso;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;

@Stateless
public class RecursoImplDAO implements GenericDAO<Recurso, Integer> {

    @PersistenceContext(unitName = "LivingCorpPU")
    private EntityManager entityManager;

    @Override
    public Recurso crear(Recurso entidad) {
        entityManager.persist(entidad);
        return entidad;
    }

    @Override
    public Recurso leer(Integer id) {
        return entityManager.find(Recurso.class, id);
    }

    @Override
    public Recurso actualizar(Recurso entidad) {
        entityManager.merge(entidad);
        return entidad;
    }

    @Override
    public void eliminar(Recurso entidad) {
        entityManager.remove(entidad);
    }

    @Override
    public List<Recurso> obtenerTodos() {
        return entityManager.createQuery("SELECT r FROM Recurso r", Recurso.class).getResultList();
    }
}
