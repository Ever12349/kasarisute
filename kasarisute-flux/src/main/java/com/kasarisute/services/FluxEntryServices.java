package com.kasarisute.services;

import com.kasarisute.common.RequestData;
import com.kasarisute.entitys.FluxEntry;
import com.kasarisute.json.RequestListEntry;
import com.kasarisute.json.ResponseListEntry;

public interface FluxEntryServices {
    public FluxEntry addFluxEntryItem(RequestData<FluxEntry> entry);

    public ResponseListEntry<FluxEntry> getFluxEntityList(RequestData<RequestListEntry> requestData);
}
