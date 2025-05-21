package sa.gov.alriyadh.amana.controller;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
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
@SecurityRequirement(name = "bearerAuth")
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

	@Operation(summary = "Get All services roles (موظف امانة , مدير مباشر , وكيل الجهة ...etc.) - you will use role id to get role actions.", tags = {"Roles Apis",}, responses = {
			@ApiResponse(responseCode = "200", description = "Get All services roles (موظف امانة , مدير مباشر , وكيل الجهة ...etc.) - you will use role id to get role actions.", content = @Content(mediaType = "application/json"))})
	@GetMapping("/roles")
	public GenericApiResponse<?> getCssUserRoles(){
		List<CssRole> roles = rolesService.getUserRoles();
		return GenericApiResponse.returnJsonTemp("0",null,roles);
	}

	@Operation(summary = "Get All available actions to selected role (user will choose or execute one action of them)", tags = {"Roles Apis",}, responses = {
			@ApiResponse(responseCode = "200", description = "Get All available actions to selected role (user will choose or execute one action of them)", content = @Content(mediaType = "application/json"))})
	@GetMapping("/roleActions/{userRole}")
	public GenericApiResponse<?> getCssRoleActions(@PathVariable(required = true) int userRole){
		List<RoleActionView> roleActions = phaseActionService.getActions(userRole);
		return GenericApiResponse.returnJsonTemp("0",null,roleActions);
	}

	@Operation(summary = "Get event types (exhibition, conference, forum...etc.)", tags = {"Lookup Apis",}, responses = {
			@ApiResponse(responseCode = "200", description = "Get event types (exhibition, conference, forum...etc.)", content = @Content(mediaType = "application/json"))})
	@GetMapping("/evenTypes")
	public GenericApiResponse<?> getCssEventTypes(){
		List<CssEventType> eventTypes = eventTypeService.getEventTypes();
		return GenericApiResponse.returnJsonTemp("0",null,eventTypes);
	}

	@Operation(summary = "Get participation types (speaker, visitor...etc.)", tags = {"Lookup Apis",}, responses = {
			@ApiResponse(responseCode = "200", description = "Get participation types (speaker, visitor...etc.)", content = @Content(mediaType = "application/json"))})
	@GetMapping("/participationTypes")
	public GenericApiResponse<?> getCssParticipationTypes(){
		List<CssParticipationType> participationTypes = participationTypeService.getAllParticipationTypes();
		return GenericApiResponse.returnJsonTemp("0",null,participationTypes);
	}


	@Operation(summary = "Get directorate types (وكالة, إداة عامة, إدارة فرعية)", tags = {"Lookup Apis",}, responses = {
			@ApiResponse(responseCode = "200", description = "Get directorate types (وكالة, إداة عامة, إدارة فرعية)", content = @Content(mediaType = "application/json"))})
	@GetMapping("/dirTypes")
	public GenericApiResponse<?> getCssDirTypes(){
		Map<Integer, String> dirTypes = dirTypeData.getTypes();
		return GenericApiResponse.returnJsonTemp("0",null,dirTypes);
	}

	@Operation(summary = "Get directorates", tags = {"Lookup Apis",}, responses = {
			@ApiResponse(responseCode = "200", description = "Get directorates by dir type.", content = @Content(mediaType = "application/json"))})
	@GetMapping("/directorates/{dirType}")
	public GenericApiResponse<?> getDirectorates(@PathVariable(required = true) Integer dirType) {
		List<Object> directorates = requestService.getDirectorates(dirType);
		return GenericApiResponse.returnJsonTemp("0",null,directorates);
	}

	@Operation(summary = "Get countries", tags = {"Lookup Apis",}, responses = {
			@ApiResponse(responseCode = "200", description = "Get all countries.", content = @Content(mediaType = "application/json"))})
	@GetMapping("/countries")
	public GenericApiResponse<?> getCountries() {
		List<Object> countries = requestService.getCountries();
		return GenericApiResponse.returnJsonTemp("0",null,countries);
	}

	@Operation(summary = "Get all country cities by country code", tags = {"Lookup Apis",}, responses = {
			@ApiResponse(responseCode = "200", description = "Get all country cities by country code.", content = @Content(mediaType = "application/json"))})
	@GetMapping("/cities/{countryCode}")
	public GenericApiResponse<?> getCities(@PathVariable(required = true) Integer countryCode) {
		List<Object> cities = requestService.getCities(countryCode);
		return GenericApiResponse.returnJsonTemp("0",null,cities);
	}

	@Operation(summary = "Get status list", tags = {"Lookup Apis",}, responses = {
			@ApiResponse(responseCode = "200", description = "Get status codes list.", content = @Content(mediaType = "application/json"))})
	@GetMapping("/statusList")
	public GenericApiResponse<?> getReqStatusList() {
		List<Object> statusList = requestService.getReqStatusList();
		return GenericApiResponse.returnJsonTemp("0",null,statusList);
	}

	@Operation(summary = "Get request attachments by request number", tags = {"Request Apis",}, responses = {
			@ApiResponse(responseCode = "200", description = "Get request attachments by request number.", content = @Content(mediaType = "application/json"))})
	@GetMapping("/reqAttachments/{requestNo}")
	public GenericApiResponse<?> getReqAttachments(@PathVariable(required = true) Long requestNo) {
		List<CssRequestAttachmentDto>  attachments = requestAttachmentService.getRequestAttachments(requestNo);
		return GenericApiResponse.returnJsonTemp("0",null,attachments);
	}

	@Operation(summary = "Get request phases (contain transactions happened on the request) by request number", tags = {"Request Apis",}, responses = {
			@ApiResponse(responseCode = "200", description = "Get request phases (contain transactions happened on the request) by request number.", content = @Content(mediaType = "application/json"))})
	@GetMapping("/requestPhases/{requestNo}")
	public GenericApiResponse<?> getRequestPhases(@PathVariable(required = true) Long requestNo) {
		List<CssRequestPhaseDto>  reqPhases = requestService.getRequestPhases(requestNo);
		return GenericApiResponse.returnJsonTemp("0",null,reqPhases);
	}

	@Operation(summary = "Add new request", tags = {"Request Apis",}, responses = {
			@ApiResponse(responseCode = "200", description = "Add new request.", content = @Content(mediaType = "application/json"))})
	@PostMapping("/addNewRequest")
	public GenericApiResponse<?> addNewRequest(@Valid @RequestBody CssRequestDto cssRequestDto){
		Map<String, Object> output = requestService.addNewRequest(cssRequestDto);
		return GenericApiResponse.returnJsonTemp("0",null,output);
	}

	@Operation(summary = "Add request action (approve, refuse).", tags = {"Request Apis",}, responses = {
			@ApiResponse(responseCode = "200", description = "Add request action (approve, refuse).", content = @Content(mediaType = "application/json"))})
	@PostMapping("/addRequestAction")
	public GenericApiResponse<?> addRequestPhase(@Valid @RequestBody RequestPhase requestPhase){
		Map<String, Object> output = requestService.addRequestPhase(requestPhase);
		return GenericApiResponse.returnJsonTemp("0",null,output);
	}

	@Hidden
	@PostMapping("/addRequestAttach")
	public GenericApiResponse<?> addRequestAttach(@Valid @RequestBody CssRequestAttachmentDto cssRequestAttachmentDto){
		CssRequestAttachment reqttachment = requestAttachmentService.insertNewAttach(cssRequestAttachmentDto);
		return GenericApiResponse.returnJsonTemp("0",null,reqttachment);
	}

	@Operation(summary = "Search for requests with many filters(roleId, userCode, requestNo, statusCode, dirCode...etc.).", tags = {"Request Apis",}, responses = {
			@ApiResponse(responseCode = "200", description = "Search for requests with many filters(roleId, userCode, requestNo, statusCode, dirCode...etc.).", content = @Content(mediaType = "application/json"))})
	@PostMapping("/findRequests")
	public GenericApiResponse<?> getRequestByFilter(@Valid @RequestBody CssRequestFilter filter) {
		System.out.println(filter.getUserCode());
		List<CssRequestDto> requests = requestService.findRequestsByFilter(filter);
		return GenericApiResponse.returnJsonTemp("0",null,requests);
	}

}
