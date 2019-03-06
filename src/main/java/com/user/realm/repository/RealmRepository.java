package com.user.realm.repository;

import com.user.realm.model.Realm;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface RealmRepository extends CrudRepository<Realm, Long> {

    @Query("SELECT r FROM Realm r WHERE r.name = :name")
    public Realm findByName(@Param("name") String name);
}

