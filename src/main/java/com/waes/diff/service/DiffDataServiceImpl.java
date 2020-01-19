package com.waes.diff.service;

import com.waes.diff.data.DiffEntry;
import com.waes.diff.data.DiffSide;
import com.waes.diff.repository.DiffEntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DiffDataServiceImpl implements DiffDataService {

    @Autowired
    private DiffEntryRepository diffEntryRepository;

    @Override
    public DiffEntry save(String id, DiffSide side, String data) {
        DiffEntry diffEntry = getEntry(id);
        diffEntry.setId(id);
        if(side.equals(DiffSide.LEFT)){
            diffEntry.setLeftData(data);
        }else if(side.equals(DiffSide.RIGHT)){
            diffEntry.setRightData(data);
        }
        return diffEntryRepository.save(diffEntry);
    }

    private DiffEntry getEntry(String id) {
        Optional<DiffEntry> diffEntry = diffEntryRepository.findById(id);
        if(diffEntry.isPresent()){
            return diffEntry.get();
        }
        return new DiffEntry();
    }
}
