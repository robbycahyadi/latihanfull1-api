package id.co.mandiri.dao;

import com.maryanto.dimas.plugins.web.commons.ui.datatables.DataTablesRequest;
import com.maryanto.dimas.plugins.web.commons.ui.datatables.dao.DaoCrudDataTablesPattern;
import id.co.mandiri.entity.DeviceBrand;
import id.co.mandiri.repository.DeviceBrandRepository;
import id.co.mandiri.utils.QueryComparator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DeviceBrandDao implements DaoCrudDataTablesPattern<DeviceBrand, String> {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    private DeviceBrandRepository repository;

    @Override
    public DeviceBrand findId(String s) {
        return repository.findOne(s);
    }

    @Override
    @Deprecated
    public List<DeviceBrand> findAll() {
        return null;
    }

    public Iterable<DeviceBrand> finddAlls() {
        return repository.findAll();
    }

    @Override
    public DeviceBrand save(DeviceBrand deviceBrand) {
        return repository.save(deviceBrand);
    }

    @Override
    public DeviceBrand update(DeviceBrand deviceBrand) {
        return repository.save(deviceBrand);
    }

    @Override
    public boolean remove(DeviceBrand deviceBrand) {
        repository.delete(deviceBrand);
        return true;
    }

    @Override
    public boolean removeById(String s) {
        repository.delete(s);
        return true;
    }

    @Override
    public List<DeviceBrand> datatables(DataTablesRequest<DeviceBrand> params) {
        String baseQuery = "select id, name, code, description\n" +
                "from device_brand \n" +
                "where 1 = 1 ";

        DeviceBrand param = params.getValue();

        DeviceBrandQueryCompare compare = new DeviceBrandQueryCompare(baseQuery);
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
                new DeviceBrand(
                        resultSet.getString("id"),
                        resultSet.getString("name"),
                        resultSet.getString("code"),
                        resultSet.getString("description")
                ));
    }

    @Override
    public Long datatables(DeviceBrand param) {
        String baseQuery = "select count(*) \n" +
                "from device_brand \n" +
                "where 1 = 1 ";

        DeviceBrandDao.DeviceBrandQueryCompare compare = new DeviceBrandDao.DeviceBrandQueryCompare(baseQuery);
        StringBuilder query = compare.getQuery(param);
        MapSqlParameterSource values = compare.getParameters();

        return this.jdbcTemplate.queryForObject(
                query.toString(),
                values,
                (resultSet, i) -> resultSet.getLong(1)
        );

    }

    private class DeviceBrandQueryCompare implements QueryComparator<DeviceBrand> {

        private MapSqlParameterSource parameterSource;
        private StringBuilder query;

        DeviceBrandQueryCompare(String query) {
            this.parameterSource = new MapSqlParameterSource();
            this.query = new StringBuilder(query);
        }


        @Override
        public StringBuilder getQuery(DeviceBrand param) {
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
