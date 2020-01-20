package com.diff.test.integration;

import com.diff.data.*;
import com.diff.repository.DiffEntryRepository;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DiffGetEndpointTest {

    private static final String ENCODED_DATA1 = "W3siaWQiOiI2Nzg5MCJ9LAp7ImlkIjoiMTIzNDUifSwKeyJpZCI6IjExMTEifSwKeyJpZCI6IjIyMjIifV0=";
    private static final String ENCODED_DATA2 = "W3siaWQiOiI2NjY2NiJ9LAp7ImlkIjoiMTExMTEifSwKeyJpZCI6Ijk5OTkifSwKeyJpZCI6IjAwMDAifV0=";
    private static final String ENCODED_DATA3 = "W3siaWQiOiI2Nzg5MCJ9LAp7ImlkIjoiNTQzMjEifSwKeyJpZCI6IjExMTEifSwKeyJpZCI6IjMzMzMzMyJ9XQ==";

    private static final String PUT_URL = "http://localhost:%1$d/v1/diff/%2$s/%3$s";
    private static final String GET_URL = "http://localhost:%1$d/v1/diff/%2$s";
    private static final String ID = "1234";

    @LocalServerPort
    private int port;

    private TestRestTemplate restTemplate = new TestRestTemplate();

    private HttpHeaders headers = new HttpHeaders();

    @Autowired
    private DiffEntryRepository repository;

    @After
    public void clean(){
        repository.deleteAll();
    }

    @Test
    public void shouldReturnEqualsAsComparisonResult() throws Exception {

        HttpEntity<String> entity = new HttpEntity<String>(ENCODED_DATA1, headers);

        restTemplate.exchange(getPutUrl(ID, DiffSide.LEFT), HttpMethod.PUT, entity, DiffEntry.class);
        restTemplate.exchange(getPutUrl(ID, DiffSide.RIGHT), HttpMethod.PUT, entity, DiffEntry.class);

        ResponseEntity<DiffComparisonResult> response = restTemplate.exchange(getGetUrl(ID), HttpMethod.GET, null, DiffComparisonResult.class);

        DiffComparisonResult result = response.getBody();

        assertEquals(DiffResult.EQUALS, result.getResult());
        assertNull(result.getDiffs());

    }

    @Test
    public void shouldReturnEqualsSizeAsComparisonResult() throws Exception {

        restTemplate.exchange(getPutUrl(ID, DiffSide.LEFT), HttpMethod.PUT,  new HttpEntity<String>(ENCODED_DATA1, headers), DiffEntry.class);
        restTemplate.exchange(getPutUrl(ID, DiffSide.RIGHT), HttpMethod.PUT, new HttpEntity<String>(ENCODED_DATA2, headers), DiffEntry.class);

        ResponseEntity<DiffComparisonResult> response = restTemplate.exchange(getGetUrl(ID), HttpMethod.GET, null, DiffComparisonResult.class);

        DiffComparisonResult result = response.getBody();

        assertEquals(DiffResult.EQUAL_SIZE, result.getResult());
        assertNull(result.getDiffs());

    }

    @Test
    public void shouldReturnNotEqualsSizeAsComparisonResult() throws Exception {

        restTemplate.exchange(getPutUrl(ID, DiffSide.LEFT), HttpMethod.PUT,  new HttpEntity<String>(ENCODED_DATA1, headers), DiffEntry.class);
        restTemplate.exchange(getPutUrl(ID, DiffSide.RIGHT), HttpMethod.PUT, new HttpEntity<String>(ENCODED_DATA3, headers), DiffEntry.class);

        ResponseEntity<DiffComparisonResult> response = restTemplate.exchange(getGetUrl(ID), HttpMethod.GET, null, DiffComparisonResult.class);

        DiffComparisonResult result = response.getBody();

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


    private String getPutUrl(String id, DiffSide side) {
        return String.format(PUT_URL, port, id, side.name());
    }

    private String getGetUrl(String id) {
        return String.format(GET_URL, port, id);
    }

}
