package com.diff.data.mapper;

import com.diff.data.Diff;
import com.diff.data.DiffOffset;
import org.eclipse.jgit.diff.Edit;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class GitEditToDiffMapper implements Function<Edit, Diff> {
    @Override
    public Diff apply(Edit edit) {
        Diff diff = new Diff();

        DiffOffset leftOffset = new DiffOffset(edit.getBeginA(), edit.getEndA(), edit.getLengthA());
        diff.setLeftOffset(leftOffset);

        DiffOffset rightOffset = new DiffOffset(edit.getBeginA(), edit.getEndA(), edit.getLengthA());
        diff.setRightOffset(rightOffset);

        return diff;
    }
}
