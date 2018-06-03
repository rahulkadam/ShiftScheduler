package com.astro.shiftscheduler.dao.repository;

import com.astro.shiftscheduler.domain.Shift;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShiftRepository extends CrudRepository<Shift , Long> {
}
