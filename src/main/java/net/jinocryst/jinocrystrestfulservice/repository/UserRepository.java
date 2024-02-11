package net.jinocryst.jinocrystrestfulservice.repository;

import net.jinocryst.jinocrystrestfulservice.bean.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
}
