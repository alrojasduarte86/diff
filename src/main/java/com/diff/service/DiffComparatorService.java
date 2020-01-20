package com.diff.service;

import com.diff.data.DiffComparisonResult;

/**
 * Defines a service that runs the comparison
 * between to pieces of data
 */
public interface DiffComparatorService {
    DiffComparisonResult compare(String id);
}
