package co.edu.unbosque.model.persistence;

import co.edu.unbosque.model.entity.CitaVisita;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;

@Stateless
public class CitaImplDAO implements GenericDAO<CitaVisita, Integer> {

    @PersistenceContext(unitName = "LivingCorpPU")
    private EntityManager entityManager;

    @Override
    public CitaVisita crear(CitaVisita entidad) {
        entityManager.persist(entidad);
        return entidad;
    }

    @Override
    public CitaVisita leer(Integer id) {
        return entityManager.find(CitaVisita.class, id);
    }

    @Override
    public CitaVisita actualizar(CitaVisita entidad) {
        entityManager.merge(entidad);
        return entidad;
    }

    @Override
    public void eliminar(CitaVisita entidad) {
        entityManager.remove(entidad);
    }

    @Override
    public List<CitaVisita> obtenerTodos() {
        return entityManager.createQuery("SELECT c FROM CitaVisita c", CitaVisita.class).getResultList();
    }
}
