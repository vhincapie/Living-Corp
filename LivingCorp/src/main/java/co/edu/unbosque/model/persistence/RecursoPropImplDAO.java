package co.edu.unbosque.model.persistence;

import co.edu.unbosque.model.entity.RecursoPropiedad;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;

@Stateless
public class RecursoPropImplDAO implements GenericDAO<RecursoPropiedad, Integer> {

    @PersistenceContext(unitName = "LivingCorpPU")
    private EntityManager entityManager;

    @Override
    public RecursoPropiedad crear(RecursoPropiedad entidad) {
        entityManager.persist(entidad);
        return entidad;
    }

    @Override
    public RecursoPropiedad leer(Integer id) {
        return entityManager.find(RecursoPropiedad.class, id);
    }

    @Override
    public RecursoPropiedad actualizar(RecursoPropiedad entidad) {
        entityManager.merge(entidad);
        return entidad;
    }

    @Override
    public void eliminar(RecursoPropiedad entidad) {
        entityManager.remove(entidad);
    }

    @Override
    public List<RecursoPropiedad> obtenerTodos() {
        return entityManager.createQuery("SELECT r FROM RecursoPropiedad r", RecursoPropiedad.class).getResultList();
    }
}