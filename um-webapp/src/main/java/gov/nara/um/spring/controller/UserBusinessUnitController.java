package gov.nara.um.spring.controller;



import gov.nara.common.persistence.service.ILongRawService;
import gov.nara.common.util.QueryConstants;
import gov.nara.common.web.controller.AbstractController;
import gov.nara.common.web.controller.AbstractLongIdController;
import gov.nara.common.web.controller.ILongIdSortingController;
import gov.nara.common.web.controller.ISortingController;
import gov.nara.um.persistence.model.BusinessUnit;
import gov.nara.um.persistence.model.User;
import gov.nara.um.persistence.model.UserBusinessUnitDTO;
import gov.nara.um.service.IBusinessUnitService;
import gov.nara.um.service.IUserService;
import gov.nara.um.util.UmMappings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;


@Controller
@RequestMapping(value = UmMappings.USERS_BUSINESSUNITS)
public class UserBusinessUnitController extends AbstractLongIdController<User>  {

    @Autowired
    private IUserService userService;


    @Autowired
    private IBusinessUnitService businessUnitService;


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // API
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // find - all
    // Unit testing  : NA
    // Integration testing : NA
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public List<UserBusinessUnitDTO> findAll(final HttpServletRequest request) {

        List<UserBusinessUnitDTO> returnList = new ArrayList<UserBusinessUnitDTO>();
        List<User>  userList = new ArrayList<User>();
        // nothing to extract from the request
        userList = userService.findAll();
        // build return list by looping through all users
        for(Iterator<User> iterUser = userList.iterator(); iterUser.hasNext(); ) {
            User current = iterUser.next();
            Set<BusinessUnit> businessUnitsList = current.getBusinessUnits();
            for(Iterator<BusinessUnit> iterBU = businessUnitsList.iterator(); iterBU.hasNext(); ) {
                BusinessUnit businessUnit = iterBU.next();
                UserBusinessUnitDTO userBusinessUnitDTO = new UserBusinessUnitDTO();
                userBusinessUnitDTO.setBusiness_unit_id(businessUnit.getId());
                userBusinessUnitDTO.setUser_id(current.getId());
                returnList.add(userBusinessUnitDTO);

            }

        }
        return returnList;
    }


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // API
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // find - one
    // Unit testing  : NA
    // Integration testing : NA
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public List<UserBusinessUnitDTO> findOne(@PathVariable("id") final Long id) {
        // return the business unit of a user
        // provided id here is the user id


        List<UserBusinessUnitDTO> returnList = new ArrayList<UserBusinessUnitDTO>();
        // nothing to extract from the request
        User user = userService.findOne(id);
        // build return list by looping through all users
        for(Iterator<BusinessUnit> iterBU =  user.getBusinessUnits().iterator(); iterBU.hasNext(); ) {
                BusinessUnit businessUnit = iterBU.next();
                UserBusinessUnitDTO userBusinessUnitDTO = new UserBusinessUnitDTO();
                userBusinessUnitDTO.setBusiness_unit_id(businessUnit.getId());
                userBusinessUnitDTO.setUser_id(user.getId());
                returnList.add(userBusinessUnitDTO);
        }

        return returnList;

    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // API
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // create - one
    // Unit testing  : NA
    // Integration testing : NA
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody final UserBusinessUnitDTO resource) {

       // createInternal(resource);

       // add relationship


    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // API
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // update - one
    // Unit testing  : NA
    // Integration testing : NA
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public void update(@PathVariable("id") final Long id, @RequestBody final User resource) {
        //updateInternal(id, resource);
    }


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // API
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Delete - one
    // Unit testing  : NA
    // Integration testing : NA
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") final Long id) {
       // deleteByIdInternal(id);
    }


    @Override
    protected ILongRawService<User> getService() {

        return userService;
    }


}