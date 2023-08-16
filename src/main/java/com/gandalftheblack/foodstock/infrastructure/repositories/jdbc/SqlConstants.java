package com.gandalftheblack.foodstock.infrastructure.repositories.jdbc;

public class SqlConstants {
    protected final static String GET_FOOD_LIST_QUERY = "SELECT * FROM FOOD";
    protected final static String ADD_FOOD_QUERY = "INSERT INTO FOOD(ID, NAME, QUANTITY, CREATED_AT, MODIFIED_AT) VALUES (?,?,?,?,?)";
    protected final static String EDIT_FOOD_QUERY = "UPDATE FOOD " +
            "SET NAME = ?, QUANTITY = ?, MODIFIED_AT = ? " +
            "WHERE ID = ?";
    protected final static String DELETE_FOOD_QUERY = "DELETE FROM FOOD " +
            "WHERE ID = ?";
    protected final static String FIND_FOOD_BY_ID = "SELECT * FROM FOOD WHERE ID = ?";
}
