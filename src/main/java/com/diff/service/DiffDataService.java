package com.diff.service;

import com.diff.data.DiffSide;
import com.diff.data.DiffEntry;

import java.util.Optional;

public interface DiffDataService {
    DiffEntry save(String id, DiffSide side, String data) ;
    Optional<DiffEntry> findBy(String id);
}
