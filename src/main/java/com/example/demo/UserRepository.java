package com.example.demo;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface UserRepository extends JpaRepository<User,
        Long> {
//    default List<User> findAllByAge(int age, String sortBy, String direction) {
//        List<User> users = null;
//        if (direction.equals("up")) {
//            users = this.findAll(Sort.by(Sort.Direction.ASC, sortBy));
//        } else {
//            users = this.findAll(Sort.by(Sort.Direction.DESC, sortBy));
//        }
//        for (int i = 0; i < users.size(); i++) {
//            if (Math.abs(users.get(i).getAge() - age) > 5) {
//                users.remove(i);
//            }
//        }
//
//        return users;
//    }


}