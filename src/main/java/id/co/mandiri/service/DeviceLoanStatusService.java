package id.co.mandiri.service;

import com.maryanto.dimas.plugins.web.commons.ui.datatables.DataTablesRequest;
import com.maryanto.dimas.plugins.web.commons.ui.datatables.DataTablesResponse;
import com.maryanto.dimas.plugins.web.commons.ui.datatables.service.ServiceCrudDataTablesPattern;
import id.co.mandiri.dao.DeviceLoanStatusDao;
import id.co.mandiri.entity.DeviceLoanStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class DeviceLoanStatusService implements ServiceCrudDataTablesPattern<DeviceLoanStatus, String> {

    @Autowired
    private DeviceLoanStatusDao dao;

    @Override
    public DeviceLoanStatus findId(String s) {
        return dao.findId(s);
    }

    @Override
    public List<DeviceLoanStatus> findAll() {
        return null;
    }

    public Iterable<DeviceLoanStatus> findAlls() {
        return dao.findAlls();
    }

    @Override
    @Transactional
    public DeviceLoanStatus save(DeviceLoanStatus value) {
        return dao.save(value);
    }

    @Override
    @Transactional
    public DeviceLoanStatus update(DeviceLoanStatus value) {
        return dao.update(value);
    }

    @Override
    @Transactional
    public boolean remove(DeviceLoanStatus value) {
        return dao.remove(value);
    }

    @Override
    @Transactional
    public boolean removeById(String s) {
        return dao.removeById(s);
    }

    @Override
    public DataTablesResponse<DeviceLoanStatus> datatables(DataTablesRequest<DeviceLoanStatus> params) {
        List<DeviceLoanStatus> values = dao.datatables(params);
        Long rowCount = dao.datatables(params.getValue());
        return new DataTablesResponse<>(values, params.getDraw(), rowCount, rowCount);
    }
}
