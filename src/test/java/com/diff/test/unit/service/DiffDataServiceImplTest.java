package com.diff.test.unit.service;

import com.diff.data.DiffEntry;
import com.diff.data.DiffSide;
import com.diff.repository.DiffEntryRepository;
import com.diff.service.DiffDataServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.junit.Assert.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DiffDataServiceImplTest {

    private static final String ID = "1234";
    private static final String DATA1 =
            "[{\"id\":\"67890\"},\n" +
            "{\"id\":\"12345\"},\n" +
            "{\"id\":\"1111\"},\n" +
            "{\"id\":\"2222\"}]";
    private static final String DATA2 =
            "[{\"id\":\"67890\"},\n" +
            "{\"id\":\"6666\"},\n" +
            "{\"id\":\"1111\"},\n" +
            "{\"id\":\"2222\"}]";
    private DiffEntryRepository diffEntryRepository = mock(DiffEntryRepository.class);

    private DiffDataServiceImpl dataService = new DiffDataServiceImpl(diffEntryRepository);

    @Test
    public void shouldSaveDiffEntryWhenItDoesNotExist(){
        when(diffEntryRepository.findById(ID)).thenReturn(Optional.ofNullable(null));
        when(diffEntryRepository.save(any(DiffEntry.class))).thenAnswer(call->call.getArgument(0));
        DiffEntry savedEntity = dataService.save(ID, DiffSide.LEFT, DATA1);

        assertEquals(ID, savedEntity.getId());
        assertEquals(DATA1, savedEntity.getLeftData());
        assertNull(savedEntity.getRightData());

    }

    @Test
    public void shouldSaveDiffEntryWhenItDoesExist(){
        DiffEntry existingEntity = new DiffEntry();
        existingEntity.setId(ID);
        existingEntity.setLeftData(DATA1);

        when(diffEntryRepository.findById(ID)).thenReturn(Optional.of(existingEntity));
        when(diffEntryRepository.save(any(DiffEntry.class))).thenAnswer(call->call.getArgument(0));
        DiffEntry savedEntity = dataService.save(ID, DiffSide.RIGHT, DATA2);

        assertEquals(ID, savedEntity.getId());
        assertEquals(DATA1, savedEntity.getLeftData());
        assertEquals(DATA2, savedEntity.getRightData());

    }

}
