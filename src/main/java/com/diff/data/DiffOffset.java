package com.diff.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Represents the offset of a given diff
 */
@Getter
@Setter
@AllArgsConstructor
public class DiffOffset {
    /**
     * The start of the diff
     */
    private int start;

    /**
     * The end of the diff
     */
    private int end;

    /**
     * The length of the diff
     */
    private int length;

}
