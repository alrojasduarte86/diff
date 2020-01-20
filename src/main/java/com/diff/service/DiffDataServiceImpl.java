package com.diff.service;

import com.diff.data.DiffSide;
import com.diff.repository.DiffEntryRepository;
import com.diff.data.DiffEntry;
import org.apache.commons.lang3.StringUtils;
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

    @Override
    public Optional<DiffEntry> findBy(String id) {
        return diffEntryRepository.findById(id);
    }

    private DiffEntry getEntry(String id) {
        Optional<DiffEntry> diffEntry = diffEntryRepository.findById(id);
        if(diffEntry.isPresent()){
            return diffEntry.get();
        }
        return new DiffEntry();
    }
}
