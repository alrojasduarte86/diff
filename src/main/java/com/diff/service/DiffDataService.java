package com.diff.service;

import com.diff.data.DiffSide;
import com.diff.data.DiffEntry;

import java.util.Optional;

/**
 * Defines a data service to get access to DiffEntry entities
 */
public interface DiffDataService {
    /**
     * Saves a given diff entry
     * @param id The id of the diff entry
     * @param side The side of the piece of data
     * @param data The data to be saved
     * @return
     */
    DiffEntry save(String id, DiffSide side, String data) ;

    /**
     * Returns a diff entry given its id
     * @param id
     * @return
     */
    Optional<DiffEntry> findBy(String id);
}
