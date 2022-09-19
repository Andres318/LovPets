package com.uis.lovpets.repository;

import com.uis.lovpets.model.TipoMascota;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITipoMascotaRepository extends JpaRepository<TipoMascota, Long> {
}
