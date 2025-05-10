package sa.gov.alriyadh.amana.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sa.gov.alriyadh.amana.entity.CssRole;
import sa.gov.alriyadh.amana.repository.CssRoleRepository;
import sa.gov.alriyadh.amana.srinterface.IRolesService;

import java.util.List;

@Service
public class RolesService implements IRolesService {

   @Autowired
   CssRoleRepository cssRoleRepository;

    @Override
    public List<CssRole> getUserRoles() {
        return cssRoleRepository.findAll();
    }
}
