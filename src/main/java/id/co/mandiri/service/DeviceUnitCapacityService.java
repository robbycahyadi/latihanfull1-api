package id.co.mandiri.service;

import com.maryanto.dimas.plugins.web.commons.ui.datatables.DataTablesRequest;
import com.maryanto.dimas.plugins.web.commons.ui.datatables.DataTablesResponse;
import com.maryanto.dimas.plugins.web.commons.ui.datatables.service.ServiceCrudDataTablesPattern;
import id.co.mandiri.dao.DeviceUnitCapacityDao;
import id.co.mandiri.entity.DeviceUnitCapacity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class DeviceUnitCapacityService implements ServiceCrudDataTablesPattern<DeviceUnitCapacity, String> {

    @Autowired
    private DeviceUnitCapacityDao dao;

    @Override
    public DeviceUnitCapacity findId(String s) {
        return dao.findId(s);
    }

    @Override
    public List<DeviceUnitCapacity> findAll() {
        return null;
    }

    public Iterable<DeviceUnitCapacity> findAlls() {
        return dao.findAlls();
    }

    @Override
    @Transactional
    public DeviceUnitCapacity save(DeviceUnitCapacity value) {
        return dao.save(value);
    }

    @Override
    @Transactional
    public DeviceUnitCapacity update(DeviceUnitCapacity value) {
        return dao.update(value);
    }

    @Override
    @Transactional
    public boolean remove(DeviceUnitCapacity value) {
        return dao.remove(value);
    }

    @Override
    @Transactional
    public boolean removeById(String s) {
        return dao.removeById(s);
    }

    @Override
    public DataTablesResponse<DeviceUnitCapacity> datatables(DataTablesRequest<DeviceUnitCapacity> params) {
        List<DeviceUnitCapacity> values = dao.datatables(params);
        Long rowCount = dao.datatables(params.getValue());
        return new DataTablesResponse<>(values, params.getDraw(), rowCount, rowCount);
    }
}
