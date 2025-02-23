package com.project.expense.tracker.api.repositories;

import com.project.expense.tracker.api.domain.Category;
import com.project.expense.tracker.api.exception.EtBadRequestException;
import com.project.expense.tracker.api.exception.EtResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class CategoryRepositoryImpl implements  CategoryRepository{
    private static final String SQL_FIND_BY_ID  = "SELECT C.CATEGORY_ID, C.USER_ID, C.TITLE, C.DESCRIPTION, " +
            "COALESCE(SUM(T.AMOUNT), 0) TOTAL_EXPENSE " + // Added space after TOTAL_EXPENSE
            "FROM ET_TRANSACTION T RIGHT OUTER JOIN ET_CATEGORIES C ON C.CATEGORY_ID = T.CATEGORY_ID " +
            "WHERE C.USER_ID = ? AND C.CATEGORY_ID = ? GROUP BY C.CATEGORY_ID";
    private  static final String SQL_CREATE ="INSERT INTO ET_CATEGORIES(CATEGORY_ID,USER_ID,TITLE,DESCRIPTION) VALUES(NEXTVAL('ET_CATEGORIES_SEQ'),?,?,?)";
    private  static   final String SQL_FIND_ALL ="SELECT C.CATEGORY_ID, C.USER_ID, C.TITLE, C.DESCRIPTION, " +
            "COALESCE(SUM(T.AMOUNT), 0) TOTAL_EXPENSE " + // Added space after TOTAL_EXPENSE
            "FROM ET_TRANSACTION T RIGHT OUTER JOIN ET_CATEGORIES C ON C.CATEGORY_ID = T.CATEGORY_ID " +
            "WHERE C.USER_ID = ?  GROUP BY C.CATEGORY_ID";

    private  static final String SQL_UPDATE = "UPDATE ET_CATEGORIES SET TITLE =? , DESCRIPTION= ? " +
            "WHERE USER_ID = ? AND CATEGORY_ID = ? ";


private  static final String  SQL_DELETE_CATEGORY = "DELETE FROM ET_CATEGORIES WHERE USER_ID = ? AND CATEGORY_ID = ? ";
private  static  final  String  SQL_DELETE_ALL_TRANSACTIONS = "DELETE FROM ET_CATEGORIES WHERE CATEGORY_ID = ? ";


    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public List<Category> findAll(Integer userId) throws EtResourceNotFoundException {

        return jdbcTemplate.query(SQL_FIND_ALL,new Object[]{userId},categoryRowMapper);
    }

    @Override
    public Category findById(Integer userId, Integer categoryId) throws EtResourceNotFoundException {
        try {
            return jdbcTemplate.queryForObject(SQL_FIND_BY_ID, new Object[]{userId, categoryId}, categoryRowMapper);
        } catch (EmptyResultDataAccessException e) {
            throw new EtResourceNotFoundException("Category with ID " + categoryId + " not found for user " + userId);
        } catch (Exception e) {
            throw new RuntimeException("Unexpected error occurred while fetching category", e);
        }
    }

    @Override
    public Integer create(Integer userId, String title, String description) throws EtBadRequestException {
        try {
            KeyHolder keyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(SQL_CREATE, Statement.RETURN_GENERATED_KEYS);
                ps.setInt(1, userId);
                ps.setString(2, title);
                ps.setString(3, description);
                return ps;
            }, keyHolder);
            Number key = (Number) keyHolder.getKeys().get("CATEGORY_ID");
            return key != null ? key.intValue() : null;
        } catch (Exception e) {
            throw new EtBadRequestException("Invalid request");

        }

    }

    @Override
    public void update(Integer userId, Integer categoryId, Category category) throws EtBadRequestException {
try{
    jdbcTemplate.update(SQL_UPDATE,new Object[]{category.getTitle(),category.getDescription(),userId,categoryId});

}
catch(Exception e){
    throw  new EtBadRequestException("Invalid request");

}

    }

    @Override
    public void removeById(Integer userId, Integer categoryId) {

    }

    @Override
    public void remove(Integer userId, Integer categoryId) {
this.removeAllCatTransactions(categoryId);
jdbcTemplate.update(SQL_DELETE_CATEGORY,new Object[]{userId,categoryId});
    }

    public  void removeAllCatTransactions( Integer categoryId){
jdbcTemplate.update(SQL_DELETE_ALL_TRANSACTIONS, new Object[]{categoryId});



    }

    private RowMapper<Category> categoryRowMapper = ((rs,rowNum) -> {
        return new Category(rs.getInt("CATEGORY_ID"),
        rs.getInt("USER_ID"),
        rs.getString("TITLE"),
                rs.getString("DESCRIPTION"),
                rs.getDouble("TOTAL_EXPENSE"));

});
}