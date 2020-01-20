package com.diff.service;

import com.diff.data.Diff;

import java.util.List;

public interface DiffComparator {
    List<Diff> compare(String left, String right);
}
