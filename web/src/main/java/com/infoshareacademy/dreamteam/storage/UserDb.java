//package com.infoshareacademy.dreamteam.storage;
//
//import com.infoshareacademy.dreamteam.dao.RoleDaoBean;
//import com.infoshareacademy.dreamteam.domain.entity.User;
//
//import javax.ejb.EJB;
//import javax.ejb.Stateless;
//import java.util.ArrayList;
//import java.util.List;
//
//public class UserDb {
//
//    private static List<User> userRepository = new ArrayList<>();
//
//    public static List<User> getRepository() {
//        if (userRepository.size() == 0) {
//            fillRepositoryWithDefaults();
//        }
//        return userRepository;
//    }
//
//
//    private static void fillRepositoryWithDefaults() {
//
//        User user2 = new User();
//        user2.setName("Dream Team");
//        user2.setEmail("jjdd9dreamteam@gmail.com");
//        user2.setRole(roleDaoBean.findByRoleType());
//        userRepository.add(user2);
//
//    }
//
//    public static boolean contains(User user) {
//        List<User> repository = getRepository();
//        for (User userFromList : repository) {
//            if (userFromList.getId().equals(user.getId())) {
//                return true;
//            }
//        }
//        return false;
//    }
//
//}
