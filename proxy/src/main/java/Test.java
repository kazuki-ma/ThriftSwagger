import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wordnik.swagger.models.Operation;
import com.wordnik.swagger.models.Response;
import com.wordnik.swagger.models.auth.AuthorizationValue;
import com.wordnik.swagger.models.parameters.Parameter;

import example.ServiceExample;
import example.ServiceExample.Iface;

@RestController
@EnableAutoConfiguration
public class Test {
    public static void main(String[] args) throws Exception {
        SpringApplication.run(Test.class, args);
    }
    
    @RequestMapping("/api-docs/{className}")
    public ResponseEntity<?> getApiDocs(@PathVariable("className") String className) {
	List<Operation> list = new ArrayList<>();

	Class<Iface> class1 = ServiceExample.Iface.class;
	
	
	
	for (Method method : class1.getMethods()) {
	    String methodName = method.toString();
	    String summary = "";
	    List<String> produces = new ArrayList<>();
	    List<String> consumes = Collections.emptyList();
	    List<String> protocols = Collections.emptyList();
	    List<Parameter> parameters = Collections.emptyList();
	    Map<String, Response> responseType = Collections.emptyMap();

	    final Operation operation = new Operation();
	    operation.setDescription(methodName);
	    operation.setOperationId(methodName);
	    operation.setSummary(summary);
	    operation.setProduces(produces);
	    operation.setConsumes(consumes);
	    operation.setProduces(protocols);
	    operation.setResponses(responseType);
	    operation.setParameters(parameters);

	    list.add(operation);
	}
	
	return new ResponseEntity<List<Operation>>(list,HttpStatus.OK);
    }
}
