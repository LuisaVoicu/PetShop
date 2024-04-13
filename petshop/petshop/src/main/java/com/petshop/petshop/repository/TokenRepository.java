package com.petshop.petshop.repository;

import com.petshop.petshop.model.Token;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token, Long> {

//    @Query("""
//            select t from Token t inner join User u on t.user.id = u.id
//            where t.user.id = :userId and t.loggedOut = false
//            """)
//    List<Token> findAllTokensByUser(Long userId);

    Optional<Token> findByToken(String token);
}
