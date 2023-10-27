package ru.fridge.fourth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Класс для работы с базой данных
 */
@Repository
public class FridgeController {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * Добавляет новую строку в базу данных
     * @param fridge Холодильник для добавления
     */
    public void addFridge(Fridge fridge) {
        String sql = "INSERT INTO fridges (model, brand, color, price, quantity) VALUES (?, ?, ?, ?, ?)";
        System.out.println(sql);
        jdbcTemplate.update(sql, fridge.getModel(), fridge.getBrand(), fridge.getColor(),
                fridge.getPrice(), fridge.getQuantity());
    }

    /**
     * Считывает все строки из базы данных
     * @return Все строки из бд
     */
    public List<Fridge> getAllFridges() {
        String sql = "SELECT * FROM fridges";
        return jdbcTemplate.query(sql, new FridgesRowMapper());
    }

    /**
     * Обновляет строку данных в таблице
     * @param id            Идентификатор записи
     * @param updatedFridge Новые данные для записи
     */
    public void updateFridge(Integer id, Fridge updatedFridge) {
        String sql = "UPDATE fridges SET model=?, brand=?, color=?, price=?, quantity=? WHERE id=?";
        jdbcTemplate.update(sql, updatedFridge.getModel(), updatedFridge.getBrand(), updatedFridge.getColor(),
                updatedFridge.getPrice(), updatedFridge.getQuantity(), id);
    }

    /**
     * Удаляет строку в таблицце
     * @param id Идентификатор строки
     */
    public void deleteFridge(Integer id) {
        String sql = "DELETE FROM fridges WHERE id=?";
        jdbcTemplate.update(sql, id);
    }

    /**
     * Ищет запись в таблице по значению одного из полей
     * @param field Название поля
     * @param value Значение поля
     * @return Найденная строка
     */
    public List<Fridge> searchFridges(String field, String value) {
        String sql = "SELECT * FROM fridges WHERE LOWER(" + field + ") LIKE ?";
        return jdbcTemplate.query(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, "%" + value.toLowerCase() + "%");
            return ps;
        }, new FridgesRowMapper());
    }

    /**
     * Преобразует результат запроса в объект класса Fridge
     */
    private static class FridgesRowMapper implements RowMapper<Fridge> {
        @Override
        public Fridge mapRow(ResultSet rs, int rowNum) throws SQLException {
            Fridge fridges = new Fridge();
            fridges.setId(rs.getInt("id"));
            fridges.setModel(rs.getString("item_name"));
            fridges.setBrand(rs.getString("brand"));
            fridges.setColor(rs.getString("color"));
            fridges.setPrice(rs.getDouble("price"));
            fridges.setQuantity(rs.getInt("quantity"));
            return fridges;
        }
    }
}