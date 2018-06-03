package com.astro.shiftscheduler.dao.repository;

import com.astro.shiftscheduler.domain.ShiftConfiguration;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShiftConfigurationRepository extends CrudRepository<ShiftConfiguration , Long> {
}
