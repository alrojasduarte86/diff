package com.diff.data;

/**
 * The type of the result of a comparison between
 * to two pieces of data
 */
public enum DiffResult {
    EQUALS,     /**Returned when the pieces of data are the same*/
    NOT_EQUAL_SIZE,  /**Returned when the pieces of data do not have the same size*/
    EQUAL_SIZE  /**Returned when the the pieces of data have the same size*/
}
