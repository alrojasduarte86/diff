package com.diff.data;


import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class DiffComparisonResult {

    private DiffResult result;
    private List<Diff> diffs;
}
