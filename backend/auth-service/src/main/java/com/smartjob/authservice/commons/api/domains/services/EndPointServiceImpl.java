package com.smartjob.authservice.commons.api.domains.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import com.smartjob.authservice.commons.api.domains.exception.BaseException;
import com.smartjob.authservice.commons.api.domains.data.IEndPointService;
import org.springframework.stereotype.Service;


import org.springframework.data.jpa.repository.JpaRepository;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import static com.smartjob.authservice.commons.api.domains.data.UtilApi.getMessage;

@Service
@Slf4j
public abstract class EndPointServiceImpl<T, ID>   implements IEndPointService<T, ID> {

    @Override
    @SneakyThrows
    public T save(T entity) {
        try {
            BaseException error = this.validate(entity);
            if(error != null)
                throw error;

            return getDao().save(entity);
        } catch (Exception e) {
            if(e instanceof BaseException) {
                log.info("smartjob.api.create.error", e);
                throw e	;
            }
            else {
                BaseException baseException=   BaseException.builder()
                        .message(getMessage("smartjob.api.create.error"))
                        .module(nameModule())
                        .exception(e)
                        .build();
                log.info("smartjob.api.create.error",baseException);
                throw baseException;
            }

        }

    }

    @Override
    @SneakyThrows
    public List<T> saveAll(List<T> entityList) {
        try {
            return this.getDao().saveAll(entityList);
        }catch (Exception e) {
            BaseException baseException=   BaseException.builder()
                    .message(getMessage("smartjob.api.create-list.error"))
                    .module(nameModule())
                    .exception(e)
                    .build();
            log.info("smartjob.api.create-list.error",baseException);
            throw baseException;
        }
    }

    @Override
    @SneakyThrows
    public T update(T entity) {
        try {
            return getDao().save(entity);
        }catch (Exception e) {
            BaseException baseException=   BaseException.builder()
                    .message(getMessage("smartjob.api.update.error"))
                    .module(nameModule())
                    .exception(e)
                    .build();
            log.info("smartjob.api.update.error",baseException);
            throw baseException;
        }
    }

    @Override
    @SneakyThrows
    public T delete(ID id) {
        try {
            T entity = getById(id);
            entity = statusChangeDelete(entity);
            return this.update(entity);

        }catch (Exception e) {
            BaseException baseException=   BaseException.builder()
                    .message(getMessage("smartjob.api.delete.error"))
                    .module(nameModule())
                    .exception(e)
                    .build();
            log.info("smartjob.api.delete.error",baseException);
            throw baseException;
        }
    }

    @Override
    @SneakyThrows
    public T getById(ID id) {
        try {
            Optional<T> obj = getDao().findById(id);
            return obj.orElse(null);
        }catch (Exception e) {
            BaseException baseException = BaseException.builder()
                    .message(getMessage("smartjob.api.read.error"))
                    .module(nameModule())
                    .exception(e)
                    .build();
            log.info("smartjob.api.read.error",baseException);
            throw baseException;
        }
    }

    @Override
    @SneakyThrows
    public List<T> getAll() {
        try {
            return new ArrayList<>(getDao().findAll());
        }catch (Exception e) {
            BaseException baseException=   BaseException.builder()
                    .message(getMessage("smartjob.api.read-list.error"))
                    .module(nameModule())
                    .exception(e)
                    .build();
            log.info("smartjob.api.read-list.error",baseException);
            throw baseException;
        }
    }



    public abstract JpaRepository<T, ID> getDao();
    public abstract T statusChangeDelete(T entity);
    public abstract String nameModule();
    public abstract BaseException validate(T entity);
}
