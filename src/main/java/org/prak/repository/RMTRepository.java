package org.prak.repository;

import org.prak.database.DatabaseConnector;
import org.prak.model.RMTEntry;
import org.prak.repository.base.BaseRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RMTRepository extends BaseRepository<RMTEntry> {

    @Override
    protected String getQuery() {
        return "SELECT * FROM rmt";
    }

    @Override
    protected RMTEntry mapResultSet(ResultSet rs) throws SQLException {
        return new RMTEntry(
                rs.getInt("temperature_time"),
                rs.getInt("_10"),
                rs.getInt("_30"),
                rs.getInt("_100"),
                rs.getInt("_300"),
                rs.getInt("_1000"),
                rs.getInt("_3000"),
                getNullableInt(rs, "_10000"),
                getNullableInt(rs, "_30000"),
                getNullableInt(rs, "_100000"),
                getNullableInt(rs, "_200000"),
                getNullableInt(rs, "_300000")
        );
    }

    public List<RMTEntry> getByMaterialId(int materialId) {
        List<RMTEntry> list = new ArrayList<>();
        String sql = "SELECT * FROM rmt WHERE material_id = ?";

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

    private Integer getNullableInt(ResultSet rs, String column) throws SQLException {
        int value = rs.getInt(column);
        return rs.wasNull() ? null : value;
    }
}
