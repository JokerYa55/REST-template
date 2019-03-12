/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test_micro;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import javax.ws.rs.FormParam;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import rest_exception.exDefaultException;
import rest_exception.exNotAuthorizedException;
import rest_exception.exNotDataFoundException;

/**
 *
 * @author vasil
 */
@Path("/rest_test")
@Produces(MediaType.APPLICATION_JSON)
@Api(value = "/rest_test", description = "REST TEST")
public class restResource {

    private final Logger log = Logger.getLogger(getClass().getName());
    private final String REGEXP_ATTR_PATTERN = "^id_app_[a-zA-Z0-9_]{1,}$";
    private final String HEADER_CACHE_CONTROL_NAME = "Cache-Control";
    private final String HEADER_CACHE_CONTROL_VAL = "no-store";
    private final String HEADER_PRAGMA_NAME = "Pragma";
    private final String HEADER_PRAGMA_VAL = "no-cache";
    private final String PERSISTENCE_NAME = "admin_rest_b2c_JPA";
    private final String ERROR_PROPERTY_NOT_FOUND = "property not found";
    private final String ERROR_DATA_NOT_FOUND = "data not found";
    private final String ERROR_INSUFFICIENTLY_PRIVILEGED = "insufficiently privileged";
    private final String ERROR_INVALID_TOKEN = "Barer";
    private final String ERROR_INTERNAL_SERVER_ERROR = "internal server error";
    private final String ERROR_TOKEN_PARSE = "token parse error";
    private static final String CLIENT_PATTERN = "^lk_(.){1,}$";
    private static String adm_token_global = null;

    @Context
    private HttpHeaders requestHeaders;
    @Context
    private Response response;
    @Context
    private Request request;
    private static ConcurrentHashMap<String, Object> cash = new ConcurrentHashMap();

    /**
     * Конструктор
     */
    public restResource() {
        log.info("\n************* constructor ****************\n");
    }

   
    /**
     * Получаение информации о пользователе
     *
     * @param p_realm
     * @return
     * @throws ParseException
     */
    @Path("/test")
    @GET
    @ApiOperation(value = "test", notes = "test")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK")        
        ,
        @ApiResponse(code = 500, message = "Something wrong in Server")})
    public Response test() throws ParseException {
        log.info(String.format("\n********************* %s  %s *********************", new Date(), "test"));
        return Response.status(Status.OK).build();
    }


    /**
     *
     * @param p_status
     * @param p_message
     * @return
     */
    private Response genResponse(Status p_status, Object p_message) {
        return Response.status(p_status).
                header(HEADER_CACHE_CONTROL_NAME, HEADER_CACHE_CONTROL_VAL).
                header(HEADER_PRAGMA_NAME, HEADER_PRAGMA_VAL).
                entity(p_message).
                build();
    }

    /**
     *
     * @param p_status
     * @param p_message
     * @return
     */
    private Response genResponse(int p_status, Object p_message) {
        return Response.status(p_status).
                header(HEADER_CACHE_CONTROL_NAME, HEADER_CACHE_CONTROL_VAL).
                header(HEADER_PRAGMA_NAME, HEADER_PRAGMA_VAL).
                entity(p_message).
                build();
    }

}
