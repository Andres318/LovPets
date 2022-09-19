package com.uis.lovpets.repository;

import com.uis.lovpets.model.Publicacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPublicacionRepository extends JpaRepository<Publicacion, Long> {
}
