package com.kasarisute.services;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.kasarisute.common.RequestData;
import com.kasarisute.entitys.FluxEntry;
import com.kasarisute.json.RequestListEntry;
import com.kasarisute.json.ResponseListEntry;
import com.kasarisute.repositorites.FluxEntryRepositority;
import com.kasarisute.utils.NumberHandler;

import jakarta.persistence.criteria.Path;

@Service
public class FluxEntryServicesImp implements FluxEntryServices {
    @Autowired

    FluxEntryRepositority fluxEntryRepositority;

    @Override
    public FluxEntry addFluxEntryItem(RequestData<FluxEntry> entry) {
        FluxEntry reqData = entry.getReqData();
        reqData.setAmountNum(NumberHandler.formatMoney(reqData.getAmount()));

        FluxEntry fluxEntryRequestData = fluxEntryRepositority.save(reqData);
        BeanUtils.copyProperties(fluxEntryRequestData, reqData);

        return reqData;
    }

    @Override
    public ResponseListEntry<FluxEntry> getFluxEntityList(RequestData<RequestListEntry> requestData) {
        ResponseListEntry<FluxEntry> responseListEntry = new ResponseListEntry<>() {
            {
                RequestListEntry reqData = requestData.getReqData();
                Page<FluxEntry> all = fluxEntryRepositority.findAll(
                        (root, query, cb) -> {
                            Path<String> createBy = root.get("createBy");
                            return cb.equal(createBy, requestData.getUid());
                        }, PageRequest.of(
                                reqData.getPageNo(),
                                reqData.getPageSize(),
                                Sort.by(Sort.Direction.DESC, "createdDate")));

                setTotal(all.getTotalElements());
                setPageSize(all.getSize());
                setPageNo(all.getNumber());
                setList(all.getContent());
                setTotalPage(all.getTotalPages());
                setHasNext(!all.isLast());
            }
        };

        return responseListEntry;
    }

}
