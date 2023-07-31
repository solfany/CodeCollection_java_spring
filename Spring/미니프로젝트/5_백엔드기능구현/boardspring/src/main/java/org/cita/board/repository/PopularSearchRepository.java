package org.cita.board.repository;

import java.util.List;

import org.cita.board.entity.PopularSearchEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PopularSearchRepository extends JpaRepository<PopularSearchEntity, String> {

	public List<PopularSearchEntity> findTop10ByOrderByPopularSearchCountDesc();
}
