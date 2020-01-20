package com.diff.data;


import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * The result of the comparison of the left and right pieces
 * of data
 */
@Getter
@Setter
public class DiffComparisonResult {

    /**
     * The result of the comparison
     */
    private DiffResult result;

    /**
     * The list of differences between the pieces of data
     */
    private List<Diff> diffs;
}
