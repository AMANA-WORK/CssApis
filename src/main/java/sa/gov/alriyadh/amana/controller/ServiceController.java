package sa.gov.alriyadh.amana.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import sa.gov.alriyadh.amana.entity.dto.CssRequestDto;
import sa.gov.alriyadh.amana.dto.response.GenericApiResponse;
import sa.gov.alriyadh.amana.entity.*;
import sa.gov.alriyadh.amana.entity.dto.CssRequestAttachmentDto;
import sa.gov.alriyadh.amana.entity.dto.CssRequestPhaseDto;
import sa.gov.alriyadh.amana.pojo.CssRequestFilter;
import sa.gov.alriyadh.amana.pojo.DirTypeData;
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

	@Autowired
	DirTypeData dirTypeData;

	@Autowired
	RequestAttachmentService requestAttachmentService;

	@Operation(summary = "Get All services roles", tags = {"Roles",}, responses = {
			@ApiResponse(responseCode = "200", description = "Get All services roles", content = @Content(mediaType = "application/json"))})
	@GetMapping("/roles")
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

	@GetMapping("/roleActions/{userRole}")
	public GenericApiResponse<?> getCssRoleActions(@PathVariable(required = true) int userRole){
		List<RoleActionView[]> roleActions = phaseActionService.getActions(userRole);
		return GenericApiResponse.returnJsonTemp("0",null,roleActions);
	}
	@GetMapping("/dirTypes")
	public GenericApiResponse<?> getCssDirTypes(){
		Map<Integer, String> dirTypes = dirTypeData.getTypes();
		return GenericApiResponse.returnJsonTemp("0",null,dirTypes);
	}

	@GetMapping("/directorates/{dirType}")
	public GenericApiResponse<?> getDirectorates(@PathVariable(required = true) Integer dirType) {
		List<Object[]> directorates = requestService.getDirectorates(dirType);
		return GenericApiResponse.returnJsonTemp("0",null,directorates);
	}

	@GetMapping("/countries")
	public GenericApiResponse<?> getCountries() {
		List<Object[]> countries = requestService.getCountries();
		return GenericApiResponse.returnJsonTemp("0",null,countries);
	}

	@GetMapping("/cities/{countryCode}")
	public GenericApiResponse<?> getCities(@PathVariable(required = true) Integer countryCode) {
		List<Object[]> cities = requestService.getCities(countryCode);
		return GenericApiResponse.returnJsonTemp("0",null,cities);
	}

	@GetMapping("/statusList")
	public GenericApiResponse<?> getReqStatusList() {
		List<Object[]> statusList = requestService.getReqStatusList();
		return GenericApiResponse.returnJsonTemp("0",null,statusList);
	}

	@GetMapping("/reqAttachments/{requestNo}")
	public GenericApiResponse<?> getReqAttachments(@PathVariable(required = true) Long requestNo) {
		List<CssRequestAttachmentDto>  attachments = requestAttachmentService.getRequestAttachments(requestNo);
		return GenericApiResponse.returnJsonTemp("0",null,attachments);
	}

	@GetMapping("/requestPhases/{requestNo}")
	public GenericApiResponse<?> getRequestPhases(@PathVariable(required = true) Long requestNo) {
		List<CssRequestPhaseDto>  reqPhases = requestService.getRequestPhases(requestNo);
		return GenericApiResponse.returnJsonTemp("0",null,reqPhases);
	}

	@PostMapping("/addNewRequest")
	public GenericApiResponse<?> addNewRequest(@Valid @RequestBody CssRequestDto cssRequestDto){
		Map<String, Object> output = requestService.addNewRequest(cssRequestDto);
		return GenericApiResponse.returnJsonTemp("0",null,output);
	}

	@PostMapping("/addRequestAction")
	public GenericApiResponse<?> addRequestPhase(@Valid @RequestBody RequestPhase requestPhase){
		Map<String, Object> output = requestService.addRequestPhase(requestPhase);
		return GenericApiResponse.returnJsonTemp("0",null,output);
	}

	@PostMapping("/addRequestAttach")
	public GenericApiResponse<?> addRequestAttach(@Valid @RequestBody CssRequestAttachmentDto cssRequestAttachmentDto){
		CssRequestAttachment reqttachment = requestAttachmentService.insertNewAttach(cssRequestAttachmentDto);
		return GenericApiResponse.returnJsonTemp("0",null,reqttachment);
	}

	@PostMapping("/findRequests")
	public GenericApiResponse<?> getRequestByFilter(@Valid @RequestBody CssRequestFilter filter) {
		System.out.println(filter.getUserCode());
		List<CssRequestDto> requests = requestService.findRequestsByFilter(filter);
		return GenericApiResponse.returnJsonTemp("0",null,requests);
	}

}
