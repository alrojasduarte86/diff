package com.diff.repository;

import com.diff.data.DiffEntry;
import org.springframework.data.repository.CrudRepository;

/**
 * Defines a crud repository for the DiffEntry entity
 */
public interface DiffEntryRepository extends CrudRepository<DiffEntry, String> {

}
