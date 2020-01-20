package com.diff.test.integration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import com.diff.data.DiffEntry;
import com.diff.data.DiffSide;
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

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DiffPutEndpointTest {

    private static final String ENCODED_DATA1 = "W3siaWQiOiI2Nzg5MCJ9LAp7ImlkIjoiMTIzNDUifSwKeyJpZCI6IjExMTEifSwKeyJpZCI6IjIyMjIifV0=";
    private static final String DECODED_DATA1 =
            "[{\"id\":\"67890\"},\n" +
            "{\"id\":\"12345\"},\n" +
            "{\"id\":\"1111\"},\n" +
            "{\"id\":\"2222\"}]";

    private static final String PUT_URL = "http://localhost:%1$d/v1/diff/%2$s/%3$s";

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
    public void shouldPutLeftData() throws Exception {

        HttpEntity<String> entity = new HttpEntity<String>(ENCODED_DATA1, headers);

        ResponseEntity<DiffEntry> response = restTemplate.exchange(getEndpointUrl(ID, DiffSide.LEFT), HttpMethod.PUT, entity, DiffEntry.class);

        DiffEntry diffEntry = response.getBody();

        assertEquals(ID, diffEntry.getId());
        assertEquals(DECODED_DATA1, diffEntry.getLeftData());
        assertNull(diffEntry.getRightData());

    }

    @Test
    public void shouldPutRightData() throws Exception {

        HttpEntity<String> entity = new HttpEntity<String>(ENCODED_DATA1, headers);

        ResponseEntity<DiffEntry> response = restTemplate.exchange(getEndpointUrl(ID, DiffSide.RIGHT), HttpMethod.PUT, entity, DiffEntry.class);

        DiffEntry diffEntry = response.getBody();

        assertEquals(ID, diffEntry.getId());
        assertEquals(DECODED_DATA1, diffEntry.getRightData());
        assertNull(diffEntry.getLeftData());

    }

    @Test
    public void shouldPutLeftAndRightData() throws Exception {

        HttpEntity<String> entity = new HttpEntity<String>(ENCODED_DATA1, headers);

        restTemplate.exchange(getEndpointUrl(ID, DiffSide.LEFT), HttpMethod.PUT, entity, DiffEntry.class);
        ResponseEntity<DiffEntry> response = restTemplate.exchange(getEndpointUrl(ID, DiffSide.RIGHT), HttpMethod.PUT, entity, DiffEntry.class);

        DiffEntry diffEntry = response.getBody();

        assertEquals(ID, diffEntry.getId());
        assertEquals(DECODED_DATA1, diffEntry.getLeftData());
        assertEquals(DECODED_DATA1, diffEntry.getRightData());

    }

    private String getEndpointUrl(String id, DiffSide side) {
       return String.format(PUT_URL, port, id, side.name());
    }

}