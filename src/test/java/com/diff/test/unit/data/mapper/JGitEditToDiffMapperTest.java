package com.diff.test.unit.data.mapper;

import static org.junit.Assert.assertEquals;

import com.diff.data.Diff;
import com.diff.data.mapper.JGitEditToDiffMapper;
import org.eclipse.jgit.diff.Edit;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;



@RunWith(MockitoJUnitRunner.class)
public class JGitEditToDiffMapperTest {

    private JGitEditToDiffMapper mapper = new JGitEditToDiffMapper();

    @Test
    public void shouldMapJGitObjectToDiffObject(){

        Edit edit = new Edit(1,2,3,4);
        Diff diff = mapper.apply(edit);

        assertEquals(edit.getBeginA(), diff.getLeftOffset().getStart());
        assertEquals(edit.getEndA(), diff.getLeftOffset().getEnd());
        assertEquals(edit.getLengthA(), diff.getLeftOffset().getLength());

        assertEquals(edit.getBeginB(), diff.getRightOffset().getStart());
        assertEquals(edit.getEndB(), diff.getRightOffset().getEnd());
        assertEquals(edit.getLengthB(), diff.getRightOffset().getLength());

    }


}
