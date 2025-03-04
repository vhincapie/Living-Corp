package co.edu.unbosque.model.persistence;

import co.edu.unbosque.model.entity.ReservaRecurso;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;

@Stateless
public class ReservaRecursoImplDAO implements GenericDAO <ReservaRecurso, Integer> {

    @PersistenceContext(unitName = "LivingCorpPU")
    private EntityManager entityManager;

    @Override
    public ReservaRecurso crear(ReservaRecurso entidad) {
        entityManager.persist(entidad);
        return entidad;
    }

    @Override
    public ReservaRecurso leer(Integer id) {
        return entityManager.find(ReservaRecurso.class,id);
    }

    @Override
    public ReservaRecurso actualizar(ReservaRecurso entidad) {
        entityManager.merge(entidad);
        return entidad;
    }

    @Override
    public void eliminar(ReservaRecurso entidad) {
        entityManager.remove(entidad);
    }

    @Override
    public List<ReservaRecurso> obtenerTodos() {
        return entityManager.createQuery("SELECT r FROM ReservaRecurso r", ReservaRecurso.class).getResultList();
    }
}
