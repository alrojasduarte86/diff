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
        String cleanedData = StringUtils.trimToEmpty(data);
        if(side.equals(DiffSide.LEFT)){
            diffEntry.setLeftData(cleanedData);
        }else if(side.equals(DiffSide.RIGHT)){
            diffEntry.setRightData(cleanedData);
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
