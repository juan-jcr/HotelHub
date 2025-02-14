package com.jacr.persistence.repositories;

import com.jacr.persistence.entities.Room;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RoomRepository extends CrudRepository<Room, Long> {

}
