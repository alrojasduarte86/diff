package com.diff.service;

import com.diff.data.Diff;
import com.diff.data.DiffOffset;
import com.diff.data.mapper.GitEditToDiffMapper;
import org.eclipse.jgit.diff.*;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.Column;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class GitDiffComparator implements DiffComparator {

    private Function<Edit, Diff> gitEditToDiffMapper;

    @Autowired
    public GitDiffComparator(Function<Edit, Diff> gitEditToDiffMapper){
        this.gitEditToDiffMapper=gitEditToDiffMapper;
    }

    @Override
    public List<Diff> compare(String left, String right) {
        RawText leftRawText = new RawText(left.getBytes(StandardCharsets.UTF_8));
        RawText rightRawText = new RawText(right.getBytes(StandardCharsets.UTF_8));
        EditList edits = new EditList();
        edits.addAll(MyersDiff.INSTANCE.diff(RawTextComparator.DEFAULT, leftRawText, rightRawText));
        List<Diff> diffs = edits.stream().map(gitEditToDiffMapper).collect(Collectors.toList());
        return diffs;
    }
}
