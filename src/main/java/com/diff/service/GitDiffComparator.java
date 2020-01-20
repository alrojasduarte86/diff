package com.diff.service;

import com.diff.data.Diff;
import com.diff.data.DiffOffset;
import com.diff.data.mapper.GitEditToDiffMapper;
import org.eclipse.jgit.diff.EditList;
import org.eclipse.jgit.diff.MyersDiff;
import org.eclipse.jgit.diff.RawText;
import org.eclipse.jgit.diff.RawTextComparator;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.Column;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class GitDiffComparator implements DiffComparator {

    private GitEditToDiffMapper gitEditToDiffMapper;

    @Autowired
    public GitDiffComparator(GitEditToDiffMapper gitEditToDiffMapper){
        this.gitEditToDiffMapper=gitEditToDiffMapper;
    }

    @Override
    public List<Diff> compare(String left, String right) {
        RawText leftRawText = new RawText(left.getBytes(StandardCharsets.UTF_8));
        RawText rightRawText = new RawText(right.getBytes(StandardCharsets.UTF_8));
        EditList edits = new EditList();
        edits.addAll(MyersDiff.INSTANCE.diff(RawTextComparator.DEFAULT, leftRawText, rightRawText));
        List<Diff> diffs = edits.stream().map(edit -> gitEditToDiffMapper.apply(edit) ).collect(Collectors.toList());
        return diffs;
    }
}
