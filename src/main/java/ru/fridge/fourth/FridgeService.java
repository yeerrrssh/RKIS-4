package ru.fridge.fourth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.sql.rowset.JdbcRowSet;
import java.util.List;

/**
 * Сервис для работы с холодильниками
 */
@Service
public class FridgeService {
    @Autowired
    private FridgeController fridgeController;

    /**
     * Добавляет новый холодильник
     * @param fridge Холодильник для добавления
     */
    public void addFridge(Fridge fridge) {
        fridgeController.addFridge(fridge);
    }

    /**
     * Возвращает список всех холодильников из базы данных
     * @return Список холодильников
     */
    public List<Fridge> getAllFridges() {
        return fridgeController.getAllFridges();
    }

    /**
     * Обновляет данные о холодильнике
     * @param id                Идентификатор холодильника
     * @param updatedFridge     Данные для обновления
     */
    public void updateFridge(Integer id, Fridge updatedFridge) {
        fridgeController.updateFridge(id, updatedFridge);
    }

    /**
     * Удаляет холодильник из базы данных
     * @param id Идентификатор холодильника для удаления
     */
    public void deleteFridge(Integer id) {
        fridgeController.deleteFridge(id);
    }

    /**
     * Осуществляет поиск холодильников по заданному полю и значению
     * @param field Поле для поиска
     * @param value Значение для поиска
     * @return Список предметов, соответствующих критериям поиска
     */
    public List<Fridge> searchFridges(String field, String value) {
        return fridgeController.searchFridges(field, value);
    }
}