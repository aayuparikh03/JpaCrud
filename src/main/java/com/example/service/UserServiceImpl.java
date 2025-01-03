package com.example.service;

import com.example.entity.Users;
import com.example.exception.UserNotFoundException;
import com.example.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{
    private UserRepository userRepository;
    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<Users> findAll() {
        return userRepository.findAll();
    }
    @Override
    public List<Users> findAllById(List<Integer> ids) {
        List<Users> usersList = userRepository.findAllById(ids);
        if (usersList.isEmpty()) {
            throw new UserNotFoundException("No users found for the provided IDs: " + ids);
        }
        return usersList;
    }

    @Override
    public void deleteAllByIds(List<Integer> ids) {
       for(int id:ids)
       {
           if (userRepository.existsById(id))
           {
               userRepository.deleteById(id);
           }
       }
    }

    @Override
    public Users findById(int id) {
        Optional<Users> foundUser=userRepository.findById(id);
        Users users=null;
        if(foundUser.isPresent()){
            users=foundUser.get();
        }else {
            throw  new UserNotFoundException("User with id:"+id+" is not present");
        }
        return users;
    }
    @Transactional
    @Override
    public Users save(Users users) {
        return userRepository.save(users);
    }
    @Transactional
    @Override
    public Users update(int id, Users users) {
        Users matched=findById(id);
        if(matched!=null){
            if(users.getName()!=null)
            {
                matched.setName(users.getName());
            }
            if(users.getSemester()!=0){
                matched.setSemester(users.getSemester());
            }
        }
        if(matched==null)
        {
            throw new UserNotFoundException("User with id:"+id+" is not found");
        }
        return userRepository.save(matched);
    }
    @Transactional
    @Override
    public void deleteById(int id) {
        Users matched=findById(id);
        userRepository.deleteById(id);
    }


}
