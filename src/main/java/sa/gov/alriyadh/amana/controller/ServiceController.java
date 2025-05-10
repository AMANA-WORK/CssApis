package sa.gov.alriyadh.amana.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import sa.gov.alriyadh.amana.dto.response.GenericApiResponse;
import sa.gov.alriyadh.amana.entity.CssEventType;
import sa.gov.alriyadh.amana.entity.CssParticipationType;
import sa.gov.alriyadh.amana.entity.CssRole;
import sa.gov.alriyadh.amana.pojo.RequestPhase;
import sa.gov.alriyadh.amana.pojo.RoleActionView;
import sa.gov.alriyadh.amana.services.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/css/api/services/")
@Validated
public class ServiceController {

	@Autowired
	RolesService rolesService;

	@Autowired
	EventTypeService eventTypeService;

	@Autowired
	ParticipationTypeService participationTypeService;

	@Autowired
	PhaseActionService phaseActionService;

	@Autowired
	RequestService requestService;


	@GetMapping("/userRoles")
	public GenericApiResponse<?> getCssUserRoles(){
		List<CssRole> roles = rolesService.getUserRoles();
		return GenericApiResponse.returnJsonTemp("0",null,roles);
	}

	@GetMapping("/evenTypes")
	public GenericApiResponse<?> getCssEventTypes(){
		List<CssEventType> eventTypes = eventTypeService.getEventTypes();
		return GenericApiResponse.returnJsonTemp("0",null,eventTypes);
	}

	@GetMapping("/participationTypes")
	public GenericApiResponse<?> getCssParticipationTypes(){
		List<CssParticipationType> participationTypes = participationTypeService.getAllParticipationTypes();
		return GenericApiResponse.returnJsonTemp("0",null,participationTypes);
	}

	@GetMapping("/userRoleActions/{userRole}")
	public GenericApiResponse<?> getCssRoleActions(@PathVariable(required = true) int userRole){
		List<RoleActionView[]> roleActions = phaseActionService.getActions(userRole);
		return GenericApiResponse.returnJsonTemp("0",null,roleActions);
	}

	@PostMapping("/addRequestPhase")
	public GenericApiResponse<?> addRequestPhase(@Valid @RequestBody RequestPhase requestPhase){
		Map<String, Object> output = requestService.addRequestPhase(requestPhase);
		return GenericApiResponse.returnJsonTemp("0",null,output);
	}

}
