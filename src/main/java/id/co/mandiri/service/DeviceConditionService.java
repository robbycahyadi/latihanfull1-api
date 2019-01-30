package id.co.mandiri.service;

import com.maryanto.dimas.plugins.web.commons.ui.datatables.DataTablesRequest;
import com.maryanto.dimas.plugins.web.commons.ui.datatables.DataTablesResponse;
import com.maryanto.dimas.plugins.web.commons.ui.datatables.service.ServiceCrudDataTablesPattern;
import id.co.mandiri.dao.DeviceConditionDao;
import id.co.mandiri.entity.DeviceCondition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class DeviceConditionService implements ServiceCrudDataTablesPattern<DeviceCondition, String> {

    @Autowired
    private DeviceConditionDao dao;

    @Override
    public DeviceCondition findId(String s) {
        return dao.findId(s);
    }

    @Override
    public List<DeviceCondition> findAll() {
        return null;
    }

    @Override
    @Transactional
    public DeviceCondition save(DeviceCondition value) {
        return dao.save(value);
    }

    @Override
    @Transactional
    public DeviceCondition update(DeviceCondition value) {
        return dao.update(value);
    }

    @Override
    @Transactional
    public boolean remove(DeviceCondition value) {
        return dao.remove(value);
    }

    @Override
    @Transactional
    public boolean removeById(String s) {
        return dao.removeById(s);
    }

    @Override
    public DataTablesResponse<DeviceCondition> datatables(DataTablesRequest<DeviceCondition> params) {
        List<DeviceCondition> values = dao.datatables(params);
        Long rowCount = dao.datatables(params.getValue());
        return new DataTablesResponse<>(values, params.getDraw(), rowCount, rowCount);
    }
}
