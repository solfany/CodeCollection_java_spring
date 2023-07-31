package org.cita.board.repository;

import org.cita.board.entity.LikyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LikyRepository extends JpaRepository<LikyEntity, Integer> {

}
