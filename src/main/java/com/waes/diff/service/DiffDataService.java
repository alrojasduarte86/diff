package com.waes.diff.service;

import com.waes.diff.data.DiffEntry;
import com.waes.diff.data.DiffSide;

public interface DiffDataService {
    DiffEntry save(String id, DiffSide side, String data) ;
}
