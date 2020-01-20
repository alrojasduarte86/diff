package com.diff.service;

import com.diff.data.Diff;
import com.diff.exception.DiffEntryNotFoundException;
import com.diff.data.DiffComparisonResult;
import com.diff.data.DiffEntry;
import com.diff.data.DiffResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implementation of a diff comparator
 */
@Service
public class DiffComparatorServiceImpl implements DiffComparatorService {

    /**
     * Used to retrieve a given diff entry
     */
    private DiffDataService diffDataService;

    /**
     * Used to get the diffs between to pieces of data
     */
    private DiffComparator diffComparator;

    @Autowired
    public DiffComparatorServiceImpl(DiffDataService diffDataService, DiffComparator diffComparator ){
        this.diffDataService = diffDataService;
        this.diffComparator = diffComparator;
    }

    /**
     * Runs a comparison of two pieces of data by retrieving
     * the diff entry associated with them and testing:
     * 1. If the pieces of data are the same
     * 2. The pieces of data have the same size
     * 3. The pieces of data do not have the same size and have diffs
     * @param id
     * @return
     */
    @Override
    public DiffComparisonResult compare(String id) {
        DiffEntry diffEntry = diffDataService.findBy(id).orElseThrow(()-> new DiffEntryNotFoundException(String.format("%s could not be found", id)));
        DiffComparisonResult diffComparisonResult = new DiffComparisonResult();
        if(diffEntry.getLeftData().equals(diffEntry.getRightData())){
            diffComparisonResult.setResult(DiffResult.EQUALS);
        }else if(diffEntry.getLeftData().length()==diffEntry.getRightData().length()){
            diffComparisonResult.setResult(DiffResult.EQUAL_SIZE);
        }else{
            diffComparisonResult.setResult(DiffResult.NOT_EQUAL_SIZE);
            List<Diff> diffs = diffComparator.compare(diffEntry.getLeftData(), diffEntry.getRightData());
            diffComparisonResult.setDiffs(diffs);
        }
        return diffComparisonResult;
    }
}
