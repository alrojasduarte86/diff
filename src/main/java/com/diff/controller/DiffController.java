package com.diff.controller;

import com.diff.controller.converter.BodyConverter;
import com.diff.data.DiffComparisonResult;
import com.diff.data.DiffSide;
import com.diff.service.DiffComparatorService;
import com.diff.service.DiffComparatorServiceImpl;
import com.diff.service.DiffDataService;
import com.diff.data.DiffEntry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * HTTP Controller of the application
 */
@RestController
public class DiffController {

    @Autowired
    private BodyConverter<String, String> bodyConverter;

    @Autowired
    private DiffDataService diffDataService;

    @Autowired
    private DiffComparatorService diffComparatorService;

    /**
     * Used to process the upload of the data to be compared
     * @param id The id of the data
     * @param side The side of the data (left|right)
     * @param encodedData The base 64 representation of the data
     * @return
     */
    @PutMapping("/v1/diff/{id}/{side}")
    public DiffEntry put(@PathVariable String id, @PathVariable DiffSide side, @RequestBody String encodedData){
        String decodedData = bodyConverter.convert(encodedData);
        DiffEntry diffEnty = diffDataService.save(id, side, decodedData);
        return diffEnty;
    }


    /**
     * Compares the left and right versions of a given piece of data
     * @param id The id of the data to be compared
     * @return The result of the comparison
     */
    @GetMapping("/v1/diff/{id}")
    public DiffComparisonResult compare(@PathVariable String id){
        return diffComparatorService.compare(id);
    }

}