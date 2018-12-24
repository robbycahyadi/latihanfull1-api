package id.co.mandiri.controller;

import com.maryanto.dimas.plugins.web.commons.ui.datatables.DataTablesRequest;
import com.maryanto.dimas.plugins.web.commons.ui.datatables.DataTablesResponse;
import id.co.mandiri.entity.CategoryDevice;
import id.co.mandiri.service.CategoryDeviceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/master/category-device")
public class CategoryDeviceController {

    @Autowired
    private CategoryDeviceService service;

    @PostMapping("/datatables")
    public DataTablesResponse<CategoryDevice> datatables(
            @RequestParam(required = false, value = "draw", defaultValue = "0") Long draw,
            @RequestParam(required = false, value = "start", defaultValue = "0") Long start,
            @RequestParam(required = false, value = "length", defaultValue = "10") Long length,
            @RequestParam(required = false, value = "order[0][column]", defaultValue = "0") Long iSortCol0,
            @RequestParam(required = false, value = "order[0][dir]", defaultValue = "asc") String sSortDir0,
            @RequestBody(required = false) CategoryDevice params) {

        if (params == null) params = new CategoryDevice();
        log.info("draw: {}, start: {}, length: {}, type: {}", draw, start, length, params);
        return service.datatables(
                new DataTablesRequest(
                        draw,
                        length,
                        start,
                        sSortDir0,
                        iSortCol0,
                        params)
        );
    }

}
