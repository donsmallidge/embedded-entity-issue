package com.dogeatdogenterprises.services.mapservices;

import com.dogeatdogenterprises.domain.DomainObject;
import com.dogeatdogenterprises.domain.User;
import com.dogeatdogenterprises.services.UserService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by donaldsmallidge on 9/14/16.
 */
@Service
@Profile("map")
public class UserServiceMapImpl extends AbstractMapService implements UserService {

    @Override
    public List<DomainObject> listAll() {

        return super.listAll();
    }

    @Override
    public User getById(Integer id) {

        return (User) super.getById(id);
    }

    @Override
    public User saveOrUpdate(User domainObject) {

        return (User) super.saveOrUpdate(domainObject);
    }

    @Override
    public void delete(Integer id) {

        super.delete(id);
    }

    @Override
    protected void loadDomainObjects() {
        // placeholder?
    }
}
