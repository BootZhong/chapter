package service;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class RedisDemoService {


    @Cacheable(value = "student" ,key = "student")
    public String getSexByName(String name) {
            String zhongSex = "man";
            return zhongSex;

    }
}
