package id.co.mandiri.dao;

import com.maryanto.dimas.plugins.web.commons.ui.datatables.DataTablesRequest;
import com.maryanto.dimas.plugins.web.commons.ui.datatables.dao.DaoCrudDataTablesPattern;
import id.co.mandiri.entity.DeviceUnitCapacity;
import id.co.mandiri.repository.DeviceUnitCapacityRepository;
import id.co.mandiri.utils.QueryComparator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DeviceUnitCapacityDao implements DaoCrudDataTablesPattern<DeviceUnitCapacity, String> {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    private DeviceUnitCapacityRepository repository;

    @Override
    public DeviceUnitCapacity findId(String s) {
        return repository.findOne(s);
    }

    @Override
    @Deprecated
    public List<DeviceUnitCapacity> findAll() {
        return null;
    }

    @Override
    public DeviceUnitCapacity save(DeviceUnitCapacity deviceUnitCapacity) {
        return repository.save(deviceUnitCapacity);
    }

    @Override
    public DeviceUnitCapacity update(DeviceUnitCapacity deviceUnitCapacity) {
        return repository.save(deviceUnitCapacity);
    }

    @Override
    public boolean remove(DeviceUnitCapacity deviceUnitCapacity) {
        repository.delete(deviceUnitCapacity);
        return true;
    }

    @Override
    public boolean removeById(String s) {
        repository.delete(s);
        return true;
    }

    @Override
    public List<DeviceUnitCapacity> datatables(DataTablesRequest<DeviceUnitCapacity> params) {
        String baseQuery = "select id, name, code, description\n" +
                "from device_unit_capacity \n" +
                "where 1 = 1 ";

        DeviceUnitCapacity param = params.getValue();

        DeviceUnitCapacityQueryCompare compare = new DeviceUnitCapacityQueryCompare(baseQuery);
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
                new DeviceUnitCapacity(
                        resultSet.getString("id"),
                        resultSet.getString("name"),
                        resultSet.getString("code"),
                        resultSet.getString("description")
                ));
    }

    @Override
    public Long datatables(DeviceUnitCapacity param) {
        String baseQuery = "select count(*) \n" +
                "from device_unit_capacity \n" +
                "where 1 = 1 ";

        DeviceUnitCapacityQueryCompare compare = new DeviceUnitCapacityQueryCompare(baseQuery);
        StringBuilder query = compare.getQuery(param);
        MapSqlParameterSource values = compare.getParameters();

        return this.jdbcTemplate.queryForObject(
                query.toString(),
                values,
                (resultSet, i) -> resultSet.getLong(1)
        );

    }

    private class DeviceUnitCapacityQueryCompare implements QueryComparator<DeviceUnitCapacity> {

        private MapSqlParameterSource parameterSource;
        private StringBuilder query;

        DeviceUnitCapacityQueryCompare(String query) {
            this.parameterSource = new MapSqlParameterSource();
            this.query = new StringBuilder(query);
        }


        @Override
        public StringBuilder getQuery(DeviceUnitCapacity param) {
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
            return query;
        }

        @Override
        public MapSqlParameterSource getParameters() {
            return this.parameterSource;
        }
    }
}
