package com.diff.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class DiffOffset {
    private int start;
    private int end;
    private int length;

}
