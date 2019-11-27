package gov.nara.um.spring.controller;

import gov.nara.common.persistence.service.ILongRawService;
import gov.nara.common.persistence.service.IRawService;
import gov.nara.common.web.controller.AbstractLongIdController;
import gov.nara.um.persistence.dto.BusinessUnitDTO;
import gov.nara.um.persistence.dto.UserDTO;
import gov.nara.um.persistence.model.BusinessUnit;
import gov.nara.um.persistence.model.User;
import gov.nara.um.service.IBusinessUnitService;
import gov.nara.um.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Iterator;

public class UserBaseController extends AbstractLongIdController<User> {


    @Autowired
    private IUserService userService;

    @Autowired
    private IBusinessUnitService businessUnitService;


    @Override
    protected ILongRawService<User> getService() {
        return userService;
    }

    protected IRawService<BusinessUnit> getBusinessUnitService() {
        return businessUnitService;
    }



    public UserDTO buildUserDTO(User user){
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setName(user.getName());
        userDTO.setUser_type(user.getUser_type());
        for(Iterator<BusinessUnit> iterBU = user.getBusinessUnits().iterator(); iterBU.hasNext();) {
            userDTO.addBusinssUnitDTO(buildBusinessUnitDTO(iterBU.next()));
        }

        return userDTO;
    }

    public BusinessUnitDTO buildBusinessUnitDTO(BusinessUnit currentBU){
        BusinessUnitDTO businessUnitDTO = new BusinessUnitDTO();
        businessUnitDTO.setId(currentBU.getId());
        businessUnitDTO.setName(currentBU.getName());
        businessUnitDTO.setOrg_code(currentBU.getOrg_code());
        businessUnitDTO.setLdapName(currentBU.getLdapName());

        return businessUnitDTO;
    }
}