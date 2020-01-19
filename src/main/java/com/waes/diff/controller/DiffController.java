package com.waes.diff.controller;

import com.waes.diff.controller.converter.BodyConverter;
import com.waes.diff.data.DiffEntry;
import com.waes.diff.data.DiffSide;
import com.waes.diff.service.DiffDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DiffController {

    @Autowired
    private BodyConverter<String, String> bodyConverter;

    @Autowired
    private DiffDataService diffDataService;

    @PutMapping("/v1/diff/{id}/{side}")
    public DiffEntry put(@PathVariable String id, @PathVariable DiffSide side, @RequestBody String encodedData){
        String decodedData = bodyConverter.convert(encodedData);
        DiffEntry diffEnty = diffDataService.save(id, side, decodedData);
        return diffEnty;
    }

}