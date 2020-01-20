package com.diff.test.unit.service;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.diff.data.Diff;
import com.diff.data.DiffComparisonResult;
import com.diff.data.DiffEntry;
import com.diff.data.DiffResult;
import com.diff.data.mapper.JGitEditToDiffMapper;
import com.diff.exception.DiffEntryNotFoundException;
import com.diff.service.DiffComparator;
import com.diff.service.DiffComparatorServiceImpl;
import com.diff.service.DiffDataService;
import com.diff.service.GitDiffComparator;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class DiffComparatorServiceImplTest {


    private static final String ID = "1234";
    private static final String DATA1 = "{\"id\":\"12345\"}";
    private static final String DATA2 = "{\"id\":\"67890\"}";
    private static final String DATA3 = "{[{\"id\"\n:\"67890\"}]}";

    private static final String DATA4 =
            "[{\"id\":\"67890\"},\n" +
            "{\"id\":\"12345\"},\n" +
            "{\"id\":\"1111\"},\n" +
            "{\"id\":\"2222\"}]";

    private static final String DATA5 =
            "[{\"id\":\"67890\"},\n" +
            "{\"id\":\"54321\"},\n" +
            "{\"id\":\"1111\"},\n" +
            "{\"id\":\"33333\"},]";

    private DiffDataService diffDataService = mock(DiffDataService.class);
    private DiffComparator diffComparator = new GitDiffComparator(new JGitEditToDiffMapper());

    private DiffComparatorServiceImpl comparatorService = new DiffComparatorServiceImpl(diffDataService, diffComparator);



    @Test(expected = DiffEntryNotFoundException.class)
    public void shouldThrowDiffEntryNotFoundException(){

        when(diffDataService.findBy(ID)).thenReturn(Optional.ofNullable(null));
        comparatorService.compare(ID);

    }
    @Test
    public void shouldReturnEqualsAsComparisonResult(){

        DiffEntry diffEntry = new DiffEntry();
        diffEntry.setId(ID);
        diffEntry.setLeftData(DATA1);
        diffEntry.setRightData(DATA1);

        when(diffDataService.findBy(ID)).thenReturn(Optional.of(diffEntry));
        DiffComparisonResult result = comparatorService.compare(ID);

        assertEquals(DiffResult.EQUALS, result.getResult());
        assertNull(result.getDiffs());

    }


    @Test
    public void shouldReturnEqualSizeAsComparisonResult(){

        DiffEntry diffEntry = new DiffEntry();
        diffEntry.setId(ID);
        diffEntry.setLeftData(DATA1);
        diffEntry.setRightData(DATA2);

        when(diffDataService.findBy(ID)).thenReturn(Optional.of(diffEntry));
        DiffComparisonResult result = comparatorService.compare(ID);

        assertEquals(DiffResult.EQUAL_SIZE, result.getResult());
        assertNull(result.getDiffs());

    }

    @Test
    public void shouldReturnNotEqualSizeAsComparisonResult(){

        DiffEntry diffEntry = new DiffEntry();
        diffEntry.setId(ID);
        diffEntry.setLeftData(DATA1);
        diffEntry.setRightData(DATA3);

        when(diffDataService.findBy(ID)).thenReturn(Optional.of(diffEntry));
        DiffComparisonResult result = comparatorService.compare(ID);

        assertEquals(DiffResult.NOT_EQUAL_SIZE, result.getResult());
        assertNotNull(result.getDiffs());
        assertEquals(1, result.getDiffs().size());

        Diff diff = result.getDiffs().get(0);
        assertEquals(0 , diff.getLeftOffset().getStart());
        assertEquals(1, diff.getLeftOffset().getEnd());
        assertEquals(1, diff.getLeftOffset().getLength());

        assertEquals(0 , diff.getRightOffset().getStart());
        assertEquals(2, diff.getRightOffset().getEnd());
        assertEquals(2, diff.getRightOffset().getLength());

    }

    @Test
    public void shouldReturnNotEqualSizeOnMultipleDiffAsComparisonResult(){

        DiffEntry diffEntry = new DiffEntry();
        diffEntry.setId(ID);
        diffEntry.setLeftData(DATA4);
        diffEntry.setRightData(DATA5);

        when(diffDataService.findBy(ID)).thenReturn(Optional.of(diffEntry));
        DiffComparisonResult result = comparatorService.compare(ID);

        assertEquals(DiffResult.NOT_EQUAL_SIZE, result.getResult());
        assertNotNull(result.getDiffs());
        assertEquals(2, result.getDiffs().size());

        Diff diff1 = result.getDiffs().get(0);
        assertEquals(1 , diff1.getLeftOffset().getStart());
        assertEquals(2, diff1.getLeftOffset().getEnd());
        assertEquals(1, diff1.getLeftOffset().getLength());

        assertEquals(1 , diff1.getRightOffset().getStart());
        assertEquals(2, diff1.getRightOffset().getEnd());
        assertEquals(1, diff1.getRightOffset().getLength());

        Diff diff2 = result.getDiffs().get(1);
        assertEquals(3 , diff2.getLeftOffset().getStart());
        assertEquals(4, diff2.getLeftOffset().getEnd());
        assertEquals(1, diff2.getLeftOffset().getLength());

        assertEquals(3 , diff2.getRightOffset().getStart());
        assertEquals(4, diff2.getRightOffset().getEnd());
        assertEquals(1, diff2.getRightOffset().getLength());

    }

}
