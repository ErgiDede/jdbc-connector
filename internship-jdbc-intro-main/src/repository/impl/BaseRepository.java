package repository.impl;

import mapper.Mapper;
import model.entity.Customer;
import repository.Repository;

public abstract class BaseRepository<ENTITY, ID> implements Repository<ENTITY, ID> {

    private final Mapper<ENTITY> mapper;

    public BaseRepository(Mapper<ENTITY> mapper) {
        this.mapper = mapper;
    }

    protected Mapper<ENTITY> getMapper() {
        return mapper;
    }

    public abstract Customer findById(int id);
}
