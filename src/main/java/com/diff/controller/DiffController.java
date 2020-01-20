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

@RestController
public class DiffController {

    @Autowired
    private BodyConverter<String, String> bodyConverter;

    @Autowired
    private DiffDataService diffDataService;

    @Autowired
    private DiffComparatorService diffComparatorService;

    @PutMapping("/v1/diff/{id}/{side}")
    public DiffEntry put(@PathVariable String id, @PathVariable DiffSide side, @RequestBody String encodedData){
        String decodedData = bodyConverter.convert(encodedData);
        DiffEntry diffEnty = diffDataService.save(id, side, decodedData);
        return diffEnty;
    }

    @GetMapping("/v1/diff/{id}")
    public DiffComparisonResult put(@PathVariable String id){
        return diffComparatorService.compare(id);
    }

}