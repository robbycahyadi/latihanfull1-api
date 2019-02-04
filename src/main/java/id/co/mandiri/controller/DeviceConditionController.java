package id.co.mandiri.controller;

import com.maryanto.dimas.plugins.web.commons.ui.datatables.DataTablesRequest;
import com.maryanto.dimas.plugins.web.commons.ui.datatables.DataTablesResponse;
import id.co.mandiri.dto.DeviceConditionDTO;
import id.co.mandiri.dto.DeviceConditionMapperRequestNew;
import id.co.mandiri.dto.DeviceConditionMapperRequestUpdate;
import id.co.mandiri.entity.DeviceCondition;
import id.co.mandiri.service.DeviceConditionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/master/condition-device")
public class DeviceConditionController {

    @Autowired
    private DeviceConditionService service;

    @PostMapping("/datatables")
    public DataTablesResponse<DeviceCondition> datatables(
            @RequestParam(required = false, value = "draw", defaultValue = "0") Long draw,
            @RequestParam(required = false, value = "start", defaultValue = "0") Long start,
            @RequestParam(required = false, value = "length", defaultValue = "10") Long length,
            @RequestParam(required = false, value = "order[0][column]", defaultValue = "0") Long iSortCol0,
            @RequestParam(required = false, value = "order[0][dir]", defaultValue = "asc") String sSortDir0,
            @RequestBody(required = false) DeviceCondition params) {

        if (params == null) params = new DeviceCondition();
        log.info("draw: {}, start: {}, length: {}, type: {}", draw, start, length, params);
        return service.datatables(
                new DataTablesRequest(draw, length, start, sSortDir0, iSortCol0, params)
        );
    }

    @GetMapping("/list")
    public ResponseEntity<List<DeviceCondition>> list() {
        List<DeviceCondition> list = service.findAll();
        if (list.isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        else return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/lists")
    public Iterable<DeviceCondition> lists() {
        return service.findAlls();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DeviceCondition> findById(@PathVariable("id") String id) {
        DeviceCondition params = service.findId(id);
        if (params != null) {
            return new ResponseEntity<>(params, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new DeviceCondition(), HttpStatus.NO_CONTENT);
        }
    }

    @PostMapping("/")
    public ResponseEntity<DeviceCondition> save(@Valid @RequestBody DeviceConditionDTO.DeviceConditionRequestNewDTO params) {
        DeviceCondition value = DeviceConditionMapperRequestNew.converter.convertToEntity(params);
        value = service.save(value);
        return new ResponseEntity<>(value, HttpStatus.CREATED);
    }

    @PutMapping("/")
    public ResponseEntity<DeviceCondition> update(@Valid @RequestBody DeviceConditionDTO.DeviceConditionRequestUpdateDTO params) {
        DeviceCondition value = DeviceConditionMapperRequestUpdate.converter.convertToEntity(params);
        value = service.save(value);
        return new ResponseEntity<>(value, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DeviceCondition> delete(@PathVariable("id") String id) {
        boolean deleted = service.removeById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
