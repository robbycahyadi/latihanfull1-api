package id.co.mandiri.dao;

import com.maryanto.dimas.plugins.web.commons.ui.datatables.DataTablesRequest;
import com.maryanto.dimas.plugins.web.commons.ui.datatables.dao.DaoCrudDataTablesPattern;
import id.co.mandiri.entity.*;
import id.co.mandiri.repository.DeviceBrandRepository;
import id.co.mandiri.repository.MasterDeviceRepository;
import id.co.mandiri.utils.QueryComparator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class MasterDeviceDao implements DaoCrudDataTablesPattern<MasterDevice, String> {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    private MasterDeviceRepository repository;

    @Override
    public MasterDevice findId(String s) {
        return repository.findOne(s);
    }

    public Iterable<MasterDevice> findAlls() {
        return repository.findAll();
    }

    @Override
    @Deprecated
    public List<MasterDevice> findAll() {
        return null;
    }

    @Override
    public MasterDevice save(MasterDevice masterDevice) {
        return repository.save(masterDevice);
    }

    @Override
    public MasterDevice update(MasterDevice masterDevice) {
        return repository.save(masterDevice);
    }

    @Override
    public boolean remove(MasterDevice masterDevice) {
        repository.delete(masterDevice);
        return true;
    }

    @Override
    public boolean removeById(String s) {
        repository.delete(s);
        return true;
    }

    @Override
    public List<MasterDevice> datatables(DataTablesRequest<MasterDevice> params) {
        String baseQuery = "select dev.id, dev.name, dev.code, dev.description, \n" +
                "dev.category_id, dev.color_id, dev.brand_id, dev.condition_id, dev.unit_capacity_id, dev.loan_status_id,\n" +
                "cat.name as category, col.name as color, \n" +
                "brand.name as brand, cond.name as `condition`, cap.name as unit_capacity, loan.name as loan_status\n" +
                "from master_device as dev\n" +
                "join device_category as cat on dev.category_id = cat.id\n" +
                "join master_color as col on dev.color_id = col.id\n" +
                "join device_brand as brand on dev.brand_id = brand.id\n" +
                "join device_condition as cond on dev.condition_id = cond.id\n" +
                "join device_unit_capacity as cap on dev.unit_capacity_id = cap.id\n" +
                "join device_loan_status as loan on dev.loan_status_id = loan.id\n" +
                "where 1=1 ";

        MasterDevice param = params.getValue();

        MasterDeviceQueryCompare compare = new MasterDeviceQueryCompare(baseQuery);
        StringBuilder query = compare.getQuery(param);
        MapSqlParameterSource values = compare.getParameters();

        switch (params.getColOrder().intValue()) {
            case 0:
                if (StringUtils.equalsIgnoreCase(params.getColDir(), "asc"))
                    query.append(" order by id asc ");
                else
                    query.append(" order by id desc ");
                break;
            case 1:
                if (StringUtils.equalsIgnoreCase(params.getColDir(), "asc"))
                    query.append(" order by name asc ");
                else
                    query.append(" order by name desc ");
                break;
            case 2:
                if (StringUtils.equalsIgnoreCase(params.getColDir(), "asc"))
                    query.append(" order by code asc ");
                else
                    query.append(" order by code desc ");
                break;
            case 3:
                if (StringUtils.equalsIgnoreCase(params.getColDir(), "asc"))
                    query.append(" order by description asc ");
                else
                    query.append(" order by description desc ");
                break;

            default:
                if (StringUtils.equalsIgnoreCase(params.getColDir(), "asc"))
                    query.append(" order by id asc ");
                else
                    query.append(" order by id desc ");
                break;
        }

        query.append("limit :limit offset :offset");
        values.addValue("offset", params.getStart());
        values.addValue("limit", params.getLength());

        return this.jdbcTemplate.query(query.toString(), values, (resultSet, i) ->
                new MasterDevice(
                        resultSet.getString("id"),
                        resultSet.getString("name"),
                        resultSet.getString("code"),
                        resultSet.getString("description"),
                        new CategoryDevice(resultSet.getString("category_id"), resultSet.getString("category"), null),
                        new MasterColor(resultSet.getString("color_id"), resultSet.getString("color"), null, null),
                        new DeviceBrand(resultSet.getString("brand_id"), resultSet.getString("brand"), null, null),
                        new DeviceCondition(resultSet.getString("condition_id"), resultSet.getString("condition"), null, null),
                        new DeviceUnitCapacity(resultSet.getString("unit_capacity_id"), resultSet.getString("unit_capacity"), null, null),
                        new DeviceLoanStatus(resultSet.getString("loan_status_id"), resultSet.getString("loan_status"), null, null)
                ));
    }

    @Override
    public Long datatables(MasterDevice param) {
        String baseQuery = "select count(*) \n" +
                "from master_device \n" +
                "where 1 = 1 ";

        MasterDeviceDao.MasterDeviceQueryCompare compare = new MasterDeviceDao.MasterDeviceQueryCompare(baseQuery);
        StringBuilder query = compare.getQuery(param);
        MapSqlParameterSource values = compare.getParameters();

        return this.jdbcTemplate.queryForObject(
                query.toString(),
                values,
                (resultSet, i) -> resultSet.getLong(1)
        );

    }

    private class MasterDeviceQueryCompare implements QueryComparator<MasterDevice> {

        private MapSqlParameterSource parameterSource;
        private StringBuilder query;

        MasterDeviceQueryCompare(String query) {
            this.parameterSource = new MapSqlParameterSource();
            this.query = new StringBuilder(query);
        }


        @Override
        public StringBuilder getQuery(MasterDevice param) {
            if (StringUtils.isNoneBlank(param.getId())) {
                query.append(" and lower(id) like :id ");
                parameterSource.addValue("id",
                        new StringBuilder("%")
                                .append(param.getId().toLowerCase())
                                .append("%")
                                .toString());
            }

            if (StringUtils.isNoneBlank(param.getName())) {
                query.append(" and lower(name) like :name ");
                parameterSource.addValue("name", new StringBuilder("%")
                        .append(param.getName().toLowerCase())
                        .append("%")
                        .toString());
            }

            if (StringUtils.isNoneBlank(param.getCode())) {
                query.append(" and lower(code) like :code ");
                parameterSource.addValue("code", new StringBuilder("%")
                        .append(param.getName().toLowerCase())
                        .append("%")
                        .toString());
            }

            if (StringUtils.isNoneBlank(param.getDescription())) {
                query.append(" and lower(description) like :description ");
                parameterSource.addValue("description", new StringBuilder("%")
                        .append(param.getDescription().toLowerCase())
                        .append("%")
                        .toString());
            }

            /*if (StringUtils.isNoneBlank(param.getCategory().getId())) {
                query.append(" and lower(category_id) like :category_id ");
                parameterSource.addValue("category_id", new StringBuilder("%")
                        .append(param.getCategory().getId().toLowerCase())
                        .append("%")
                        .toString());
            }*/

            /*if (StringUtils.isNoneBlank(param.getColor().getId())) {
                query.append(" and lower(color_id) like :color_id ");
                parameterSource.addValue("color_id", new StringBuilder("%")
                        .append(param.getColor().getId().toLowerCase())
                        .append("%")
                        .toString());
            }

            if (StringUtils.isNoneBlank(param.getBrand().getId())) {
                query.append(" and lower(brand_id) like :brand_id ");
                parameterSource.addValue("brand_id", new StringBuilder("%")
                        .append(param.getBrand().getId().toLowerCase())
                        .append("%")
                        .toString());
            }

            if (StringUtils.isNoneBlank(param.getCondition().getId())) {
                query.append(" and lower(condition_id) like :condition_id ");
                parameterSource.addValue("condition_id", new StringBuilder("%")
                        .append(param.getCondition().getId().toLowerCase())
                        .append("%")
                        .toString());
            }

            if (StringUtils.isNoneBlank(param.getUnitCapacity().getId())) {
                query.append(" and lower(unit_capacity_id) like :unit_capacity_id ");
                parameterSource.addValue("unit_capacity_id", new StringBuilder("%")
                        .append(param.getUnitCapacity().getId().toLowerCase())
                        .append("%")
                        .toString());
            }

            if (StringUtils.isNoneBlank(param.getLoanStatus().getId())) {
                query.append(" and lower(loan_status_id) like :loan_status_id ");
                parameterSource.addValue("loan_status_id", new StringBuilder("%")
                        .append(param.getLoanStatus().getId().toLowerCase())
                        .append("%")
                        .toString());
            }*/
            return query;
        }

        @Override
        public MapSqlParameterSource getParameters() {
            return this.parameterSource;
        }
    }
}
