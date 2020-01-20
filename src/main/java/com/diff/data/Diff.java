package com.diff.data;

import lombok.Getter;
import lombok.Setter;

/**
 * Represents a diff between the left and right pieces
 * of data to be compared
 */
@Getter
@Setter
public class Diff {

    /**
     * Offset of the diff the left piece of data
     */
    private DiffOffset leftOffset;

    /**
     * Offset of the diff the right piece of data
     */
    private DiffOffset rightOffset;
}