package com.diff.service;

import com.diff.data.Diff;
import org.apache.commons.lang3.StringUtils;
import org.eclipse.jgit.diff.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Implementation of a diff comparator.
 * This implementation compares two pieces of data
 * by using the JGit library which implements standard
 * algorithms for text comparison
 */
@Component
public class GitDiffComparator implements DiffComparator {

    /**
     * Used to map a JGit Edit object to a Diff object
     */
    private Function<Edit, Diff> gitEditToDiffMapper;

    @Autowired
    public GitDiffComparator(Function<Edit, Diff> gitEditToDiffMapper){
        this.gitEditToDiffMapper=gitEditToDiffMapper;
    }

    /**
     * Compares two pieces of data by using JGit library and its implementation
     * of the Myers diff algorithm
     * @param left The left piece of data to be compared
     * @param right The right piece of data to be compared
     * @return The list of differences between the two pieces of data
     */
    @Override
    public List<Diff> compare(String left, String right) {
        String cleanedLeft = StringUtils.trimToEmpty(left);
        String cleanedRight = StringUtils.trimToEmpty(right);
        RawText leftRawText = new RawText(cleanedLeft.getBytes(StandardCharsets.UTF_8));
        RawText rightRawText = new RawText(cleanedRight.getBytes(StandardCharsets.UTF_8));
        EditList edits = new EditList();
        edits.addAll(MyersDiff.INSTANCE.diff(RawTextComparator.DEFAULT, leftRawText, rightRawText));
        List<Diff> diffs = edits.stream().map(gitEditToDiffMapper).collect(Collectors.toList());
        return diffs;
    }
}
