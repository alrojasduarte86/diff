package com.diff.service;

import com.diff.data.Diff;

import java.util.List;

/**
 * Defines a comparator between to pieces of data
 */
public interface DiffComparator {
    List<Diff> compare(String left, String right);
}
