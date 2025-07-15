package org.prak.repository;

import org.prak.database.DatabaseConnector;
import org.prak.model.PhisMechProperty;
import org.prak.repository.base.BaseRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PhisMechPropertyRepository extends BaseRepository<PhisMechProperty> {

    @Override
    protected String getQuery() {
        return "SELECT * FROM phis_mech_properties";
    }

    @Override
    protected PhisMechProperty mapResultSet(ResultSet rs) throws SQLException {
        return new PhisMechProperty(
                rs.getString("temperature"),
                rs.getString("c"),
                getNullableFloat(rs, "_20"),
                getNullableFloat(rs, "_50"),
                getNullableFloat(rs, "_100"),
                getNullableFloat(rs, "_150"),
                getNullableFloat(rs, "_200"),
                getNullableFloat(rs, "_250"),
                getNullableFloat(rs, "_300"),
                getNullableFloat(rs, "_350"),
                getNullableFloat(rs, "_400"),
                getNullableFloat(rs, "_450"),
                getNullableFloat(rs, "_500")
        );
    }

    public List<PhisMechProperty> getByMaterialId(int materialId) {
        List<PhisMechProperty> list = new ArrayList<>();
        String sql = "SELECT * FROM phis_mech_properties WHERE material_id = ?";

        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, materialId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    list.add(mapResultSet(rs));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    private Float getNullableFloat(ResultSet rs, String column) throws SQLException {
        float value = rs.getFloat(column);
        return rs.wasNull() ? null : value;
    }
}
