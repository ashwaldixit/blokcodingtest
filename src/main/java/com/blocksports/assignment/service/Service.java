package com.blocksports.assignment.service;

import com.blocksports.assignment.exceptions.BlokBadRequestException;
import com.blocksports.assignment.exceptions.BlokRuntimeException;
import com.blocksports.assignment.enums.RecordStatus;
import com.blocksports.assignment.enums.ValidationMode;
import com.blocksports.assignment.model.Model;
import com.blocksports.assignment.model.User;
import com.blocksports.assignment.util.ObjectUtil;
import org.springframework.lang.NonNull;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Transactional
@SuppressWarnings("unchecked")
public interface Service<M extends Model> {

    /**
     * This method creates the model object and then inserts to the table and returns back the data .
     * The functionalities present in this method are :
     * 1. Populate the value for the field Key.
     * 3. Pre validation of the data sent.
     * 4. Pre inserting of any data.
     * 5. Inserting the data to the database.
     * 6. Post insert operation.
     * 7. Post validation of the data.
     *
     * @param model
     * @return the saved object
     */
    default M create(M model) {
        if (null == model) {
            throw new BlokBadRequestException("Input is not valid");
        }
        model = populateKey(model);
        preInsert(model);
        model = insertRecord(model);
        postInsert(model);
        postValidate(model, ValidationMode.CREATE);
        return model;
    }

    /**
     * This method updates the model object and then inserts to the table and returns back the data .
     * The functionalities present in this method are :
     * 1. Set the value for the field Id.
     * 3. Pre validation of the data sent.
     * 4. Pre updating of any data.
     * 5. Update the data to the database.
     * 6. Post update operation.
     * 7. Post validation of the data.
     *
     * @param model
     * @return the updates object
     */
    default M modify(M model) {
        if (null == model) {
            throw new BlokBadRequestException("Input is not valid");
        }
        preUpdate(model);
        model = update(model);
        postUpdate(model);
        return model;
    }

    /**
     * This method updates the model the object and then inserts to the table and returns back the data .
     * There will no validations or authorization performed.
     *
     * @param obj
     * @return the updates object
     */
    default M update(M obj) {
        return insertRecord(obj);

    }


    default void preValidate(M obj, ValidationMode mode) {

    }

    default void postValidate(M obj, ValidationMode mode) {

    }

    default void preUpdate(@NonNull M obj) {

    }

    default void postUpdate(M obj) {

    }

    default void preInsert(M obj) {

    }

    default void postInsert(M obj) {

    }

    M insertRecord(M obj);


    default M populateKey(M model) {

        if (null == model)
            return null;

        try {
            Field field = getField(model.getClass(), "key");
            field.setAccessible(true);
            if (field.get(model) == null || String.valueOf(field.get(model)).isEmpty())
                field.set(model, UUID.randomUUID().toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return model;

    }

    default M delete(M model) {
        if (null == model)
            return null;
        try {
            Field field = getField(model.getClass(), "status");
            field.setAccessible(true);
            field.set(model, RecordStatus.DELETED);
            return insertRecord(model);
        } catch (SecurityException | IllegalArgumentException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return model;
    }


    default User getLoggedInUser() {

//        try {
//            return AvailabilityAppContextAware.bean(BaseContextUser.class).getAuthenticatedUser();
//        } catch (Exception e) {
//            log(Level.ERROR, "getLoggedInUser", "Warning :  Error in getting Request user: " + e.getMessage());
//        }
        return null;
    }

    public static Field getField(Class clzz, String field) {
        Set<Field> allFields = getAllFields(null, clzz);
        for (Field item : allFields) {
            if (item.getName().equalsIgnoreCase(field)) return item;
        }
        return null;
    }
    public static Set<Field> getAllFields(Set<Field> fields, Class<?> type) {
        if (ObjectUtil.isNullOrEmpty(fields)) {
            fields = new HashSet<>();
        }
        fields.addAll(Stream.of(type.getDeclaredFields()).collect(Collectors.toSet()));

        if (type.getSuperclass() != null) {
            getAllFields(fields, type.getSuperclass());
        }

        return fields;
    }

}
