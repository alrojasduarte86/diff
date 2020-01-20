package com.diff.service;

import com.diff.data.Diff;
import com.diff.data.DiffSide;
import com.diff.repository.DiffEntryRepository;
import com.diff.data.DiffEntry;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Implementation of a diff data service
 */
@Service
public class DiffDataServiceImpl implements DiffDataService {

    /**
     * Used to get access to the diff entry entities
     */
    @Autowired
    private DiffEntryRepository diffEntryRepository;

    /**
     * Saves a given piece of data as part of a diff entry.
     * @param id The id of the diff entry
     * @param side The side of the piece of data
     * @param data The data to be saved
     * @return
     */
    @Override
    public DiffEntry save(String id, DiffSide side, String data) {
        DiffEntry diffEntry = diffEntryRepository.findById(id).orElseGet(()-> new DiffEntry());
        diffEntry.setId(id);
        if(side.equals(DiffSide.LEFT)){
            diffEntry.setLeftData(data);
        }else if(side.equals(DiffSide.RIGHT)){
            diffEntry.setRightData(data);
        }
        return diffEntryRepository.save(diffEntry);
    }

    /**
     * Finds a diff entry given its id
     * @param id The id of the diff entry to be returned
     * @return
     */
    @Override
    public Optional<DiffEntry> findBy(String id) {
        return diffEntryRepository.findById(id);
    }

}
