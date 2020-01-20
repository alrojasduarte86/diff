package com.diff.data;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Diff {

    private DiffOffset leftOffset;
    private DiffOffset rightOffset;
}