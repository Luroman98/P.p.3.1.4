package ru.kata.spring.boot_security.demo.web.service;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.web.dao.UserRepository;
import ru.kata.spring.boot_security.demo.web.model.User;

import java.util.List;

@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Service
public class UserServiceImp implements  UserDetailsService, UserService {


   private final UserRepository userRepository;

   private final BCryptPasswordEncoder bCryptPasswordEncoder;
   @Autowired
   @Lazy
   public UserServiceImp(BCryptPasswordEncoder bCryptPasswordEncoder, UserRepository userRepository) {
      this.bCryptPasswordEncoder = bCryptPasswordEncoder;
      this.userRepository = userRepository;
   }

   @Transactional
    @Override
   public boolean saveUser(User user) {
         user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
      userRepository.save(user);
         return true;
   }

   @Override
   public List<User> listUsers() {
      return (List<User>) userRepository.findAll();
   }

   @Override
   public Object getUser(Long id) {
      return userRepository.findById(id);
   }
   @Override
   public void updateUser( User user) {
      user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
      userRepository.save(user);

   }
   public void deleteUser(Long id) {
      userRepository.deleteById(id);
   }

   @Override
   public User loadUserByUsername(String username) throws UsernameNotFoundException {
      User user = userRepository.findByUsername(username);
      if (user == null) {
         throw new UsernameNotFoundException("User not found");
      }
      return user;
   }
}
