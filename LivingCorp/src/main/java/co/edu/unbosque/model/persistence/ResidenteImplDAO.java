package co.edu.unbosque.model.persistence;

import co.edu.unbosque.model.entity.ResidentePropiedad;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;

@Stateless
public class ResidenteImplDAO implements GenericDAO<ResidentePropiedad, Integer>{

    @PersistenceContext(unitName = "LivingCorpPU")
    private EntityManager entityManager;

    @Override
    public ResidentePropiedad crear(ResidentePropiedad entidad) {
        entityManager.persist(entidad);
        return entidad;
    }

    @Override
    public ResidentePropiedad leer(Integer id) {
        return entityManager.find(ResidentePropiedad.class, id);
    }

    @Override
    public ResidentePropiedad actualizar(ResidentePropiedad entidad) {
        entityManager.merge(entidad);
        return entidad;
    }

    @Override
    public void eliminar(ResidentePropiedad entidad) {
        entityManager.remove(entidad);
    }

    @Override
    public List<ResidentePropiedad> obtenerTodos() {
        return entityManager.createQuery("SELECT r FROM ResidentePropiedad r", ResidentePropiedad.class).getResultList();
    }
}
