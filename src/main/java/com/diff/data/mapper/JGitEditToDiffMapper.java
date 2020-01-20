package com.diff.data.mapper;

import com.diff.data.Diff;
import com.diff.data.DiffOffset;
import org.eclipse.jgit.diff.Edit;
import org.springframework.stereotype.Component;

import java.util.function.Function;

/**
 * Mapper from a jgit edit objet to a Diff object
 */
@Component
public class JGitEditToDiffMapper implements Function<Edit, Diff> {
    @Override
    public Diff apply(Edit edit) {
        Diff diff = new Diff();

        DiffOffset leftOffset = new DiffOffset(edit.getBeginA(), edit.getEndA(), edit.getLengthA());
        diff.setLeftOffset(leftOffset);

        DiffOffset rightOffset = new DiffOffset(edit.getBeginB(), edit.getEndB(), edit.getLengthB());
        diff.setRightOffset(rightOffset);

        return diff;
    }
}
