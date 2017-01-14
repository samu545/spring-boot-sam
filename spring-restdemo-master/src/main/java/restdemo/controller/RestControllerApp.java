package restdemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import restdemo.objects.RestObject;
import restdemo.service.IDataService;

/**
 * 
 * @author Samraj T
 *
 */

@RestController
@RequestMapping("/api")
public class RestControllerApp {	 

	private IDataService service;

	@Autowired
	public RestControllerApp(IDataService service){
		this.service = service;
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public List<RestObject> getAll() {
		return service.getAllObjects();
	}

	@RequestMapping(method=RequestMethod.POST, 
		    consumes = "text/plain")
	@ResponseBody
	public RestObject create(@RequestBody String str) {
		RestObject ob = new RestObject();
		ob.setContents(str);
		return service.create(ob);
	}

	@RequestMapping(method=RequestMethod.DELETE, value="{id}")
	public void delete(@PathVariable String id) {
		service.delete(id);
	}
	
	@RequestMapping(method=RequestMethod.PUT, consumes = "text/plain", value="{id}")
	public RestObject update(@PathVariable String id, @RequestBody String obj) {
		return service.update(id, obj);
	}
	
	@ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void handleNotFound(Exception ex) {
    }


}
