package id.co.mandiri.controller;

import com.maryanto.dimas.plugins.web.commons.ui.datatables.DataTablesRequest;
import com.maryanto.dimas.plugins.web.commons.ui.datatables.DataTablesResponse;
import id.co.mandiri.dto.DeviceLoanStatusDTO;
import id.co.mandiri.dto.DeviceLoanStatusMapperRequestNew;
import id.co.mandiri.dto.DeviceLoanStatusMapperRequestUpdate;
import id.co.mandiri.entity.DeviceLoanStatus;
import id.co.mandiri.service.DeviceLoanStatusService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/master/loan-status-device")
public class DeviceLoanStatusController {

    @Autowired
    private DeviceLoanStatusService service;

    @PostMapping("/datatables")
    public DataTablesResponse<DeviceLoanStatus> datatables(
            @RequestParam(required = false, value = "draw", defaultValue = "0") Long draw,
            @RequestParam(required = false, value = "start", defaultValue = "0") Long start,
            @RequestParam(required = false, value = "length", defaultValue = "10") Long length,
            @RequestParam(required = false, value = "order[0][column]", defaultValue = "0") Long iSortCol0,
            @RequestParam(required = false, value = "order[0][dir]", defaultValue = "asc") String sSortDir0,
            @RequestBody(required = false) DeviceLoanStatus params) {

        if (params == null) params = new DeviceLoanStatus();
        log.info("draw: {}, start: {}, length: {}, type: {}", draw, start, length, params);
        return service.datatables(
                new DataTablesRequest(draw, length, start, sSortDir0, iSortCol0, params)
        );
    }

    @GetMapping("/list")
    public ResponseEntity<List<DeviceLoanStatus>> list() {
        List<DeviceLoanStatus> list = service.findAll();
        if (list.isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        else return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DeviceLoanStatus> findById(@PathVariable("id") String id) {
        DeviceLoanStatus params = service.findId(id);
        if (params != null) {
            return new ResponseEntity<>(params, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new DeviceLoanStatus(), HttpStatus.NO_CONTENT);
        }
    }

    @PostMapping("/")
    public ResponseEntity<DeviceLoanStatus> save(@Valid @RequestBody DeviceLoanStatusDTO.DeviceLoanStatusRequestNewDTO params) {
        DeviceLoanStatus value = DeviceLoanStatusMapperRequestNew.converter.convertToEntity(params);
        value = service.save(value);
        return new ResponseEntity<>(value, HttpStatus.CREATED);
    }

    @PutMapping("/")
    public ResponseEntity<DeviceLoanStatus> update(@Valid @RequestBody DeviceLoanStatusDTO.DeviceLoanStatusRequestUpdateDTO params) {
        DeviceLoanStatus value = DeviceLoanStatusMapperRequestUpdate.converter.convertToEntity(params);
        value = service.save(value);
        return new ResponseEntity<>(value, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DeviceLoanStatus> delete(@PathVariable("id") String id) {
        boolean deleted = service.removeById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
