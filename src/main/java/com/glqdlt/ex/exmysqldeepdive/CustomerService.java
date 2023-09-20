package com.glqdlt.ex.exmysqldeepdive;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

/**
 * @author glqdlt
 */
@Service
public class CustomerService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerService.class);

    @Autowired
    private CustomerEntityRepo customerEntityRepo;

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void updateWithXlock(Integer addresId) {
        try {
            Optional<CustomerEntity> d = customerEntityRepo.findByAddressId(addresId);
            d.ifPresent(x -> {
                LOGGER.info("find. session : {}, dump : {}", "with x lock", x.toString());
                String updatedName = x.getLastName() + "1";
                x.setLastName(updatedName);
                LOGGER.info("saved. session : {}, dump : {}", "with x lock", x.toString());
                customerEntityRepo.save(x);
            });
            Thread.sleep(TimeUnit.SECONDS.toMillis(30));

        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void updateWith(Integer addresId) {
        try {
            Optional<CustomerEntity> d = customerEntityRepo.findByAddressId(addresId);
            d.ifPresent(x -> {
                LOGGER.info("find. session : {}, dump : {}", "none", x.toString());
                String updatedName = x.getLastName() + "2";
                x.setLastName(updatedName);
                LOGGER.info("saved. session : {}, dump : {}", "none", x.toString());
                customerEntityRepo.save(x);
            });
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }

    }

}
