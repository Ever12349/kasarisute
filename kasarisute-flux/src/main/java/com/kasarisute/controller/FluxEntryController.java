package com.kasarisute.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kasarisute.common.RequestData;
import com.kasarisute.common.ResponseData;
import com.kasarisute.entitys.FluxEntry;
import com.kasarisute.json.RequestListEntry;
import com.kasarisute.json.ResponseListEntry;
import com.kasarisute.services.FluxEntryServices;
import com.kasarisute.utils.CommonUtils;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("flux/entry")
public class FluxEntryController {
    @Autowired
    FluxEntryServices fluxEntryServices;

    @GetMapping("list")
    public ResponseEntity<ResponseData<?>> getFluxEntityList(RequestListEntry requestListEntry,
            HttpServletRequest request) {

        HttpHeaders httpHeaders = new HttpHeaders();

        RequestData<RequestListEntry> handlerRequestData = CommonUtils.handlerRequestData(requestListEntry, request);

        return new ResponseEntity<>(
                new ResponseData<ResponseListEntry<FluxEntry>>() {
                    {
                        setData(fluxEntryServices.getFluxEntityList(handlerRequestData));
                    }
                },
                httpHeaders,
                HttpStatus.OK);
    }

    @PostMapping("item")
    public ResponseEntity<ResponseData<FluxEntry>> postFluxEntityItem(@RequestBody FluxEntry entity,
            HttpServletRequest request)
            throws RuntimeException {

        HttpHeaders responseHeaders = new HttpHeaders();
        RequestData<FluxEntry> handlerRequestData = CommonUtils.handlerRequestData(entity, request);

        return new ResponseEntity<ResponseData<FluxEntry>>(
                new ResponseData<FluxEntry>() {
                    {
                        setData(fluxEntryServices.addFluxEntryItem(handlerRequestData));
                    }
                },
                responseHeaders,
                HttpStatus.OK);
    }

}
